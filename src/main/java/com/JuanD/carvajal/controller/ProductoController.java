package com.JuanD.carvajal.controller;

import com.JuanD.carvajal.dto.HttpGlobalResponse;
import com.JuanD.carvajal.dto.ProductoResponseDTO;
import com.JuanD.carvajal.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<HttpGlobalResponse<List<ProductoResponseDTO>>> getAll() {
        List<ProductoResponseDTO> products = productoService.getAll();
        return ResponseEntity.ok(new HttpGlobalResponse<>(200, "Catálogo de productos", products));
    }
}