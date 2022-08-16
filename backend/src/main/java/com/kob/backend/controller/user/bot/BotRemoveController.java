package com.kob.backend.controller.user.bot;

import com.kob.backend.service.user.bot.BotRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BotRemoveController {

    private BotRemoveService botRemoveService;

    @Autowired
    public void setBotRemoveService(BotRemoveService botRemoveService) {
        this.botRemoveService = botRemoveService;
    }

    @PostMapping("/user/bot/remove/")
    public Map<String, String> remove(@RequestParam Map<String, String> data) {
        return botRemoveService.remove(data);
    }
}
