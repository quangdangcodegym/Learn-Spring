package com.codegym.controller.api;

import com.codegym.model.EColor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorAPI {
    @GetMapping("")
    public ResponseEntity<?> listColors() {
        List<EColor> eColors = List.of(EColor.values());
        return new ResponseEntity<>(eColors, HttpStatus.OK);
    }
}
