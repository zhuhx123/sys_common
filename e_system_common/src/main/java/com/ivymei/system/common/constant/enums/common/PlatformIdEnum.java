package com.ivymei.system.common.constant.enums.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 平台id常量值
 * 
 * @author liuxh
 */
public enum PlatformIdEnum {
	
	UNKNOW(0, "未知", "UNK"),
	M_WEBSITE(1, "M站", "M"),
	ANDROID(2, "android端", "APP"),
	IOS(3, "IOS端", "APP"),
	ADMIN(4, "站方后台", "ADM"),
	HIS(5, "HIS后台", "HIS"),
	CHANNEL(6, "mall-third第三方合作平台", "CHL"),
	SAAS(7, "SAAS系统", "SAAS"),
	
	;

	private int id;
	private String name;
	private String ename;

	private PlatformIdEnum(int id, String name, String ename) {
		this.id = id;
		this.name = name;
		this.ename = ename;
	}
	
	
	private static final Map<Integer,PlatformIdEnum> _map = new HashMap<Integer,PlatformIdEnum>();
	static{
		PlatformIdEnum[] values = PlatformIdEnum.values();
		for(PlatformIdEnum platformIdEnum : values){
			_map.put(platformIdEnum.getId(),platformIdEnum);
		}
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public static PlatformIdEnum getObj(int id) {
		PlatformIdEnum[] array = PlatformIdEnum.values();
		for (PlatformIdEnum obj : array) {
			if (obj.getId() == id) {
				return obj;
			}
		}
		return null;
	}

	public static String getName(int id) {
		PlatformIdEnum[] values = PlatformIdEnum.values();
		String name = "";
		for (PlatformIdEnum orderStatusEnum : values) {
			if (orderStatusEnum.getId() == id) {
				name = orderStatusEnum.getName();
				break;
			}
		}
		return name;
	}
	
	
	
	/**
	 * 根据ID获取枚举
	 * @param id
	 * @return
	 */
	public static PlatformIdEnum getPlatformIdEnum(Integer id){
		PlatformIdEnum code = PlatformIdEnum._map.get(id);
		if(code!=null){
			return code;
		}else{
			return null;
		}
	}
	public static boolean  have(Integer value){
		
		if (value != null && value.intValue() !=0) {
			for (PlatformIdEnum m : PlatformIdEnum.values()) {
				if (m.getId() == value) {
					return true;
				}
			}
		}
		return false;
	}
}
