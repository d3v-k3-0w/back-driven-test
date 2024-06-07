package mycomp.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mycomp.models.CategoriaModel;
import mycomp.models.ExamenModel;
import mycomp.services.ExamenServiceI;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ExamenController {

  @Autowired
  private ExamenServiceI exmnsrvc;

  // guardar un nuevo examen
  @PostMapping("/examen/registrar")
  public ResponseEntity<ExamenModel> handlerGuardarExamen(@RequestBody ExamenModel examen) {

	 return ResponseEntity.ok(this.exmnsrvc.agregarExamen(examen));
  }

  // obtener un examen por su ID
  @GetMapping("/examen/listar/{idExamen}")
  public ResponseEntity<ExamenModel> handlerListarExamenPorId(@PathVariable Long idExamen) {

	 return ResponseEntity.ok(this.exmnsrvc.obtenerExamen(idExamen));
  }

  // listar todos los exámenes
  @GetMapping("/examen/listar-all")
  public ResponseEntity<Set<ExamenModel>> handlerListarExamenes() {

	 return ResponseEntity.ok(this.exmnsrvc.obtenerAllExamenes());
  }

  // listar exámenes por categoría
  @GetMapping("/examen/categoria/{idCategoria}")
  public ResponseEntity<List<ExamenModel>> handlerListarExamenesCategoria(@PathVariable Long idCategoria) {

	 var categoria = new CategoriaModel();

	 categoria.setIdCategoria(idCategoria);

	 return ResponseEntity.ok(this.exmnsrvc.listarExamenesCategoria(categoria));
  }

  // listar exámenes activos
  @GetMapping("/examen/activo")
  public ResponseEntity<List<ExamenModel>> handlerListarExamenesActivos() {

	 return ResponseEntity.ok(this.exmnsrvc.obtenerExamenesActivos());
  }

  // listar exámenes activos por categoría
  @GetMapping("/examen/categoria/activo/{idCategoria}")
  public ResponseEntity<List<ExamenModel>> handlerListarExamenesActivosCategoria(@PathVariable Long idCategoria) {

	 var categoria = new CategoriaModel();

	 categoria.setIdCategoria(idCategoria);

	 return ResponseEntity.ok(this.exmnsrvc.obtenerExamenesActivosCategoria(categoria));
  }

  // actualizar un examen existente
  @PutMapping("/examen/actualizar")
  public ResponseEntity<ExamenModel> handlerActualizarExamen(@RequestBody ExamenModel examen) {

	 return ResponseEntity.ok(this.exmnsrvc.actualizarExamen(examen));
  }

  // eliminar un examen por su ID
  @DeleteMapping("/examen/eliminar/{idExamen}")
  public ResponseEntity<Void> handlerEliminarExamen(@PathVariable Long idExamen) {

	 this.exmnsrvc.eliminarExamen(idExamen);

	 return ResponseEntity.noContent().build();
  }

}
