package com.example.demo.controller;

import com.example.demo.exceptions.UsuarioNullException;
import com.example.demo.model.IMC;
import com.example.demo.model.Usuario;
import com.example.demo.service.IMCService;
import com.example.demo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/imcalculator")
public class IMCController {

    @Autowired
    private IMCService imcService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/imc")
    public String calcularIMC(Model model, HttpSession session) {
        Usuario usuario = usuarioService.getUsuarioActual(session);
        if (usuario == null) {
            return "redirect:/imcalculator/login";
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("imc", new IMC());
        List<IMC> historialIMC = imcService.obtenerHistorialIMC(usuario);
        model.addAttribute("historialIMC", historialIMC);
        return "imc";
    }

    @PostMapping("/imc")
    public String calcularIMC(@ModelAttribute IMC imc, Model model, HttpSession session, @RequestParam double estatura) {
        Usuario usuario = usuarioService.getUsuarioActual(session);
        if(usuario == null){
            throw new UsuarioNullException("Usuario No encontrado");
        }
        usuario.setEstatura(estatura);
        imc.setUsuario(usuario);
        imc.setFecha(LocalDateTime.now());
        double imcValor = imc.getMasaCorporal() / Math.pow(usuario.getEstatura(), 2);
        imc.setValorImc(imcValor);
        imcService.guardarIMC(imc);
        return "redirect:/imcalculator/imc";
    }
}