package com.JuanD.carvajal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoResponseDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer cantidadStock;
    private CategoriaDTO categoria;
}
