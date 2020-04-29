package com.foan.crm.repository;


import com.foan.crm.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
