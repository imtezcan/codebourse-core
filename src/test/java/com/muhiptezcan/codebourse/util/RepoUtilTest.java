package com.muhiptezcan.codebourse.util;

import com.muhiptezcan.codebourse.Constants;
import com.muhiptezcan.codebourse.model.Language;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventRepository;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by imtezcan on 10.04.2016.
 */
public class RepoUtilTest {

    @Test
    public void getRepositoryId() throws Exception {
        final EventRepository eventRepository = new EventRepository();
        eventRepository.setName("muhiptezcan/codebourse");
        final Event event = new Event();
        event.setRepo(eventRepository);
        final RepoUtil repoUtil = new RepoUtil();
        final RepositoryId generatedRepositoryId = repoUtil.getRepositoryId(event);
        final RepositoryId expectedRepositoryId = new RepositoryId("muhiptezcan", "codebourse");
        assertEquals(expectedRepositoryId, generatedRepositoryId);
    }

    @Test
    public void createLanguageList() throws Exception {
        final long score = 100;
        final Map<String, Long> scoreMap = new HashMap<>();
        scoreMap.put(Constants.JAVA, score);
        final RepoUtil repoUtil = new RepoUtil();
        final List<Language> languageList = repoUtil.createLanguageList(scoreMap);
        assertEquals(Constants.JAVA, languageList.get(0).getName());
        assertTrue(score == languageList.get(0).getScore());
    }
}
