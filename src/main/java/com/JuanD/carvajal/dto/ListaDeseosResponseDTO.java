package com.JuanD.carvajal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListaDeseosResponseDTO {

    private Integer id;
    private Integer productoId;
    private String nombreProducto;
    private Double precio;
    private Integer cantidad;
    private Integer stockDisponible;
    private Boolean outOfStock; 
}