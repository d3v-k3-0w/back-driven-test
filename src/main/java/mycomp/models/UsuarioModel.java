package mycomp.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class UsuarioModel implements Serializable {
  
  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	private String username;
	private String password;
	private String nombre;
	private String apellidos;
	private String correo;
	private String telefono;
	private String perfil;
	private boolean enable = true;

	/* ::un usuario - muchos roles && un rol - muchos usuarios:: */
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "usuarios_roles", 
	           joinColumns = { @JoinColumn(name = "idUsuario") },
	           inverseJoinColumns = { @JoinColumn(name = "idRol") }
	)
	private List<RolModel> lstRoles;

}
