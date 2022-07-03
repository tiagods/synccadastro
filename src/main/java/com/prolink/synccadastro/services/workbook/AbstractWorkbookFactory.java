package com.prolink.synccadastro.services.workbook;

import com.prolink.synccadastro.exception.FileInvalidException;
import com.prolink.synccadastro.util.FileUtils;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AbstractWorkbookFactory {
    enum Extension {
        XLS, XLSX;
    }

    public WorkbookProcessor getWorkbookService(Path filePathOrigin, Path folderDestiny, String password) throws IOException {
        if(!Files.exists(filePathOrigin)) {
            throw new FileNotFoundException("File not found");
        }

        String extension = FileUtils.getFileExtension(filePathOrigin).toUpperCase();
        Extension enumExtension = Extension.valueOf(extension);

        switch (enumExtension) {
            case XLS:
                return new FileXlsService(filePathOrigin, folderDestiny, password);
            case XLSX:
                return new FileXlsxService(filePathOrigin, folderDestiny, password);
            default:
                throw new FileInvalidException("File is invalid");
        }
    }
}
