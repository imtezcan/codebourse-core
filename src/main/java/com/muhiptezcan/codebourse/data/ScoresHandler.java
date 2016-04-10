package com.muhiptezcan.codebourse.data;

import com.muhiptezcan.codebourse.model.Language;
import com.muhiptezcan.codebourse.data.provider.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScoresHandler {
    @Autowired
    private DataProvider dataProvider;

    public void putOrUpdateScore(final Language language) {
        final String key = language.getName();
        final Map<String, Long> scoresMap = dataProvider.getScoresMap();
        if (scoresMap.containsKey(key)) {
            scoresMap.put(key, scoresMap.get(key) + language.getScore());
        } else {
            scoresMap.put(key, language.getScore());
        }
    }

    public void setDataProvider(final DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }
}
