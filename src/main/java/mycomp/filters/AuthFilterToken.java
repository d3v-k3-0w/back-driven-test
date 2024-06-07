package mycomp.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mycomp.securitys.UserDetailsServiceImpl;
import mycomp.utils.JwtUtil;

/* :: interceptar las peticiones para validar el token del usuario hacia el servidor::*/
@Component
public class AuthFilterToken extends OncePerRequestFilter {

  @Autowired
  private UserDetailsServiceImpl usudtsrvcimpl;

  @Autowired
  private JwtUtil jwutil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

	 /*
	  * ::extraer el header Authorization: donde se encuentra el token enviado por el
	  * usuario, esta parte puede estar en la clase JwtUtil::
	  */
	 final String headerAuth = request.getHeader("Authorization");

	 String token = null;
	 String username = null;

	 /* ::extraer el token de la cabecera:: */
	 if (headerAuth != null && headerAuth.startsWith("Bearer ")) {

		token = headerAuth.substring(7); // extraer el token quitando el "Bearer "

		username = jwutil.extraerUsername(token); // buscar el username en el token
	 }

	 /* ::validar los valores extraidos del token y el contexto de seguridad:: */
	 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

		/* ::obtener el username de nuestra BD y poblamos el UserDetails:: */
		UserDetails userDetails = usudtsrvcimpl.loadUserByUsername(username);

		/*
		 * ::validar que el token este vigente y si concuerda con el usuario de la db::
		 */
		if (jwutil.validarToken(token, userDetails)) {

		  var userPassAuthToken = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());

		  /* ::generar los detalles de la autenticacion por token:: */
		  userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		  /* ::establecer el tipo de seguridad:: */
		  SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
		}
	 }

	 filterChain.doFilter(request, response);

  }

}
