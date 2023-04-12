package com.login.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario addUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public Usuario getUsuario (String usuarioId){
        return usuarioRepository.findById(usuarioId);
    }
    public List<Usuario> getAllUsuario(){
        return usuarioRepository.findAll();
    }
    public Usuario updateUsuario(Usuario usuario){
        if(usuarioRepository.existsById(usuario.getId())){
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void deleteUsuario(Long usuarioId){
        usuarioRepository.deleteById(usuarioId);
    }
}
