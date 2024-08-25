package com.portal.jobsphere.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.jobsphere.model.Message;
import com.portal.jobsphere.repository.MessageRepository;

@Service
public class MessageService {

	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
		
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private MessageRepository messageRepository;
	
	public boolean messageIdExists(UUID messageId) {
		return messageRepository.existsById(messageId);
	}

	public boolean createMessage(UUID sender, UUID receiver, String content) {
		try {
			if(sender.toString().isEmpty() || receiver.toString().isEmpty() || content.isEmpty()) {
				return false;
			}
			
			if(profileService.profileIdExists(sender) && profileService.profileIdExists(receiver)) {
				Message message = new Message(sender, receiver, content);
				messageRepository.save(message);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error("Error occurred at {}", e.getMessage());
			return false;
		}
	}

	public Message readMessage(UUID message_id) {
		return (Message) messageRepository.findById(message_id).orElse(null);
	}

	public boolean deleteMessage(UUID message_id, String content) {
		try {
			if(message_id.toString().isEmpty()) {
				return false;
			}
			
			if(messageIdExists(message_id)) {
				messageRepository.deleteById(message_id);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error("Error occurred at {}", e.getMessage());
			return false;
		}
	}

	public List<Message> getAllMessages(UUID profile_id) {
		return profileService.getAllMessages(profile_id);
	}
}
