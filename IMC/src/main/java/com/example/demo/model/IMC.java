package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "imc")
public class IMC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @NotNull
    @Column(name = "masa_corporal", nullable = false)
    private Double masaCorporal;

    @Column(name = "valor_imc", nullable = false)
    private Double valorImc;

    @Column(name = "fecha", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getMasaCorporal() {
        return masaCorporal;
    }

    public void setMasaCorporal(Double masaCorporal) {
        this.masaCorporal = masaCorporal;
    }

    public Double getValorImc() {
        return valorImc;
    }

    public void setValorImc(Double valorImc) {
        this.valorImc = valorImc;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}