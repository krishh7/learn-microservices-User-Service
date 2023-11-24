package com.lcwd.user.service.util;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private String message;
    private Boolean success;
    private HttpStatus status;
}
