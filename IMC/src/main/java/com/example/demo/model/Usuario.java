package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "nombre_usuario", length = 50, nullable = false)
    private String nombreUsuario;
    @NotNull
    @Column(name = "contrasena", length = 60, nullable = false)
    private String contrasena;
    @NotNull
    @Column(name = "edad", nullable = false)
    private Integer edad;
    @NotNull
    @Column(name = "estatura", nullable = false)
    private Double estatura;
    @NotNull
    @Column(name = "nombre", length = 255, nullable = false)
    private String nombre;
    @NotNull
    @Column(name = "apellido", length = 255, nullable = false)
    private String apellido;
    @NotNull
    @Column(name = "sexo", length = 1, nullable = false)
    private String sexo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getEstatura() {
        return estatura;
    }

    public void setEstatura(Double estatura) {
        this.estatura = estatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}