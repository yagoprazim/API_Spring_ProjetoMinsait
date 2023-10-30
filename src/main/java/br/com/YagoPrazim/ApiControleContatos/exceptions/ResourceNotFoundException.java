package br.com.YagoPrazim.ApiControleContatos.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}
