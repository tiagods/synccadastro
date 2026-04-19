package com.prolink.synccadastro.services.workbook;

import com.prolink.synccadastro.model.Cliente;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface WorkbookProcessor {
    List<Cliente> readWorkbook(Path filePath);
    Optional<Path> createTemporaryWorkbook() throws IOException;
    void deleteTemporaryWorkbook();
}
