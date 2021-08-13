package com.example.mockito.demo.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.mockito.demo.Modelo.Datos;

public class PreguntaRepositoryImpl implements PreguntaRepository{

    @Override
    public List<String> findPreguntasPorExamenId(Long id) {
        System.out.println("PreguntaRepositoryImpl.findPreguntasPorExamenId");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Datos.PREGUNTAS;
    }

    @Override
    public void guardarVarias(List<String> preguntas) {
        System.out.println("PreguntasRepositoryImpl.guardarVarias");
        
    }

    @Override
    public List<String> findAllQuestions() {
        return Datos.PREGUNTAS;
    }
    
}
