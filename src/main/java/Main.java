import utils.ClassUtils;
import utils.YamlUtils;

import java.util.List;

/**
 * yamlgen
 * Created by wangzhilei3 on 2018/1/9.
 */
public class Main {
    public static void main(String... args) throws Exception {
        //加载类，默认为 "E:\\yamlgen\\src\\main\\java\\model"
        List<Class> list = ClassUtils.getcClassList(null);
        YamlUtils.genYaml(list,null);
        //YamlUtils.genYaml(list.get(2),null);

    }


}
