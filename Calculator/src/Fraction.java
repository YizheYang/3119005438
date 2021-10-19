/**
 * description None
 * <p>
 * author ez_yang@qq.com
 * date 2021.10.19 下午 5:51
 **/
public class Fraction {
    /**
     * 分数加法
     *
     * @param calculation 加数
     * @param fraction    被加数，必须为分数
     * @return 分数
     */
    public static String add(String calculation, String fraction) {
        String[] c = calculation.split(" / ");
        String[] f = fraction.split(" / ");
        int numerator, denominator;
        String res = null;
        if (c.length == 1) {
            numerator = Integer.parseInt(c[0]) * Integer.parseInt(f[1]) + Integer.parseInt(f[0]);
            denominator = Integer.parseInt(f[1]);
            if (numerator == denominator) {
                res = "1";
            } else {
                int temp = gy(numerator, denominator);
                res = numerator / temp + " / " + denominator / temp;
            }
        } else if (c.length == 2) {
            numerator = Integer.parseInt(c[0]) * Integer.parseInt(f[1]) + Integer.parseInt(f[0]) * Integer.parseInt(c[1]);
            denominator = Integer.parseInt(c[1]) * Integer.parseInt(f[1]);
            if (numerator == denominator) {
                res = "1";
            } else {
                int temp = gy(numerator, denominator);
                res = numerator / temp + " / " + denominator / temp;
            }
        }
        return res;
    }

    /**
     * 分数减法
     *
     * @param calculation 减数
     * @param fraction    被减数，必须为分数
     * @return 分数
     */
    public static String subtract(String calculation, String fraction) {
        String[] c = calculation.split(" / ");
        String[] f = fraction.split(" / ");
        int numerator, denominator;
        String res = null;
        if (c.length == 1) {
            numerator = Integer.parseInt(c[0]) * Integer.parseInt(f[1]) - Integer.parseInt(f[0]);
            denominator = Integer.parseInt(f[1]);
            if (numerator == denominator) {
                res = "1";
            } else {
                int temp = gy(numerator, denominator);
                res = numerator / temp + " / " + denominator / temp;
            }
        } else if (c.length == 2) {
            numerator = Integer.parseInt(c[0]) * Integer.parseInt(f[1]) - Integer.parseInt(f[0]) * Integer.parseInt(c[1]);
            denominator = Integer.parseInt(c[1]) * Integer.parseInt(f[1]);
            if (numerator == denominator) {
                res = "1";
            } else {
                int temp = gy(numerator, denominator);
                res = numerator / temp + " / " + denominator / temp;
            }
        }
        return res;
    }

    /**
     * 分数乘法
     *
     * @param calculation 乘数
     * @param fraction    被乘数，必须为分数
     * @return 分数
     */
    public static String multiply(String calculation, String fraction) {
        String[] c = calculation.split(" / ");
        String[] f = fraction.split(" / ");
        int numerator, denominator;
        String res = null;
        if (c.length == 1) {
            numerator = Integer.parseInt(c[0]) * Integer.parseInt(f[0]);
            denominator = Integer.parseInt(f[1]);
            if (numerator == denominator) {
                res = "1";
            } else {
                int temp = gy(numerator, denominator);
                res = numerator / temp + " / " + denominator / temp;
            }
        } else if (c.length == 2) {
            numerator = Integer.parseInt(c[0]) * Integer.parseInt(f[0]);
            denominator = Integer.parseInt(c[1]) * Integer.parseInt(f[1]);
            if (numerator == denominator) {
                res = "1";
            } else {
                int temp = gy(numerator, denominator);
                res = numerator / temp + " / " + denominator / temp;
            }
        }
        return res;
    }

    /**
     * 分数除法
     *
     * @param calculation 除数
     * @param fraction    被除数，必须为分数
     * @return 分数
     */
    public static String divide(String calculation, String fraction) {
        String[] c = calculation.split(" / ");
        String[] f = fraction.split(" / ");
        int numerator, denominator;
        String res = null;
        if (c.length == 1) {
            numerator = Integer.parseInt(c[0]) * Integer.parseInt(f[1]);
            denominator = Integer.parseInt(f[0]);
            if (numerator == denominator) {
                res = "1";
            } else {
                int temp = gy(numerator, denominator);
                res = numerator / temp + " / " + denominator / temp;
            }
        } else if (c.length == 2) {
            numerator = Integer.parseInt(c[0]) * Integer.parseInt(f[1]);
            denominator = Integer.parseInt(c[1]) * Integer.parseInt(f[0]);
            if (numerator == denominator) {
                res = "1";
            } else {
                int temp = gy(numerator, denominator);
                res = numerator / temp + " / " + denominator / temp;
            }
        }
        return res;
    }

    /**
     * 假分数转真分数
     *
     * @param fraction 分数
     * @return 真分数
     */
    public static String improper2Proper(String fraction) {
        String[] s = fraction.split(" / ");
        int numerator = Integer.parseInt(s[0]);
        int denominator = Integer.parseInt(s[1]);
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

    /**
     * 求最大公约数
     *
     * @param m 数1
     * @param n 数2
     * @return 最大公约数
     */
    private static int gy(int m, int n) {
        if (m % n == 0) return n;
        return gy(n, m % n);
    }
}
