package mycomp.services;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mycomp.models.CategoriaModel;
import mycomp.models.ExamenModel;
import mycomp.repositorys.ExamenRepository;

@Service
public class ExamenServiceImpl implements ExamenServiceI {

  @Autowired
  private ExamenRepository exmnrepo;

  @Override
  public ExamenModel agregarExamen(ExamenModel examen) {
	 return this.exmnrepo.save(examen);
  }

  @Override
  public Set<ExamenModel> obtenerAllExamenes() {

	 return new LinkedHashSet<>(this.exmnrepo.findAll());
  }

  @Override
  public ExamenModel obtenerExamen(Long idExamen) {

	 return this.exmnrepo.findById(idExamen).get();
  }

  @Override
  public List<ExamenModel> listarExamenesCategoria(CategoriaModel categoria) {

	 return this.exmnrepo.findByCategoria(categoria);

  }

  @Override
  public List<ExamenModel> obtenerExamenesActivos() {

	 return this.exmnrepo.findByIsActivo(true); // solo obtendra los examenes activos (true)
  }

  @Override
  public List<ExamenModel> obtenerExamenesActivosCategoria(CategoriaModel categoria) {

	 /* ::de una categoria especifica se obtiene solo los examenes activos:: */
	 return this.exmnrepo.findByCategoriaAndIsActivo(categoria, true);
  }

  @Override
  public ExamenModel actualizarExamen(ExamenModel examen) {

	 return this.exmnrepo.save(examen);
  }

  @Override
  public void eliminarExamen(Long idExamen) {

	 this.exmnrepo.deleteById(idExamen);

  }

}
