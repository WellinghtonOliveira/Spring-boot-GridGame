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
    private double velocityY;

    private long gravity;
    private long lastMoveTime;

    private boolean pulo;

    public JogadorEntity(String nome) {
        this.nome = nome;
        this.vida = 3;
        this.cor = "whitesmoke";
        this.x = 4 * 40;
        this.y = 0 * 40;
        this.velocityX = 3;
        this.velocityY = 0.8;
        this.gravity = 10;
        this.lastMoveTime = 0;
        this.pulo = false;
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

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public void setGravity(long gravity) {
        this.gravity = gravity;
    }

    public void setLastMoveTime(long lastMoveTime) {
        this.lastMoveTime = lastMoveTime;
    }

    public void setPulo(boolean pulo) {
        this.pulo = pulo;
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

    public double getVelocityY() {
        return velocityY;
    }

    public long getGravity() {
        return gravity;
    }

    public long getLastMoveTime() {
        return lastMoveTime;
    }

    public boolean getPulo() {
        return pulo;
    }
}