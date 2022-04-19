package com.example.integrador.repository;

import com.example.integrador.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository< Odontologo, Long> {
}
