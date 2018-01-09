package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * yamlgen
 * Created by wangzhilei3 on 2018/1/9.
 */
public class ClassUtils {
    public static List<Class> getcClassList(String path) {
        if (path == null) {
            path = "E:\\yamlgen\\src\\main\\java\\model";
        }
        File file = new File(path);
        file.isDirectory();
        File[] files = file.listFiles();
        List<Class> result = new ArrayList<Class>(files.length);
        for (File file1 : files) {
            try {
                String name = file1.getName();
                name = name.substring(0,name.length()-5);
                result.add(Class.forName("model." + name));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
