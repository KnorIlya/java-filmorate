package ru.yandex.practicum.filmorate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    protected User user;

    @BeforeEach
    public void beforeEach() {
        user = User.builder()
                .id(1)
                .login("Test123")
                .name("TestName")
                .email("test@mail.ru")
                .birthday(LocalDate.now().minusYears(1))
                .build();
    }

    @Nested
    class Create {
        private final String url = "/users";

        @Test
        public void shouldOK_WhenUserFilledOutCorrectly() throws Exception {
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isCreated());
        }

        @Test
        public void shouldBadRequest_WhenUserWithoutEmail() throws Exception {
            user.setEmail(null);
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenUserEmailIncorrect() throws Exception {
            user.setEmail("email.com");
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenUserBirthdayIsFuture() throws Exception {
            user.setBirthday(LocalDate.now().plusYears(20));
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenUserBirthdayNull() throws Exception {
            user.setBirthday(null);
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenUserLoginNull() throws Exception {
            user.setLogin(null);
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }
    }

    @Nested
    class Update {
        private final String url = "/users";

        @Test
        public void shouldOK_WhenUserFilledOutCorrectly() throws Exception {
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isOk());
        }

        @Test
        public void shouldBadRequest_WhenUserWithoutEmail() throws Exception {
            user.setEmail(null);
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenUserEmailIncorrect() throws Exception {
            user.setEmail("email.com");
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenUserBirthdayIsFuture() throws Exception {
            user.setBirthday(LocalDate.now().plusYears(20));
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenUserBirthdayNull() throws Exception {
            user.setBirthday(null);
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenUserLoginNull() throws Exception {
            user.setLogin(null);
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }
    }

    @Nested
    class All {
        private final String url = "/users";

        @Test
        public void shouldOK_WhenUserFilledOutCorrectly() throws Exception {
            String json = objectMapper.writeValueAsString(user);
            mockMvc.perform(MockMvcRequestBuilders.get(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isOk());
        }
    }
}