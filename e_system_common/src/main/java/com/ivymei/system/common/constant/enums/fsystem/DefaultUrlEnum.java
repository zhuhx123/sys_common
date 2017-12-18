package com.ivymei.system.common.constant.enums.fsystem;

/**
 * 默认图片链接地址
 * 
 * @author jht
 *
 */
public enum DefaultUrlEnum {

	DOCTOR_DEFAULT_AVATAR("医生默认头像", "http://test.img.jinhuatuo.com/image/public/2016/05/25/f5421c134f864b7aad224b4ce9446641_origin.jpg"), //
	PATIENT_DEFAULT_AVATAR("患者默认头像", "http://test.img.jinhuatuo.com/image/public/2016/05/25/3ea0fcfbc84e44d1802b72d7ecde4272_origin.png"), //
	;

	private String name;
	private String url;

	private DefaultUrlEnum(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

}
