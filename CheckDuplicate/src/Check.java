import java.text.DecimalFormat;

public class Check {
    private static final String orig = "E:/CODE/ruan_jian_gong_cheng/3119005438/check/testText2/orig.txt";
    private static final String orig_add = "E:/CODE/ruan_jian_gong_cheng/3119005438/check/testText2/orig_0.8_add.txt";
    private static final String orig_del = "E:/CODE/ruan_jian_gong_cheng/3119005438/check/testText2/orig_0.8_del.txt";
    private static final String orig_dis_1 = "E:/CODE/ruan_jian_gong_cheng/3119005438/check/testText2/orig_0.8_dis_1.txt";
    private static final String orig_dis_10 = "E:/CODE/ruan_jian_gong_cheng/3119005438/check/testText2/orig_0.8_dis_10.txt";
    private static final String orig_dis_15 = "E:/CODE/ruan_jian_gong_cheng/3119005438/check/testText2/orig_0.8_dis_15.txt";
    private static final String result = "E:/CODE/ruan_jian_gong_cheng/3119005438/check/result.txt";

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("请输入正确的参数！");
            return;
        }
        String[] origList, testList;
        origList = TextUtil.splitText(FileUtil.getText(args[0]));
        testList = TextUtil.splitText(FileUtil.getText(args[1]));
        float result = CheckUtil.kmp(origList, testList);
        if (result != -1) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            FileUtil.outPut(args[0], args[1], args[2], decimalFormat.format(result));
            System.out.println("匹配完成，结果已输出到：" + args[2]);
        } else {
            System.out.println("匹配失败，请检查匹配文件是否存在！");
        }
    }
}
