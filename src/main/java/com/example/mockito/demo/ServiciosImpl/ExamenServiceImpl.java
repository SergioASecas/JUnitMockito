package com.example.mockito.demo.ServiciosImpl;

import java.util.List;
import java.util.Optional;

import com.example.mockito.demo.Modelo.Examen;
import com.example.mockito.demo.Repository.ExamenRepository;
import com.example.mockito.demo.Repository.PreguntaRepository;
import com.example.mockito.demo.Servicios.ExamenService;

import org.springframework.beans.factory.annotation.Autowired;

public class ExamenServiceImpl implements ExamenService{

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    

    public ExamenServiceImpl(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    public ExamenServiceImpl(ExamenRepository examenRepository, PreguntaRepository preguntaRepository) {
        this.examenRepository = examenRepository;
        this.preguntaRepository = preguntaRepository;
    }

    @Override
    public Optional<Examen> findExamenporNombre(String nombre) {
        System.out.println(examenRepository.findAll());
        return examenRepository.findAll()
                .stream()
                .filter(e->e.getNombre().contains(nombre))
                .findFirst();
    }

    @Override
    public Examen findExamenPorNombreConPreguntas(String nombre) {
        Optional<Examen> examenOptional = findExamenporNombre(nombre);
        Examen examen = null;
        if (examenOptional.isPresent()) {
            examen = examenOptional.orElseThrow();
            List<String> preguntas = preguntaRepository.findPreguntasPorExamenId(examen.getId());
            examen.setPreguntas(preguntas);
        }
        return examen;
    }

    @Override
    public Examen guardar(Examen examen) {
        if (!examen.getPreguntas().isEmpty()) {
            preguntaRepository.guardarVarias(examen.getPreguntas());
        }
        return examenRepository.guardar(examen);
    }
   
    
}
