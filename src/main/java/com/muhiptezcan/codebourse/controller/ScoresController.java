package com.muhiptezcan.codebourse.controller;

import com.google.gson.Gson;
import com.muhiptezcan.codebourse.data.provider.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoresController {
    @Autowired
    private DataProvider dataProvider;

    @RequestMapping(method = RequestMethod.GET, path = "/scores")
    public final String getScores() {
        final Gson gson = new Gson();
        return gson.toJson(dataProvider.getScoresMap());
    }
}
