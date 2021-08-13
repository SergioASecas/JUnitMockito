package com.example.mockito.demo.Repository;

import java.util.List;

public interface PreguntaRepository {

    List<String> findAllQuestions();

    List<String> findPreguntasPorExamenId(Long id);
    
    void guardarVarias(List<String> preguntas);
}
