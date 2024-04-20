package pmgkn.pescores.pescores.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.util.TestDataUtils;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class NormsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private UserEntity userEntity;

    @Autowired
    private TestDataUtils testDataUtils;

    @BeforeEach
    void setUp() {
        userEntity=testDataUtils.createTestUser("userEmail@abv.bg");
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    @WithMockUser(username = "userEmail@abv.bg", roles = "USER")
    void testJumpPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/jump")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("jump"));

    }

    @Test
    @WithMockUser(username = "userEmail@abv.bg", roles = "USER")
    void testTtestPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tTest")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("t-test"));

    }

    @Test
    @WithMockUser(username = "userEmail@abv.bg", roles = "USER")
    void testDenseBallPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/denseBall")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("dense-ball"));

    }

    @Test
    @WithMockUser(username = "userEmail@abv.bg", roles = "USER")
    void testThirtyMetersPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/thirty")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("thirty-meters"));

    }

    @Test
    @WithMockUser(username = "userEmail@abv.bg", roles = "USER")
    void testTwoHundredMetersPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/twoHundred")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("two-hundred-meters"));

    }
}
