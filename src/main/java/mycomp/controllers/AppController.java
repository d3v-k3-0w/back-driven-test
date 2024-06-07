package mycomp.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import mycomp.models.RolModel;
import mycomp.models.UsuarioModel;
import mycomp.payloads.JwtRequestLogin;
import mycomp.payloads.JwtResponseToken;
import mycomp.securitys.CustomUserDetails;
import mycomp.securitys.UserDetailsServiceImpl;
import mycomp.services.UsuarioServiceI;
import mycomp.utils.JwtUtil;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AppController {

  @Autowired
  private UsuarioServiceI ususrvc;

  @Autowired
  private BCryptPasswordEncoder bcryptpass;

  @Autowired
  private AuthenticationManager authmngr;

  @Autowired
  private UserDetailsServiceImpl userdtsrvcimpl;

  @Autowired
  private JwtUtil jwtutil;

  @GetMapping("/")
  public String home() {

	 return "Pagina de inicio publica";
  }

  @PostMapping("/user/registrar")
  public ResponseEntity<UsuarioModel> handlerRegistrar(@RequestBody UsuarioModel usuario) {
	 try {

		/* ::establecer foto perfil predeterminado y hasshear contraseña:: */
		usuario.setPerfil("foto.png");
		usuario.setPassword(this.bcryptpass.encode(usuario.getPassword()));

		long count = this.ususrvc.countUsuarios(); // contar el num de usuarios registrados

		/*
		 * ::si count==0, el primer usuario registrado es ADMIN y los demas son NORMAL::
		 */
		List<String> lstRoleNames = count == 0 ? List.of("ADMIN") : List.of("NORMAL");

		/* ::convertir nombres de roles a objetos RolModel:: */
		List<RolModel> roles = lstRoleNames.stream().map(roleName -> {

		  var rol = new RolModel();

		  rol.setNombreRol(roleName);

		  return rol;
		}).toList();

		/* :: asignar roles al usuario :: */
		usuario.setLstRoles(roles);

		/* ::guardar usuario y devolver respuesta:: */
		UsuarioModel usuRegistrado = this.ususrvc.saveUsuario(usuario);
		return ResponseEntity.ok(usuRegistrado);

	 } catch (Exception exc) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	 }
  }

  @PostMapping("/user/login")
  public ResponseEntity<?> handlerIniciarSesion(@RequestBody JwtRequestLogin authReqLogin) {
	 try {
		
		// autenticar al usuario con las credenciales proporcionadas
		this.authmngr.authenticate(
			 new UsernamePasswordAuthenticationToken(authReqLogin.getUsername(), authReqLogin.getPassword()));
		
	 } catch (BadCredentialsException exc) {
		
		// devolver una respuesta de error si las credenciales son incorrectas
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			 .body("Error en el username o contraseña: " + exc.getMessage());
	 }

	 /* ::obtener los detalles o datos desde la db para construir el token:: */
	 UserDetails userDetails = this.userdtsrvcimpl.loadUserByUsername(authReqLogin.getUsername());

	 /* ::crear un token JWT para el usuario autenticado:: */
	 String token = jwtutil.createToken(userDetails);

	 /* ::devolver el token en la respuesta:: */
	 return ResponseEntity.ok(new JwtResponseToken(token));
  }

  @GetMapping("/user/{username}")
  public ResponseEntity<UsuarioModel> handlerObtenerUsuario(@PathVariable String username) {

	 UsuarioModel usuario = this.ususrvc.obteinUsername(username);

	 return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @GetMapping("/actual-usuario")
  public ResponseEntity<UsuarioModel> handlerUsuarioActual(Principal principal) {

	 UserDetails userDetails = this.userdtsrvcimpl.loadUserByUsername(principal.getName());

	 if (userDetails instanceof CustomUserDetails customUserDetails) {

		return ResponseEntity.ok(CustomUserDetails.convertToUsuarioModel(customUserDetails));
	 }

	 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @DeleteMapping("/user/{idUsu}")
  public ResponseEntity<Void> handlerEliminarUsuario(@PathVariable Long idUsu) {

	 this.ususrvc.deleteUsuario(idUsu);

	 return ResponseEntity.noContent().build();
  }

}
