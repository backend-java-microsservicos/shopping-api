package mz.co.muianga.shoppingapi.service;

import mz.co.muianga.shoppingclient.dto.UserDTO;
import mz.co.muianga.shoppingclient.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UserService {

    public UserDTO getUserByCpf(String cpf, String key) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/users/" + cpf;

            UriComponents builder = UriComponentsBuilder.newInstance()
                    .scheme("http").host("localhost:8080").path("/users/" + cpf)
                    .queryParam("key", key).build();

            ResponseEntity<UserDTO> response = restTemplate.getForEntity(builder.toString(), UserDTO.class);
            return response.getBody();
        }catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException();
        }
    }
}
