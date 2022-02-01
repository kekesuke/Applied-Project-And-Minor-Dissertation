package com.fitnessbuddyapi.models;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//@Entity
//@Table(name = "Messages")
public class MessageModel {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int messageId;
	 */

	private String messageContent;
	private String userName;

	
	/*
	 * @ManyToMany(fetch = FetchType.LAZY)
	 * 
	 * @JoinTable( name = "Messages_users", joinColumns = @JoinColumn(name =
	 * "message_id"), inverseJoinColumns = @JoinColumn(name = "user_id")) private
	 * Set <User> senderUser;
	 */
	 

	/* private String timeStamp; */

	/*
	 * public MessageModel() {
	 * 
	 * }
	 * 
	 * public String getTimeStamp() { return timeStamp; }
	 * 
	 * public void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp; }
	 */

	public String getMessage() {
		return messageContent;
	}

	public void setMessage(String message) {
		this.messageContent = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

	
	/*
	 * public Set<User> getUser() { return senderUser; }
	 * 
	 * public void setUser(Set<User> user) { this.senderUser = user; }
	 * 
	 * @Override public String toString() { return "MessageModel{" + "messsage= " +
	 * messageContent + '/' + "fromUser=" + senderUser + '/' + '}'; }
	 */
	 

}
