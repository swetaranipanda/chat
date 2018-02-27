package com.sallychat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sallychat.database.schemas.ChatEntity;

import java.util.List;

/**
 * Created by Swetarani Panda on 2/27/2018.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListViewHolder> {
    Context context;
    List<ChatEntity> chatList;

    public ChatListAdapter(Context context, List<ChatEntity> list) {
        this.context = context;
        chatList = list;
    }

    @Override
    public ChatListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_chat, null);
        ChatListViewHolder rcv = new ChatListViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ChatListViewHolder holder, int position) {
        holder.getView().setTag(position);
        String type = chatList.get(position).getType();
        if (type.equalsIgnoreCase("user")) {
            holder.userTxt.setText(chatList.get(position).getChat());

        } else {
            holder.systemTxt.setText(chatList.get(position).getChat());
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
