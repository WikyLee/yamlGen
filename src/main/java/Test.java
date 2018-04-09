import org.springframework.web.bind.annotation.RequestMapping;

/**
 * yamlgen
 * Created by wangzhilei3 on 2018/1/10.
 */
public class Test {
    public static void main(String... args) throws Exception {
        Class c = Class.forName("service.openApi.OpenApiServiceTermController");
        RequestMapping requestMapping = (RequestMapping) c.getAnnotation(RequestMapping.class);
        String s = "/queryServiceTerm/{serviceCode}";
        String[] strings = s.split("/");

        System.out.println(requestMapping.path()[0]);

    }
}
