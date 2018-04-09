package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * yamlgen
 * Created by wangzhilei3 on 2018/1/9.
 */
public class TestVo {

    private String name;
    private Integer age;
    private ServiceTerm serviceTerm;
    private List<ServiceTerm> serviceTermList;
    private List<String> stringList;
    private Date createTime;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ServiceTerm getServiceTerm() {
        return serviceTerm;
    }

    public void setServiceTerm(ServiceTerm serviceTerm) {
        this.serviceTerm = serviceTerm;
    }

    public List<ServiceTerm> getServiceTermList() {
        return serviceTermList;
    }

    public void setServiceTermList(List<ServiceTerm> serviceTermList) {
        this.serviceTermList = serviceTermList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
