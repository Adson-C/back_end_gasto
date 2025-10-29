package com.ads.gasto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    
    private int codigo;
    private String message;
    // private String path;
    // private String timestamp;
    // private String trace;
    // private String status;
    // private String error;
    // private String exception;
}
