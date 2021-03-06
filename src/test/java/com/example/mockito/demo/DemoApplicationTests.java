package com.example.mockito.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
	@Tag("Tama??o, elementos y filtro")
	void pruebaPreguntas(){
		//List<String> datos = Arrays.asList("Espa??ol", "Programaci??n", "Danzas");
		List<String> datos = Arrays.asList("Aritm??tica", "integrales", "derivadas", "trigonometria", "geometria", "Espa??ol");   
		when(preguntaRepositoryImpl.findPreguntasPorExamenId(1L)).thenReturn(datos);
		assertEquals(Datos.PREGUNTAS.size(), datos.size());
		assertEquals(datos, Datos.PREGUNTAS); 

		Optional<String> data = datos.stream().filter(x->x.contains("Espa??ol")).findFirst();
		when(preguntaRepositoryImpl.findAllQuestions()).thenReturn(datos);
		assertEquals(data, Datos.PREGUNTAS.stream().filter(x->x.contains("Espa??ol")).findFirst());
		assertNotNull(data);
	}


	@Test
	@DisplayName("Testeando con Verify")
	void testPreguntasExamenVerify(){
		when(repository.findAll()).thenReturn(Datos.EXAMENES);
		when(preguntaRepositoryImpl.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
		Examen examen = service.findExamenPorNombreConPreguntas("Matem??ticas");
		assertEquals(6, examen.getPreguntas().size());
		assertTrue(examen.getPreguntas().contains("integrales"));
		verify(repository).findAll();
		verify(preguntaRepositoryImpl).findPreguntasPorExamenId(anyLong());
	}
 
}
