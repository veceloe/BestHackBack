package mirea.enjoyers.BestHackBack;

import mirea.enjoyers.BestHackBack.Controllers.SSEController;
import mirea.enjoyers.BestHackBack.Services.SSEService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SSEControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SSEService webhookService;
//
//    @Test
//    public void testSendPushNotification() throws Exception {
//        String webhookUrl = "http://example.com/webhook";
//        long pushId = 1L;
//
//        mockMvc.perform(post("/webhook")
//                        .param("webhookUrl", webhookUrl)
//                        .param("pushId", Long.toString(pushId)))
//                .andExpect(status().isOk());
//    }
}