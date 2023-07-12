package com.codegym.exception;

import com.codegym.config.ApiError;
import com.codegym.model.Cart;
import com.codegym.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/** class GlobalExceptionHandler extends ResponseEntityExceptionHandler
  + nếu ko override cái class này thì response khi lỗi rất gớm
  + Nếu extends ResponseEntityExceptionHandler mà ko cần CODE gì ở bên trong nó cũng format ra gọn gàng
 * **/
@ControllerAdvice
public class GlobalWebExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ICartService iCartService;

    /**
     * Hàm này sẽ chạy khi @Valid và @Validated phát hiện có lỗi
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }

    /**
     * Hàm này sẽ chạy khi url PARSE id FAIL
     * Ví dụ: @PostMapping("/create/{id}") @PathVariable Long id ==> Nhập .../create/aaa ==> fail
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }


    /**
     * CHƯA ĐƯỢC Đang viết hàm test handleTypeMismatch: khi trong parse thuộc tính bị sai String ("aa") -> Bigdecimal
     * **/
    /**
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatusCode status, WebRequest request) {
        List<String> errors = List.of("AAA", "BBB");

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }
    **/


    @ExceptionHandler({NumberInputExceptionWeb.class})
    public ModelAndView showInputNotAcceptable(NumberInputExceptionWeb numberInputException) {
        Cart cart = iCartService.findById(numberInputException.getCardId());

        ModelAndView modelAndView = new ModelAndView("frontend/cart/list");
        modelAndView.addObject("message", "Số lượng sản phẩm không hợp lệ");
        modelAndView.addObject("cart", cart);
        return modelAndView ;
    }
}
