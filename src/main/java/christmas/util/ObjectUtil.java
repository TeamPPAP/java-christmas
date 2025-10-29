package christmas.util;

import java.text.DecimalFormat;

public class ObjectUtil {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");
    private static final String CURRENCY_UNIT = "원";

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isBlank(Object object) {
        return object == null || object.toString().trim().isEmpty();
    }

    public static boolean isDigit(String s) {
        return s.matches("^[1-9]\\d*$");
    }

    public static String[] split(String str, String separator) {
        return str.split(separator);
    }

    public static String formatPrice(int price) {
        return DECIMAL_FORMAT.format(price) + CURRENCY_UNIT;
    }
}
