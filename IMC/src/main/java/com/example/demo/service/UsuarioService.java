package com.example.demo.service;


import com.example.demo.exceptions.UsuarioAlreadyExistsException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarUsuario(Usuario usuario) {

        if (usuarioRepository.existsByNombreUsuario(usuario.getNombreUsuario())) {
            throw new UsuarioAlreadyExistsException("El nombre de usuario ya existe");
        }
        if (usuario.getEdad() < 15) {
            throw new IllegalArgumentException("La edad debe ser mayor o igual a 15");
        }
        if (usuario.getEstatura() < 1 || usuario.getEstatura() > 2.5) {
            throw new IllegalArgumentException("La estatura debe estar entre 1 y 2.5 metros");
        }

        usuario.setContrasena(hashPassword(usuario.getContrasena()));

        return usuarioRepository.save(usuario);

    }
    public Usuario findByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (usuario != null && usuario.getContrasena().equals(hashPassword(contrasena))) {
            return usuario;
        }
        return null;
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public Usuario getUsuarioActual(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioActual");
    }
}