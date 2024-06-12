package mycomp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mycomp.exceptions.UsuarioExistException;
import mycomp.models.RolModel;
import mycomp.models.UsuarioModel;
import mycomp.repositorys.RolRepository;
import mycomp.repositorys.UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioServiceI {

  @Autowired
  private UsuarioRepository usurepo;

  @Autowired
  private RolRepository rolrepo;

  @Override
  public UsuarioModel saveUsuario(UsuarioModel usu) {

	 /* ::buscar usuario existente por nombre de usuario:: */
	 UsuarioModel usuario = this.usurepo.findByUsername(usu.getUsername());

	 /* ::si el usuario ya existe, lanzar excepción :: */
	 if (usuario != null) {
		throw new UsuarioExistException("El usuario ya existe");
	 }

	 /* ::procesar los roles usando Streams:: */
	 List<RolModel> rolesAsignados = usu.getLstRoles().stream().map(rol ->
	 // intentar encontrar el rol existente por nombre
	 this.rolrepo.findByNombreRol(rol.getNombreRol())

		  // si no se encuentra, crear y guardar un nuevo rol
		  .orElseGet(() -> {

			 var nuevoRol = new RolModel();

			 nuevoRol.setNombreRol(rol.getNombreRol());

			 return this.rolrepo.save(nuevoRol);
		  }))
		  // Recolectar todos los roles procesados en una lista
		  .collect(Collectors.toList());

	 /* ::asignar los roles finales al usuario :: */
	 usu.setLstRoles(rolesAsignados);

	 /* ::Guardar y retornar el usuario con roles asignados:: */
	 return this.usurepo.save(usu);
  }

  @Override
  public UsuarioModel obteinUsername(String username) {

	 return this.usurepo.findByUsername(username);
  }

  @Override
  public void deleteUsuario(Long idUsu) {
	 this.usurepo.deleteById(idUsu);

  }

  @Override
  public long countUsuarios() {

	 return this.usurepo.count();
  }

}
