package com.prolink.synccadastro.exception;

import java.io.IOException;

public class FileInvalidException extends IOException {
    public FileInvalidException(String message) {
        super(message);
    }
}
