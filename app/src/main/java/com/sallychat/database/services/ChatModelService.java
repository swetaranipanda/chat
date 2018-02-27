package com.sallychat.database.services;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.sallychat.database.schemas.ChatEntity;

import java.util.List;

/**
 * Created by Girish
 */
public class ChatModelService {
    private static ChatModelService chatModelService = null;


    private ChatModelService() {

    }

    public static ChatModelService getInstance() {
        if (chatModelService == null) {
            synchronized (ChatModelService.class) {
                if (chatModelService == null) {
                    chatModelService = new ChatModelService();
                    return chatModelService;
                }
            }
        }
        return chatModelService;
    }

    public List<ChatEntity> getChatList() {
        List<ChatEntity> chatEntityList = new Select().from(ChatEntity.class).execute();
        return chatEntityList;
    }

    public void saveChat(String chat, String type) {

        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setChat(chat);
        chatEntity.setType(type);
        chatEntity.save();

    }


    public void delete() {
        new Delete().from(ChatEntity.class).execute();
    }
}
