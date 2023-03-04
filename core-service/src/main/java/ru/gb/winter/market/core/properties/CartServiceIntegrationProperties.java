package ru.gb.winter.market.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.cart-service")
@Data
public class CartServiceIntegrationProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
