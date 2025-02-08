package mz.co.muianga.shoppingapi.service;

import mz.co.muianga.shoppingclient.dto.UserDTO;
import mz.co.muianga.shoppingclient.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    public UserDTO getUserByCpf(String cpf) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/users/" + cpf;

            ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
            return response.getBody();
        }catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException();
        }
    }
}
