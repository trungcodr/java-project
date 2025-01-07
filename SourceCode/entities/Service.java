package entities;

import java.math.BigDecimal;

public class Service {
    private static int autoId;
    private String serviceId;
    private String serviceName;
    private BigDecimal servicePrice;

    public Service( String serviceName, BigDecimal servicePrice) {
        this.serviceId = "S" + ++autoId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    @Override
    public String toString() {
        return serviceId + "," + serviceName + "," + servicePrice;
    }


}
