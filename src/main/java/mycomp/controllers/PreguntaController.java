package mycomp.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mycomp.models.ExamenModel;
import mycomp.models.PreguntaModel;
import mycomp.services.ExamenServiceI;
import mycomp.services.PreguntaServiceI;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PreguntaController {

  @Autowired
  private PreguntaServiceI pregsrvc;

  @Autowired
  private ExamenServiceI exmnsrvc;

  // guardar una nueva pregunta
  @PostMapping("/pregunta/registrar")
  public ResponseEntity<PreguntaModel> handlerGuardarPregunta(@RequestBody PreguntaModel pregunta) {

	 return ResponseEntity.ok(this.pregsrvc.agregarPregunta(pregunta));
  }

  // listar preguntas de un examen específico
  @GetMapping("/pregunta/examen/{idExamen}")
  public ResponseEntity<List<PreguntaModel>> handlerListarPreguntasDelExamen(@PathVariable Long idExamen) {

	 ExamenModel examen = this.exmnsrvc.obtenerExamen(idExamen);

	 List<PreguntaModel> lstPreguntas = new ArrayList<>(examen.getLstPreguntas());

	 if (lstPreguntas.size() > Integer.parseInt(examen.getNumPreguntas())) {
		
		lstPreguntas = lstPreguntas.stream()
			 .limit(Integer.parseInt(examen.getNumPreguntas()))
			 .collect(Collectors.toList());
	 }

	 Collections.shuffle(lstPreguntas);

	 return ResponseEntity.ok(lstPreguntas);
  }

  // listar todas las preguntas de un examen (para administrador)
  @GetMapping("/pregunta/all/{idExamen}")
  public ResponseEntity<Set<PreguntaModel>> handlerListarPreguntasDelExamenAdmin(@PathVariable Long idExamen) {

	 var examen = new ExamenModel();

	 examen.setIdExamen(idExamen);

	 Set<PreguntaModel> lstPreguntas = this.pregsrvc.obtenerPreguntasDelExamen(examen);

	 return ResponseEntity.ok(lstPreguntas);
  }

  // evaluar un examen con una lista de preguntas respondidas
  @PostMapping("/pregunta/evaluar-examen")
  public ResponseEntity<Map<String, Object>> evaluarExamen(@RequestBody List<PreguntaModel> lstPreguntas) {

	 double puntosMax = lstPreguntas.stream().mapToDouble(p -> {

		PreguntaModel pregunta = this.pregsrvc.listarPregunta(p.getIdPregunta());

		return pregunta.getRespuesta().equals(p.getRespuestaDada())
			 ? Double.parseDouble(p.getExamen().getPuntosMax()) / lstPreguntas.size()
			 : 0;
	 }).sum();

	 long rptaCorrectas = lstPreguntas.stream().filter(p -> {
		
		PreguntaModel pregunta = this.pregsrvc.listarPregunta(p.getIdPregunta());
		
		return pregunta.getRespuesta().equals(p.getRespuestaDada());
		
	 }).count();

	 long intentos = lstPreguntas.stream().filter(p -> p.getRespuestaDada() != null).count();

	 Map<String, Object> rptas = Map.of(
		  "puntosMaximos", puntosMax,
		  "respuestasCorrectas", rptaCorrectas, 
		  "intentos", intentos);

	 return ResponseEntity.ok(rptas);
  }

  // obtener una pregunta por su ID
  @GetMapping("/pregunta/{idPregunta}")
  public ResponseEntity<PreguntaModel> handlerListarPreguntaPorId(@PathVariable Long idPregunta) {
	 
	 return ResponseEntity.ok(this.pregsrvc.obtenerPregunta(idPregunta));
  }

  // actualizar una pregunta existente
  @PutMapping("/pregunta/actualizar")
  public ResponseEntity<PreguntaModel> handlerActualizarPregunta(@RequestBody PreguntaModel pregunta) {
	 
	 return ResponseEntity.ok(this.pregsrvc.actualizarPregunta(pregunta));
  }

  // eliminar una pregunta por su ID
  @DeleteMapping("/pregunta/eliminar/{idPregunta}")
  public ResponseEntity<Void> handlerEliminarPregunta(@PathVariable Long idPregunta) {
	 
	 this.pregsrvc.eliminarPregunta(idPregunta);
	 
	 return ResponseEntity.noContent().build();
  }

}
