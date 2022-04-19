package com.example.integrador.controller;

import com.example.integrador.entities.Odontologo;
import com.example.integrador.exceptions.ResourceNotFoundException;
import com.example.integrador.repository.OdontologoRepository;
import com.example.integrador.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith( SpringRunner.class)
@SpringBootTest
class OdontologoControllerTest {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private OdontologoController controlador;
    @Autowired
    OdontologoRepository repository;
    @BeforeEach
    void limbiarBD (){
        repository.deleteAll (  );
    }
    private List <Odontologo> crearOdontólogos ( ) {

        Odontologo odontologo1 = new Odontologo ( );
        odontologo1.setNombre ( "Pepe" );
        odontologo1.setApellido ( "Perez " );
        odontologo1.setMatricula ( "ABC12345" );


        Odontologo odontologo2 = new Odontologo ( );
        odontologo2.setNombre ( "Marco" );
        odontologo2.setApellido ( "Antonio " );
        odontologo2.setMatricula ( "5as4dsa1" );

        odontologoService.guardar ( odontologo1 );
        odontologoService.guardar ( odontologo2 );
        List < Odontologo > lista = new ArrayList<Odontologo> ( );
        lista.add ( odontologo1 );
        lista.add ( odontologo2 );
        return lista;

    }
    @Test
    void listarOdontologos ( ) {
        //Dado
        List <Odontologo> listaEsperada = crearOdontólogos ();
        //Cuando
        List <Odontologo> resultado= controlador.listarOdontologos ();
        //Entonces
        Assertions.assertNotNull ( resultado );
        Assertions.assertEquals ( resultado.size (),listaEsperada.size ());
        Assertions.assertEquals ( listaEsperada.get ( 0 ).getNombre () ,resultado.get ( 0 ).getNombre () );
        Assertions.assertEquals ( listaEsperada.get ( 1 ).getNombre () ,resultado.get ( 1 ).getNombre () );
        Assertions.assertEquals ( listaEsperada.get ( 0 ).getId () ,resultado.get ( 0 ).getId () );
        Assertions.assertEquals ( listaEsperada.get ( 1 ).getId () ,resultado.get ( 1 ).getId () );
    }

    @Test
    void registrar ( ) {
        //Dado
        Odontologo odontologo1 = new Odontologo ( );
        odontologo1.setNombre ( "Pepe" );
        odontologo1.setApellido ( "Perez " );
        odontologo1.setMatricula ( "ABC12345" );
        //Cuando
        Odontologo odontologGuardado  = controlador.registrar ( odontologo1 );
        //Entonces
        Assertions.assertEquals ( odontologo1,odontologGuardado );
    }

    @Test
    void actualizarPaciente ( ) {
        //DADO
        Odontologo odontologo1 = new Odontologo ( );
        odontologo1.setNombre ( "Pepe" );
        odontologo1.setApellido ( "Perez " );
        odontologo1.setMatricula ( "ABC12345" );

        Odontologo odontologGuardado  = controlador.registrar ( odontologo1 );

        //CUANDO
        Odontologo odontologoModificado= new Odontologo ();
        odontologoModificado.setId ( odontologGuardado.getId ( ) );
        odontologoModificado.setNombre ( "Este ya no es pepe" );
        odontologoModificado.setApellido ( "Pineda" );
        odontologoModificado.setMatricula ( "123asb" );
        Odontologo odontologoRecibido = controlador.actualizarPaciente ( odontologoModificado );

        //Entonces

        Assertions.assertEquals ( odontologo1.getId (),odontologoRecibido.getId ());
        Assertions.assertEquals ( "Este ya no es pepe",odontologoRecibido.getNombre ());
        Assertions.assertNotEquals ( odontologo1.getApellido (),odontologoRecibido.getApellido ());
        Assertions.assertNotEquals ( odontologo1.getMatricula (),odontologoRecibido.getMatricula ());
    }

    @Test
    void buscarOdontologoExistente ( ) {
        //Dado
        Odontologo odontologo1 = new Odontologo ( );
        odontologo1.setNombre ( "Pepe" );
        odontologo1.setApellido ( "Perez " );
        odontologo1.setMatricula ( "ABC12345" );

        Odontologo odontologGuardado  = controlador.registrar ( odontologo1 );
        //Cuando
        ResponseEntity <Odontologo> respuesta = controlador.buscarOdontologo ( odontologGuardado.getId () );
        //Entonces

        Assertions.assertEquals ( ResponseEntity.ok(respuesta.getBody ().getId ()), ResponseEntity.ok(odontologoService.buscar(odontologGuardado.getId ()).get().getId ()));
        Assertions.assertEquals ( ResponseEntity.ok(respuesta.getBody ().getNombre ()), ResponseEntity.ok(odontologoService.buscar(odontologGuardado.getId ()).get().getNombre ()));

    }
    @Test
    void buscarOdontologoNoExistente ( ) {
        //Dado
        Odontologo odontologo1 = new Odontologo ( );
        odontologo1.setNombre ( "Pepe" );
        odontologo1.setApellido ( "Perez " );
        odontologo1.setMatricula ( "ABC12345" );

        Odontologo odontologGuardado  = controlador.registrar ( odontologo1 );
        //Cuando
        ResponseEntity <Odontologo> respuesta = controlador.buscarOdontologo ( 650L );
        //Entonces
        Assertions.assertEquals ( respuesta, ResponseEntity.status( HttpStatus.NOT_FOUND).build());
    }

    @Test
    void eliminarOdontologo ( ) throws ResourceNotFoundException {
        //Dado
        Odontologo odontologo1 = new Odontologo ( );
        odontologo1.setNombre ( "Pepe" );
        odontologo1.setApellido ( "Perez " );
        odontologo1.setMatricula ( "ABC12345" );

        Odontologo odontologGuardado  = controlador.registrar ( odontologo1 );
        //Cuando
        ResponseEntity < String > odontologoEliminado = controlador.eliminarOdontologo ( odontologGuardado.getId () );
        //Entonces
        Assertions.assertEquals ( odontologoEliminado, ResponseEntity.ok("Se elimino el odontólogo correspondiente al ID: "+ odontologGuardado.getId ()) );

    }
}