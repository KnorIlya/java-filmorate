package ru.yandex.practicum.filmorate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmController.class)
class FilmControllerTest {
    @MockBean
    FilmService filmService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    protected Film film;

    @BeforeEach
    public void beforeEach() {
        film = Film.builder()
                .id(1)
                .name("TestName")
                .description("Test")
                .releaseDate(LocalDate.now().minusDays(1))
                .duration(60)
                .build();
    }

    @Nested
    class Add {
        private final String url = "/films";

        @Test
        public void shouldOK_WhenFilmFilledOutCorrectly() throws Exception {
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isCreated());
        }

        @Test
        public void shouldBadRequest_WhenFilmWithoutName() throws Exception {
            film.setName(null);
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmNameIsBlank() throws Exception {
            film.setName(" ");
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmDescriptionNull() throws Exception {
            film.setDescription(null);
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmDescriptionIsBlank() throws Exception {
            film.setDescription("");
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmReleaseDateNull() throws Exception {
            film.setReleaseDate(null);
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(json)
                    ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmReleaseDateIsFuture() throws Exception {
            film.setReleaseDate(LocalDate.now().plusDays(10));
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmDurationNull() throws Exception {
            film.setDuration(null);
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }
    }

    @Nested
    class Update {
        private final String url = "/films";

        @Test
        public void shouldOK_WhenFilmFilledOutCorrectly() throws Exception {
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isOk());
        }

        @Test
        public void shouldBadRequest_WhenFilmWithoutName() throws Exception {
            film.setName(null);
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmNameIsBlank() throws Exception {
            film.setName(" ");
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmDescriptionNull() throws Exception {
            film.setDescription(null);
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmDescriptionIsBlank() throws Exception {
            film.setDescription("");
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmReleaseDateNull() throws Exception {
            film.setReleaseDate(null);
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmReleaseDateIsFuture() throws Exception {
            film.setReleaseDate(LocalDate.now().plusDays(10));
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

        @Test
        public void shouldBadRequest_WhenFilmDurationNull() throws Exception {
            film.setDuration(null);
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isBadRequest());
        }

    }

    @Nested
    class All {
        private final String url = "/films";

        @Test
        public void shouldOK_WhenFilmFilledOutCorrectly() throws Exception {
            String json = objectMapper.writeValueAsString(film);
            mockMvc.perform(MockMvcRequestBuilders.get(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andExpect(status().isOk());
        }
    }

}