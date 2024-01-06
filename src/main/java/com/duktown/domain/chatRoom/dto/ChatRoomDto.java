package com.duktown.domain.chatRoom.dto;

import com.duktown.domain.chat.entity.Chat;
import com.duktown.domain.chatRoom.entity.ChatRoom;
import com.duktown.domain.delivery.entity.Delivery;
import com.duktown.domain.post.dto.PostDto;
import com.duktown.global.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ChatRoomDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ChatRoomResponse {
        private Long chatRoomId;
        private Long deliveryId;
        private String title;
        private Integer maxPeople;
        private String orderTime;
        private String accountNumber;

        public static ChatRoomResponse from(Delivery delivery, String accountNumber) {
            return ChatRoomResponse.builder()
                    .chatRoomId(delivery.getChatRoom().getId())
                    .deliveryId(delivery.getId())
                    .title(delivery.getTitle())
                    .maxPeople(delivery.getMaxPeople())
                    .orderTime(DateUtil.convertToAMPMFormat(delivery.getOrderTime()))
                    .accountNumber(accountNumber)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ChatRoomListElementResponse {   // TODO: 읽지 않은 알림 개수
        private Long chatRoomId;
        private String title;
        private String recentChatMessage;
        private LocalDateTime recentChatCreatedAt;

        public static ChatRoomListElementResponse from(ChatRoom chatRoom, String recentChatContent, LocalDateTime recentChatCreatedAt) {
            return ChatRoomListElementResponse.builder()
                    .chatRoomId(chatRoom.getId())
                    .title(chatRoom.getDelivery().getTitle())
                    .recentChatMessage(recentChatContent)
                    .recentChatCreatedAt(recentChatCreatedAt)
                    .build();
        }
    }

    @Getter
    public static class ChatRoomListResponse {
        private List<ChatRoomListElementResponse> chatRooms;

        public ChatRoomListResponse(List<ChatRoomListElementResponse> chatRooms) {
            this.chatRooms = chatRooms;
        }
    }
}
