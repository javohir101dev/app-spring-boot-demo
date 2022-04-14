package demo.uz.helper;

public class Utils {

    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

}
