package com.sallychat.database.schemas;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


/**
 * Created by Girish
 */

@Table(name = "chatentity")
public class ChatEntity extends Model {


    @Column(name = "chat")
    private String chat;

    @Column(name = "type")
    private String type;

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
