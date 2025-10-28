package christmas.util;

public class ObjectUtil {

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

}
