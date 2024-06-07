package mycomp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* ::clase para poder crear y manipular los tokens JWT == (header, payload, signature):: */
@Component
public class JwtUtil {

  @Value("${token.secret.magic}")
  private String SECRETO;

  /*
   * ::extraer una parte del token [header.payload.signature] siendo los claims
   * registrados(iss, sub, aude, exp)::
   */
  public Claims extraerContenidoClaims(String token) {

	 /*
	  * ::parser convierte a String, establecemos la clave secreta para determinar si
	  * el JWT es valido dentro del header y retornamos el conjunto de Claims::
	  */
	 return Jwts.parser().setSigningKey(SECRETO).parseClaimsJws(token).getBody();
  }

  /*
   * Extraer del claim el nombre de usuario del token
   */
  public String extraerUsername(String token) {

	 return extraerContenidoClaims(token).getSubject();
  }

  /*
   * ::extraer del claim el contenido de "exp" para identificar el tiempo de
   * vencimiento del token::
   */
  public Date extraerTiempoVencimiento(String token) {

	 return extraerContenidoClaims(token).getExpiration();
  }

  /*
   * ::verificar si el token ha expirado y determina si se acepta procesarlo
   * actual::
   */
  public boolean isTokenExpiration(String token) {

	 return extraerTiempoVencimiento(token).before(new Date());
  }

  /*
   * ::preparar la estructura del payload del token con los
   * claims(sub,issu,exp,etc) y firma especificados poblandolo con el
   * contenido, setSubject: establece el nombre del usuario al payload,
   * setIssuedAt: establece el tiempo en que se crea, setExpiration: tiempo que va
   * a durar el token, signWith: Cifra la palabra secreta con el algoritmo que se
   * especifica::
   */
  public String prepararEstructuraToken(Map<String, Object> payload, String subject) {

	 return Jwts.builder()
		  .setClaims(payload)
		  .setSubject(subject)
		  .setIssuedAt(new Date(System.currentTimeMillis()))
		  .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
		  .signWith(SignatureAlgorithm.HS256, SECRETO).
		  compact(); 
  }

  /*
   * ::crear el token por medio del UserDetails autenticado e invocar al metodo
   * que construye la estructura(payload)::
   */
  public String createToken(UserDetails userDetails) {

	 Map<String, Object> claims = new HashMap<>();

	 return prepararEstructuraToken(claims, userDetails.getUsername());
  }

  /* ::validar si el token es valido para el usuario autenticado:: */
  public boolean validarToken(String token, UserDetails userDetails) {

	 final String username = extraerUsername(token);

	 return (username.equals(userDetails.getUsername()) && !isTokenExpiration(token));
  }

}
