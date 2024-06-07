package mycomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackDrivenTestApplication {

  public static void main(String[] args) {
	 SpringApplication.run(BackDrivenTestApplication.class, args);
  }

  /*
   * @Bean CommandLineRunner run(IUsuarioService ususrvc) {
   * 
   * return args -> {
   * 
   * UsuarioModel usuario = new UsuarioModel();
   * 
   * usuario.setUsername("keiow"); usuario.setPassword("k123");
   * usuario.setNombre("Kerwin"); usuario.setApellidos("Gomez");
   * usuario.setCorreo("keiow@gmail.com"); usuario.setTelefono("960321027");
   * usuario.setPerfil("foto.png");
   * 
   * // ::crear una lista de nombres de roles:: List<String> lstRoleNames =
   * Arrays.asList("ADMIN");
   * 
   * //::crear una lista de roles y asignarlos al usuario:: List<RolModel> roles =
   * new ArrayList<>();
   * 
   * for (String roleName : lstRoleNames) {
   * 
   * RolModel rol = new RolModel();
   * 
   * rol.setNombreRol(roleName); roles.add(rol); }
   * 
   * usuario.setLstRoles(roles);
   * 
   * // ::guardar el usuario con los roles asignados::
   * ususrvc.saveUsuario(usuario);
   * 
   * System.out.println(usuario.getUsername());
   * 
   * };
   * 
   * }
   */

}
