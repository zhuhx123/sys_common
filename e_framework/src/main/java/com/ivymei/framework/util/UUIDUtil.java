package com.ivymei.framework.util;

import java.util.UUID;

public class UUIDUtil {
	
	public static String generate(){
		String uuid=UUID.randomUUID().toString();
		uuid=uuid.replaceAll("-", "");
		return uuid;
	}
}
