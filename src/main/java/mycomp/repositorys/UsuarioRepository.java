package mycomp.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mycomp.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

  public UsuarioModel findByUsername(String username);

  /*
   * ::genera una consulta personalizada que permita obtener el registro de un
   * Usuario atraves de su correo::
   */
  @Query("SELECT u FROM UsuarioModel u WHERE u.correo = ?1")
  Optional<UsuarioModel> findUsuarioByCorreo(String correo);

}
