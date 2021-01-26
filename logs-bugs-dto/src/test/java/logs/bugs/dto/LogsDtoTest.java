package logs.bugs.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//@ExtendWith(SprinngExtention.class)
@AutoConfigureMockMvc
@WebMvcTest(LogsDtoTest.TestController.class)
@ContextConfiguration(classes = LogsDtoTest.TestController.class)
public class LogsDtoTest {
    public static @RestController
    class TestController {
        static LogDto logDtoExp;

        @PostMapping("/")
        void testPost(@RequestBody @Valid LogDto logDto) {
            assertEquals(logDtoExp, logDto);
        }
    }

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    MockMvc mock;

    @BeforeEach
    void setUp() {
        TestController.logDtoExp = new LogDto(new Date(), LogType.NO_EXCEPTION,
                "artifact", 0, "");
    }

    @Test
    void testPostNormal() throws Exception {
        int statusExp = 200;
        testPost(statusExp);
    }

    @Test
    void testPostNoDate() throws Exception {
        TestController.logDtoExp.dateTime = null;
        int statusExp = 400;
        testPost(statusExp);
    }

    @Test
    void testPostNoType() throws Exception {
        TestController.logDtoExp.logType = null;
        int statusExp = 400;
        testPost(statusExp);
    }

    @Test
    void testPostNoArtifact() throws Exception {
        TestController.logDtoExp.artifact = "";
        int statusExp = 400;
        testPost(statusExp);
    }

    private void testPost(int statusExp) throws Exception {
        assertEquals(statusExp, mock.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(TestController.logDtoExp)))
                .andReturn()
                .getResponse().getStatus());
    }
}
