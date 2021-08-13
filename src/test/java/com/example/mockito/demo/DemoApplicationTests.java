package com.example.mockito.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.mockito.demo.Modelo.Datos;
import com.example.mockito.demo.Modelo.Examen;
import com.example.mockito.demo.Repository.ExamenRepository;
import com.example.mockito.demo.Repository.ExamenRepositoryImpl;
import com.example.mockito.demo.Repository.PreguntaRepository;
import com.example.mockito.demo.Repository.PreguntaRepositoryImpl;
import com.example.mockito.demo.Servicios.ExamenService;
import com.example.mockito.demo.ServiciosImpl.ExamenServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Mock
	ExamenRepository repository = mock(ExamenRepository.class);

	ExamenRepositoryImpl examenRepositoryImpl = new ExamenRepositoryImpl();

	@Mock
	PreguntaRepositoryImpl preguntaRepositoryImpl = mock(PreguntaRepositoryImpl.class);

	@InjectMocks
	ExamenServiceImpl service;

	
	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
		repository = mock(ExamenRepositoryImpl.class);
		preguntaRepositoryImpl = mock(PreguntaRepositoryImpl.class);
		service = new ExamenServiceImpl(repository, preguntaRepositoryImpl);
	}


	@Test
	void contextLoads() {
		
		ExamenService service = new ExamenServiceImpl(repository);
		List<Examen> datos = examenRepositoryImpl.findAll();
		when(repository.findAll()).thenReturn(datos);
		Optional<Examen> examen = service.findExamenporNombre("Lenguaje");
		System.out.println(examen.toString());
		assertTrue(examen.isPresent());
		assertEquals(6L, examen.orElseThrow().getId());
		assertEquals("Lenguaje", examen.get().getNombre());		

	}

}
