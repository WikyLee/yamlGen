package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * yamlgen
 * Created by wangzhilei3 on 2018/1/9.
 *
 * @since 1.6+
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
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        FileOutputStream outputStream = new FileOutputStream(file);
        //写入默认头
        writeDef(bufferedWriter, c);

        //根据变量写入参数
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            writeFiled(bufferedWriter, field);
        }

        bufferedWriter.flush();
        bufferedWriter.close();

    }

    /**
     * 公共头
     *
     * @param bufferedWriter
     * @param c
     * @throws Exception
     */
    public static void writeDef(BufferedWriter bufferedWriter, Class c) throws Exception {
        bufferedWriter.write("swagger: \"2.0\"");
        bufferedWriter.newLine();
        bufferedWriter.write("definitions:");
        bufferedWriter.newLine();
        bufferedWriter.write("  ");
        String name = c.getSimpleName();
        bufferedWriter.write(name.substring(0, 1).toLowerCase());
        bufferedWriter.write(name.substring(1));
        bufferedWriter.write(":");
        bufferedWriter.newLine();
        bufferedWriter.write("    x-jcloud-module: renewal");
        bufferedWriter.newLine();
        bufferedWriter.write(("    title: " + c.getSimpleName()));
        bufferedWriter.newLine();

        bufferedWriter.write("    type: object");
        bufferedWriter.newLine();

        bufferedWriter.write("    properties:");
        bufferedWriter.newLine();
    }

    /**
     * 当前仅支持 List String Integer Integer Date 以及包内Vo引用
     *
     * @param outputStream
     * @param field
     * @throws Exception
     */
    public static void writeFiled(BufferedWriter outputStream, Field field) throws Exception {
        String name = field.getType().getSimpleName();
        if (name.equals("String") || name.equals("Integer") || name.equals("Integer") || name.equals("int") || name.equals("BigDecimal")) {
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
    public static void writeBasicFiled(BufferedWriter outputStream, Field field) throws Exception {
        outputStream.write(("      " + field.getName() + ":"));
        outputStream.newLine();
        if (field.getType().getSimpleName().equals("int")) {
            outputStream.write(("        type: " + "integer"));
            outputStream.newLine();
        } else if (field.getType().getSimpleName().equals("BigDecimal")) {
            outputStream.write(("        type: " + "string"));
            outputStream.newLine();
        } else {
            String s = field.getType().getSimpleName().toLowerCase();
            outputStream.write(("        type: " + s));
            outputStream.newLine();
        }

    }

    /**
     * 引用类型
     *
     * @param outputStream
     * @param field
     * @throws Exception
     */
    public static void writeObjectFiled(BufferedWriter outputStream, Field field) throws Exception {


        outputStream.write(("      " + field.getName() + ":"));
        outputStream.newLine();
        String s = field.getType().getSimpleName();
        outputStream.write(("        $ref: \"../model/" + s + ".yaml#/definitions/" + field.getName() + "\""));
        outputStream.newLine();
    }

    /**
     * Date类型
     *
     * @param outputStream
     * @param field
     * @throws Exception
     */
    public static void writeDateFiled(BufferedWriter outputStream, Field field) throws Exception {
        outputStream.write(("      " + field.getName() + ":"));
        outputStream.newLine();

        outputStream.write("        type: string");
        outputStream.newLine();

        outputStream.write("        format: date-time");
        outputStream.newLine();
    }

    /**
     * List类型，仅支持四中泛型
     *
     * @param outputStream
     * @param field
     * @throws Exception
     */
    public static void writeListFiled(BufferedWriter outputStream, Field field) throws Exception {

        outputStream.write(("      " + field.getName() + ":"));
        outputStream.newLine();

        outputStream.write("        type: array");
        outputStream.newLine();

        outputStream.write("        items:");
        outputStream.newLine();

        Type fieldType = field.getGenericType();
        Type type = ((ParameterizedType) fieldType).getActualTypeArguments()[0];
        String name = type.getTypeName();
        String[] strings = name.split("\\.");
        name = strings[strings.length - 1];
        if (name.equals("String") || name.equals("Integer") || name.equals("Long")) {
            outputStream.write(("          type: " + name.toLowerCase()));
            outputStream.newLine();
        } else {
            String ss = name.substring(0, 1).toLowerCase() + name.substring(1);
            outputStream.write((("          $ref: \"../model/" + name + ".yaml#/definitions/" + ss) + "\""));
            outputStream.newLine();
        }
    }


    public static void nextLine(FileOutputStream outputStream) throws Exception {
        outputStream.write("\n".getBytes());
    }


}
