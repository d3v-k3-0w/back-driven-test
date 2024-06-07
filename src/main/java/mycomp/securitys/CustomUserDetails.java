package mycomp.securitys;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import mycomp.models.UsuarioModel;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

  private static final long serialVersionUID = 1L;

  private UsuarioModel usuario;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

	 /*
	  * :: transformar y retornar la lista de roles del usuario a objs
	  * GrantedAuthority que representan los roles del usuario::
	  */
	 return usuario.getLstRoles().stream()
		  // Mapea cada RolModel a un objeto SimpleGrantedAuthority
		  .map(rol -> new SimpleGrantedAuthority(rol.getNombreRol()))
		  // Recolecta los objetos SimpleGrantedAuthority en una lista
		  .collect(Collectors.toList());

  }

  public static UsuarioModel convertToUsuarioModel(CustomUserDetails customUserDetails) {

	 /* :: simplemente devuelve el campo 'usuario' de CustomUserDetails:: */
	 return customUserDetails.usuario;
  }

  /* ::obtener la contraseña del usuario:: */
  @Override
  public String getPassword() {

	 return usuario.getPassword();
  }

  /* :: obtener el nombre del usuario:: */
  @Override
  public String getUsername() {

	 return usuario.getUsername();
  }

  /*
   * ::indicar si la cuenta del usuario no ha expirado (siempre devuelve true)::
   */
  @Override
  public boolean isAccountNonExpired() {

	 return true;
  }

  /*
   * ::indicar si la cuenta del usuario no está bloqueada (siempre devuelve
   * true)::
   */
  @Override
  public boolean isAccountNonLocked() {

	 return true;
  }

  /*
   * ::indicar si las credenciales del usuario no han expirado (siempre devuelve
   * true) ::
   */
  @Override
  public boolean isCredentialsNonExpired() {

	 return true;
  }

  @Override
  public boolean isEnabled() {

	 return usuario.isEnable(); // el usuario debe estar habilitado
  }
}
