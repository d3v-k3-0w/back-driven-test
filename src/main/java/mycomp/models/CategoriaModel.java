package mycomp.models;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categoria")
public class CategoriaModel implements Serializable {
  
  
  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoria;

	private String titulo;

	private String descripcion;

	/* :: excluir los HashCode para evitar el LazyInitializationException cuando utilizo LAZY en lstPreguntas:: */
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	@JsonIgnore
	@EqualsAndHashCode.Exclude
	//LinkedHashSet mantiene el orden de inserccion de elementos
	private Set<ExamenModel> lstExamenes = new LinkedHashSet<>(); 

}
