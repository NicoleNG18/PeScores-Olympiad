//package pmgkn.pescores.pescores.web;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import pmgkn.pescores.pescores.domain.entity.ClassEntity;
//import pmgkn.pescores.pescores.domain.entity.UserEntity;
//import pmgkn.pescores.pescores.util.TestDataUtils;
//
//import java.util.UUID;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ClassesControllerIT {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TestDataUtils testDataUtils;
//
//    private UserEntity testUser;
//    private ClassEntity testClass;
//
//    @BeforeEach
//    void setUp() {
//        testUser = testDataUtils.createTestUser("testEmail@abv.bg");
//        testClass = testDataUtils.createClass(testUser);
//    }
//
//    @AfterEach
//    void tearDown() {
//        testDataUtils.cleanUpDatabase();
//    }
//
//    @Test
//    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
//    void testClassesPageShowsUp() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/classes")
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("classes"));
//    }
//
//    @Test
//    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
//    void testAddClassPageShowsUp() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/classes/add")
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("add-class"));
//    }
//
//    @Test
//    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
//    void testAddingClassWorksCorrectly() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/classes/add")
//                        .param("className", "8A")
//                        .param("classNum", "8")
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection());
//    }
//
//    @Test
//    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
//    void testAddingClassDontWorksCorrectly() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/classes/add")
//                        .param("className", "")
//                        .param("classNum", "8")
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/classes/add"));
//
//    }
//
//    @Test
//    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
//    void testGetCurrentClassFails() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/classes/{idTeacher}/{id}", UUID.randomUUID(), testClass.getId())
//                        .with(csrf()))
//                .andExpect(status().is4xxClientError())
//                .andExpect(view().name("error"));
//    }
//
//    @Test
//    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
//    void testGetEditClassFails() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/classes/edit/{idTeacher}/{id}", UUID.randomUUID(), testClass.getId())
//                        .with(csrf()))
//                .andExpect(status().is4xxClientError())
//                .andExpect(view().name("error"));
//    }
//
//    @Test
//    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
//    void testGetEditClassPatchCorrect() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.patch("/classes/edited/{id}", testClass.getId())
//                        .param("classNum", "4")
//                        .param("className", "9A")
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/classes/" + testUser.getId() + "/" + testClass.getId()));
//    }
//
//    @Test
//    @WithMockUser(username = "testEmail@abv.bg", roles = "USER")
//    void testDeleteClass() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/classes/delete/{idTeacher}/{id}", UUID.randomUUID(), testClass.getId())
//                        .with(csrf()))
//                .andExpect(status().is4xxClientError())
//                .andExpect(view().name("error"));
//    }
//
//}
