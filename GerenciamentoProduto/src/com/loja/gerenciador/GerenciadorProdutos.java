package com.loja.gerenciador;

import com.loja.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {

    private List<Produto> produtos;

    private int proximoId;

    public GerenciadorProdutos() {
        this.produtos = new ArrayList<>();
    }


}
