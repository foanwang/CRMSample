package com.foan.crm.entity.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetCompanyRequest {
    /**
     * user name
     */
    @ApiModelProperty(value = "name", example = "company123")
    private String name;

}
