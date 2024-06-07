package mycomp.exceptions;

public class UsuarioExistException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UsuarioExistException(String message) {
	 super(message);
  }

}
