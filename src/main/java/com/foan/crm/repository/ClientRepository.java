package com.foan.crm.repository;

import com.foan.crm.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByName(String Name);

    Page<Client> findAll(Pageable pageable);
}
