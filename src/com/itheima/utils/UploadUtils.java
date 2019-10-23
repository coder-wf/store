package com.itheima.utils;

import java.util.Random;
import java.util.UUID;

public class UploadUtils {
	
	/**
	 * 获取随机名称
	 * @param realName 真实名称
	 * @return uuid 随机名称
	 */
	public static String getUUIDName(String realName){
		//realname  eg:, a--->UUID      a.jpg---->UUID.jpg
		//获取后缀名
		int index = realName.lastIndexOf(".");
		if(index==-1){
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		}else{
			return UUID.randomUUID().toString().replace("-", "").toUpperCase()+realName.substring(index);
		}
	}
	
	
	/**
	 * 获取文件目录,可以获取256个随机目录
	 * @return 随机目录  
	 */
	public static String getDir(){
		String s="0123456789ABCDEF";
		Random r = new Random();
		return "/"+s.charAt(r.nextInt(16))+"/"+s.charAt(r.nextInt(16));
	}
	
	public static void main(String[] args) {
		//String s="G:\\day17-基础加强\\resource\\1.jpg";
		String s="1.jgp";
		String uuidName = getUUIDName(s);
		System.out.println(uuidName);
		
		String dir = getDir();
		System.out.println(dir);
	}
}
