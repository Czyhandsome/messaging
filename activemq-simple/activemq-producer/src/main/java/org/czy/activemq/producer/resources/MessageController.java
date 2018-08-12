package org.czy.activemq.producer.resources;

import lombok.Data;
import org.czy.activemq.MessagingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messaging")
public class MessageController {
    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping
    public String postMessage(@RequestBody MessageRequest messageRequest) {
        String message = messageRequest.getMessage();
        jmsTemplate.convertAndSend(MessagingConstants.TOPIC, message);
        return "消息成功发送! Message: " + message;
    }

    @Data
    private static class MessageRequest {
        private String message;
    }
}
