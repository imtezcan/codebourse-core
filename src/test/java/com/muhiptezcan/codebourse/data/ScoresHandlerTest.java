package com.muhiptezcan.codebourse.data;

import com.muhiptezcan.codebourse.Constants;
import com.muhiptezcan.codebourse.data.provider.DataProvider;
import com.muhiptezcan.codebourse.model.Language;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertTrue;


/**
 * Created by imtezcan on 10.04.2016.
 */
public class ScoresHandlerTest {

    private DataProvider dataProvider;
    private ScoresHandler scoresHandler;

    @Before
    public final void setUp() throws Exception {
        final Map<String, Long> scoresMap = new TreeMap<>();
        dataProvider = Mockito.mock(DataProvider.class);
        Mockito.when(dataProvider.getScoresMap()).thenReturn(scoresMap);
        scoresHandler = new ScoresHandler();
        scoresHandler.setDataProvider(dataProvider);
    }

    @Test
    public final void putScore() throws Exception {
        final Language java = new Language(Constants.JAVA, 200);
        scoresHandler.putOrUpdateScore(java);
        assertTrue(java.getScore() == dataProvider.getScoresMap().get(java.getName()));
    }

    @Test
    public final void updateScore() throws Exception {
        final Language java = new Language(Constants.JAVA, 200);
        dataProvider.getScoresMap().put(java.getName(), java.getScore());
        scoresHandler.putOrUpdateScore(java);
        assertTrue(java.getScore() * 2 == dataProvider.getScoresMap().get(java.getName()));
    }
}
