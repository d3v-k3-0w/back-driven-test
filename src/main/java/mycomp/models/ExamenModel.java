package mycomp.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "examen")
public class ExamenModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idExamen;

  private String titulo;
  private String descripcion;
  private String puntosMax;
  private String numPreguntas;
  private boolean isActivo = false;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "idCategoria")
  private CategoriaModel categoria;

  @OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnore
  @EqualsAndHashCode.Exclude
  private Set<PreguntaModel> lstPreguntas = new HashSet<>();
}
