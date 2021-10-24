import java.util.ArrayList;
import java.util.List;

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

    /**
     * 对算式进行计算
     *
     * @param formula 算式
     * @return 计算结果
     */
    public static String calculate(String formula) {
        String[] strings = formula.split(" ");
        String[] type = new String[strings.length / 2];
        List<Integer> parameter = new ArrayList<>();
        for (int i = 0, j = 0; i < strings.length; i++) {
            switch (i % 2) {
                case 0 -> parameter.add(Integer.parseInt(strings[i]));
                case 1 -> {
                    type[j] = strings[i];
                    j++;
                }
            }
        }
        // 进行计算优先级判断
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < type.length; i++) {
            if (type[i].equals("*") || type[i].equals("/")) {
                order.add(i);
            }
        }
        for (int i = 0; i < type.length; i++) {
            if (type[i].equals("+") || type[i].equals("-")) {
                order.add(i);
            }
        }
        String res = null;
        String tempRes = null;
        String calculate = String.valueOf(parameter.get(order.get(0)));
        String beCalculate = String.valueOf(parameter.get(order.get(0) + 1));
        for (int i = 0; i < order.size(); i++) {
            int j = order.get(i);
            if (i > 0) {
                if (j - order.get(i - 1) == 2) {
                    // 当出现两个乘除夹着加减的时候，需要有一个变量来临时存储第一个乘除的值
                    tempRes = res;
                    calculate = String.valueOf(parameter.get(j));
                    beCalculate = String.valueOf(parameter.get(j + 1));
                } else if (i == 2 && order.get(0) == 0 && order.get(1) == 2 && j == 1) {
                    // 当出现两个乘除夹着加减的时候，最后的运算必定是中间的加减，把前后乘除的结果赋值
                    calculate = tempRes;
                    beCalculate = res;
                } else if (j < order.get(i - 1)) {
                    // 当乘除出现在加减后面，先运算的乘除的结果就变成被计算的值，如1 + 2 * 3
                    calculate = String.valueOf(parameter.get(j));
                    beCalculate = res;
                } else if (j > order.get(i - 1)) {
                    // 当乘除出现在加减后面，先运算的乘除的结果就变成计算的值，如1 * 2 + 3
                    calculate = res;
                    beCalculate = String.valueOf(parameter.get(j + 1));
                }
            }
            switch (type[j]) {
                case "+" -> res = Fraction.add(calculate, beCalculate + " / 1");
                case "-" -> res = Fraction.subtract(calculate, beCalculate + " / 1");
                case "*" -> res = Fraction.multiply(calculate, beCalculate + " / 1");
                case "/" -> res = Fraction.divide(calculate, beCalculate + " / 1");
            }
            if (res != null && res.equals("1")) {
                res = "1 / 1";
            }
        }
        String[] temp = res.split(" / ");
        if (temp.length == 2) {
            if (temp[1].equals("1")) {
                return temp[0];
            } else {
                res = Fraction.checkFraction(res);
            }
        }
        return res;
    }
}
