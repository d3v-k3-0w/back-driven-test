package mycomp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import mycomp.models.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {

}
