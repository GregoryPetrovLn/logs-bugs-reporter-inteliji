package logs.bugs.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
@WebMvcTest
@ContextConfiguration(classes = LogsDtoTest.TestController.class)
public class LogsDtoTest {

    public static @RestController class TestController {
        static LogDto logDtoExp = new LogDto(new Date(), LogType.NO_EXCEPTION, "artifact", 0, "");

        @PostMapping("/")
        void testPost(@RequestBody @Valid LogDto logDto) {
            assertEquals(logDtoExp, logDto);
        }
    }
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    MockMvc mock;

    @BeforeEach
    public void setup() {
        TestController.logDtoExp.dateTime = new Date();
        TestController.logDtoExp.logType = LogType.NO_EXCEPTION;
        TestController.logDtoExp.artifact = "artifact";
        TestController.logDtoExp.responseTime = 0;
        TestController.logDtoExp.result = "";
    }

    @Nested
    class faildTests {
        @DisplayName("dateTime = null")
        @Test
        void testArifactDateNull() throws Exception {
            TestController.logDtoExp.dateTime = null;
            assertEquals(400,
                    mock.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(TestController.logDtoExp))).andReturn().getResponse()
                            .getStatus());
        }

        @DisplayName("logType = null")
        @Test
        void testLogType() throws Exception {
            TestController.logDtoExp.logType = null;
            assertEquals(400,
                    mock.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(TestController.logDtoExp))).andReturn().getResponse()
                            .getStatus());
        }

        @DisplayName("artifact = ''")
        @Test
        void testArtifactEmpty() throws Exception {
            TestController.logDtoExp.artifact = "";
            assertEquals(400,
                    mock.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(TestController.logDtoExp))).andReturn().getResponse()
                            .getStatus());
        }
    }
}
