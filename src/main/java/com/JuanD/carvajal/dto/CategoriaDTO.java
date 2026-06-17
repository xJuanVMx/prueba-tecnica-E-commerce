package com.JuanD.carvajal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaDTO {
    private Integer id;        
    private String nombre;     
    private String descripcion; 
}
