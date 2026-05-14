package com.game.GridGame.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class JogadorEntity {
    @Id
    @UuidGenerator
    private String id;
    private String nome;
    private int vida;
    private String cor;

    private double x;
    private double y;

    private double velocityX;

    private double gravity;

    private int quantiaPulo;

    private boolean empuxo;
    private boolean pulo;

    public JogadorEntity(String nome) {
        this.nome = nome;
        this.vida = 3;
        this.cor = "#002366";
        this.x = 0 * 39;
        this.y = 0 * 39;
        this.velocityX = 1.5;
        this.gravity = 0;
        this.quantiaPulo = 15; // TETO
        this.pulo = false;
        this.empuxo = true;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public void setPulo(boolean pulo) {
        this.pulo = pulo;
    }

    public void setQuantiaPulo(int quantiaPulo) {
        this.quantiaPulo = quantiaPulo;
    }

    public void setEmpuxo(boolean empuxo) {
        this.empuxo = empuxo;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public String getCor() {
        return cor;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getGravity() {
        return gravity;
    }

    public boolean getPulo() {
        return pulo;
    }

    public int getQuantiaPulo() {
        return quantiaPulo;
    }

    public boolean getEmpuxo() {
        return empuxo;
    }
}