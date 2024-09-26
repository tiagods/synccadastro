package com.prolink.synccadastro.services.workbook;

import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.model.ClienteComentario;
import com.prolink.synccadastro.util.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class AbstractWorkbookProcessor implements WorkbookProcessor {

    Logger logger = LoggerFactory.getLogger(getClass());

    protected Path filePathOrigin, folderDestination;
    protected String password;

    public AbstractWorkbookProcessor(Path filePathOrigin, Path folderDestination, String password) {
        this.filePathOrigin = filePathOrigin;
        this.folderDestination = folderDestination;
        this.password = password;
    }

    protected List<Cliente> readLines(Sheet sheet) {
        List<Cliente> lista = new ArrayList<>();

        Iterator<Row> linha = sheet.rowIterator();
        Map<Integer, String> colunas = new HashMap<>();
        int positionCodIndex = 0;

        while (linha.hasNext()) {
            Row row = linha.next();
            if (row == null) break;
            if (row.getRowNum() == 0) {
                positionCodIndex = checkColumnsPositions(row, colunas);
                continue;// pular a 1a linha
            }

            Cliente cli = getCliente(row, colunas, positionCodIndex);
            if(ObjectUtils.isEmpty(cli)) break;
            if (cli.getCOD() != 0) lista.add(cli);
        }
        return lista;
    }

    private Integer checkColumnsPositions(Row row, Map<Integer, String> colunas) {
        // pegar nome das colunas
        int codIndex = 0;
        Iterator<Cell> columns = row.cellIterator();
        while (columns.hasNext()) {
            Cell celula = columns.next();
            String valor = readingCell(celula).toUpperCase().trim().replace("-", "_").replace(" ", "_");
            colunas.put(celula.getColumnIndex(), valor);
            if (valor.toUpperCase().trim().equals("COD")){
                codIndex = celula.getColumnIndex();
            }
        }
        return codIndex;
    }

    private Cliente getCliente(Row row, Map<Integer, String> colunas, int positionCodigo) {

        Cliente cli = new Cliente();
        ClienteComentario com = new ClienteComentario();
        // iterando sobre as colunas da linha
        Iterator<Cell> columns = row.cellIterator();
        while (columns.hasNext()) {
            Cell celula = columns.next();
            if (row.getRowNum() != 0 && celula.getColumnIndex() == positionCodigo) {
                try {
                    int result = Integer.parseInt(readingCell(celula));// se nao conseguir o cast, o campo codigo
                    // estara em branco, nesse caso o for ira fechar
                    if (result == 0) {
                        break;
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }

            Comment comment = celula.getCellComment();
            String comentario = "";
            if (comment != null) {
                RichTextString rts = comment.getString();
                comentario = StringUtils.hasText(rts.getString()) ? rts.getString().trim() : "";
            }

            if (colunas.get(celula.getColumnIndex()) == null) continue;
            switch (colunas.get(celula.getColumnIndex())) {// retorna o numero das colunas
                case "COD":
                    int id = Integer.parseInt(readingCell(celula));
                    cli.setCOD(id);
                    com.setID(id);
                    com.setCOD(comentario);
                    com.setCliente(cli);
                    break;
                case "STATUS":
                    cli.setSTATUS(readingCell(celula));
                    com.setSTATUS(comentario);
                    break;
                case "COMPL_STS":
                    cli.setCOMPL_STS(readingCell(celula));
                    com.setCOMPL_STS(comentario);
                    break;
                case "ATENDIMENTO":
                    cli.setATENDIMENTO(readingCell(celula));
                    com.setATENDIMENTO(comentario);
                    break;
                case "PROCESSOS":
                    cli.setPROCESSOS(readingCell(celula));
                    com.setPROCESSOS(comentario);
                    break;
                case "SISTEMA":
                    cli.setSISTEMA(readingCell(celula));
                    com.setSISTEMA(comentario);
                    break;
                case "MP":
                    cli.setMP(readingCell(celula));
                    com.setMP(comentario);
                    break;
                case "TIPO":
                    cli.setTIPO(readingCell(celula));
                    com.setTIPO(comentario);
                    break;
                case "REGIME_TRIBUTARIO":
                    cli.setREGIME_TRIBUTARIO(readingCell(celula));
                    com.setREGIME_TRIBUTARIO(comentario);
                    break;
                case "QUANT_SOC_GER":
                    cli.setQUANT_SOC_GER(readingCell(celula));
                    com.setQUANT_SOC_GER(comentario);
                    break;
                case "EMPRESA":
                    cli.setEMPRESA(readingCell(celula));
                    com.setEMPRESA(comentario);
                    break;
                case "NOME":
                    cli.setNOME(readingCell(celula));
                    com.setNOME(comentario);
                    break;
                case "ENDERECO":
                    cli.setENDERECO(readingCell(celula));
                    com.setENDERECO(comentario);
                    break;
                case "BAIRRO":
                    cli.setBAIRRO(readingCell(celula));
                    com.setBAIRRO(comentario);
                    break;
                case "CIDADE":
                    cli.setCIDADE(readingCell(celula));
                    com.setCIDADE(comentario);
                    break;
                case "EST":
                    cli.setEST(readingCell(celula));
                    com.setEST(comentario);
                    break;
                case "CEP":
                    cli.setCEP(readingCell(celula));
                    com.setCEP(comentario);
                    break;
                case "CNPJ":
                    cli.setCNPJ(readingCell(celula));
                    com.setCNPJ(comentario);
                    break;
                case "DATA_CNPJ":
                    cli.setDATA_CNPJ(readingCell(celula));
                    com.setDATA_CNPJ(comentario);
                    break;
                case "IE":
                    cli.setIE(readingCell(celula));
                    com.setIE(comentario);
                    break;
                case "VLR_CAPITAL":
                    cli.setVLR_CAPITAL(readingCell(celula));
                    com.setVLR_CAPITAL(comentario);
                    break;
                case "VL_EXTENSO":
                    cli.setVL_EXTENSO(readingCell(celula));
                    com.setVL_EXTENSO(comentario);
                    break;
                case "VL_COTA":
                    cli.setVL_COTA(readingCell(celula));
                    com.setVL_COTA(comentario);
                    break;
                case "VL_EXT_COTA":
                    cli.setVL_EXT_COTA(readingCell(celula));
                    com.setVL_EXT_COTA(comentario);
                    break;
                case "COTAS_TOTAL":
                    cli.setCOTAS_TOTAL(readingCell(celula));
                    com.setCOTAS_TOTAL(comentario);
                    break;
                case "COTAS_EXTENSO":
                    cli.setCOTAS_EXTENSO(readingCell(celula));
                    com.setCOTAS_EXTENSO(comentario);
                    break;
                case "PERC_CAP_1":
                    cli.setPERC_CAP_1(readingCell(celula));
                    com.setPERC_CAP_1(comentario);
                    break;
                case "PERC_CAP_2":
                    cli.setPERC_CAP_2(readingCell(celula));
                    com.setPERC_CAP_2(comentario);
                    break;
                case "PERC_CAP_DEMAIS":
                    cli.setPERC_CAP_DEMAIS(readingCell(celula));
                    com.setPERC_CAP_DEMAIS(comentario);
                    break;
                case "SOMA_PERC_CAP":
                    cli.setSOMA_PERC_CAP(readingCell(celula));
                    com.setSOMA_PERC_CAP(comentario);
                    break;
                case "COTAS_01":
                    cli.setCOTAS_01(readingCell(celula));
                    com.setCOTAS_01(comentario);
                    break;
                case "COTAS_02":
                    cli.setCOTAS_02(readingCell(celula));
                    com.setCOTAS_02(comentario);
                    break;
                case "PREF":
                    cli.setPREF(readingCell(celula));
                    com.setPREF(comentario);
                    break;
                case "DATA_IM":
                    cli.setDATA_IM(readingCell(celula));
                    com.setDATA_IM(comentario);
                    break;
                case "ALIQUOTA_DE_ISS":
                    cli.setALIQUOTA_DE_ISS(readingCell(celula));
                    com.setALIQUOTA_DE_ISS(comentario);
                    break;
                case "ENC_MUNIC":
                    cli.setENC_MUNIC(readingCell(celula));
                    com.setENC_MUNIC(comentario);
                    break;
                case "DT_DISTRATO":
                    cli.setDT_DISTRATO(readingCell(celula));
                    com.setDT_DISTRATO(comentario);
                    break;
                case "NIRC":
                    cli.setNIRC(readingCell(celula));
                    com.setNIRC(comentario);
                    break;
                case "N_REG_CART_OU_NIRE":
                    cli.setN_REG_CART_OU_NIRE(readingCell(celula));
                    com.setN_REG_CART_OU_NIRE(comentario);
                    break;
                case "DATA_REG_CART_OU_JUCESP":
                    cli.setDATA_REG_CART_OU_JUCESP(readingCell(celula));
                    com.setDATA_REG_CART_OU_JUCESP(comentario);
                    break;
                case "SIND_CNPJ":
                    cli.setSIND_CNPJ(readingCell(celula));
                    com.setSIND_CNPJ(comentario);
                    break;
                case "ATIVIDADE":
                    cli.setATIVIDADE(readingCell(celula));
                    com.setATIVIDADE(comentario);
                    break;
                case "SINDICATO":
                    cli.setSINDICATO(readingCell(celula));
                    com.setSINDICATO(comentario);
                    break;
                case "CNAE1":
                    cli.setCNAE1(readingCell(celula));
                    com.setCNAE1(comentario);
                    break;
                case "COMP_CNAE1":
                    cli.setCOMP_CNAE1(readingCell(celula));
                    com.setCOMP_CNAE1(comentario);
                    break;
                case "DIG_CNAE1":
                    cli.setDIG_CNAE1(readingCell(celula));
                    com.setDIG_CNAE1(comentario);
                    break;
                case "CNAE":
                    cli.setCNAE(readingCell(celula));
                    com.setCNAE(comentario);
                    break;
                case "COMP_CNAE":
                    cli.setCOMP_CNAE(readingCell(celula));
                    com.setCOMP_CNAE(comentario);
                    break;
                case "DIG_CNAE":
                    cli.setDIG_CNAE(readingCell(celula));
                    com.setDIG_CNAE(comentario);
                    break;
                case "FPAS":
                    cli.setFPAS(readingCell(celula));
                    com.setFPAS(comentario);
                    break;
                case "QUANT_SOCIOS":
                    cli.setQUANT_SOCIOS(readingCell(celula));
                    com.setQUANT_SOCIOS(comentario);
                    break;
                case "CONSULTORIA":
                    cli.setCONSULTORIA(readingCell(celula));
                    com.setCONSULTORIA(comentario);
                    break;
                case "INDICADO":
                    cli.setINDICADO(readingCell(celula));
                    com.setINDICADO(comentario);
                    break;
                case "APELIDO_1":
                    cli.setAPELIDO_1(readingCell(celula));
                    com.setAPELIDO_1(comentario);
                    break;
                case "END_CORRESP":
                    cli.setEND_CORRESP(readingCell(celula));
                    com.setEND_CORRESP(comentario);
                    break;
                case "BAIRRO_CO":
                    cli.setBAIRRO_CO(readingCell(celula));
                    com.setBAIRRO_CO(comentario);
                    break;
                case "CID_CO":
                    cli.setCID_CO(readingCell(celula));
                    com.setCID_CO(comentario);
                    break;
                case "EST_CO":
                    cli.setEST_CO(readingCell(celula));
                    com.setEST_CO(comentario);
                    break;
                case "CEP_CO":
                    cli.setCEP_CO(readingCell(celula));
                    com.setCEP_CO(comentario);
                    break;
                case "NOME_SOCIO1":
                    cli.setNOME_SOCIO1(readingCell(celula));
                    com.setNOME_SOCIO1(comentario);
                    break;
                case "QUALIDADE1":
                    cli.setQUALIDADE1(readingCell(celula));
                    com.setQUALIDADE1(comentario);
                    break;
                case "NAC_01":
                    cli.setNAC_01(readingCell(celula));
                    com.setNAC_01(comentario);
                    break;
                case "NATURALIDADE_1":
                    cli.setNATURALIDADE_1(readingCell(celula));
                    com.setNATURALIDADE_1(comentario);
                    break;
                case "EST_CIVEL_01":
                    cli.setEST_CIVEL_01(readingCell(celula));
                    com.setEST_CIVEL_01(comentario);
                    break;
                case "MAIOR_EMANCIPADO_1":
                    cli.setMAIOR_EMANCIPADO_1(readingCell(celula));
                    com.setMAIOR_EMANCIPADO_1(comentario);
                    break;
                case "PROF_01":
                    cli.setPROF_01(readingCell(celula));
                    com.setPROF_01(comentario);
                    break;
                case "DIA_NASC1":
                    cli.setDIA_NASC1(readingCell(celula));
                    com.setDIA_NASC1(comentario);
                    break;
                case "MES_NASC1":
                    cli.setMES_NASC1(readingCell(celula));
                    com.setMES_NASC1(comentario);
                    break;
                case "ANO_NASC1":
                    cli.setANO_NASC1(readingCell(celula));
                    com.setANO_NASC1(comentario);
                    break;
                case "PIS_SOC1":
                    cli.setPIS_SOC1(readingCell(celula));
                    com.setPIS_SOC1(comentario);
                    break;
                case "END_SOC_1":
                    cli.setEND_SOC_1(readingCell(celula));
                    com.setEND_SOC_1(comentario);
                    break;
                case "BAIRRO1":
                    cli.setBAIRRO1(readingCell(celula));
                    com.setBAIRRO1(comentario);
                    break;
                case "CIDADE1":
                    cli.setCIDADE1(readingCell(celula));
                    com.setCIDADE1(comentario);
                    break;
                case "EST1":
                    cli.setEST1(readingCell(celula));
                    com.setEST1(comentario);
                    break;
                case "CEP1":
                    cli.setCEP1(readingCell(celula));
                    com.setCEP1(comentario);
                    break;
                case "RG1":
                    cli.setRG1(readingCell(celula));
                    com.setRG1(comentario);
                    break;
                case "ORGAO1":
                    cli.setORGAO1(readingCell(celula));
                    com.setORGAO1(comentario);
                    break;
                case "ESTD1":
                    cli.setESTD1(readingCell(celula));
                    com.setESTD1(comentario);
                    break;
                case "DATA_EXP1":
                    cli.setDATA_EXP1(readingCell(celula));
                    com.setDATA_EXP1(comentario);
                    break;
                case "CPF1":
                    cli.setCPF1(readingCell(celula));
                    com.setCPF1(comentario);
                    break;
                case "EMAIL_SOC_1":
                    cli.setEMAIL_SOC_1(readingCell(celula));
                    com.setEMAIL_SOC_1(comentario);
                    break;
                case "EMAIL_FINANCEIRO":
                    cli.setEMAIL_FINANCEIRO(readingCell(celula));
                    com.setEMAIL_FINANCEIRO(comentario);
                    break;
                case "DDD1":
                    cli.setDDD1(readingCell(celula));
                    com.setDDD1(comentario);
                    break;
                case "FONERES1":
                    cli.setFONERES1(readingCell(celula));
                    com.setFONERES1(comentario);
                    break;
                case "RES_RAMAL_1":
                    cli.setRES_RAMAL_1(readingCell(celula));
                    com.setRES_RAMAL_1(comentario);
                    break;
                case "DDD1COML":
                    cli.setDDD1COML(readingCell(celula));
                    com.setDDD1COML(comentario);
                    break;
                case "FONECOML1":
                    cli.setFONECOML1(readingCell(celula));
                    com.setFONECOML1(comentario);
                    break;
                case "COML_RAMAL_1":
                    cli.setCOML_RAMAL_1(readingCell(celula));
                    com.setCOML_RAMAL_1(comentario);
                    break;
                case "DDD1CEL":
                    cli.setDDD1CEL(readingCell(celula));
                    com.setDDD1CEL(comentario);
                    break;
                case "CELULAR":
                    cli.setCELULAR(readingCell(celula));
                    com.setCELULAR(comentario);
                    break;
                case "DDD1REC":
                    cli.setDDD1REC(readingCell(celula));
                    com.setDDD1REC(comentario);
                    break;
                case "FONEREC1":
                    cli.setFONEREC1(readingCell(celula));
                    com.setFONEREC1(comentario);
                    break;
                case "OBSFONE_REC1":
                    cli.setOBSFONE_REC1(readingCell(celula));
                    com.setOBSFONE_REC1(comentario);
                    break;
                case "NOME_SOC_2":
                    cli.setNOME_SOC_2(readingCell(celula));
                    com.setNOME_SOC_2(comentario);
                    break;
                case "QUALIDADE2":
                    cli.setQUALIDADE2(readingCell(celula));
                    com.setQUALIDADE2(comentario);
                    break;
                case "NAC_02":
                    cli.setNAC_02(readingCell(celula));
                    com.setNAC_02(comentario);
                    break;
                case "NATURALIDADE_2":
                    cli.setNATURALIDADE_2(readingCell(celula));
                    com.setNATURALIDADE_2(comentario);
                    break;
                case "EST_CIVEL_02":
                    cli.setEST_CIVEL_02(readingCell(celula));
                    com.setEST_CIVEL_02(comentario);
                    break;
                case "MAIOR_EMANCIPADO_2":
                    cli.setMAIOR_EMANCIPADO_2(readingCell(celula));
                    com.setMAIOR_EMANCIPADO_2(comentario);
                    break;
                case "PROF_02":
                    cli.setPROF_02(readingCell(celula));
                    com.setPROF_02(comentario);
                    break;
                case "END_SOC_2":
                    cli.setEND_SOC_2(readingCell(celula));
                    com.setEND_SOC_2(comentario);
                    break;
                case "BAIRRO2":
                    cli.setBAIRRO2(readingCell(celula));
                    com.setBAIRRO2(comentario);
                    break;
                case "CIDADE2":
                    cli.setCIDADE2(readingCell(celula));
                    com.setCIDADE2(comentario);
                    break;
                case "EST2":
                    cli.setEST2(readingCell(celula));
                    com.setEST2(comentario);
                    break;
                case "CEP2":
                    cli.setCEP2(readingCell(celula));
                    com.setCEP2(comentario);
                    break;
                case "DIA_NASC2":
                    cli.setDIA_NASC2(readingCell(celula));
                    com.setDIA_NASC2(comentario);
                    break;
                case "MES_NASC2":
                    cli.setMES_NASC2(readingCell(celula));
                    com.setMES_NASC2(comentario);
                    break;
                case "ANO_NASC2":
                    cli.setANO_NASC2(readingCell(celula));
                    com.setANO_NASC2(comentario);
                    break;
                case "PIS_SOC2":
                    cli.setPIS_SOC2(readingCell(celula));
                    com.setPIS_SOC2(comentario);
                    break;
                case "RG2":
                    cli.setRG2(readingCell(celula));
                    com.setRG2(comentario);
                    break;
                case "ORGAO2":
                    cli.setORGAO2(readingCell(celula));
                    com.setORGAO2(comentario);
                    break;
                case "ESTD2":
                    cli.setESTD2(readingCell(celula));
                    com.setESTD2(comentario);
                    break;
                case "DATA_EXP2":
                    cli.setDATA_EXP2(readingCell(celula));
                    com.setDATA_EXP2(comentario);
                    break;
                case "CPF2":
                    cli.setCPF2(readingCell(celula));
                    com.setCPF2(comentario);
                    break;
                case "DDD2RES":
                    cli.setDDD2RES(readingCell(celula));
                    com.setDDD2RES(comentario);
                    break;
                case "FONERES2":
                    cli.setFONERES2(readingCell(celula));
                    com.setFONERES2(comentario);
                    break;
                case "DDD2COML":
                    cli.setDDD2COML(readingCell(celula));
                    com.setDDD2COML(comentario);
                    break;
                case "FONECOM2":
                    cli.setFONECOM2(readingCell(celula));
                    com.setFONECOM2(comentario);
                    break;
                case "DDD2CEL":
                    cli.setDDD2CEL(readingCell(celula));
                    com.setDDD2CEL(comentario);
                    break;
                case "CELULAR2":
                    cli.setCELULAR2(readingCell(celula));
                    com.setCELULAR2(comentario);
                    break;
                case "OBS":
                    cli.setOBS(readingCell(celula));
                    com.setOBS(comentario);
                    break;
                default:
                    break;
            }
        }
        cli.setComentario(com);
        return cli;
    }

    private String readingCell(Cell celula) { // metodo usado para tratar as celulas
        switch (celula.getCellTypeEnum()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(celula))
                    return new SimpleDateFormat("dd/MM/yyyy").format(celula.getDateCellValue());// campo do tipo data
                    // formatando no ato
                else {
                    return String.valueOf((long) celula.getNumericCellValue());// campo do tipo numerico, convertendo double
                    // para long
                }
            case STRING:
                return String.valueOf(celula.getStringCellValue()).trim();// conteudo do tipo texto
            case BOOLEAN:
                return "";
            case BLANK:
                return "";
            default:
                return "";
        }

    }

    public Optional<Path> createTemporaryWorkbook() throws IOException {
        if (!Files.exists(folderDestination)) {
            logger.info("Criando diretorio: {}", folderDestination);
            Files.createDirectory(folderDestination);
        }

        String valor = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
        String extension = FileUtils.getFileExtension(filePathOrigin);
        Path fileDestination = folderDestination.resolve("Cadastro"+valor+(StringUtils.hasText(extension) ? "." + extension : ""));
        Files.copy(filePathOrigin, fileDestination, StandardCopyOption.REPLACE_EXISTING);
        logger.info("Criando planilha: " + fileDestination);
        return Optional.of(fileDestination);
    }

    // deletar arquivo temporario
    @Override
    public void deleteTemporaryWorkbook() {
        logger.info("Removendo arquivos temporarios: " + folderDestination);
        try {
            Files.walk(folderDestination)
                    .filter( p -> p.getFileName().toString().matches("^Cadastro\\w{14}\\.\\w{3,4}"))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

}
