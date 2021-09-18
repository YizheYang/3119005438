import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Check {
    private static final String orig = "check/testText2/orig.txt";
    private static final String orig_add = "check/testText2/orig_0.8_add.txt";
    private static final String orig_del = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_del.txt";
    private static final String orig_dis_1 = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_dis_1.txt";
    private static final String orig_dis_10 = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_dis_10.txt";
    private static final String orig_dis_15 = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_dis_15.txt";

    public static void main(String[] args) throws IOException {
        String[] origList, testList;
        origList = splitText(getText(orig));
        testList = splitText(getText(orig_add));
        float result = kmp(origList, testList);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        System.out.println(decimalFormat.format(result));
    }

    /**
     * 从文件里获得字符串
     *
     * @param dir 文件的位置
     * @return 文件里的字符串
     * @throws IOException 文件找不到的异常
     */
    private static String getText(String dir) throws IOException {
        String text = null;
        File file = new File(dir);
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[is.available()];
        if (is.read(bytes) != -1) {
            text = new String(bytes);
            is.close();
        }
        return text;
    }

    /**
     * 将一大段文字先按双引号分开，再按逗号、句号、感叹号、问号、省略号分开，最后把句子中的符号全部删除
     * 这样设计是为了让双引号内的话不被独立开来，否则语义就会变得很奇怪
     *
     * @param text 原来的文段
     * @return 切割后的句子列表
     */
    private static String[] splitText(String text) {
//        Pattern p = Pattern.compile("[。！…][^”]|[。！…]$|[，\n\r]");
        Pattern p = Pattern.compile("[，。！？…\n\r]");
        Pattern pattern = Pattern.compile("[，。！…？][”]|[\n\r]");
        String[] array = pattern.split(text);
        ArrayList<String> list = new ArrayList<>();
        for (String s1 : array) {
            String[] t = p.split(s1);
            for (String temp : t) {
                while (temp.matches(".*[“”，。？！…：]+.*")) {
                    temp = temp.replaceAll("([“”，。？！…：]|\\s)+", "");
                }
                if (!temp.equals("")) {
                    list.add(temp);
                }
            }
        }
        String[] convert = new String[list.size()];
        return list.toArray(convert);
    }

    /**
     * 判断两个字符串的重复率
     *
     * @param orig   原本的句子
     * @param detect 要被检测的句子
     * @return 重复率
     */
    private static float detect(String orig, String detect) {
        int i, j;
        int length = 0;
        int tempLength;
        for (j = 0; j < detect.length() || j < orig.length(); j++) {
            tempLength = 0;
            for (i = j + 1; i <= detect.length(); i++) {
                if (orig.contains(detect.substring(j, i))) {
                    tempLength = i - j;
                } else {
                    break;
                }
            }
            length = Math.max(tempLength, length);
        }
        return ((float) length / detect.length());
    }

    /**
     * 判断两个字符串数组的相似度，思路是将每一对字符串的相似度累加起来，然后除以数组的长度
     *
     * @param orig   原本的字符串数组
     * @param detect 被检测的字符串数组
     * @return 两个字符串数组的相似度
     */
    private static float kmp(String[] orig, String[] detect) {
        float DuplicationRate = 0.0f;// 整个文本的相似度
        for (String s1 : detect) {
            float maxDuplicationRate = 0.0f;// 某一行文本的相似度
            for (String s2 : orig) {
                float tempRate = detect(s1, s2);// detect方法检测这两个语句的相似度
                if (tempRate > maxDuplicationRate) {
                    maxDuplicationRate = tempRate;
                }
                if (maxDuplicationRate == 1.0) {
                    break;
                }
            }
            DuplicationRate += maxDuplicationRate;
        }
        return (DuplicationRate / orig.length);
    }
}
