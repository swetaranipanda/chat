package com.sallychat.api.services;

import com.sallychat.api.OKHTTPClient;
import com.sallychat.api.config.APIConfig;
import com.squareup.okhttp.Callback;

import java.io.IOException;

/**
 * Created by Swetarani Panda on 2/27/2018.
 */

public class GetChatResponseAPiService {
    private static final String TAG = "GetChatResponseAPiService";

    private static GetChatResponseAPiService getChatResponseAPiService = null;

    public static GetChatResponseAPiService getInstance() {
        if (getChatResponseAPiService == null) {
            synchronized (GetChatResponseAPiService.class) {
                if (getChatResponseAPiService == null) {
                    getChatResponseAPiService = new GetChatResponseAPiService();
                    return getChatResponseAPiService;
                }
            }
        }
        return getChatResponseAPiService;
    }


    public void getChatResponse(String text, Callback callback) throws IOException {
        try {
            OKHTTPClient.get(APIConfig.WEB_SERVER_URL +text,  callback);
        } catch (Exception e) {
        }
    }

}
