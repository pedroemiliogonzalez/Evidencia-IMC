package com.example.demo.repository;

import com.example.demo.model.IMC;
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMCRepository extends JpaRepository<IMC, Long> {
    boolean existsById(Long id);
    List<IMC> findByUsuarioOrderByFechaDesc(Usuario usuario);
}
