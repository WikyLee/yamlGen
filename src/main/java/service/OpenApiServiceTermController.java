package service;


import model.ServiceTerm;
import org.springframework.web.bind.annotation.*;
import result.OpenApiResult;
import result.ServiceTermResult;

import java.util.Map;

/**
 * jcloud-renewal
 * Created by wangzhilei3 on 2018/1/9.
 */
@RestController
@RequestMapping(value = "/renewal")
public class OpenApiServiceTermController {

    @RequestMapping(path = "/queryServiceTerm/{serviceCode}", method = RequestMethod.GET)
    public OpenApiResult<ServiceTermResult> query(@PathVariable("serviceCode") String serviceCode) {
        return null;
    }

    @RequestMapping(path = "/query/{serviceCode}:delete", method = RequestMethod.GET)
    public OpenApiResult<ServiceTermResult> query(@PathVariable(value = "serviceCode") String serviceCode,
                                                  @RequestParam(value = "filters", required = false) Map<String, String> filters,
                                                  @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                  @RequestParam(value = "pageSize") Integer pageSize,
                                                  @RequestHeader(value = "x-jcloud-pin") String pin,
                                                  @RequestHeader(value = "x-jcloud-erp", required = false) String erp
    ) {
        return null;
    }


    @RequestMapping(path = "/postQuery/{serviceCode}", method = RequestMethod.POST)
    public OpenApiResult<ServiceTermResult> postQuery(@PathVariable(value = "serviceCode") String serviceCode,
                                                      @RequestParam(value = "filters", required = false) Map<String, String> filters,
                                                      @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                      @RequestParam(value = "pageSize") Integer pageSize,
                                                      @RequestHeader(value = "x-jcloud-pin") String pin,
                                                      @RequestHeader(value = "x-jcloud-erp", required = false) String erp,
                                                      @RequestBody() ServiceTerm serviceTerm
    ) {
        return null;
    }

}