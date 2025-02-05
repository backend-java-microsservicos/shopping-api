package mz.co.muianga.shoppingapi.service;

import mz.co.muianga.shoppingclient.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    public ProductDTO getProductByIdentifier(String identifier) {
        RestTemplate restTemplate = new RestTemplate();
        var url = "http://localhost:8081/products/" + identifier;
        ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);

        return response.getBody();
    }
}
