package com.example.integrador.service;
import com.example.integrador.entities.Paciente;
import com.example.integrador.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService{
    @Autowired
    PacienteRepository repository;

    public List< Paciente > listarPacientes() {
        return repository.findAll();
    }
    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente actualizar(Long id, Paciente paciente) {
        if(buscar(id).isPresent()) {
            return repository.save ( paciente );
        }
        else
            return null;
    }

    public Optional<Paciente> buscar(Long id) {
        return repository.findById(id);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
