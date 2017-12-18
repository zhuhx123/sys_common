package com.ivymei.framework.plugin.token;

import com.ivymei.framework.util.Md5Encrypt;

public class AppToken {
	private long currentTime;
	private int userId;
	private String md5Pwd;//MD5后的密码
	private long registerTime;//注册时间
	
	
	public AppToken(int userId,String md5Pwd,long registerTime,long currentTime){
		this.userId=userId;
		this.md5Pwd=md5Pwd;
		this.registerTime=registerTime;
		this.currentTime=currentTime;
	}

	@Override
	public String toString() {
		String str=userId+md5Pwd+registerTime+currentTime;
		return Md5Encrypt.md5ByUTF8(str);
	}
	
	public static void main(String[] args) {
		
	}
}
