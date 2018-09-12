package cn.ys.shop.utils;

import java.util.UUID;

public class UUidUtils {

	public  static String getUUID(){
		
		return UUID.randomUUID().toString().replace("-","");
	}

}
