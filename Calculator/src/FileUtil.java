import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * description None
 * <p>
 * author ez_yang@qq.com
 * date 2021.10.19 下午 5:15
 **/
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
     * @param target 输出结果的地址
     * @param result 查重的结果
     */
    static void outPut(String target, String result) {
        File file = new File(target);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return;
                }
            }
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
            bw.write(result);
            bw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
