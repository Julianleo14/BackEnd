package com.example.integrador.service;

import com.example.integrador.entities.Odontologo;
import com.example.integrador.repository.OdontologoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.integrador.service.OdontologoService;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith( SpringRunner.class)
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    OdontologoRepository repository;
    @BeforeEach
    void limbiarBD (){
        repository.deleteAll (  );
    }
    Odontologo crearOdontólogo (){
        Odontologo odontologo1 = new Odontologo ();
        odontologo1.setNombre ( "Pepe" );
        odontologo1.setApellido ( "Perez " );
        odontologo1.setMatricula ( "ABC12345" );
        odontologoService.guardar ( odontologo1 );
        return odontologo1;
    }
    @Test
    void buscarOdontólogoExistente ( ) {
        //Dado
        Odontologo odontologoPrueba =  crearOdontólogo ();
        //Cuando
        Odontologo respuesta = odontologoService.buscar (odontologoPrueba.getId ()).get ();
        //Entonces
        Assertions.assertEquals ( respuesta.getId (), odontologoPrueba.getId () );
        Assertions.assertEquals ( respuesta.getNombre (), odontologoPrueba.getNombre () );
        Assertions.assertEquals ( respuesta.getApellido (), odontologoPrueba.getApellido () );
        Assertions.assertEquals ( respuesta.getMatricula (), odontologoPrueba.getMatricula () );
    }

    @Test
    void buscarOdontologoNoExistente ( ) {
        //Cuando
        //Busco un odontólogo que no existe
        Optional respuesta = odontologoService.buscar ( 25L );
        //Entonces
        Assertions.assertFalse ( respuesta.isPresent() );
    }
}