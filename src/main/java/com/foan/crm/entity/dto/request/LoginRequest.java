package com.foan.crm.entity.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginRequest {
    /**
     * user name
     */
    @ApiModelProperty(value = "name", example = "test123")
    private String name;

    /**
     * password
     */
    @ApiModelProperty(value = "password", example = "password")
    private String password;
}
