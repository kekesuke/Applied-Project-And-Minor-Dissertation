package com.fitnessbuddy.stomp.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageModel {

	@Id
	@SequenceGenerator(
			name = "notification_id_sequence",
			sequenceName = "notification_id_sequence"
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "notification_id_sequence"
	)
	private int messageId;
	private String messageContent;
    private String userName;
	private String timeStamp;










































	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int messageId;
	 */

//	private String messageContent;
//	private String userName;

	
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

//	public String getMessage() {
//		return messageContent;
//	}
//
//	public void setMessage(String message) {
//		this.messageContent = message;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
	
	

	
	/*
	 * public Set<User> getUser() { return senderUser; }
	 * 
	 * public void setUser(Set<User> user) { this.senderUser = user; }
	 * 
	 * @Override public String toString() { return "MessageModel{" + "messsage= " +
	 * messageContent + '/' + "fromUser=" + senderUser + '/' + '}'; }
	 */
	 

}
