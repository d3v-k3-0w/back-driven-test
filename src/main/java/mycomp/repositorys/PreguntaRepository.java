package mycomp.repositorys;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import mycomp.models.ExamenModel;
import mycomp.models.PreguntaModel;

public interface PreguntaRepository extends JpaRepository<PreguntaModel, Long> {

  Set<PreguntaModel> findByExamen(ExamenModel examen);

}
