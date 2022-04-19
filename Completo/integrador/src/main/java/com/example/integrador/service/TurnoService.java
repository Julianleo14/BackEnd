package com.example.integrador.service;


import com.example.integrador.entities.Turno;
import com.example.integrador.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    TurnoRepository repository;

    public Turno guardar( Turno turno){
        return repository.save(turno);
    }

    public List<Turno> listarTurnos(){
        return repository.findAll();
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }

    public Optional<Turno> buscar(Long id){
        return repository.findById(id);
    }

    public Turno actualizar(Turno turno) {
        if(buscar(turno.getId()).isPresent())
            return repository.save(turno);
        else
            return null;
    }


}
