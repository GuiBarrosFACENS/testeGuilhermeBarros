package br.teste.SimplesDealGui.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException{
    public DataIntegrityViolationException(String msg){
        super(msg);
    }
}
