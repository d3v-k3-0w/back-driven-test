package mycomp.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mycomp.models.CategoriaModel;
import mycomp.services.CategoriaServiceI;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CategoriaController {

  @Autowired
  private CategoriaServiceI catsrvc;

  @PostMapping("/categoria/registrar")
  public ResponseEntity<CategoriaModel> handlerGuardarCategoria(@RequestBody CategoriaModel categoria) {

	 CategoriaModel categoriaGuardada = this.catsrvc.agregarCategoria(categoria);

	 return ResponseEntity.ok(categoriaGuardada);
  }

  // obtener una categoría por su ID
  @GetMapping("/categoria/listar/{idCategoria}")
  public ResponseEntity<CategoriaModel> handlerListarCategoriaPorId(@PathVariable Long idCategoria) {
	 return ResponseEntity.ok(this.catsrvc.obtenerCategoria(idCategoria));
  }

  // listar todas las categorías
  @GetMapping("/categoria/listar-all")
  public ResponseEntity<Set<CategoriaModel>> handlerListarCategorias() {

	 return ResponseEntity.ok(this.catsrvc.obtenerAllCategorias());
  }

  // actualizar una categoría existente
  @PutMapping("/categoria/actualizar")
  public ResponseEntity<CategoriaModel> handlerActualizarCategoria(@RequestBody CategoriaModel categoria) {

	 CategoriaModel categoriaActualizada = this.catsrvc.actualizarCategoria(categoria);

	 return ResponseEntity.ok(categoriaActualizada);
  }

  // eliminar una categoría por su ID
  @DeleteMapping("/categoria/eliminar/{idCategoria}")
  public ResponseEntity<Void> handlerEliminarCategoria(@PathVariable Long idCategoria) {

	 this.catsrvc.eliminarCategoria(idCategoria);

	 return ResponseEntity.noContent().build();
  }

}
