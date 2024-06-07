package mycomp.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mycomp.models.CategoriaModel;
import mycomp.models.ExamenModel;

public interface ExamenRepository extends JpaRepository<ExamenModel, Long> {

  List<ExamenModel> findByCategoria(CategoriaModel categoria);

  List<ExamenModel> findByIsActivo(Boolean estado);

  List<ExamenModel> findByCategoriaAndIsActivo(CategoriaModel categoria, Boolean estado);

}
