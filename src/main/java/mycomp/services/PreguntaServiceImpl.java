package mycomp.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mycomp.models.ExamenModel;
import mycomp.models.PreguntaModel;
import mycomp.repositorys.PreguntaRepository;

@Service
public class PreguntaServiceImpl implements PreguntaServiceI {

  @Autowired
  private PreguntaRepository pregrepo;

  @Override
  public PreguntaModel agregarPregunta(PreguntaModel pregunta) {
	 return this.pregrepo.save(pregunta);
  }

  @Override
  public Set<PreguntaModel> obtenerAllPreguntas() {

	 List<PreguntaModel> preguntasList = this.pregrepo.findAll();

	 /*
	  * :: crear un conjunto Set a partir de la lista, asegura que no haya duplicados
	  * y conjunto unico::
	  */
	 return new HashSet<>(preguntasList);
  }

  @Override
  public PreguntaModel listarPregunta(Long idPregunta) {

	 return this.pregrepo.findById(idPregunta).orElse(null);
  }

  @Override
  public PreguntaModel obtenerPregunta(Long idPregunta) {

	 return this.pregrepo.findById(idPregunta).get();
  }

  @Override
  public Set<PreguntaModel> obtenerPreguntasDelExamen(ExamenModel examen) {

	 return this.pregrepo.findByExamen(examen);
  }

  @Override
  public PreguntaModel actualizarPregunta(PreguntaModel pregunta) {

	 return this.pregrepo.save(pregunta);
  }

  @Override
  public void eliminarPregunta(Long idPregunta) {

	 /*
	  * ::usar deleteById ya que estamos eliminando una entidad por su id que le
	  * llega por param
	  */
	 this.pregrepo.deleteById(idPregunta);
  }

}
