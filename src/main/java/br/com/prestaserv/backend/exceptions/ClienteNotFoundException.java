package br.com.prestaserv.backend.exceptions;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("Cliente " + id + " não encontrado!");
    }
}