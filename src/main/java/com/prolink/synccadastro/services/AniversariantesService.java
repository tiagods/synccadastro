package com.prolink.synccadastro.services;

import com.prolink.synccadastro.model.Aniversariante;
import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.util.LocalDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AniversariantesService {

    @Value("${aniversariantes.exclude}")
    private List<String> filtroStatus;
    @Autowired
    private ClientesServices clientes;
    public List<Aniversariante> getAniversariantes() {
        List<LocalDate> lista = LocalDateConverter.GetDaysOfWeek();
        List<Aniversariante> aniversariantes = Stream.concat(
                        processarAniversariantes(Aniversariante.Socio.SOCIO1, clientes.listarAniversariantes(lista, filtroStatus, "DIA_NASC1", "MES_NASC1")),
                        processarAniversariantes(Aniversariante.Socio.SOCIO2, clientes.listarAniversariantes(lista, filtroStatus, "DIA_NASC2", "MES_NASC2")))
                .collect(Collectors.toList());
        Comparator<Aniversariante> comparator = Comparator.comparing(Aniversariante::getData);
        Collections.sort(aniversariantes, comparator.thenComparing(c -> c.getId()).thenComparing(c -> c.getStatus()));
        return aniversariantes;
    }

    private Stream<Aniversariante> processarAniversariantes(Aniversariante.Socio socio, List<Cliente> clientes) {
        return clientes.stream()
                .map(c -> {
                    Aniversariante aniversariante = new Aniversariante();
                    aniversariante.setId(String.valueOf(c.getCOD()));
                    aniversariante.setStatus(c.getSTATUS());
                    aniversariante.setEmpresa(c.getEMPRESA());
                    aniversariante.setTipoSocio(socio.getValor());
                    aniversariante.setEmail(c.getEMAIL_SOC_1());
                    LocalDate localDate;
                    if (socio.equals(Aniversariante.Socio.SOCIO1)) {
                        localDate = recuperarData(c.getDIA_NASC1(), c.getMES_NASC1());
                        aniversariante.setNome(c.getNOME_SOCIO1());
                        aniversariante.setTelefone(montarTelefone1(c));
                    } else {
                        localDate = recuperarData(c.getDIA_NASC2(), c.getMES_NASC2());
                        aniversariante.setNome(c.getNOME_SOC_2());
                        aniversariante.setTelefone(montarTelefone2(c));
                    }
                    aniversariante.setAniversario(LocalDateConverter.formatMD(localDate));
                    aniversariante.setData(localDate);
                    return aniversariante;
                });
    }

    public LocalDate recuperarData(String dia, String mes) {
        int diaR = Integer.parseInt(dia);
        int mesR = Integer.parseInt(LocalDateConverter.ConvertMonth(mes));
        return LocalDate.of(00, mesR, diaR);
    }

    private String montarTelefone1(Cliente c) {
        String listaFones = "";
        if (c.getFONECOML1().trim().equals("") && !c.getCELULAR().trim().equals(""))
            listaFones = c.getDDD1CEL() + " " + c.getCELULAR();
        else if (!c.getFONECOML1().trim().equals("") && c.getCELULAR().trim().equals(""))
            listaFones = c.getDDD1COML() + " " + c.getFONECOML1();
        else if (!c.getFONECOML1().trim().equals("") && !c.getCELULAR().trim().equals(""))
            listaFones = c.getDDD1COML() + " " + c.getFONECOML1() + "," + c.getDDD1CEL() + " " + c.getCELULAR();
        return listaFones;
    }

    private String montarTelefone2(Cliente c) {
        String listaFones2 = "";
        if (c.getFONECOM2().trim().equals("") && !c.getCELULAR2().trim().equals(""))
            listaFones2 = c.getDDD2CEL() + " " + c.getCELULAR2();
        else if (!c.getFONECOM2().trim().equals("") && c.getCELULAR2().trim().equals(""))
            listaFones2 = c.getDDD2COML() + " " + c.getFONECOM2();
        else if (!c.getFONECOM2().trim().equals("") && !c.getCELULAR2().trim().equals(""))
            listaFones2 = c.getDDD2COML() + " " + c.getFONECOM2() + "," + c.getDDD2CEL() + " " + c.getCELULAR2();
        return listaFones2;
    }
}
