package pmgkn.pescores.pescores.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pmgkn.pescores.pescores.util.TestDataUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentsControllerIT {

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
}
