package com.kob.backend.controller.user.bot;


import com.kob.backend.service.user.bot.BotAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BotAddController {

    private BotAddService botAddService;

    @Autowired
    public void setBotAddService(BotAddService botAddService) {
        this.botAddService = botAddService;
    }

    @PostMapping("/user/bot/add/")
    public Map<String, String> add(@RequestParam Map<String, String> data) {
        return botAddService.add(data);
    }
}
