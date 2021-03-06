package com.sallychat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sallychat.api.dto.res.ChatRes;
import com.sallychat.api.services.GetChatResponseAPiService;
import com.sallychat.database.schemas.ChatEntity;
import com.sallychat.database.services.ChatModelService;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    TTSManager ttsManager = null;
    RecyclerView chatRcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ttsManager = new TTSManager();
        ttsManager.init(this);
        btnSpeak = findViewById(R.id.btnSpeak);
        chatRcv = findViewById(R.id.chat_rcv);

        setAdapter();
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result != null) {
                        ChatModelService.getInstance().saveChat(result.get(0), "user");
                        ttsManager.initQueue(result.get(0));
                        try {
                            GetChatResponseAPiService.getInstance().getChatResponse(result.get(0), callback());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            }

        }
    }

    private Callback callback() {
        return new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.code() == 200) {
                    ChatRes chatRes = new Gson().fromJson(response.body().string(), new TypeToken<ChatRes>() {
                    }.getType());
                    ArrayList<String> textArray = chatRes.getResponse().getOutput().getText();
                    String text = textArray.get(0);
                    ChatModelService.getInstance().saveChat(text, "system");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setAdapter();

                        }
                    });

                }
            }
        };
    }

    private void setAdapter() {
        List<ChatEntity> chatList = ChatModelService.getInstance().getChatList();
        StaggeredGridLayoutManager gaggeredGridLayoutManager = new StaggeredGridLayoutManager(1, 1);
        chatRcv.setLayoutManager(gaggeredGridLayoutManager);
        if (chatList != null && chatList.size() > 0) {
            ChatListAdapter chatListAdapter = new ChatListAdapter(this, chatList);
            chatRcv.setAdapter(chatListAdapter);
        }
    }


    /**
     * Releases the resources used by the TextToSpeech engine.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        ttsManager.shutDown();
    }
}
