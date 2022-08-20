package com.kob.botrunningsystem.service.impl.utils;

import com.kob.botrunningsystem.utils.BotInterface;
import com.kob.botrunningsystem.utils.CompilerUtil;
import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@Component
public class Consumer extends Thread{

    private Bot bot;
    private static RestTemplate restTemplate;
    private static final String receiveBotMoveUrl = "http://127.0.0.1:3000/pk/receive/bot/move/";

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        Consumer.restTemplate = restTemplate;
    }

    public void startTimeOut(long timeout, Bot bot) {
        this.bot = bot;
        this.start();

        try {
            this.join(timeout); //最多等待timeout秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.interrupt(); // 中断当前线程
        }

    }

    private String addUid(String code, String uid) {
        int k = code.indexOf(" implements com.kob.botrunningsystem.utils.BotInterface");
        return code.substring(0, k) + uid + code.substring(k);
    }

    @Override
    public void run() {
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString().substring(0, 8);
        String code = addUid(bot.getBotCode(), uid);

        createFile("Bot" + uid, code);
        try {
            BotInterface botInterface = CompilerUtil.generateClass("Bot" + uid, "com.kob.botrunningsystem.utils", code);
            Integer direction = botInterface.nextMove(bot.getInput());
            System.out.println("move-direction: " + bot.getUserId() + " " + direction);
            MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
            data.add("user_id", bot.getUserId().toString());
            data.add("direction", direction.toString());

            deleteFile("Bot" + uid);
            restTemplate.postForObject(receiveBotMoveUrl, data, String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFile(String name) {
        // 这个路径是自己写的，自己定义即可
        File file = new File("E:\\JavaProjects\\kob\\backendcloud\\botrunningsystem\\src\\main\\java\\com\\kob\\botrunningsystem\\utils\\" + name + ".java");
        if (file.exists()) {
            System.out.println("----");
            file.delete();
        }
    }

    private void createFile(String name, String code) {
        try (FileWriter file = new FileWriter("E:\\JavaProjects\\kob\\backendcloud\\botrunningsystem\\src\\main\\java\\com\\kob\\botrunningsystem\\utils\\" + name + ".java");) {
            file.write(code);
            file.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
