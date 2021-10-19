/**
 * description None
 * <p>
 * author ez_yang@qq.com
 * date 2021.10.19 下午 5:51
 **/
public class NumberUtil {
    public static String trueFraction(int numerator, int denominator) {
        if (denominator == 0) {
            return null;
        } else if (numerator == 0) {
            return "0";
        }
        String result;
        if (numerator == denominator) {
            result = "1";
        } else if (numerator < denominator) {
            result = numerator + "/" + denominator;
        } else {
            int quotient = numerator / denominator;
            int remainder = numerator - quotient * denominator;
            result = quotient + "'" + remainder + "/" + denominator;
        }
        return result;
    }
}
