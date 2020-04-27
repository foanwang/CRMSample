package com.foan.crm.entity.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginResponse {

    /**
     * user name
     */
    @ApiModelProperty(value = "name", example = "test123")
    private String name;

    /**
     * token
     */
    @ApiModelProperty(value = "name", example = "121xXDe=ae=1=e1")
    private String token;
}
