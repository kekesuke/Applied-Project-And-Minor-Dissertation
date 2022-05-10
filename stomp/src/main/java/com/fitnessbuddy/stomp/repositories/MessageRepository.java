package com.fitnessbuddy.stomp.repositories;

import com.fitnessbuddy.stomp.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.DocFlavor;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, String> {

    long count(String sendId, String receiveId);

    List<MessageModel> findByIdChat (String chatId);
}
