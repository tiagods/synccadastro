package com.prolink.synccadastro.services.workbook;

import com.prolink.synccadastro.model.Cliente;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.List;

public class FileXlsxService extends AbstractWorkbookProcessor {

    public FileXlsxService(Path filePathOrigin, Path folderDestination, String password) {
        super(filePathOrigin, folderDestination, password);
    }

    @Override
    public List<Cliente> readWorkbook(Path filePath) {
        XSSFWorkbook workbook = null;
        final long start = System.currentTimeMillis();
        try {
            logger.info("Lendo planilha temporaria: " + filePath);
            POIFSFileSystem fs = new POIFSFileSystem(filePath.toFile());
            EncryptionInfo info = new EncryptionInfo(fs);
            Decryptor decryptor = Decryptor.getInstance(info);
            //Verifying the password
            if (!decryptor.verifyPassword(password)) {
                throw new RuntimeException("Incorrect password: Unable to process");
            }
            InputStream dataStream = decryptor.getDataStream(fs);
            // Now parse dataStream
            workbook = new XSSFWorkbook(dataStream);

            XSSFSheet sheet = workbook.getSheetAt(0);

            return readLines(sheet);
        } catch (GeneralSecurityException | IOException ex) {
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
