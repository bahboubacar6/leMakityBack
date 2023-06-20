package com.group.makity.leMakity.dtos;

import com.group.makity.leMakity.security.SecurityConstants;
import lombok.Data;

@Data
public class AuthResponseDTO {

    private String accessToken;
    private String tokenType = "Bearer ";
    private long expiresIn = SecurityConstants.JWT_EXPIRATION;
    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
