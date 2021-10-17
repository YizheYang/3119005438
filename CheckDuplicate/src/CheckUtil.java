public class CheckUtil {
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
    static float kmp(String[] orig, String[] detect) {
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
