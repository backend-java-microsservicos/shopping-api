package mz.co.muianga.shoppingapi.service;

import mz.co.muianga.shoppingclient.dto.ProductDTO;
import mz.co.muianga.shoppingclient.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    public ProductDTO getProductByIdentifier(String identifier) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            var url = "http://localhost:8081/products/" + identifier;
            ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);

            return response.getBody();
        } catch(HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException();
        }
    }
}
