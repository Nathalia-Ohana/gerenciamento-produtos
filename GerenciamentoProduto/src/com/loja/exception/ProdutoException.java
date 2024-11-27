package com.loja.exception;

public class ProdutoException extends RuntimeException{
    public ProdutoException(String message) {
        super(message);
    }

    public ProdutoException(String message, Throwable cause) {
        super(message, cause);
    }
}
