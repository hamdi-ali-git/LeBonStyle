package com.lebonstyle.configservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.config.server.environment.NativeEnvironmentRepository;
import org.springframework.cloud.config.server.resource.ResourceController;
import org.springframework.mock.env.MockEnvironment;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class ConfigServiceMockTests {

    @Autowired
    private ResourceController resourceController;

    @Test
    void testRetrieveMockedConfiguration() {
        // Create a mock for EnvironmentRepository
        EnvironmentRepository repository = mock(EnvironmentRepository.class);

        // Create a mocked PropertySource
        PropertySource propertySource = new PropertySource("mockSource", Map.of(
                "test.key", "test-value"
        ));

        // Create a mock Environment and add the mocked PropertySource
        Environment mockEnvironment = new Environment("user-service",
                "default", "master");
        mockEnvironment.add(propertySource);

        Mockito.when(repository.findOne("user-service",
                "default", "master")).thenReturn(mockEnvironment);

        // Verify the mocked repository behavior
        Environment returnedEnv = repository.findOne("user-service",
                "default", "master");
        assertThat(returnedEnv.getPropertySources().getFirst().getSource().get("test.key")).isEqualTo("test-value");
    }

    @Test
    void testNonExistentConfiguration() {
        EnvironmentRepository repository = mock(EnvironmentRepository.class);
        // Mock the repository to return an empty Environment
        Mockito.when(repository.findOne("nonexistent-service",
                "default", "master"))
                .thenReturn(new Environment("nonexistent-service",
                        "default", "master"));
        // Verify that the returned environment is empty
        Environment returnedEnv = repository.findOne("nonexistent-service",
                "default", "master");
        assertThat(returnedEnv.getPropertySources()).isEmpty();
    }
}
