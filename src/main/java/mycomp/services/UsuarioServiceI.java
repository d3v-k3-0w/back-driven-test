package mycomp.services;

import mycomp.models.UsuarioModel;

public interface UsuarioServiceI {

  public UsuarioModel saveUsuario(UsuarioModel usuario);

  public UsuarioModel obteinUsername(String username);

  public void deleteUsuario(Long idUsu);

  public long countUsuarios();

}
