package com.muhiptezcan.codebourse.data;

import com.muhiptezcan.codebourse.Constants;
import com.muhiptezcan.codebourse.model.Language;
import org.junit.Before;
import org.junit.Test;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

/**
 * Created by imtezcan on 10.04.2016.
 */
public class FutureHandlerTest {

    private List<Future<List<Language>>> futures;
    private final Language language1 = new Language(Constants.JAVA, 100);
    private final Language language2 = new Language(Constants.PYTHON, 50);
    private final Language language3 = new Language(Constants.JAVA, 200);

    @Before
    public final void setUp() throws Exception {
        futures = new ArrayList<>();
        final List<Language> languageList1 = new ArrayList<>();
        final List<Language> languageList2 = new ArrayList<>();
        languageList1.add(language1);
        languageList1.add(language2);
        languageList2.add(language3);
        final AsyncResult<List<Language>> future1 = new AsyncResult<>(languageList1);
        final AsyncResult<List<Language>> future2 = new AsyncResult<>(languageList2);
        futures.add(future1);
        futures.add(future2);
    }

    @Test
    public final void extractFutures() throws Exception {
        final FutureHandler futureHandler = new FutureHandler();
        final List<Language> extractedLanguages = futureHandler.extractFutures(futures);
        assertEquals(language1.getName(), extractedLanguages.get(0).getName());
        assertEquals(language2.getName(), extractedLanguages.get(1).getName());
        assertEquals(language3.getName(), extractedLanguages.get(2).getName());
        assertEquals(language1.getScore(), extractedLanguages.get(0).getScore());
        assertEquals(language2.getScore(), extractedLanguages.get(1).getScore());
        assertEquals(language3.getScore(), extractedLanguages.get(2).getScore());
    }
}
