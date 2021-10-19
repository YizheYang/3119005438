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
        float res = parameter[0];
        for (int i = 0; i < type.length; i++) {
            switch (type[i]) {
                case "+" -> res += parameter[i + 1];
                case "-" -> {
                    res -= parameter[i + 1];
                    if (res < 0) {
                        return true;
                    }
                }
                case "*" -> res *= parameter[i + 1];
                case "/" -> res /= parameter[i + 1];
            }
        }
        return false;
    }

    public static float calculate(String formula) {
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
        float res = parameter[0];
        for (int i = 0; i < type.length; i++) {
            switch (type[i]) {
                case "+" -> res += parameter[i + 1];
                case "-" -> res -= parameter[i + 1];
                case "*" -> res *= parameter[i + 1];
                case "/" -> res /= parameter[i + 1];
            }
        }
        return res;
    }
}
