package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * yamlgen
 * Created by wangzhilei3 on 2018/1/9.
 *
 */

public class YamlUtils {
    /**
     * 生成yaml
     *
     * @param classList
     * @param path
     * @throws Exception
     */
    public static void genYaml(List<Class> classList, String path) throws Exception {
        for (Class c : classList) {
            genYaml(c, path);
        }
    }


    public static void genYaml(Class c, String path) throws Exception {
        if (path == null) {
            path = "E:\\yamlgen\\src\\main\\java\\yaml\\model\\";
        }
        File file = new File(path + c.getSimpleName() + ".yaml");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        //写入默认头
        writeDef(outputStream, c);
        //根据变量写入参数
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            writeFiled(outputStream, field);
        }

        outputStream.flush();
        outputStream.close();

    }

    /**
     * 公共头
     *
     * @param outputStream
     * @param c
     * @throws Exception
     */
    public static void writeDef(FileOutputStream outputStream, Class c) throws Exception {
        outputStream.write("swagger: \"2.0\"".getBytes());
        nextLine(outputStream);
        outputStream.write("definitions:".getBytes());
        nextLine(outputStream);
        outputStream.write("  ".getBytes());
        String name = c.getSimpleName();
        outputStream.write(name.substring(0, 1).toLowerCase().getBytes());
        outputStream.write(name.substring(1).getBytes());
        outputStream.write(":".getBytes());
        nextLine(outputStream);
        outputStream.write("    x-jcloud-module: renewal".getBytes());
        nextLine(outputStream);

        outputStream.write(("    title: " + c.getSimpleName()).getBytes());
        nextLine(outputStream);

        outputStream.write("    type: object".getBytes());
        nextLine(outputStream);

        outputStream.write("    properties:".getBytes());
        nextLine(outputStream);
    }

    /**
     * 当前仅支持 List String Integer Integer Date 以及包内Vo引用
     *
     * @param outputStream
     * @param field
     * @throws Exception
     */
    public static void writeFiled(FileOutputStream outputStream, Field field) throws Exception {
        String name = field.getType().getSimpleName();
        if (name.equals("String") || name.equals("Integer") || name.equals("Integer")) {
            writeBasicFiled(outputStream, field);
        } else if (field.getType().getPackage().getName().equals("model")) {
            writeObjectFiled(outputStream, field);
        } else if (field.getType().getSimpleName().equals("List")) {
            writeListFiled(outputStream, field);
        } else if (field.getType().getSimpleName().equals("Date")) {
            writeDateFiled(outputStream, field);
        }
    }

    /**
     * 基本类型
     *
     * @param outputStream
     * @param field
     * @throws Exception
     */
    public static void writeBasicFiled(FileOutputStream outputStream, Field field) throws Exception {

        outputStream.write(("      " + field.getName() + ":").getBytes());
        nextLine(outputStream);
        String s = field.getType().getSimpleName().toLowerCase();
        outputStream.write(("        type: " + s).getBytes());
        nextLine(outputStream);
    }

    /**
     * 引用类型
     *
     * @param outputStream
     * @param field
     * @throws Exception
     */
    public static void writeObjectFiled(FileOutputStream outputStream, Field field) throws Exception {


        outputStream.write(("      " + field.getName() + ":").getBytes());
        nextLine(outputStream);
        String s = field.getType().getSimpleName();
        outputStream.write(("        $ref: \"../model/" + s + ".yaml#/definitions/" + field.getName() + "\"").getBytes());
        nextLine(outputStream);
    }

    /**
     * Date类型
     *
     * @param outputStream
     * @param field
     * @throws Exception
     */
    public static void writeDateFiled(FileOutputStream outputStream, Field field) throws Exception {
        outputStream.write(("      " + field.getName() + ":").getBytes());
        nextLine(outputStream);

        outputStream.write("        type: string".getBytes());
        nextLine(outputStream);

        outputStream.write("        format: date-time".getBytes());
        nextLine(outputStream);
    }

    /**
     * List类型，仅支持四中泛型
     *
     * @param outputStream
     * @param field
     * @throws Exception
     */
    public static void writeListFiled(FileOutputStream outputStream, Field field) throws Exception {

        outputStream.write(("      " + field.getName() + ":").getBytes());
        nextLine(outputStream);

        outputStream.write("        type: array".getBytes());
        nextLine(outputStream);

        outputStream.write("        items:".getBytes());
        nextLine(outputStream);

        Type fieldType = field.getGenericType();
        Type type = ((ParameterizedType) fieldType).getActualTypeArguments()[0];
        String name = type.getTypeName();
        String[] strings = name.split("\\.");
        name = strings[strings.length - 1];
        if (name.equals("String") || name.equals("Integer") || name.equals("Long")) {
            outputStream.write(("          type: " + name.toLowerCase()).getBytes());
            nextLine(outputStream);
        } else {
            String ss = name.substring(0, 1).toLowerCase() + name.substring(1);
            outputStream.write((("          $ref: \"../model/" + name + ".yaml#/definitions/" + ss) + "\"").getBytes());
            nextLine(outputStream);
        }
    }


    public static void nextLine(FileOutputStream outputStream) throws Exception {
        outputStream.write("\n".getBytes());
    }


}
