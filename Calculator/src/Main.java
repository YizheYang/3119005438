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
    static int num = 1;
    static int range = -1;

    public static void main(String[] args) {
        handleArgument(args);
        if (generateMode) {
            generate();
        } else if (judgeMode) {
            judge();
        }
    }

    /**
     * 对输入的答案进行判断
     */
    private static void judge() {
        if (exerciseFilePath == null || answerFilePath == null) {
            throw new IllegalArgumentException("wrong argument:path is null");
        }
        String e = FileUtil.getText(exerciseFilePath);
        String a = FileUtil.getText(answerFilePath);
        String[] exercise = new String[0];
        String[] answer = new String[0];
        if (e != null) {
            exercise = e.split("\n");
        }
        if (a != null) {
            answer = a.split("\n");
        }
        if (exercise.length == 0 || answer.length == 0) {
            throw new IllegalArgumentException("wrong argument:exercise or answer is null");
        }
        boolean[] result = Judge.compare(exercise, answer);
        int i = 0, j = 0, k = 0;
        StringBuilder t = new StringBuilder("(");
        StringBuilder f = new StringBuilder("(");
        for (boolean res : result) {
            if (res) {
                t.append(i + 1).append(",");
                j++;
            } else {
                f.append(i + 1).append(",");
                k++;
            }
            i++;
        }
        if (t.toString().charAt(t.length() - 1) == ',') {
            t.replace(t.length() - 1, t.length(), ")");
        } else {
            t.append(")");
        }
        if (f.toString().charAt(f.length() - 1) == ',') {
            f.replace(f.length() - 1, f.length(), ")");
        } else {
            f.append(")");
        }
        t.insert(0, "Correct:" + j);
        f.insert(0, "Wrong:" + k);
        FileUtil.outPut("./Grade.txt", t + "\n" + f);
    }

    /**
     * 生成题目及答案并保存
     */
    private static void generate() {
        if (range < 1) {
            throw new IllegalArgumentException("wrong argument:-r must bigger than 0");
        }
        StringBuilder sb_question = new StringBuilder();
        StringBuilder sb_answer = new StringBuilder();
        Generate generate = new Generate(range);
        for (int i = 0; i < num; i++) {
            String[] temp = generate.safeRandomGenerate();
            sb_question.append(i + 1).append(": e = ").append(temp[0]).append("\n");
            sb_answer.append(i + 1).append(": ").append(temp[1]).append("\n");
        }
        sb_question.deleteCharAt(sb_question.length() - 1);
        sb_answer.deleteCharAt(sb_answer.length() - 1);
        FileUtil.outPut("./Exercises.txt", sb_question.toString());
        FileUtil.outPut("./Answer.txt", sb_answer.toString());
    }

    /**
     * 识别处理参数
     *
     * @param args 参数
     */
    private static void handleArgument(String[] args) {
        if (args.length < 1 || args.length > 4) {
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
    }
}
