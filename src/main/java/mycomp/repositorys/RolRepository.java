package mycomp.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mycomp.models.RolModel;

public interface RolRepository extends JpaRepository<RolModel, Long> {

  Optional<RolModel> findByNombreRol(String nombreRol);

}
