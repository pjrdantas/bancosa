package br.com.meuBanco.utils;


import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import br.com.meuBanco.exceptions.ApiBadRequestException;
import br.com.meuBanco.exceptions.ApiFormatoParametroInvalidoException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
@Slf4j
public class DateUtils {

	private DateUtils(){}

	private static String dateFormat = "dd-MM-yyyy";

	/**
	 *
	 * @param dateToValidate
	 * @param dateFormat
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static void isThisDateValid(String dateToValidate, String dateFormat) throws NoSuchFieldException, SecurityException{
		validAndParseDate(dateToValidate, dateFormat);
	}

	public static Date validAndParseDate(String dateToValidate, String dateFormat) throws NoSuchFieldException, SecurityException{
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);

		try {
			return sdf.parse(dateToValidate);
		} catch (ParseException e) {
			Log4j2.class.getDeclaredField( e.getMessage());
			throw new ApiFormatoParametroInvalidoException("Formatação da data inválida.", e);
		}

	}

	/**
	 *
	 * @param date
	 * @param dateFormat
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static Date parseDateIfNotEmpty(String date, String dateFormat) throws NoSuchFieldException, SecurityException{
		if ( StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(dateFormat)){
			final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.applyPattern(dateFormat);
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				Log4j2.class.getDeclaredField(e.getMessage());
				throw new ApiFormatoParametroInvalidoException("Formatação da data inválida.", e);
			}
		}
		return null;
	}


	/**
	 *
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static Map<String, Date> validarERetornarDataInicioEFim(String dataInicio, String dataFim) throws NoSuchFieldException, SecurityException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

		try {

			DateUtils.isThisDateValid(dataInicio, dateFormat);
			DateUtils.isThisDateValid(dataFim, dateFormat);

			Date dateInicio = simpleDateFormat.parse(dataInicio);
			Date dateFim = simpleDateFormat.parse(dataFim);

			Map<String, Date> datas = new HashMap<>();
			datas.put("dataInicio",dateInicio);
			datas.put("dataFim",dateFim);

			return datas;

		} catch (ParseException e) {
			Log4j2.class.getDeclaredField(e.getMessage());
			throw new ApiBadRequestException("Formato do parâmetro data inválido.", e);
		}


	}


	/**
	 *
	 * @param date
	 * @return
	 */
	public static LocalDate fromDateToLocalDate(Date date) {
		return date != null ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
	}

	public static boolean isDate(String data) {
        try {
            //SimpleDateFormat é usada para trabalhar com formatação de datas
            //neste caso meu formatador irá trabalhar com o formato "dd/MM/yyyy"
            //dd = dia, MM = mes, yyyy = ano
            //o "M" dessa String é maiusculo porque "m" minusculo se n me engano é minutos
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            //a mágica desse método acontece aqui, pois o setLenient() é usado para setar
            //sua escolha sobre datas estranhas, quando eu seto para "false" estou dizendo
            //que não aceito datas falsas como 31/02/2016
            sdf.setLenient(false);
            //aqui eu tento converter a String em um objeto do tipo date, se funcionar
            //sua data é valida
            sdf.parse(data);
            //se nada deu errado retorna true (verdadeiro)
            return true;
        } catch (ParseException ex) {
            //se algum passo dentro do "try" der errado quer dizer que sua data é falsa, então,
            //retorna falso
            return false;
        }
    }	

}