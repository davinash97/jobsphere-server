package com.portal.jobsphere.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import com.portal.jobsphere.utils.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "message")
public class Message implements Constants {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.UUID)
	private final UUID messageId;

	@Column(nullable = false)
	private final UUID sender;

	@Column(nullable = false)
	private final UUID receiver;

	@Column(nullable = false)
	private Boolean is_read;

	private Boolean is_delivered;

	@CreatedDate
	LocalDateTime time;

	private final String content;

	public Message(UUID sender, UUID receiver, String content) {
		messageId = UUID.randomUUID();
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		is_read = false;
		is_delivered = false;
		time = LocalDateTime.now();
	}

	public UUID getId() {
		return messageId;
	}

	public UUID getSender() {
		return sender;
	}

	public UUID getReceiver() {
		return receiver;
	}

	public String getTime() {
		return time.format(formatter);
	}

	public String getContent() {
		return content;
	}

	public Boolean getIsRead() {
		return is_read;
	}

	public void setIsRead(Boolean is_read) {
		this.is_read = is_read;
	}

	public Boolean getIsDeliver() {
		return is_delivered;
	}

	public void setIsDelivered(Boolean is_delivered) {
		this.is_delivered = is_delivered;
	}
}
