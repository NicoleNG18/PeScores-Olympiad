package pmgkn.pescores.pescores.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class HomeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHomePageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));

    }

    @Test
    void testContactPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contact")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));

    }
}
