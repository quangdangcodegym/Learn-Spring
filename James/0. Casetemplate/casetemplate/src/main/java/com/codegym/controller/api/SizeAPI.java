package com.codegym.controller.api;

import com.codegym.model.EColor;
import com.codegym.model.ESize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/sizes")
public class SizeAPI {
    @GetMapping("")
    public ResponseEntity<?> listSizes() {
        List<ESize> eSizes = List.of(ESize.values());
        return new ResponseEntity<>(eSizes, HttpStatus.OK);
    }
}