package com.example.labdesenvolvimento.controleestoque;

public class Estoque {
    private int _ID;
    private String nome_produto;
    private int quantidade;
    private int preco_unitario;

    public Estoque() {
    }

    public Estoque(String nome_produto, int quantidade, int preco_unitario) {
        this.nome_produto = nome_produto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(int preco_unitario) {
        this.preco_unitario = preco_unitario;
    }
}

