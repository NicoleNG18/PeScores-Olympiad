package pmgkn.pescores.pescores.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import pmgkn.pescores.pescores.domain.entity.TaskEntity;
import pmgkn.pescores.pescores.util.TestDataUtils;

import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TasksControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;
    @AfterEach
    void tearDown(){
        testDataUtils.cleanUpDatabase();
    }

    @Test
    @WithMockUser(username = "testEmail@abv.bg",roles = "USER")
    @Transactional
    void testGetTasksShowsUp() throws Exception {
        testDataUtils.createTask(testDataUtils.createTestUser("testEmail@abv.bg"));
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks")
                .with(csrf())) .andExpect(view().name("tasks"))
                .andExpect(model().attributeExists("tasks", "tasksDone"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "testEmail@abv.bg",roles = "USER")
    void testAddTaskWorksCorrectly() throws Exception {
       testDataUtils.createTask(testDataUtils.createTestUser("testEmail@abv.bg"));
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks/save")
                        .param("description","task description")
                        .param("dueDate","2045-01-23")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));
    }

    @Test
    @WithMockUser(username = "testEmail@abv.bg",roles = "USER")
    void testAddTaskWithInvalidData() throws Exception {
       testDataUtils.createTask(testDataUtils.createTestUser("testEmail@abv.bg"));
        BindingResult result=mock(BindingResult.class);

        Mockito.when(result.hasErrors()).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks/save")
                        .param("description","")
                        .param("dueDate","2023-01-23")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(flash().attributeExists("taskDto"))
                .andExpect(redirectedUrl("/tasks"));
    }

    @Test
    @WithMockUser(username = "testEmail@abv.bg",roles = "USER")
    @Transactional
    void testMakingDoneTodoWorksCorrectly() throws Exception {
        TaskEntity task=testDataUtils.createTask(testDataUtils.createTestUser("testEmail@abv.bg"));
        mockMvc.perform(patch("/tasks/done/")
                        .param("descr",task.getId().toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));

    }

    @Test
    @WithMockUser(username = "testEmail@abv.bg",roles = "USER")
    @Transactional
    void testDeletingTodoWorksCorrectly() throws Exception {
        TaskEntity task=testDataUtils.createTask(testDataUtils.createTestUser("testEmail@abv.bg"));
        mockMvc.perform(patch("/tasks/delete/")
                        .param("descr",task.getId().toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));

    }


}
