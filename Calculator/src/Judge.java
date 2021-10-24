import java.io.*;
import java.util.ArrayList;

/**
 * description None
 * <p>
 * author ez_yang@qq.com
 * date 2021.10.19 下午 5:50
 **/
public class Judge {
    public static boolean[] compare(String[] e, String[] a) {
        if (e.length != a.length) {
            throw new IllegalArgumentException("Wrong File Length!");
        }
        int length = e.length;
        boolean[] record = new boolean[length];
        for (int i = 0; i < length; i++) {
            String correct = Check.calculate(e[i].split(": ")[1]);
            record[i] = a[i].split(": ")[1].equals(correct);
        }
        return record;
    }

}