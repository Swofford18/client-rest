package client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Configuration
public class RestTemplateConfiguration {

    private HttpHeaders header(){
        HttpHeaders header = new HttpHeaders();
        String usernamePassword = "adminX:adminX";
        byte[] encode = Base64Utils.encode(usernamePassword.getBytes());
        String base64EncString = "Basic " + new String(encode);
        header.set(org.springframework.http.HttpHeaders.AUTHORIZATION, base64EncString);
        header.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return header;
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(new MyRestTemplateInterceptor()));
        return restTemplate;
    }

    private class MyRestTemplateInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            request.getHeaders().addAll(header());
            return execution.execute(request, body);
        }
    }
}
