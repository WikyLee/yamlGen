package utils.service;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @since 1.6+
 * yamlgen
 * Created by wangzhilei3 on 2018/1/10.
 */
public class YamlServiceUtils {

    public static void genYaml(List<Class> classList, String path) throws Exception {
        for (Class c : classList) {
            genYaml(c, path);
        }
    }


    public static void genYaml(Class c, String path) throws Exception {
        if (path == null) {
            path = "E:\\yamlgen\\src\\main\\java\\yaml\\service\\";
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


        //根据方法写入信息
        Method[] methods = c.getDeclaredMethods();
        List<PathVariable> pathVariableList = new ArrayList<PathVariable>();
        for (Method method : methods) {
            writeMethod(bufferedWriter, method, pathVariableList);
        }
        writePathParams(bufferedWriter, pathVariableList);
        bufferedWriter.flush();
        bufferedWriter.close();

    }


    public static void writeDef(BufferedWriter bufferedWriter, Class c) throws Exception {
        bufferedWriter.write("swagger: \"2.0\"");
        bufferedWriter.newLine();
        writeNewLine(bufferedWriter, "info:");
        writeNewLine(bufferedWriter, "  title: JCLOUD Renewal API");
        writeNewLine(bufferedWriter, "  description: API related to Renewal");
        RequestMapping basePath = (RequestMapping) c.getAnnotation(RequestMapping.class);
        writeNewLine(bufferedWriter, "basePath: " + basePath.value()[0]);
        writeNewLine(bufferedWriter, "paths:");
    }

    /**
     * @param bufferedWriter
     * @param method
     * @throws Exception
     */
    public static void writeMethod(BufferedWriter bufferedWriter, Method method, List<PathVariable> pathVariableList) throws Exception {
        RequestMapping requestMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
        String message = requestMapping.path()[0];

        writeNewLine(bufferedWriter, "  \"" + message + "\":");
        if (requestMapping.method()[0].equals(RequestMethod.GET)) {
            writeNewLine(bufferedWriter, "    get:");
        } else {
            writeNewLine(bufferedWriter, "    post:");
        }
        String[] strings = message.split(":");
        String action = null;
        if (strings != null && strings.length > 1 && strings[strings.length - 1] != null) {
            action = strings[strings.length - 1];
        }
        message = "      operationId: " + message.split("/")[1];
        if (action!=null){
            message = message+action;
        }
        writeNewLine(bufferedWriter, message);
        //写入请求参数
        Parameter[] parameters = method.getParameters();
        writeParams(bufferedWriter, parameters);


        writeNewLine(bufferedWriter, "      responses:");
        writeNewLine(bufferedWriter, "        200:");
        writeNewLine(bufferedWriter, "          description: OK");
        writeNewLine(bufferedWriter, "          schema:");
        writeNewLine(bufferedWriter, "            properties:");
        writeNewLine(bufferedWriter, "              result:");
        Type result = method.getGenericReturnType();
        Type type = ((ParameterizedType) result).getActualTypeArguments()[0];
        //返回结果的泛型参数
        String name = type.getTypeName();
        //Class c = Class.forName("result."+name);
        String nameUp = name.substring(7, name.length() - 6);
        name = nameUp.substring(0, 1).toLowerCase() + nameUp.substring(1);

        writeNewLine(bufferedWriter, "                $ref: \"../model/" + nameUp + ".yaml#/definitions/" + name + "\"");
        writeNewLine(bufferedWriter, "              requestId:");
        writeNewLine(bufferedWriter, "                type: string");
        writeNewLine(bufferedWriter, "        404:");
        writeNewLine(bufferedWriter, "          description: NOT_FOUND");
        pathVariableList.addAll(getPathParams(parameters));


    }

    public static List<PathVariable> getPathParams(Parameter[] parameters) throws Exception {
        if (parameters == null || parameters.length == 0) {
            return null;
        } else {
            List<PathVariable> pathVariableList = new ArrayList<PathVariable>();

            for (Parameter parameter : parameters) {
                PathVariable pathVariable = parameter.getAnnotation(PathVariable.class);

                if (pathVariable != null) {
                    pathVariableList.add(pathVariable);
                }
            }
            return pathVariableList;
        }
    }

    public static void writePathParams(BufferedWriter bufferedWriter, List<PathVariable> pathVariableList) throws Exception {
        if (pathVariableList == null || pathVariableList.size() == 0) {
            return;
        }
        Set<String> nameSet = new HashSet<String>();
        writeNewLine(bufferedWriter, "parameters:");
        for (PathVariable pathVariable : pathVariableList) {

            String name = pathVariable.value();
            if (nameSet.contains(name)) {
                break;
            }
            writeNewLine(bufferedWriter, "  " + name + ":");
            writeNewLine(bufferedWriter, "    name: " + name);
            writeNewLine(bufferedWriter, "    in: path");
            writeNewLine(bufferedWriter, "    required: true");
            writeNewLine(bufferedWriter, "    type: string");
            nameSet.add(name);
        }
    }

    public static void writeParams(BufferedWriter bufferedWriter, Parameter[] parameters) throws Exception {
        if (parameters == null || parameters.length == 0) {
            return;
        } else {

            for (Parameter parameter : parameters) {
                RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
                if (requestParam != null || requestBody != null) {
                    writeNewLine(bufferedWriter, "      parameters:");
                    break;
                }
            }


            for (Parameter parameter : parameters) {
                RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
                if (requestParam != null || requestBody != null) {
                    writeParam(bufferedWriter, parameter);
                }
            }
        }
    }

    public static void writeParam(BufferedWriter bufferedWriter, Parameter parameter) throws Exception {
        RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
        if (requestParam != null) {
            String name = requestParam.value();
            boolean required = requestParam.required();
            if (parameter.getType().getSimpleName().equals("Map")) {
                writeNewLine(bufferedWriter, "        - name: filters");
                writeNewLine(bufferedWriter, "          in: query");
                writeNewLine(bufferedWriter, "          type: array");
                writeNewLine(bufferedWriter, "          items:");
                writeNewLine(bufferedWriter, "            $ref: \"../../common/model/Filter.yaml#/definitions/filter\"");
            } else {
                writeNewLine(bufferedWriter, "        - name: " + name);
                writeNewLine(bufferedWriter, "          in: query");
                writeNewLine(bufferedWriter, "          type: " + parameter.getType().getSimpleName().toLowerCase());
                writeNewLine(bufferedWriter, "          required: " + required);
            }
        }
        RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
        if (requestBody != null) {
            boolean required = requestBody.required();
            String name = parameter.getType().getSimpleName();
            String nameLow = name.substring(0, 1).toLowerCase() + name.substring(1);
            writeNewLine(bufferedWriter, "        - name: " + nameLow);
            writeNewLine(bufferedWriter, "          in: body");
            writeNewLine(bufferedWriter, "          schema:");
            writeNewLine(bufferedWriter, "            $ref: " + "\"../model/" + name + ".yaml#/definitions/" + nameLow + "\"");

        }


    }

    public static void writeNewLine(BufferedWriter bufferedWriter, String message) throws Exception {
        bufferedWriter.write(message);
        bufferedWriter.newLine();
    }


}
