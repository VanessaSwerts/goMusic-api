package br.inatel.icc.goMusic.controller;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldCreateANewUser() throws Exception {
		String url = "/users";
		String data = "{\"name\":\"Vanessa Esteves\", \"email\":\"vanessaesteves@email.com\", \"password\":\"12345678\", \"country\":\"Brasil\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(url).content(data).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201))
				.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Vanessa Esteves"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.avatar").value("goMusic/goMusic-dev/user/default-avatar.png"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.country").value("Brasil"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalFollowers").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalFollowings").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalPlaylists").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalCreatedPlaylists").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalLikedPlaylists").value(0));
	}

	@Test
	@WithMockUser(username = "user@email.com", password = "12345678")
	public void shouldListAUser() throws Exception {
		String url = "/users/1";

		mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("User Default"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.avatar").value("goMusic/goMusic-dev/user/default-avatar.png"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.country").value("Brasil"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalFollowers").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalFollowings").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalPlaylists").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalCreatedPlaylists").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalLikedPlaylists").value(2));
	}

}
