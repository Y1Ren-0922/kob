package com.kob.backend.controller.user.bot;


import com.kob.backend.pojo.Bot;
import com.kob.backend.service.user.bot.BotGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BotGetListController {

    private BotGetListService botGetListService;

    @Autowired
    public void setBotGetListService(BotGetListService botGetListService) {
        this.botGetListService = botGetListService;
    }

    @GetMapping("/user/bot/getlist/")
    public List<Bot> getList() {
        return botGetListService.getList();
    }
}
