package br.com.dev.marcos.passguard.services.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class DateUtil {

	public static final String DDMMYYYY_HHMMSS_PATTERN = "dd/MM/yyyy HH:mm:ss";

	public static String format(LocalDateTime date) {
		return format(date, DDMMYYYY_HHMMSS_PATTERN);
	}
	
	public static String format(LocalDateTime date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
}
