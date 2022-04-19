package com.example.integrador.repository;

import com.example.integrador.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository< Turno,Long> {
}
