package pmgkn.pescores.pescores.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassesControllerIT {

    //TODO: to make mock users and write tests with them

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testClassesPageShowsUp(){

    }
}
