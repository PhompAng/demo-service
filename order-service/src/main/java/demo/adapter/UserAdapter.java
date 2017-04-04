package demo.adapter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAdapter {

    @Value("${user_service.url}")
    private String userServiceUrl;

    @Value("${user_service.port}")
    private String userServicePort;

    public User getUserDetail(long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String userService = "http://"+userServiceUrl+":"+userServicePort+"/user/";
        String url = userService + userId;
        User user = restTemplate.getForObject(url, User.class);
        return  user;
    }

}


