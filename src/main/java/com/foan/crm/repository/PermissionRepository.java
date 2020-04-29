package com.foan.crm.repository;


import com.foan.crm.constants.RoleType;
import com.foan.crm.entity.Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PermissionRepository extends CrudRepository<Permission, Long> {
    Optional<Permission> findByFunctionnameAndRole(String functionname, RoleType role);
}
