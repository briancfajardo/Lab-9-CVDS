package com.login.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean  implements Serializable {
    private final UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioService usuarioService;
    private String user;
    private String password;

    public UsuarioBean(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String login() {
        Usuario usuario = usuarioRepository.findById(user);
        if (usuario == null || !usuario.getPassword().equals(password)) {
            FacesContext.getCurrentInstance().addMessage("@all", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos incorrectos, intente nuevamente.", null));
            return null;
        } else {
            return "welcome.xhtml";
        }
    }
    @Bean
    public CommandLineRunner currentUser() throws Exception{
        return args -> {
            usuarioService.addUsuario(new Usuario("admin", "xdsrewq"));
            usuarioService.addUsuario(new Usuario("cam", "1234"));
            usuarioService.getAllUsuario().forEach(System.out::println);
        };
    }
}
