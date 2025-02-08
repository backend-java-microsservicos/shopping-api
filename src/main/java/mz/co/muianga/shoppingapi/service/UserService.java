package mz.co.muianga.shoppingapi.service;

import mz.co.muianga.shoppingclient.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    @Value("${USER_API_URL:http://localhost:8080/users/}")
    private String userApiUrl;

    public UserDTO getUserByCpf(String cpf, String key) {
        RestTemplate restTemplate = new RestTemplate();
        String url = userApiUrl + cpf;

        UriComponents builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("key", key)
                .build();

        /*UriComponents builder = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:8080").path("/users/" + cpf)
                .queryParam("key", key).build();*/

        ResponseEntity<UserDTO> response = restTemplate.getForEntity(builder.toString(), UserDTO.class);
        return response.getBody();
    }
}
