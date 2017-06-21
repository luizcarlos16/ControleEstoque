package com.example.labdesenvolvimento.controleestoque;

/**
 * Created by LT on 15/06/2017.
 */

public class Clube {
    private long ID;
    private String nome;

    public Clube() {
    }

    public Clube(long ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
