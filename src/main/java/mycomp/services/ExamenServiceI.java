package mycomp.services;

import java.util.List;
import java.util.Set;

import mycomp.models.CategoriaModel;
import mycomp.models.ExamenModel;

public interface ExamenServiceI {

  ExamenModel agregarExamen(ExamenModel examen);

  Set<ExamenModel> obtenerAllExamenes();

  ExamenModel obtenerExamen(Long idExamen);

  List<ExamenModel> listarExamenesCategoria(CategoriaModel categoria);

  List<ExamenModel> obtenerExamenesActivos();

  List<ExamenModel> obtenerExamenesActivosCategoria(CategoriaModel categoria);

  ExamenModel actualizarExamen(ExamenModel examen);

  void eliminarExamen(Long idExamen);

}
