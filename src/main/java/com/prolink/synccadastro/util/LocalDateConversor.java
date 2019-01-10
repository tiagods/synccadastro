package com.prolink.synccadastro.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LocalDateConversor {
	public List<LocalDate> tratarDias() {
		List<LocalDate> lista = new ArrayList<>();
		LocalDate hoje = LocalDate.now();
		LocalDate segunda = null;
		LocalDate terca = null;
		LocalDate quarta = null;
		LocalDate quinta = null;
		LocalDate sexta = null;
		LocalDate sabado = null;
		LocalDate domingo = null;
		
		switch (hoje.getDayOfWeek()) {
		case MONDAY:
			segunda = hoje;
			terca = hoje.plusDays(1);
			quarta = hoje.plusDays(2);
			quinta = hoje.plusDays(3);
			sexta = hoje.plusDays(4);
			sabado = hoje.plusDays(5);
			domingo = hoje.plusDays(6);
			break;
		case TUESDAY:
			segunda = hoje.plusDays(-1);
			terca = hoje;
			quarta = hoje.plusDays(1);
			quinta = hoje.plusDays(2);
			sexta = hoje.plusDays(3);
			sabado = hoje.plusDays(4);
			domingo = hoje.plusDays(5);
			break;
		case WEDNESDAY:
			segunda = hoje.plusDays(-2);
			terca = hoje.plusDays(-1);
			quarta = hoje;
			quinta = hoje.plusDays(1);
			sexta = hoje.plusDays(2);
			sabado = hoje.plusDays(3);
			domingo = hoje.plusDays(4);
			break;
		case THURSDAY:
			segunda = hoje.plusDays(-3);
			terca = hoje.plusDays(-2);
			quarta = hoje.plusDays(-1);
			quinta = hoje;
			sexta = hoje.plusDays(1);
			sabado = hoje.plusDays(2);
			domingo = hoje.plusDays(3);
			break;
		case FRIDAY:
			segunda = hoje.plusDays(-4);
			terca = hoje.plusDays(-3);
			quarta = hoje.plusDays(-2);
			quinta = hoje.plusDays(-1);
			sexta = hoje;
			sabado = hoje.plusDays(1);
			domingo = hoje.plusDays(2);
			break;
		case SATURDAY:
			segunda = hoje.plusDays(-5);
			terca = hoje.plusDays(-4);
			quarta = hoje.plusDays(-3);
			quinta = hoje.plusDays(-2);
			sexta = hoje.plusDays(-1);
			sabado = hoje;
			domingo = hoje.plusDays(1);
			break;
		case SUNDAY:
			segunda = hoje.plusDays(-6);
			terca = hoje.plusDays(-5);
			quarta = hoje.plusDays(-4);
			quinta = hoje.plusDays(-3);
			sexta = hoje.plusDays(-2);
			sabado = hoje.plusDays(-1);
			domingo = hoje;
			break;
		default:
			break;
		}
		lista.add(segunda);
		lista.add(terca);
		lista.add(quarta);
		lista.add(quinta);
		lista.add(sexta);
		lista.add(sabado);
		lista.add(domingo);
		return lista;
	}
	// use localdate para o metodo conversor, se for usar calendar (0 - Janeiro a 11
	// - Dezembro) soma calendar.get(Calendar.Month)+1
	public String convertMounth(int mes) {
		switch (mes) {
		case 1:
			return "Janeiro";
		case 2:
			return "Fevereiro";
		case 3:
			return "Março";
		case 4:
			return "Abril";
		case 5:
			return "Maio";
		case 6:
			return "Junho";
		case 7:
			return "Julho";
		case 8:
			return "Agosto";
		case 9:
			return "Setembro";
		case 10:
			return "Outubro";
		case 11:
			return "Novembro";
		case 12:
			return "Dezembro";
		}
		return "";
	}

	public String convertMounth(String mes) {
		switch (mes.toUpperCase()) {
		case "JANEIRO":
			return "01";
		case "FEVEREIRO":
			return "02";
		case "MARÇO":
			return "03";
		case "ABRIL":
			return "04";
		case "MAIO":
			return "05";
		case "JUNHO":
			return "06";
		case "JULHO":
			return "07";
		case "AGOSTO":
			return "08";
		case "SETEMBRO":
			return "09";
		case "OUTUBRO":
			return "10";
		case "NOVEMBRO":
			return "11";
		case "DEZEMBRO":
			return "12";
		}
		return "";
	}
}
