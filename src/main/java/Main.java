import utils.ClassUtils;
import utils.ServiceClassUtils;
import utils.YamlUtils;
import utils.service.YamlServiceUtils;

import java.util.List;

/**
 * yamlgen
 * Created by wangzhilei3 on 2018/1/9.
 */
public class Main {
    public static void main(String... args) throws Exception {
        geModel();
        geService();

    }

    public static void geModel() throws Exception{
        //加载类，默认为 "E:\\yamlgen\\src\\main\\java\\model"
        List<Class> list = ClassUtils.getcClassList(null);
        YamlUtils.genYaml(list,null);
    }

    public static void geService() throws  Exception{
        //加载类，默认为 "E:\\yamlgen\\src\\main\\java\\service"
        List<Class> list = ServiceClassUtils.getcClassList("E:\\yamlgen\\src\\main\\java\\service");
        YamlServiceUtils.genYaml(list,null);
    }


}
