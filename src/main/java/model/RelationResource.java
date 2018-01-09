package model;

import com.jcloud.billing.fs.resourceorder.vo.ResourceOrderVo;

import java.util.Date;

/**
 * Created by zhaojunqiang on 2017/5/9.
 */
public class RelationResource {
    private String resourceId;//资源Id
    private String resourceName;//资源名称
    private String serviceCode;//资源类型
    private int billingType;//计费类型
    private String expireTime;//到期时间
    private String dataCenter;//
    private int autoRenew;//是否开通自动续费(0:未开通,1:已开通)单独设置
    private int lastTime;//倒计时
    private String remark;//关联资源特殊需求

    public RelationResource() {
    }

    public RelationResource(ResourceOrderVo resourceOrderVo) {
        this.resourceId = resourceOrderVo.getResourceId();
        this.serviceCode = resourceOrderVo.getServiceCode();
        this.dataCenter = resourceOrderVo.getRegion();
        this.billingType = resourceOrderVo.getBillingType();
        if (resourceOrderVo.getBillingType() == 3 && resourceOrderVo.getEndTime() != null) {
            this.expireTime = String.valueOf(resourceOrderVo.getEndTime().getTime());
            this.lastTime = (int) ((resourceOrderVo.getEndTime().getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24));
        }
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public int getBillingType() {
        return billingType;
    }

    public void setBillingType(int billingType) {
        this.billingType = billingType;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(String dataCenter) {
        this.dataCenter = dataCenter;
    }

    public int getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(int autoRenew) {
        this.autoRenew = autoRenew;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RelationResource{");
        sb.append("resourceId='").append(resourceId).append('\'');
        sb.append(", resourceName='").append(resourceName).append('\'');
        sb.append(", serviceCode='").append(serviceCode).append('\'');
        sb.append(", billingType=").append(billingType);
        sb.append(", expireTime='").append(expireTime).append('\'');
        sb.append(", dataCenter='").append(dataCenter).append('\'');
        sb.append(", autoRenew=").append(autoRenew);
        sb.append(", lastTime=").append(lastTime);
        sb.append(", remark='").append(remark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
