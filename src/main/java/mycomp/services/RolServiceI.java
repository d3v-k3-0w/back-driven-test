package mycomp.services;

import java.util.List;
import java.util.Optional;

import mycomp.models.RolModel;


public interface RolServiceI {

  Optional<RolModel> getByNombreRol(String nombreRol);

  List<RolModel> getAllRoles();

  RolModel findRolById(Long id);

}
