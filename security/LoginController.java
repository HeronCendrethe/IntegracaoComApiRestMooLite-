package br.com.alura.mvc.mudi.security;

import javax.validation.Valid;

import br.com.alura.mvc.mudi.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService service;

    @PostMapping
    public String autentica(@RequestBody@Valid LoginForm loginForm) {



        UsernamePasswordAuthenticationToken dadosLogin = loginForm.login();

        Authentication auth = authManager.authenticate(dadosLogin);
        String token = service.buildToken(auth);
             return token;




    }
}
