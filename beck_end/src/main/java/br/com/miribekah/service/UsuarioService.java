package br.com.miribekah.service;

import br.com.miribekah.model.Usuario;
import br.com.miribekah.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public Usuario adicionar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
}
