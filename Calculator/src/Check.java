/**
 * description None
 * <p>
 * author ez_yang@qq.com
 * date 2021.10.19 下午 8:31
 **/
public class Check {

    /**
     * 判断算式是否出现负数
     *
     * @param formula 算式
     * @return 是否为负数
     */
    public static boolean checkNegative(String formula) {
        String[] strings = formula.split(" ");
        String[] type = new String[strings.length / 2];
        int[] parameter = new int[strings.length / 2 + 1];
        for (int i = 0, j = 0, k = 0; i < strings.length; i++) {
            switch (i % 2) {
                case 0 -> {
                    parameter[j] = Integer.parseInt(strings[i]);
                    j++;
                }
                case 1 -> {
                    type[k] = strings[i];
                    k++;
                }
            }
        }
        String res = String.valueOf(parameter[0]);
        for (int i = 0; i < type.length; i++) {
            switch (type[i]) {
                case "+" -> {
                    res = Fraction.add(res, parameter[i + 1] + " / 1");
                    if (res.equals("1")) {
                        res = "1 / 1";
                    }
                }
                case "-" -> {
                    res = Fraction.subtract(res, parameter[i + 1] + " / 1");
                    if (res.toCharArray()[0] == '-') {
                        return true;
                    }
                    if (res.equals("1")) {
                        res = "1 / 1";
                    }
                }
                case "*" -> {
                    res = Fraction.multiply(res, parameter[i + 1] + " / 1");
                    if (res.equals("1")) {
                        res = "1 / 1";
                    }
                }
                case "/" -> {
                    res = Fraction.divide(res, parameter[i + 1] + " / 1");
                    if (res.equals("1")) {
                        res = "1 / 1";
                    }
                }
            }
        }
        return false;
    }

    public static String calculate(String formula) {
        String[] strings = formula.split(" ");
        String[] type = new String[strings.length / 2];
        int[] parameter = new int[strings.length / 2 + 1];
        for (int i = 0, j = 0, k = 0; i < strings.length; i++) {
            switch (i % 2) {
                case 0 -> {
                    parameter[j] = Integer.parseInt(strings[i]);
                    j++;
                }
                case 1 -> {
                    type[k] = strings[i];
                    k++;
                }
            }
        }
        String res = String.valueOf(parameter[0]);
        for (int i = 0; i < type.length; i++) {
            switch (type[i]) {
                case "+" -> {
                    res = Fraction.add(res, parameter[i + 1] + " / 1");
                    if (res.equals("1")) {
                        res = "1 / 1";
                    }
                }
                case "-" -> {
                    res = Fraction.subtract(res, parameter[i + 1] + " / 1");
                    if (res.equals("1")) {
                        res = "1 / 1";
                    }
                }
                case "*" -> {
                    res = Fraction.multiply(res, parameter[i + 1] + " / 1");
                    if (res.equals("1")) {
                        res = "1 / 1";
                    }
                }
                case "/" -> {
                    res = Fraction.divide(res, parameter[i + 1] + " / 1");
                    if (res.equals("1")) {
                        res = "1 / 1";
                    }
                }
            }
        }
        String[] temp = res.split(" / ");
        if (temp.length == 2 && temp[1].equals("1")) {
            return temp[0];
        }
        return res;
    }
}
