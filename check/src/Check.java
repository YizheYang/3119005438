import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Check {
    private static final String orig = "E:/CODE/ruan_jian_gong_cheng/3119005438/check/testText2/orig.txt";
    private static final String orig_add = "E:/CODE/ruan_jian_gong_cheng/3119005438/check/testText2/orig_0.8_add.txt";
    private static final String orig_del = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_del.txt";
    private static final String orig_dis_1 = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_dis_1.txt";
    private static final String orig_dis_10 = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_dis_10.txt";
    private static final String orig_dis_15 = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_dis_15.txt";
    private static final String result = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check/result.txt";

    public static void main(String[] args) throws IOException {
        String[] origList, testList;
        origList = splitText(getText(args[0]));
        testList = splitText(getText(args[1]));
        float result = kmp(origList, testList);
        if (result != -1) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            outPut(args[0], args[1], args[2], decimalFormat.format(result));
            System.out.println("匹配完成，结果已输出到：" + args[2]);
        } else {
            System.out.println("匹配失败，请检查匹配文件是否存在！");
        }
    }

    /**
     * 从文件里获得字符串
     *
     * @param dir 文件的位置
     * @return 文件里的字符串
     * @throws IOException 文件找不到的异常
     */
    private static String getText(String dir) throws IOException {
        String text;
        File file = new File(dir);
        if (!file.exists()) {
            return null;
        }
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        while ((text = br.readLine()) != null) {
            sb.append(text).append("\n");
        }
        br.close();
        isr.close();
        return sb.toString();
    }

    /**
     * 将结果输出到指定的文件
     *
     * @param orig   原文的地址
     * @param detect 被检测的文件的地址
     * @param target 输出结果的地址
     * @param result 查重的结果
     * @throws IOException 文件找不到的异常
     */
    private static void outPut(String orig, String detect, String target, String result) throws IOException {
        File file = new File(target);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                return;
            }
        }
        FileOutputStream fos = new FileOutputStream(file.getAbsolutePath(), true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
        LocalDateTime ldt = LocalDateTime.now();
        bw.write(ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        bw.newLine();
        bw.write(orig + " 与 " + detect + " 匹配的相似度为：" + result);
        bw.newLine();
        bw.close();
        fos.close();
    }

    /**
     * 将一大段文字先按双引号分开，再按逗号、句号、感叹号、问号、省略号分开，最后把句子中的符号全部删除
     * 这样设计是为了让双引号内的话不被独立开来，否则语义就会变得很奇怪
     *
     * @param text 原来的文段
     * @return 切割后的句子列表
     */
    private static String[] splitText(String text) {
        if (text == null) {
            return null;
        }
        Pattern p1 = Pattern.compile("[，。！…？][”]|[\n\r]");// 按双引号和空白分开
        Pattern p2 = Pattern.compile("[，。！？…\n\r]");// 按逗号、句号、感叹号、问号、省略号、空格分开
        String[] array = p1.split(text);
        ArrayList<String> list = new ArrayList<>();
        for (String s1 : array) {
            String[] t = p2.split(s1);
            for (String temp : t) {
                while (temp.matches(".*([“”，。？！…：]|\\s)+.*")) {
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
     * 判断两个字符串的相似度，原理是最长公共子串长度除以被检测的字符串长度
     *
     * @param orig   原本的句子
     * @param detect 要被检测的句子
     * @return 相似度
     */
    private static float detect(String orig, String detect) {
        int i, j;
        int length = 0;// 最大公共子串长度
        int tempLength;// 临时公共子串长度
        for (j = 0; j < detect.length() || j < orig.length(); j++) {
            tempLength = 0;
            for (i = j + 1; i <= detect.length(); i++) {
                if (orig.contains(detect.substring(j, i))) {// 一点点切割，从第一个字开始切割，依次递增切割
                    tempLength = i - j;// 包含切割后的子串，则最大长度+1，然后增加一个字符长度进入下一次判断
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
        if (orig == null || detect == null) {
            return -1;
        }
        float DuplicationRate = 0.0f;// 整个文本的相似度
        for (String s1 : detect) {
            float maxDuplicationRate = 0.0f;// 某一行文本的相似度
            for (String s2 : orig) {
                float tempRate = detect(s1, s2);// detect方法检测这两个语句的相似度
                if (tempRate > maxDuplicationRate) {
                    maxDuplicationRate = tempRate;
                }
                if (maxDuplicationRate == 1.0) {// 当相似度为1说明两个字符串一样
                    break;
                }
            }
            DuplicationRate += maxDuplicationRate;
        }
        return (DuplicationRate / orig.length);// 相似度之和除以字符串数组长度得出平均
    }
}
