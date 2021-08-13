package com.example.mockito.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
	@DisplayName("Dado que Mockeamos examenRepository, enviamos datos para buscar por nombre")
	void contextLoads() {
		
		ExamenService service = new ExamenServiceImpl(repository);
		List<Examen> datos = examenRepositoryImpl.findAll();
		when(repository.findAll()).thenReturn(datos);
		Optional<Examen> examen = service.findExamenporNombre("Lenguaje");
		assertTrue(examen.isPresent());
		assertEquals(6L, examen.orElseThrow().getId());
		assertEquals("Lenguaje", examen.get().getNombre());		

	}

	@Test
	@DisplayName("Dado que Mockeamos preguntaRepositoryImpl, los comparamos con los datos de la clase Datos")
	@Tag("Tamaño, elementos y filtro")
	void pruebaPreguntas(){
		//List<String> datos = Arrays.asList("Español", "Programación", "Danzas");
		List<String> datos = Arrays.asList("Aritmética", "integrales", "derivadas", "trigonometria", "geometria", "Español");   
		when(preguntaRepositoryImpl.findPreguntasPorExamenId(1L)).thenReturn(datos);
		assertEquals(Datos.PREGUNTAS.size(), datos.size());
		assertEquals(datos, Datos.PREGUNTAS); 

		Optional<String> data = datos.stream().filter(x->x.contains("Español")).findFirst();
		when(preguntaRepositoryImpl.findAllQuestions()).thenReturn(datos);
		assertEquals(data, Datos.PREGUNTAS.stream().filter(x->x.contains("Español")).findFirst());
		assertNotNull(data);
	}


 
}
