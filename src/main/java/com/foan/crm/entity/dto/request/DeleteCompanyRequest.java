package com.foan.crm.entity.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeleteCompanyRequest {
    /**
     * user name
     */
    @ApiModelProperty(value = "name", example = "company123")
    private String name;

    /**
     * token
     */
    @ApiModelProperty(value = "token", example = "1231233r2rfsf")
    private String token;

}
