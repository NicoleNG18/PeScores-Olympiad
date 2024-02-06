package pmgkn.pescores.pescores.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pmgkn.pescores.pescores.util.TestDataUtils;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    @BeforeEach
    void setUp() {
        testDataUtils.createTestUser("testEmail@abv.bg");
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
    void testClassesPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/classes")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("classes"));
    }

    @Test
    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
    void testAddClassPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/classes/add")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("classes"));
    }
}
