package mycomp.services;

import java.util.Set;

import mycomp.models.ExamenModel;
import mycomp.models.PreguntaModel;

public interface PreguntaServiceI {

  PreguntaModel agregarPregunta(PreguntaModel pregunta);

  Set<PreguntaModel> obtenerAllPreguntas();

  PreguntaModel listarPregunta(Long idPregunta);

  PreguntaModel obtenerPregunta(Long idPregunta);

  Set<PreguntaModel> obtenerPreguntasDelExamen(ExamenModel examen);

  PreguntaModel actualizarPregunta(PreguntaModel pregunta);

  void eliminarPregunta(Long idPregunta);

}
