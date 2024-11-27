package com.loja.gerenciador;

import com.loja.exception.ProdutoException;
import com.loja.exception.ValidacaoException;
import com.loja.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorProdutos {

    private List<Produto> produtos;

    private int proximoId;

    public GerenciadorProdutos() {

        this.produtos = new ArrayList<>();
        this.proximoId = 1;
    }

    public void criar(Produto produto) {
        try {
            validarProduto(produto);
            produto.setId(proximoId++);
            produtos.add(produto);
        } catch (ValidacaoException e) {
            throw e;
        } catch (Exception e) {
            throw new ProdutoException("Erro ao criar produto: " + e.getMessage(), e);
        }
    }

    public Produto buscarPorId(int id) {
        try {
            for (Produto produto1 : produtos) {
                if (produto1.getId() == id) {
                    return produto1;
                }
            }
            return null;
        } catch (Exception e) {
            throw new ProdutoException("Erro ao buscar produto: " + e.getMessage(), e);
        }

    }

    public List<Produto> listarTodos() {
        try {
            return new ArrayList<>(produtos);
        } catch (Exception e) {
            throw new ProdutoException("Erro ao listar produtos", e);
        }
    }

    public boolean atualizar(Produto produto) {
        try {
            validarProduto(produto);
            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getId().equals(produto.getId())) {
                    produtos.set(i, produto);
                    return true;
                }
            }
            return false;
        } catch (ValidacaoException e) {
            throw e;
        } catch (Exception e) {
            throw new ProdutoException("Erro ao atualizar produto: " + e.getMessage(), e);
        }
    }

    public boolean deletar(int id) {
        try {
            return produtos.removeIf(usuario -> usuario.getId() == id);
        } catch (Exception e) {
            throw new ProdutoException("Erro ao deletar produto: " + e.getMessage() + e);
        }
    }

    public List<Produto> buscarPorNome(String nome) {
        try {
            List<Produto> produto = new ArrayList<>();
            for (Produto produto1 : produtos) {
                if (produto1.getNome().equalsIgnoreCase(nome)) {
                    produto.add(produto1);
                }

            }
            return produto;
        } catch (Exception e) {
            throw new ProdutoException("Erro ao buscar produto: " + e.getMessage() + e);
        }
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        try {
            if (categoria == null || categoria.trim().isEmpty()) {
                throw new ValidacaoException("Categoria de busca não pode ser vazia");
            }
            List<Produto> produtosCategoria = new ArrayList<>();
            for (Produto produto : produtos) {
                if (produto.getCategoria().equalsIgnoreCase(categoria)) {
                    produtosCategoria.add(produto);
                }
            }
            return produtosCategoria;
        } catch (Exception e) {
            throw new ProdutoException("Erro ao buscar produtos por categoria: " + e.getMessage(), e);
        }
    }

    public void validarProduto(Produto produto) {
        if (produto == null) {
            throw new ValidacaoException("Produto não pode ser nulo");
        }
        if (produto.getNome() == null || produto.getNome().trim().length() < 2) {
            throw new ValidacaoException("Nome insuficiente! O Nome do produto precisa ter ao menos 2 caracteres");
        }
        if (produto.getPreco() <= 0) {
            throw new ValidacaoException("Preço insuficicente! O Preço do produto deve ser maior que 0");
        }
        if (produto.getQuantidadeEstoque() < 0) {
            throw new ValidacaoException("Quantidade em estoque não pode ser negativa");
        }
        if (produto.getCategoria() == null || produto.getCategoria().trim().isEmpty()) {
            throw new ValidacaoException("Categoria do produto não pode ser vazia");
        }
    }
}
