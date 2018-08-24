package com.sunstar.product.control;

import com.sunstar.product.enty.User;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * @Author 黄昌焕
 * @Date 2018-08-24  17:02
 */
public class UserControllerTest {
    private final WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();

    @Test
    public void testCreateUser() throws Exception {
        final User user = new User();
        user.setName("Test");
        client.post().uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("name").isEqualTo("Test");
    }
}
