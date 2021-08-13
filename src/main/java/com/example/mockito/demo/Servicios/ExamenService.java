package com.example.mockito.demo.Servicios;

import java.util.Optional;

import com.example.mockito.demo.Modelo.Examen;

public interface ExamenService {

    Optional<Examen> findExamenporNombre(String nombre);

    Examen findExamenPorNombreConPreguntas(String nombre);

    Examen guardar(Examen examen);
    
}
