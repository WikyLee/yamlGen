package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojunqiang on 2017/5/9.
 */
public class ListQuery {

    private String expireTime; //到期时间
    private String serviceCode; //产品类型
    private int lastTime;//倒计时
    private String resourceName;//资源名称
    private String resourceId;//资源ID
    private String dataCenter;//地域
    private int billingType;//计费类型
    private int autoRenew;//是否开通自动续费(0:未开通,1:已开通)
    private int associateResource=2;// 1:关联包年包月资源一并自动续费 2：关联包年包月资源不自动续费
    private int renewTime;//续费周期
    /*private String floatingIp;//公网Ip
    private List<String> fixedIps;//内网Ip*/
    private String databaseType;//数据库类型
    private String remark;//特殊需求
    private List<RelationResource> relationList = new ArrayList<RelationResource>();//资源列表

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ListQuery{");
        sb.append("expireTime='").append(expireTime).append('\'');
        sb.append(", serviceCode='").append(serviceCode).append('\'');
        sb.append(", lastTime=").append(lastTime);
        sb.append(", resourceName='").append(resourceName).append('\'');
        sb.append(", resourceId='").append(resourceId).append('\'');
        sb.append(", dataCenter='").append(dataCenter).append('\'');
        sb.append(", billingType=").append(billingType);
        sb.append(", autoRenew=").append(autoRenew);
        sb.append(", associateResource=").append(associateResource);
        sb.append(", renewTime=").append(renewTime);

        sb.append(", databaseType='").append(databaseType).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", relationList=").append(relationList);
        sb.append('}');
        return sb.toString();
    }


    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(String dataCenter) {
        this.dataCenter = dataCenter;
    }

    public int getBillingType() {
        return billingType;
    }

    public void setBillingType(int billingType) {
        this.billingType = billingType;
    }

    public int getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(int autoRenew) {
        this.autoRenew = autoRenew;
    }

    public int getAssociateResource() {
        return associateResource;
    }

    public void setAssociateResource(int associateResource) {
        this.associateResource = associateResource;
    }

    public int getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(int renewTime) {
        this.renewTime = renewTime;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RelationResource> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<RelationResource> relationList) {
        this.relationList = relationList;
    }
}
