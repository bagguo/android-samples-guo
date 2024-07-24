package com.example.android_lesson.json;

import com.google.gson.Gson;

public class JsonSamples {
    public static void main(String[] args) {
        String json = "{\"e\":\"{\\\"body\\\":\\\"{\\\\\\\"type\\\\\\\":1,\\\\\\\"targetId\\\\\\\":\\\\\\\"847712\\\\\\\"}\\\",\\\"em_push_content_zapry\\\":\\\"tggggg\\\",\\\"em_push_title_zapry\\\":\\\"bagguo163\\\"}\",\"f\":\"847712\",\"m\":\"1304133162997126616\",\"t\":\"847711\",\"EPush\":\"{\\\"provider\\\":\\\"ANDROID\\\",\\\"origin\\\":\\\"im-push\\\",\\\"msg_id\\\":\\\"1304133162997126616\\\"}\",\"alert\":\"bagguo163\"}";
        PushEntityV2 object = new Gson().fromJson(json, PushEntityV2.class);
        System.out.println(object);
    }
}
