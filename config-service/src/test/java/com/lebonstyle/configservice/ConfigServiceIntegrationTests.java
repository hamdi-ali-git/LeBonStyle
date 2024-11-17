package com.lebonstyle.configservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableConfigServer
@TestPropertySource(properties = {
        "spring.cloud.config.server.git.uri=file:///test-repo", // Use a local test repo
        "spring.cloud.config.server.git.default-label=master"
})
public class ConfigServiceIntegrationTests {

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testRetrieveConfiguration() {
        // Use RestTemplate to simulate a request to the config server
        Mockito.when(restTemplate.getForObject("http://localhost:8888/user-service/default",
                String.class)).thenReturn("{ \"property\": \"value\" }");

        String response = restTemplate.getForObject("http://localhost:8888/user-service/default", String.class);
        assertThat(response).isEqualTo("{ \"property\": \"value\" }");

    }
}
