package mz.co.muianga.shoppingapi.service;

import mz.co.muianga.shoppingclient.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Value("${PRODUCT_API_URL:http://localhost:8081/products/}")
    private String productApiUrl;

    public ProductDTO getProductByIdentifier(String identifier) {
        RestTemplate restTemplate = new RestTemplate();
        var url = productApiUrl + identifier;
        ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);

        return response.getBody();
    }
}
