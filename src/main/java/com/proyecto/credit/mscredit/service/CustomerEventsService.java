package com.proyecto.credit.mscredit.service;

import com.proyecto.credit.mscredit.events.Event;
import com.proyecto.credit.mscredit.events.CreditCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class
CustomerEventsService {

	@KafkaListener(
			topics = "${topic.notifications.name:credittopic}",
			containerFactory = "kafkaListenerContainerFactory",
	groupId = "escuchatopic")
	public void consumer(Event<?> event) {
		if (event.getClass().isAssignableFrom(CreditCreatedEvent.class)) {
			CreditCreatedEvent creditCreatedEvent = (CreditCreatedEvent) event;
			log.info("Received Credit created event .... with Id={}, data={}",
					creditCreatedEvent.getId(),
					creditCreatedEvent.getData().toString());
		}

	}
	
	

}
