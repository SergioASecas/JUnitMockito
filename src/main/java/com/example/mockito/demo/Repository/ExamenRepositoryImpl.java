package com.example.mockito.demo.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.mockito.demo.Modelo.Datos;
import com.example.mockito.demo.Modelo.Examen;

public class ExamenRepositoryImpl implements ExamenRepository {

    @Override
    public Examen guardar(Examen examen) {
        System.out.println("ExamenRepositoryImpl.guardar");
        return Datos.EXAMEN;
    }

    @Override
    public List<Examen> findAll() {
        System.out.println("ExamenRepositoryImpl.findAll");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Datos.EXAMENES;
    }
    
}
