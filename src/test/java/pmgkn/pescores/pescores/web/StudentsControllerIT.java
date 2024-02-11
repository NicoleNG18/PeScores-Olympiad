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
import pmgkn.pescores.pescores.domain.entity.ClassEntity;
import pmgkn.pescores.pescores.domain.entity.UserEntity;
import pmgkn.pescores.pescores.util.TestDataUtils;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testUser;
    private ClassEntity testClass;

    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("testEmail@abv.bg");
        testClass = testDataUtils.createClass(testUser);
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }


    @Test
    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
    void testGetAddStudents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/add")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("student-add"))
                .andExpect(model().attributeExists("classes"));
    }

    @Test
    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
    void testAddStudentsFails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
                        .param("gender", "FEMALE")
                        .param("studentNumber", "1")
                        .param("studentName", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/students/add"));
    }

    @Test
    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
    void testDeleteStudentFails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/students/delete/{idTeacher}/{id}", UUID.randomUUID(), testClass.getId()).with(csrf()))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("error"));
    }

}
