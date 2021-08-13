package com.example.mockito.demo.Repository;

import java.util.List;

import com.example.mockito.demo.Modelo.Examen;

public interface ExamenRepository {
    
    Examen guardar(Examen examen);

    List<Examen> findAll();
}
