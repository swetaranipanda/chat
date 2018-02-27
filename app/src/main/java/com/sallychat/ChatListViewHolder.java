package com.sallychat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Swetarani Panda on 2/27/2018.
 */

public class ChatListViewHolder extends RecyclerView.ViewHolder {
    TextView userTxt,systemTxt;
    public ChatListViewHolder(View itemView) {
        super(itemView);
        userTxt=itemView.findViewById(R.id.usertxt);
        systemTxt=itemView.findViewById(R.id.systemtxt);
    }
    public View getView() {
        return itemView;
    }
}
