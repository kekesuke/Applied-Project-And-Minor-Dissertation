package com.fitnessbuddy.stomp.model;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomModel {

    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
