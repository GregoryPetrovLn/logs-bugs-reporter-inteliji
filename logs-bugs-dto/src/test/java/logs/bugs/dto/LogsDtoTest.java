package logs.bugs.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    void testPostRun() throws Exception {

        assertEquals(200, mock.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(TestController.logDtoExp)))
                .andReturn()
                .getResponse()
                .getStatus());
    }


}
