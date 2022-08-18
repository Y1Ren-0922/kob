package com.kob.backend.controller.user.bot;


import com.kob.backend.service.user.bot.BotUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BotUpdateController {

    private BotUpdateService botUpdateService;

    @Autowired
    public void setBotUpdateService(BotUpdateService botUpdateService) {
        this.botUpdateService = botUpdateService;
    }

    @PostMapping("/user/bot/update/")
    public Map<String, String> update(@RequestParam Map<String, String> data) {
        return botUpdateService.update(data);
    }
}
