package com.example.integrador.controller;

import com.example.integrador.entities.Paciente;
import com.example.integrador.exceptions.ResourceNotFoundException;
import com.example.integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List< Paciente > listarPacientes(){
        return pacienteService.listarPacientes();
    }
    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardar(paciente);
    }

    @PutMapping("/{id}")
    public Paciente actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente){
        return pacienteService.actualizar(id,paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        if(pacienteService.buscar(id).isPresent()){
            return ResponseEntity.ok(pacienteService.buscar(id).get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
            pacienteService.eliminar(id);
            return ResponseEntity.ok("Se elimino el paciente sin problemas");
    }



}
