package util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

public class FormatUtil {

	public static String formatarData(String data) {
		SimpleDateFormat para = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat de = new SimpleDateFormat("yyyyMMdd");
		Date dataAux = null;
		try {
			dataAux = de.parse(data);
		} catch (ParseException e) {
			System.err.println("Erro ao converter data!");
			e.printStackTrace();
		}
		para.format(dataAux);
		return para.format(dataAux);
	}

	public static String formatarHora(String hora) {
		SimpleDateFormat para = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat de = new SimpleDateFormat("HHmmss");
		Date dataAux = null;
		try {
			dataAux = de.parse(hora);
		} catch (ParseException e) {
			System.err.println("Erro ao converter hora!");
			e.printStackTrace();
		}
		para.format(dataAux);
		return para.format(dataAux);
	}

	public static String formatarDataString(Date data, String formato) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		String strData = sdf.format(data);
		return strData;
	}

	public static String getCampo(String[] campos, int indice) throws Exception {
		String retorno = null;
		try {
			if (campos != null) {
				retorno = campos[indice];
			}

		} catch (java.lang.ArrayIndexOutOfBoundsException ex) {//
			System.out.println("Coluna não existe!");
			throw new Exception();
		}
		return retorno;
	}

	public static String formatarValor(String valor) {
		Long valorLong = Long.valueOf(0);

		try {
			valorLong = Long.parseLong(valor);
		} catch (NumberFormatException ex) {
			valorLong = Long.valueOf(0);
			System.err.println("Não é um valor numérico válido");
		}

		double valorDecimal = (valorLong.doubleValue() / 100);
		DecimalFormat formatarValor = new DecimalFormat("0.00");

		return formatarValor.format(valorDecimal);
	}

	public static String formatarNumeroConvenio(String valor) {
		Long valorLong = Long.valueOf(0);
		try {
			valorLong = Long.parseLong(valor);
		} catch (NumberFormatException ex) {
			valorLong = Long.valueOf(0);
			System.err.println("Não é um valor numérico válido");
		}
		DecimalFormat formatarNumeroConvenio = new DecimalFormat("00000");
		return formatarNumeroConvenio.format(valorLong);
	}

	public static int getMes(Date data) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		int mes = calendar.get(Calendar.MONTH);
		return mes + 1;
	}

	public static int getAno(Date data) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		int ano = calendar.get(Calendar.YEAR);
		return ano;
	}

	public static int getDia(Date data) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		int dia = calendar.get(Calendar.DAY_OF_WEEK);
		return dia;
	}

	public static int getDiaMes(Date data) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		return dia;
	}

	public static int getSemanaMes(Date data) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		int s = calendar.get(Calendar.WEEK_OF_MONTH);
		return s;
	}

	public static int getHora(Date data) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		int h = calendar.get(Calendar.HOUR_OF_DAY);
		return h;
	}

	public static int getMinuto(Date data) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		int m = calendar.get(Calendar.MINUTE);
		return m;
	}

	public static List<Integer> getListaAnos(Integer anoInicial) {
		Calendar dataFinal = Calendar.getInstance();
		Integer anoAtual = dataFinal.get(Calendar.YEAR);
		List<Integer> listaAnos = new ArrayList<Integer>();
		for (Integer ano = anoInicial; ano <= anoAtual + 5; ano++) {
			listaAnos.add(ano);
		}
		return listaAnos;
	}

	public static Date construirData(int dia, int mes, int ano, int hora, int minuto, int segundo) {
		Calendar c = Calendar.getInstance();
		c.set(ano, mes - 1, dia, hora, minuto, segundo);
		return c.getTime();
	}

	public static java.sql.Date converteDataSQL(Date data) {
		if (data != null) {
			return new java.sql.Date(data.getTime());
		} else {
			return null;
		}
	}

	public static java.sql.Timestamp converteTimeStampSQL(Date data) {
		if (data != null) {
			return new java.sql.Timestamp(data.getTime());
		} else {
			return null;
		}
	}

	public static Date criteriaMenorData(Date date) {
		Calendar aux = Calendar.getInstance();
		aux.setTime(date);
		criteriaApenasData(aux); // zera os parametros de hour,min,sec,milisec
		return aux.getTime();
	}

	public static Date criteriaMaiorData(Date date) {
		Calendar aux = Calendar.getInstance();
		aux.setTime(date);
		criteriaApenasData(aux); // zera os parametros de hour,min,sec,milisec
		aux.roll(Calendar.DATE, true); // vai para o dia seguinte
		aux.roll(Calendar.MILLISECOND, false); // reduz 1 milisegundo
		return aux.getTime();
	}

	public static Date paraDate(String strData) throws Exception {
		if (strData == null || strData.equals(""))
			return null;

		// System.out.println("STR DATA "+strData);
		SimpleDateFormat para = new SimpleDateFormat("dd/MM/yy");

		Date dataAux = null;
		Date data_ = null;
		try {
			// TimeZone.setDefault(TimeZone.getTimeZone("GMT-03:00"));
			dataAux = new Date(para.parse(strData).getTime());
			data_ = new Date(dataAux.getTime());
		} catch (ParseException e) {
			// System.err.println("Erro ao converter data!");

			try {
				data_ = dataPlanilha(strData);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		return data_;
	}

	public static Date apenasData(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}

	/**
	 * Converte do formato número para data
	 * 
	 * @param strData
	 *            - Data em string
	 * @return data convertida
	 * @throws Exception
	 */
	public static Date dataPlanilha(String strData) throws Exception {
		GregorianCalendar gc = new GregorianCalendar(1900, Calendar.JANUARY, 0);
		Integer numData = Integer.valueOf(strData);
		gc.add(Calendar.DATE, numData - 1);
		System.out.println(gc.getTime());
		return gc.getTime();
	}

	public static void criteriaApenasData(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
	}

	public static String retornaDiaSemana(int dia) {
		Map<Integer, String> mapDiasSemana = new java.util.HashMap<Integer, String>();
		mapDiasSemana.put(2, "Segunda-feira");
		mapDiasSemana.put(3, "Terça-feira");
		mapDiasSemana.put(4, "Quarta-feira");
		mapDiasSemana.put(5, "Quinta-feira");
		mapDiasSemana.put(6, "Sexta-feira");
		return mapDiasSemana.get(dia);
	}

	// CNAB 240

	public static Double formataValor(String valor) {

		String valorAux = null, valorFinal = null;
		double valorDecimal = 0;
		Long valorLong = Long.valueOf(0);
		if (valor != null && !(valor.contains(".") || valor.contains(","))) {

			try {
				valorLong = Long.parseLong(valor);
			} catch (NumberFormatException ex) {
				valorLong = Long.valueOf(0);
				System.err.println("Não é um valor numérico válido");
			}
			valorDecimal = valorLong.doubleValue();
			return valorDecimal;
		}

		if (valor != null) {
			valorAux = valor.replaceAll("[^0-9]", "");
			valorFinal = valorAux;
			if (valorAux.contains(".")) {
				valorFinal = valorAux.replace(",", ".");
			}
		}

		try {
			valorLong = Long.parseLong(valorFinal);
		} catch (NumberFormatException ex) {
			valorLong = Long.valueOf(0);
			System.err.println("Não é um valor numérico válido");
		}

		valorDecimal = (valorLong.doubleValue() / 100);
		return valorDecimal;
	}

	public static String formataValor(Object valor, int casas, String caracterePreenchimento, String caractereDecimal)
			throws NumberFormatException {

		double valorAux = 0;
		if (valor instanceof Double) {
			valorAux = (Double) valor;
		} else if (valor instanceof String) {
			valorAux = Double.parseDouble((String) valor);
		}
		String valorFinal = org.apache.commons.lang.StringUtils.leftPad("0", casas);
		DecimalFormat formatarValor = new DecimalFormat("0.00");
		String strValor = formatarValor.format(valorAux);
		if (strValor != null && !strValor.isEmpty()) {
			String strAux = strValor.replace(",", caractereDecimal);
			valorFinal = org.apache.commons.lang.StringUtils.leftPad(strAux, casas, caracterePreenchimento);
		}
		return valorFinal;
	}

	public static String trataValor(String valor) {
		String valorAux = null;
		if (valor != null && valor.contains("[^0-9]")) {
			valorAux = valor.replaceAll("[^0-9]", "");
		} else {
			valorAux = valor;
		}
		return valorAux;
	}

	public static boolean validaValor(String valor) {

		if (valor == null) {
			return false;
		}

		DecimalFormat df = new DecimalFormat("0.00");
		df.setParseBigDecimal(true);
		try {
			df.parse(valor.trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static BigDecimal stringParaBigDecimal(String strValor) {
		BigDecimal valor = new BigDecimal("0.00");
		DecimalFormat df = new DecimalFormat("0.00");
		df.setParseBigDecimal(true);
		try {
			valor = (BigDecimal) df.parse(strValor);
		} catch (ParseException e) {
			valor = new BigDecimal("0.00");
			e.printStackTrace();
		}

		return valor;
	}

	public static String bigDecimalParaString(BigDecimal valor) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#,###.00");
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(0);
		df.setGroupingUsed(false);

		return df.format(valor);
	}

	public static String preencheEspacosDireita(String str, int totalEspacos, String caractere) {
		String valorFinal = org.apache.commons.lang.StringUtils.rightPad(str, totalEspacos, caractere);
		return valorFinal;
	}

	public static String preencheEspacosEsquerda(String str, int totalEspacos, String caractere) {
		String valorFinal = org.apache.commons.lang.StringUtils.leftPad(str, totalEspacos, caractere);
		return valorFinal;
	}

	public static Double stringParaDouble(String strValor) throws NumberFormatException {
		return Double.parseDouble(strValor);
	}

	public static String removeAcentos(String str) {
		String strSemAcentos = Normalizer.normalize(str, Normalizer.Form.NFD);
		strSemAcentos = strSemAcentos.replaceAll("[^\\p{ASCII}]", "");
		return strSemAcentos;
	}

	public static String extrairDigito(String str) {
		String digito = null;
		try {
			String texto = apenasLetrasENumeros(str);
			int tam = texto.length();
			digito = texto.substring(tam - 1, tam);
		} catch (Exception e) {
			return null;
		}

		return digito;
	}

	public static String extrairNumero(String str) {
		String num = null;
		try {
			String texto = apenasLetrasENumeros(str);
			int tam = texto.length();
			num = texto.substring(0, tam - 1);
		} catch (Exception e) {
			return null;
		}
		return num;
	}

	public static String apenasLetrasENumeros(String texto) {
		String txt = texto.replaceAll("[^\\w]", "");
		return txt;
	}

	public static String removerMascara(String str) {
		return str.replaceAll("\\D", "");
	}

	public static String criptografaSenha(String original) {
		MessageDigest algorithm = null;
		byte messageDigest[] = null;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");
			messageDigest = algorithm.digest(original.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		String senha = hexString.toString();

		return senha;
	}

	public static boolean validarEmail(String email) {
		boolean emailValido = false;
		if (email != null && email.length() > 0) {
			String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				emailValido = true;
			}
		}
		return emailValido;
	}

	public static String formataCPF(String cpf) {
		Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
		Matcher matcher = pattern.matcher(cpf);
		if (matcher.matches())
			cpf = matcher.replaceAll("$1.$2.$3-$4");
		return cpf;
	}

	public static String formataCNPJ(String cnpj) {
		Pattern pattern = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");
		Matcher matcher = pattern.matcher(cnpj);
		if (matcher.find()) {
			return matcher.replaceAll("$1.$2.$3/$4-$5");
		}
		return cnpj;
	}

	public static String caracteresNumericos(String texto) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < texto.length(); i++) {
			if (Character.isDigit(texto.charAt(i))) {
				str.append(texto.charAt(i));
			}
		}
		int l = str.toString().length();
		if (l == 0) {
			l++;
		}
		l++;
		return org.apache.commons.lang.StringUtils.leftPad(str.toString(), l, "0");
	}

	public static String removerZerosStringNumerica(String strValor) {
		String valorSemZerosEsquerda = null;
		try {
			valorSemZerosEsquerda = Long.valueOf(strValor).toString();
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			valorSemZerosEsquerda = "0";
		}
		return valorSemZerosEsquerda;
	}

	/**
	 * Transforma de bytes para BufferedImage para ser redimensionada
	 * 
	 * @param b
	 * @return
	 */
	public static BufferedImage bytesParaBuffedImage(byte[] b) {
		InputStream in = new ByteArrayInputStream(b);
		BufferedImage bImageFromConvert = null;
		try {
			bImageFromConvert = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bImageFromConvert;
	}

	
	
	public static void baixarArquivo(String url) {
		
		int DEFAULT_BUFFER_SIZE = 10240;

		if (url == null) {
			return;
		}

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		File file = new File(url);
		if (!file.exists()) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		response.reset();
		response.setBufferSize(DEFAULT_BUFFER_SIZE);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try {
			try {
				input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			try {
				while ((length = input.read(buffer)) > 0) {
					output.write(buffer, 0, length);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			try {
				input.close();
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		context.responseComplete();
	}

	// public static byte[] bytesImagemRedimensionada(byte[] b, int largura, int
	// altura, String extensao) {
	//
	// System.out.println("EXTENSÃO 01: " + extensao);
	//
	// BufferedImage imgAux = bytesParaBuffedImage(b);
	//
	// //String ext = null;
	// if (extensao != null) {
	// if (extensao.contains("png")) {
	// imgAux = bytesParaBuffedImage(PNGparaJPG(imgAux));
	// }
	// }
	//
	// System.out.println("IMG AUX: " + imgAux);
	//
	// BufferedImage img = getScaledInstance(imgAux, largura, altura,
	// RenderingHints.VALUE_INTERPOLATION_BICUBIC,
	// true);
	//
	// System.out.println("EXTENSÃO 02: " + extensao);
	//
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// try {
	// ImageIO.write(img, "jpg", baos);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// byte[] bytes = baos.toByteArray();
	// try {
	// baos.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return bytes;
	// }

	public static byte[] extrairBytes(String url) {
		File arq = new File(url);
		byte[] b = null;
		try {
			b = Files.readAllBytes(arq.toPath());
		} catch (IOException e) {
			b = null;
			e.printStackTrace();
		}
		return b;
	}

	public static void salvarArquivo(String caminhoArquivo, byte[] bytesArquivo) throws IOException {
		File file = new File(caminhoArquivo);
		File f = new File(file.getAbsolutePath());
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		if (!f.exists()) {
			f.createNewFile();
		}
		String url = f.getAbsolutePath();
		FileOutputStream fos = new FileOutputStream(url);
		fos.write(bytesArquivo);
		fos.flush();
		fos.close();
	}

	public static <T> List<List<T>> dividir(List<T> list, final int L) {
		List<List<T>> parts = new ArrayList<List<T>>();
		final int N = list.size();
		for (int i = 0; i < N; i += L) {
			parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L))));
		}
		return parts;
	}

	/**
	 * Divide um item em partes proporcionais
	 * 
	 * @param total
	 *            total de itens
	 * @param percentual
	 *            percentual de particionamento ex: 5% -> 05.0f
	 * @return número de partes a dividir o total
	 */
	public static int divisoes(int total, float percentual) {
		// int i = 5000;
		// float percentual = 05.0f;
		int tamanhoParte = (int) Math.round(total * percentual / 100);

		if (tamanhoParte <= 0) {
			tamanhoParte = 1;
		}

		int partes = ((int) Math.round(total / tamanhoParte));
		System.out.println(partes);
		return partes;
	}

	

	public static <T> List<List<T>> batchList(List<T> inputList, final int maxSize) {
		List<List<T>> sublists = new ArrayList<>();

		final int size = inputList.size();

		for (int i = 0; i < size; i += maxSize) {
			// Math.min... size will be smaller than i + maxSize for the last
			// batch (unless perfectly divisible),
			// including the first batch if size is smaller than max size
			sublists.add(new ArrayList<>(inputList.subList(i, Math.min(size, i + maxSize))));
		}

		return sublists;
	}

	/**
	 * Avança ou retorna data em dias no caso de retornar, utiliza-se valor
	 * negativo
	 * 
	 * @param dias
	 * @param fimDeSemana ignorar ou não fim de semana
	 *            - dias a avançar ou retornar
	 * @return data final sendo anterior ou próxima
	 */
	public static Date dias(int dias, boolean fimDeSemana) {
		java.util.Calendar comeco = java.util.Calendar.getInstance();
//		java.util.Date teste = util.FormatUtil.construirData(05, 03, 2018, 0, 0, 0);
//		comeco.setTime(teste);
		comeco.add(java.util.Calendar.DATE, dias);
		if(fimDeSemana) {
			comeco = checaFDS(comeco, dias);
		}
		java.util.Date di = comeco.getTime();
		return di;
	}

	/**
	 * Verifica se data á sábado ou domingo e acrescenta dias conforme
	 * necessário p/ retornar dia de semana.
	 *
	 * @param data
	 *            Um objeto Calendar
	 * @return Calendar
	 */
	public static Calendar checaFDS(Calendar data, int sinal) {
		
		boolean negativo = false;
		int indice = 0;
		
		if(sinal<0) {
			negativo = true;
		}else{
			negativo = false;
		}
		
		System.out.println("Data inicial "+data.getTime());
		
		// se for domingo
		if (data.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			if(negativo) {
				indice = -2;
			}else{
				indice = 1;
			}
			data.add(Calendar.DATE, indice);
			System.out.println("É domingo, mudando data para "+indice+" dias");
		}
		// se for sábado
		else if (data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			
			if(negativo) {
				indice = -1;
			}else{
				indice = 2;
			}
			
			data.add(Calendar.DATE, indice);
			System.out.println("É sábado, mudando data para "+indice+" dias");
		} else {
			System.out.println("Eh dia de semana, mantem data");
		}
		System.out.println("Eh dia de semana, mantem data");
		System.out.println(" DATA FINAL NO MÉTODO "+data.getTime());
		return data;
	}

}
