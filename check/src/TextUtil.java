import java.util.ArrayList;
import java.util.regex.Pattern;

public class TextUtil {
    /**
     * 将一大段文字先按双引号分开，再按逗号、句号、感叹号、问号、省略号分开，最后把句子中的符号全部删除
     * 这样设计是为了让双引号内的话不被独立开来，否则语义就会变得很奇怪
     *
     * @param text 原来的文段
     * @return 切割后的句子列表
     */
    static String[] splitText(String text) {
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
}
