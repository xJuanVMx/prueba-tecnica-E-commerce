package com.JuanD.carvajal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HistoricoResponseDTO {
    private Integer id;
    private Integer productoId;
    private String nombreProducto;
    private String accion;
    private LocalDateTime fecha;
}