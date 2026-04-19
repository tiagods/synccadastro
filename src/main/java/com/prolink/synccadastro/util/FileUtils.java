package com.prolink.synccadastro.util;

import java.nio.file.Path;

public class FileUtils {

    public static String getFileExtension(Path filePath) {
        int pos = filePath.getFileName().toString().lastIndexOf(".");
        return filePath.getFileName().toString().substring(pos + 1);
    }
}
