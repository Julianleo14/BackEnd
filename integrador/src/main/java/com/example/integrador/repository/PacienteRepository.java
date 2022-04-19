package com.example.integrador.repository;

import com.example.integrador.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository< Paciente, Long> {
}
