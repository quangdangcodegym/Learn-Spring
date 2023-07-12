package com.codegym.service.impl;

import com.codegym.model.dto.CartDTO;
import com.codegym.model.dto.CartItemDTO;
import com.codegym.model.dto.api.CartApiResDTO;
import com.codegym.exception.ExceptionFactory;
import com.codegym.exception.NumberInputException;
import com.codegym.mapper.MapperUtils;
import com.codegym.model.Cart;
import com.codegym.model.CartItem;
import com.codegym.model.Product;
import com.codegym.repository.CartItemRepository;
import com.codegym.repository.CartRepository;
import com.codegym.repository.ProductRepository;
import com.codegym.service.ICartService;
import com.codegym.utils.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private MapperUtils mapperUtils;

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).get();
    }


    @Override
    public CartDTO findCartDTOById(Long id) {
        Cart cart = cartRepository.findById(id).get();
        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotal(cart.getTotal())
                .setId(cart.getId())
                .setUserId(cart.getUserID())
                .setExpiratedDate(cartDTO.getExpiratedDate())
                .setCreateAt(cart.getCreateAt());

//        List<CartItemsDTO> cartItemsDTOS = cart.getCartItems().stream().map(cartItem -> {
//            CartItemsDTO cartItemsDTO =  new CartItemsDTO();
//            cartItemsDTO.setCartDTO(cartItem.getCart().toCartDTO());
//            cartItemsDTO.setProductDTO(cartItem.getProduct().toProductDTO());
//            cartItemsDTO.setPrice(cartItem.getPrice());
//            cartItemsDTO.setCartId(cartItem.getCart().getId());
//            cartItemsDTO.setProductId(cartItem.getProduct().getId());
//            cartItemsDTO.setId(cartItem.getId());
//            return cartItemsDTO;
//        }).collect(Collectors.toList());

        List<CartItemDTO> cartItemsDTOS = cart.getCartItems().stream().map(CartItem::toCartItemsDTO).collect(Collectors.toList());

        return cartDTO;
    }

    @Override
    public CartDTO findCartDTOByIdUseModelMapper(Long id) {
        Cart cart = cartRepository.findById(id).get();

        CartDTO cartDTO = mapperUtils.toCartDTO(cart);

        return cartDTO;
    }

    @Override
    public CartApiResDTO findCartApiResDTO(Long id) {
        Cart cart = cartRepository.findById(id).get();
        CartApiResDTO cartApiResDTO = mapperUtils.toCartApiResDTO(cart);

        return cartApiResDTO;
    }

    @Override
    public void update(Long id, Cart cart) {
        cart.setId(id);
        cartRepository.save(cart);
    }

    @Override
    public void remove(Long id) {
        Cart cart = cartRepository.findById(id).get();
        cartRepository.delete(cart);
    }

    @Override
    public Cart findByTokenIdContaining(String tokenId) {
        return cartRepository.findByTokenIdContaining(tokenId);
    }

    @Override
    public List<CartItem> getCartItems(Long cardId) {
        Cart c = cartRepository.findById(cardId).get();
        return cartItemRepository.findCartItemsByCartEquals(c);
    }
    public Cart getCartInfoFromRequestAndDB(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();

        boolean check = CookieUtils.checkCookiesByName("token-id", cookies);
        LocalDateTime now = LocalDateTime.now();
        if (check == false) {
            now.plusMinutes(5);         // cộng thêm 1 phút
            Cookie cookie = new Cookie("token-id", RequestContextHolder.currentRequestAttributes().getSessionId());
            // RequestContextHolder.currentRequestAttributes().getSessionId(): ở bước này server tạo sessionid và gửi về client
            cookie.setMaxAge(60*30); // expires in 7 days
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        String tokenId = CookieUtils.getCookieValueByName("token-id", cookies);
        Cart cart = cartRepository.findByTokenIdContaining(tokenId);
        if (cart == null) {
            cart = new Cart();
            cart.setExpiratedDate(LocalDateTime.now().minusDays(1));
            cart.setTokenId(RequestContextHolder.currentRequestAttributes().getSessionId());
            cart = cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public Cart addProductToCart(Long idProduct, int quantity, String type, HttpServletRequest request, HttpServletResponse response) throws NumberInputException {

        Cart cart = getCartInfoFromRequestAndDB(request, response);

        Product product = productRepository.findById(idProduct).get();
        BigDecimal price = product.getPrice();

        // Nếu sản phẩm có trong giỏ hàng thì tăng số lượng
        if (checkProductExists(cart, product)) {
            quantity = getCartItemByProduct(cart,product).getQuantity() + 1;
            if(quantity > product.getQuantity()){
                throw ExceptionFactory.getNumberInputException(type, "Số lượng vượt quá số lượng trong kho", cart.getId());
            }
        }
        updateCart(cart, product, price, quantity);
        return cart;
    }

    @Override
    public Cart updateProductInCart(Long id, int quantity, String type, HttpServletRequest request, HttpServletResponse response) throws NumberInputException {
        Cart cart = getCartInfoFromRequestAndDB(request, response);

        Product product = productRepository.findById(id).get();
        BigDecimal price = product.getPrice();

        // Nếu sản phẩm có trong giỏ hàng thì tăng số lượng
        if (checkProductExists(cart, product)) {
            if(quantity > product.getQuantity()){
                throw ExceptionFactory.getNumberInputException(type, "Số lượng vượt quá số lượng trong kho", cart.getId());
            }
        }
        updateCart(cart, product, price, quantity);
        return cart;
    }

    public void updateCart(Cart cart, Product product, BigDecimal price, int quantity){
        List<CartItem> cartItems = cartItemRepository.findCartItemsByCartEquals(cart);

        if (cartItems == null) {
            cartItems = new ArrayList<>();
            CartItem cartItem = new CartItem(null, product, cart, quantity, price);
            cartItems.add(cartItem);
        }else{
            if (!checkProductExists(cart, product)) {
                cartItems.add(new CartItem(null, product, cart, quantity, price));
            }else{
                updateCart(cartItems, product, price, quantity);
            }
        }
        cart.setCartItems(cartItems);
        cartItemRepository.saveAll(cartItems);
        cartRepository.save(cart);
    }

    public boolean checkProductExists(Cart cart, Product product) {
        if (cart.getCartItems() != null) {
            for (CartItem cartItem : cart.getCartItems()) {
                if (product.getId() == cartItem.getProduct().getId()) {
                    return true;
                }
            }
        }
        return false;
    }
    public CartItem getCartItemByProduct(Cart cart, Product product) {
        if (cart.getCartItems() != null) {
            for (CartItem cartItem : cart.getCartItems()) {
                if (product.getId() == cartItem.getProduct().getId()) {
                    return cartItem;
                }
            }
        }
        return null;
    }
    public void updateCart(List<CartItem> cartItems, Product product, BigDecimal price, int quantity) {
            for (CartItem cartItem : cartItems) {
                if (product.getId() == cartItem.getProduct().getId()) {
                    cartItem.setPrice(price);
                    cartItem.setQuantity(quantity);
                }
            }
    }
}
