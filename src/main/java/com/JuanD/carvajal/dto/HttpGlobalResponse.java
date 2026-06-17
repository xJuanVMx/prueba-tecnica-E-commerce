package com.JuanD.carvajal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpGlobalResponse<T> {

    private int status;
    private String message;
    private T data;
}