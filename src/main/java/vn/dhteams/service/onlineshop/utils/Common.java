package vn.dhteams.service.onlineshop.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Common {

	// Split parameters from queryString
	public static Map<String, Object> splitSearchData(String searchData) {
		String[] params = searchData.split("&");
		Map<String, Object> listParams = new HashMap<>();

		for (String param : params) {
			String name = param.split("=")[0];
			String value = (param.split("=").length == 2) ? param.split("=")[1] : null;
			listParams.put(name, value);
		}
		return listParams;
	}

	public static String covertStringToURL(String str) {
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String currencyFormat(BigDecimal n) {
		try {
			DecimalFormat decimalFormat = new DecimalFormat("###,###");
			String result = decimalFormat.format(n);
			return result.replaceAll(",", ".");
		} catch (Exception e) {
			return "";
		}
	}
}
