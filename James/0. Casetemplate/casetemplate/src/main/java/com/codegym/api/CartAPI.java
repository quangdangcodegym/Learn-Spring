package com.codegym.api;

import com.codegym.dto.CartDTO;
import com.codegym.service.ICartService;
import com.codegym.testmodelmapper.Game;
import com.codegym.testmodelmapper.GameDTO;
import com.codegym.testmodelmapper.Player;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {
    @Autowired
    private ICartService iCartService ;

    @GetMapping("{id}")
    public ResponseEntity<?> listCartDTOs(@PathVariable Long id){
//        CartDTO cartDTO = iCartService.findCartDTOById(id);
        CartDTO cartDTO = iCartService.findCartDTOByIdUseModelMapper(id);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }
    @GetMapping("/mapper")
    public ResponseEntity<?> testmapper(){
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Game, GameDTO> propertyMapper = modelMapper.createTypeMap(Game.class, GameDTO.class);
        // add deep mapping to flatten source's Player object into a single field in destination
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> {
                    return src.getCreator().getName();
                }, GameDTO::setCreator)
        );
        Game game = new Game(1L, "Game 1", null);
        game.setCreator(new Player(1L, "John"));
        GameDTO gameDTO = modelMapper.map(game, GameDTO.class);

        return new ResponseEntity<>(gameDTO, HttpStatus.OK);
    }
}
