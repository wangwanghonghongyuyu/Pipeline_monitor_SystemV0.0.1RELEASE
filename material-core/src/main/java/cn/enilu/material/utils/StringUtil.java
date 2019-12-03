package cn.enilu.material.utils;

/**
 * 字符串 工具类
 */
public class StringUtil {

	// 截取字符串
	public static String subCmd(String cmd, int from, int to) {
		String ret = null;
		if (cmd != null && !"".equals(cmd)) {
			int len = to - from;
			if (len > 0) {
				if (to <= cmd.length()) {
					ret = cmd.substring(from, to);
				}
			}
		}
		return ret;
	}

	public static int subIntCmd(String cmd, int from, int to) {
		return Integer.valueOf(subCmd(cmd, from, to));
	}

	/**
	 * 补充0 零
	 */
	public static String handleCode(String str , Integer strLength){
		Integer length = str.length();
		Integer index = strLength - length;
		for(int i =0 ; i < index ; i++){
			str = "0"+ str ;
		}
		return str;
	}


}
