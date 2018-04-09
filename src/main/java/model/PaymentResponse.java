package model;

/**
 * Created by wangzhilei3 on 2017/8/21.
 */
public class PaymentResponse {
    private String orderNumber;
    private String returnURL;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getReturnURL() {
        return returnURL;
    }

    public void setReturnURL(String returnURL) {
        this.returnURL = returnURL;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PaymentResponse{");
        sb.append("orderNumber='").append(orderNumber).append('\'');
        sb.append(", returnURL='").append(returnURL).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
