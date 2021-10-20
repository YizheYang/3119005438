import java.util.Random;

/**
 * description None
 * <p>
 * author ez_yang@qq.com
 * date 2021.10.19 下午 5:50
 **/
public class Generate {
    private final int range;

    Generate(int range) {
        this.range = range;
    }

    /**
     * 根据范围随机生成运算数
     *
     * @return 自然数
     */
    private int randomGenerateNum() {
        return new Random().nextInt(range);
    }

    /**
     * 随机生成题目的字符串
     *
     * @return 题目字符串
     */
    private String randomGenerate() {
        Random random = new Random();
        int typeNum = random.nextInt(4);
        // 防止运算符数量为0
        while (typeNum == 0) {
            typeNum = random.nextInt(4);
        }
        int[] type = {-1, -1, -1};// 0、1、2、3对应加减乘除
        int[] parameter = {randomGenerateNum(), -1, -1, -1};
        // 随机生成运算符和运算数
        for (int i = 0; i < typeNum; i++) {
            type[i] = random.nextInt(4);
            parameter[i + 1] = randomGenerateNum();
        }
        StringBuilder sb = new StringBuilder(String.valueOf(parameter[0]));
        for (int i = 0; i < typeNum && type[i] != -1 && parameter[i + 1] != -1; i++) {
            // 防止除以0的情况出现
            if (parameter[i + 1] == 0 && type[i] == 3) {
                type[i] = random.nextInt(3);
            }
            switch (type[i]) {
                case 0 -> sb.append(" + ");
                case 1 -> sb.append(" - ");
                case 2 -> sb.append(" * ");
                case 3 -> sb.append(" / ");
            }
            sb.append(parameter[i + 1]);
        }
        return sb.toString();
    }

    /**
     * 生成过程中不出现负数的随机题目
     *
     * @return 过程中不出现负数的随机题目
     */
    public String safeRandomGenerate() {
        String res = randomGenerate();
        while (Check.checkNegative(res)) {
            res = randomGenerate();
        }
        return res;
    }
}
