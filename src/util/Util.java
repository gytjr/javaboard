package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		return time1;
	}
	
	public static long getDateStringToNumber(String date) {
		
		String str = " ";
		
		String[] date_bits = date.split(" ");
		String[] bits1 = date_bits[0].split("-");
		String[] bits2 = date_bits[1].split(":");
		
		for(int i = 0; i <bits1.length; i++) {
			str += bits1[i];
		}
		for(int i = 0; i <bits2.length; i++) {
			str += bits2[i];
		}
		
		return Long.parseLong(str);
	}

}
