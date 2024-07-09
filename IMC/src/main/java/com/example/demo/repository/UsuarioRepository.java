package com.example.demo.repository;



import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByNombreUsuario(String nombreUsuario);
    Usuario findByNombreUsuario(String nombreUsuario);
}