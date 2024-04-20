package pmgkn.pescores.pescores.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import pmgkn.pescores.pescores.domain.entity.SchoolEntity;
import pmgkn.pescores.pescores.util.TestDataUtils;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class UserRegisterControllerIT {

    @Autowired
    private TestDataUtils testDataUtils;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        testDataUtils.createSchool();
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    @Transactional
    void testGetRegisterShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("schools"));
    }

    @Test
    @Transactional
    void testRegistrationWorksCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .param("firstName", "Nicole")
                        .param("lastName", "Georgieva")
                        .param("email", "userEmail@abv.bg")
                        .param("school", "PMG Prof. Emanuil Ivanov")
                        .param("password", "Test1234@")
                        .param("confirmPassword", "Test1234@")
                        .with(csrf())).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));

    }

    @Test
    @Transactional
    void testRegistrationWithIncorrectData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .param("firstName", "Nicole")
                        .param("lastName", "")
                        .param("email", "testEmail@abv.bg")
                        .param("school", "PMG Prof. Emanuil Ivanov")
                        .param("password", "Test")
                        .param("confirmPassword", "Test")
                        .with(csrf())).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));

    }
}
