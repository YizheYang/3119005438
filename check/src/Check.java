import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Check {

    private static final String orig = "check/testText2/orig.txt";
    private static final String orig_add = "check/testText2/orig_0.8_add.txt";
    private static final String orig_del = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_del.txt";
    private static final String orig_dis_1 = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_dis_1.txt";
    private static final String orig_dis_10 = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_dis_10.txt";
    private static final String orig_dis_15 = "E:\\CODE\\ruan_jian_gong_cheng\\3119005438\\check\\testText2\\orig_0.8_dis_15.txt";

    public static void main(String[] args) {
        ArrayList<String> origList = null, testList = null;
        ArrayList<Float> rate = new ArrayList<>();
        try {
            origList = splitText(getText(orig));
            testList = splitText(getText(orig_add));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if (origList != null && testList != null) {
//            int length = Math.min(origList.size(), testList.size());
//            int index = 0;
//            while (index < length) {
//                rate.add(KMP(origList.get(index), testList.get(index)));
//                index++;
//            }
//        }
//        float all = 0;
//        for (Float aFloat : rate) {
//            all += aFloat;
//        }
//        System.out.println(all / rate.size());
        float result = 0;
        if (origList != null && testList != null) {
            String[] o = new String[origList.size()];
            String[] t = new String[testList.size()];
            result = kmp(origList.toArray(o), testList.toArray(t));
        }
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
        String text;
        File file = new File(dir);
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        text = new String(bytes);
        is.close();
        return text;
    }

    /**
     * 将一大段文字按逗号、句号和换行分割开来，并且将句子里的空格删除
     *
     * @param text 原来的文段
     * @return 切割后的句子列表
     */
    private static ArrayList<String> splitText(String text) {
        StringTokenizer st = new StringTokenizer(text, "(，|。\n)+?", true);
        ArrayList<String> list = new ArrayList<>();
        while (st.hasMoreElements()) {
            String temp = st.nextToken();
            if (!temp.matches("(，|。|\n)+?")) {
                while (temp.contains(" ")) {
                    temp = temp.replace(" ", "");
                }
                list.add(temp);
            }
        }
        return list;
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

    private static float kmp(String[] orig, String[] detect) {
        float DuplicationRate = 0.0f;//整个文本的相似度
        for (String s1 : detect) {
            float maxDuplicationRate = 0.0f;//某一行文本的相似度
            for (String s2 : orig) {
                float tempRate = detect(s1, s2);//detect方法检测这两个语句的相似度
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
