package br.com.alura.mvc.mudi.dto;

import io.micrometer.core.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    @Nullable
    private String username;
    @Nullable
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public UsernamePasswordAuthenticationToken login() {
        return new UsernamePasswordAuthenticationToken(getPassword(), getUsername());

    }


}
