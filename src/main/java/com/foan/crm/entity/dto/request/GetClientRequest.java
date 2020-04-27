package com.foan.crm.entity.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetClientRequest {

    /**
     * companyId
     */
    @ApiModelProperty(value = "companyid", example = "123")
    private Long companyid;
    /**
     * user name
     */
    @ApiModelProperty(value = "name", example = "client123")
    private String name;
}
