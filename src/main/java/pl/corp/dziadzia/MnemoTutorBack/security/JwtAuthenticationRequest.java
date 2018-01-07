package pl.corp.dziadzia.MnemoTutorBack.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtAuthenticationRequest implements Serializable{

    private String username;
    private String password;

}
