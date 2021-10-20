import java.io.*;
import java.util.ArrayList;

/**
 * description None
 * <p>
 * author ez_yang@qq.com
 * date 2021.10.19 下午 5:50
 **/
public class Judge {
    public static ArrayList<String> loadFile(String fileName) {
        String str;
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        ArrayList<String> al = new ArrayList<String>();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                al.add(str);
            }
            br.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return al;

    }

    public static boolean[] compare(ArrayList<String> a, ArrayList<String> b) {
        if (a.size() != b.size()) {
            System.out.println("Wrong File Length!");
        }
        int length = a.size();
        boolean record[] = new boolean[length];
        int i = 0;
        for (i = 0; i < length; i++) {
            if (!a.get(i).equals(b.get(i))) {
                record[i] = false;
            } else {
                record[i] = true;
            }
        }
        return record;
    }

    public static void write2File(boolean[] record) {
        int i;
        int tnum = 0, fnum = 0;
        StringBuilder tno = new StringBuilder();
        StringBuilder fno = new StringBuilder();
        for (i = 0; i < record.length; i++) {
            if (record[i] == true) {
                tnum++;
                tno.append(i + 1);
                tno.append(" ");
            } else {
                fnum++;
                fno.append(i + 1);
                fno.append(" ");
            }
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("Grade.txt"));
            out.write("Correct: " + tnum + "(" + tno + ")");
            out.write("\n");
            out.write("Wrong: " + fnum + "(" + fno + ")");
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}