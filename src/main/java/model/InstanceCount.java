package model;

/**实例数
 * Created by wangzhilei3 on 2017/8/8.
 */
public class InstanceCount {
    private String instanceAll;
    private String instanceExpired;
    private String instanceRunning;
    private String instanceToExpire;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AutoRenew{");
        sb.append("instanceAll=").append(instanceAll);
        sb.append(", instanceExpired='").append(instanceExpired).append('\'');
        sb.append(", instanceRunning='").append(instanceRunning).append('\'');
        sb.append(", instanceExpired='").append(instanceExpired).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getInstanceAll() {
        return instanceAll;
    }

    public void setInstanceAll(String instanceAll) {
        this.instanceAll = instanceAll;
    }

    public String getInstanceExpired() {
        return instanceExpired;
    }

    public void setInstanceExpired(String instanceExpired) {
        this.instanceExpired = instanceExpired;
    }

    public String getInstanceRunning() {
        return instanceRunning;
    }

    public void setInstanceRunning(String instanceRunning) {
        this.instanceRunning = instanceRunning;
    }

    public String getInstanceToExpire() {
        return instanceToExpire;
    }

    public void setInstanceToExpire(String instanceToExpire) {
        this.instanceToExpire = instanceToExpire;
    }
}
