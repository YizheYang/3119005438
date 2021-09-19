import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtil {
    /**
     * 从文件里获得字符串
     *
     * @param dir 文件的位置
     * @return 文件里的字符串
     */
    static String getText(String dir) {
        String text;
        File file = new File(dir);
        if (!file.exists()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            br.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 将结果输出到指定的文件
     *
     * @param orig   原文的地址
     * @param detect 被检测的文件的地址
     * @param target 输出结果的地址
     * @param result 查重的结果
     */
    static void outPut(String orig, String detect, String target, String result) {
        File file = new File(target);
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
