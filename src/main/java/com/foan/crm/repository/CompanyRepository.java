package com.foan.crm.repository;

import com.foan.crm.entity.Client;
import com.foan.crm.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Optional<Company> findByName(String name);

    Page<Company> findAll(Pageable pageable);
}
