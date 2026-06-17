package com.JuanD.carvajal.service;

import com.JuanD.carvajal.dto.CategoriaDTO;
import com.JuanD.carvajal.dto.ProductoResponseDTO;
import com.JuanD.carvajal.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<ProductoResponseDTO> getAll() {
        return productoRepository.findAll().stream().map(p -> new ProductoResponseDTO(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getCantidadStock(),
                new CategoriaDTO(
                        p.getCategoria().getId(),
                        p.getCategoria().getNombre(),
                        p.getCategoria().getDescripcion())))
                .collect(Collectors.toList());
    }
}