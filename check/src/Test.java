public class Test {
    public static void main(String[] args) {
//        testTextUtil();
        testCheckUtil();
    }

    private static void testTextUtil() {
        String text = """
                我想“你还是先管好自己吧……”
                她说“ABasdsadas！”
                今天是个好日子！我想，这是快过年了吧……妈妈说：“快来帮忙啊。”
                这就叫“六”学！""";
        String[] temp = TextUtil.splitText(text);
        int i = 0;
        for (String s : temp) {
            System.out.println(i + ": " + s);
            i++;
        }
    }

    private static void testCheckUtil() {
        String[] s1 = {"1234565432"};
        String[] s2 = {"12321"};
        System.out.println(CheckUtil.kmp(s1, s2));
    }
}
