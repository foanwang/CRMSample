package com.foan.crm.entity.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateClientRequest {
    /**
     * user name
     */
    @ApiModelProperty(value = "companyid", example = "1")
    private Long companyid;

    /**
     * user name
     */
    @ApiModelProperty(value = "name", example = "client123")
    private String name;

    /**
     * email
     */
    @ApiModelProperty(value = "email", example = "client@test.com")
    private String email;

    /**
     * phone
     */
    @ApiModelProperty(value = "phone", example = "1234566")
    private String phone;

    /**
     * token
     */
    @ApiModelProperty(value = "token", example = "1231233r2rfsf")
    private String token;
}
