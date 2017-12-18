package com.ivymei.framework.util;

import java.text.DecimalFormat;

public class DecimalUtil {
	
	private static DecimalFormat DEFAULT_FORMAT = new DecimalFormat("0.00");
	
	
	public static String format(Object number) {
		return DEFAULT_FORMAT.format(number);	
	}
	
	
}
