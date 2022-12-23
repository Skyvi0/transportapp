package com.transport.transportapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transport.transportapp.entity.TransportCompany;

@Repository
public interface TransportCompanyRepository extends JpaRepository<TransportCompany, Long> {
}
