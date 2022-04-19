package com.example.integrador.repository;

import com.example.integrador.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository< Domicilio,Long> {
}
