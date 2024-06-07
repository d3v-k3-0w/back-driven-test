package mycomp.payloads;

import java.io.Serializable;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseToken implements Serializable {

  private static final long serialVersionUID = 1L;

  private String token;

}
