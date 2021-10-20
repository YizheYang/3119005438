/**
 * description None
 * <p>
 * author ez_yang@qq.com
 * date 2021.10.19 下午 4:58
 **/
public class Main {
    static boolean generateMode = false;
    static boolean judgeMode = false;

    static String exerciseFilePath;
    static String answerFilePath;
    static int num = -1;
    static int range = -1;

    public static void main(String[] args) {
        handleArgument(args);
        generate();
    }

    /**
     * 生成题目及答案并保存
     */
    private static void generate() {
        StringBuilder sb_question = new StringBuilder();
        StringBuilder sb_answer = new StringBuilder();
        Generate generate = new Generate(range);
        for (int i = 0; i < num; i++) {
            String[] temp = generate.safeRandomGenerate();
            sb_question.append(i + 1).append(".").append(temp[0]).append("\n");
            sb_answer.append(i + 1).append(".").append(temp[1]).append("\n");
        }
        sb_question.deleteCharAt(sb_question.length() - 1);
        sb_answer.deleteCharAt(sb_answer.length() - 1);
        FileUtil.outPut("./Exercises.txt", sb_question.toString());
        FileUtil.outPut("./Answer.txt", sb_answer.toString());
    }

    private static void handleArgument(String[] args) {
        if (args.length < 2 || args.length > 4 || args.length % 2 != 0) {
            throw new IllegalArgumentException("wrong argument:argument's number is wrong!");
        }
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-n" -> {
                    num = Integer.parseInt(args[i + 1]);
                    generateMode = true;
                }
                case "-r" -> {
                    range = Integer.parseInt(args[i + 1]);
                    generateMode = true;
                }
                case "-e" -> {
                    exerciseFilePath = args[i + 1];
                    judgeMode = true;
                }
                case "-a" -> {
                    answerFilePath = args[i + 1];
                    judgeMode = true;
                }
                default -> throw new IllegalArgumentException("unknown argument");
            }
        }
        if (generateMode == judgeMode) {
            throw new IllegalArgumentException("wrong argument:confirm mode is generate or judge?");
        }
        if (num < 1) {
            throw new IllegalArgumentException("wrong argument:-n must bigger than 0");
        }
        if (range < 1) {
            throw new IllegalArgumentException("wrong argument:-r must bigger than 0");
        }
    }
}
