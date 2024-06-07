package mycomp.services;

import java.util.Set;

import mycomp.models.CategoriaModel;

public interface CategoriaServiceI {

  CategoriaModel agregarCategoria(CategoriaModel categoria);

  Set<CategoriaModel> obtenerAllCategorias();

  CategoriaModel obtenerCategoria(Long idCategoria);

  CategoriaModel actualizarCategoria(CategoriaModel categoria);

  void eliminarCategoria(Long idCategoria);

}
