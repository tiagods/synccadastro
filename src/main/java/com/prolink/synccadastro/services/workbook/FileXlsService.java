package com.prolink.synccadastro.services.workbook;

import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.model.ClienteComentario;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileXlsService extends AbstractWorkbookProcessor {

    Logger logger = LoggerFactory.getLogger(getClass());

    public FileXlsService(Path filePathOrigin, Path folderDestiny, String password) {
        super(filePathOrigin, folderDestiny, password);
    }

    @Override
    public List<Cliente> readWorkbook(Path filePath) {
        HSSFWorkbook workbook = null;
        final long start = System.currentTimeMillis();
        try {
            logger.info("Lendo planilha temporaria: " + filePath);
            // informando a senha de descriptografia
            if (StringUtils.hasText(password)) {
                Biff8EncryptionKey.setCurrentUserPassword(password);
            }
            POIFSFileSystem fs = new POIFSFileSystem(filePath.toFile(), true);
            workbook = new HSSFWorkbook(fs.getRoot(), true);
            // removendo senha para leitura
            Biff8EncryptionKey.setCurrentUserPassword(null);

            HSSFSheet sheet = workbook.getSheetAt(0);
            // pegando linhas e jogando no iterator
            return readLines(sheet);
        } catch (IOException ex) {
            logger.error("Erro ao ler documento: " + ex.getMessage());
            return null;
        } finally {
            logger.info("Tempo de Leitura: {}", System.currentTimeMillis() - start);
            try {
                if (workbook != null)
                    workbook.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
