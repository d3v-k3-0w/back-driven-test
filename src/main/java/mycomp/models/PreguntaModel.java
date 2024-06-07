package mycomp.models;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pregunta")
public class PreguntaModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPregunta;

  @Column(length = 5000)
  private String contenido;
  private String imagen;
  private String opcion1;
  private String opcion2;
  private String opcion3;
  private String opcion4;
  @Transient
  private String respuestaDada; // con transient el campo respuestaDada no se agrega a la tabla
  private String respuesta;

  @ManyToOne(fetch = FetchType.EAGER) // EAGER de manera ansiosa me cargara los idExamen asociados
  @JoinColumn(name = "idExamen")
  private ExamenModel examen;

}
