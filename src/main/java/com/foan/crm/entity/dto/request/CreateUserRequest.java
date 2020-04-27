package com.foan.crm.entity.dto.request;

import com.foan.crm.constants.RoleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateUserRequest {
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

    /**
     * role
     */
    @ApiModelProperty(value = "role")
    private RoleType role;


    /**
     * createdby
     */
    @ApiModelProperty(value = "createdby", example = "admin")
    private String createdby;

}
