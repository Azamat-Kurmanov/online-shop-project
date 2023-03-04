package ru.gb.winter.market.carts.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.product-service")
@Data
public class ProductServiceIntegrationProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
