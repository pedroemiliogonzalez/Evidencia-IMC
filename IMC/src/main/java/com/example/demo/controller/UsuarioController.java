package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/imcalculator")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String nombreUsuario, @RequestParam String contrasena, Model model, HttpSession session) {
        Usuario usuario = usuarioService.iniciarSesion(nombreUsuario, contrasena);
        if (usuario != null) {
            session.setAttribute("usuarioActual", usuario);
            model.addAttribute("usuario", usuario);
            return "redirect:/imcalculator/imc";
        } else {
            model.addAttribute("loginerror", true);
            return "login";
        }
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }
    @PostMapping("/registro")
    public String registro(@ModelAttribute Usuario usuario, Model model) {
        if (usuarioService.existsByNombreUsuario(usuario.getNombreUsuario())) {
            model.addAttribute("error", "El nombre de usuario ya existe");
            return "registro";
        } else if (usuario.getEdad() < 15) {
            model.addAttribute("error", "La edad debe ser mayor o igual a 15");
            return "registro";
        } else if (usuario.getEstatura() < 1 || usuario.getEstatura() > 2.5) {
            model.addAttribute("error", "La estatura debe estar entre 1 y 2.5 metros");
            return "registro";
        } else {
            Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);
            return "redirect:/imcalculator/login";
        }
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/imcalculator/login";
    }
}