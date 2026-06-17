package com.JuanD.carvajal.dto;

import lombok.Data;

@Data
public class ListaDeseosRequestDTO {

    private Integer usuarioId;
    private Integer productoId;
    private Integer cantidad;
}