package com.example.demo.service;

import com.example.demo.model.IMC;
import com.example.demo.model.Usuario;
import com.example.demo.repository.IMCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IMCService {
    private final IMCRepository imcRepository;

    @Autowired
    public IMCService(IMCRepository imcRepository) {
        this.imcRepository = imcRepository;
    }

    public IMC guardarIMC(IMC imc) {
        return imcRepository.save(imc);
    }

    public List<IMC> obtenerHistorialIMC(Usuario usuario) {
        return imcRepository.findByUsuarioOrderByFechaDesc(usuario);
    }
    public boolean existsById(Long id){
        return imcRepository.existsById(id);
    }
}