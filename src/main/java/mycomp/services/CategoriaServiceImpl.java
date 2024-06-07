package mycomp.services;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mycomp.models.CategoriaModel;
import mycomp.repositorys.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaServiceI {

  @Autowired
  private CategoriaRepository catrepo;

  @Override
  public CategoriaModel agregarCategoria(CategoriaModel categoria) {
	 
	 return this.catrepo.save(categoria);
  }

  @Override
  public Set<CategoriaModel> obtenerAllCategorias() {
	 
	 return new LinkedHashSet<>(catrepo.findAll());
  }

  @Override
  public CategoriaModel obtenerCategoria(Long idCategoria) {
	 
	 return this.catrepo.findById(idCategoria).get();
  }

  @Override
  public CategoriaModel actualizarCategoria(CategoriaModel categoria) {
	 
	 return this.catrepo.save(categoria);
  }

  @Override
  public void eliminarCategoria(Long idCategoria) {

	 this.catrepo.deleteById(idCategoria);

  }

}
