package com.foan.crm.entity.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;

@Data
public class CreateCompanyRequest {

    /**
     * user name
     */
    @ApiModelProperty(value = "name", example = "company123")
    private String name;
    /**
     *  address
     */
    @ApiModelProperty(value = "address", example = "address123")
    private String address;

    /**
     * token
     */
    @ApiModelProperty(value = "token", example = "1231233r2rfsf")
    private String token;
}
