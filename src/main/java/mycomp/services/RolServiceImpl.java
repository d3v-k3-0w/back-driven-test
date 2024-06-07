package mycomp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mycomp.models.RolModel;
import mycomp.repositorys.RolRepository;

@Service
@Transactional(readOnly = true) // transacción por defecto solo para lectura
public class RolServiceImpl implements RolServiceI {

  @Autowired
  private RolRepository rolrepo;

  @Override
  public Optional<RolModel> getByNombreRol(String nombreRol) {

	 return this.rolrepo.findByNombreRol(nombreRol);
  }

  @Override
  public List<RolModel> getAllRoles() {

	 return this.rolrepo.findAll();
  }

  @Override
  public RolModel findRolById(Long id) {

	 return this.rolrepo.findById(id).orElse(null);
  }

}
