package mycomp.securitys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import mycomp.exceptions.ResourceNotFoundException;
import mycomp.models.UsuarioModel;
import mycomp.repositorys.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioRepository usurepo;

  @Override
  public UserDetails loadUserByUsername(String username) {

	 UsuarioModel usuario = usurepo.findByUsername(username);

	 if (usuario == null) {

		/* ::excepcion personalizada para usuario no encontrado:: */
		throw new ResourceNotFoundException("Usuario no encontrado: " + username);
	 }

	 var userDetails = new CustomUserDetails(usuario);

	 return userDetails;
  }

}
