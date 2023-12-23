package com.dange.tanmay.service;


import com.dange.tanmay.common.Event;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueSender {
        @Autowired
        private RabbitTemplate rabbitTemplate;

        @Autowired
        private Queue queue;

        public void send(Event productEvent) {
                log.info("Sending response");
                log.info(productEvent.toString());
                rabbitTemplate.convertAndSend(this.queue.getName(), productEvent);
        }
}

