package com.afd.exception;

public class InvalidFileException extends Exception{
    public InvalidFileException() {
        super("Arquivo Inválido!");
    }
}
