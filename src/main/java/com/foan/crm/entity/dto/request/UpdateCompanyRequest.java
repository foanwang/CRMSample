package com.foan.crm.entity.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateCompanyRequest {
    /**
     * user name
     */
    @ApiModelProperty(value = "name", example = "company123")
    private String name;
    /**
     *  address
     */
    @ApiModelProperty(value = "address", example = "address3121")
    private String address;

    /**
     * token
     */
    @ApiModelProperty(value = "token", example = "1231233r2rfsf")
    private String token;

}
