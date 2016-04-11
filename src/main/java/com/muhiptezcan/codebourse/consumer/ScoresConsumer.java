package com.muhiptezcan.codebourse.consumer;

import com.muhiptezcan.codebourse.data.FutureHandler;
import com.muhiptezcan.codebourse.data.ScoresHandler;
import com.muhiptezcan.codebourse.github.PublicEvents;
import com.muhiptezcan.codebourse.github.RepositoryLanguages;
import com.muhiptezcan.codebourse.model.Language;
import org.apache.log4j.Logger;
import org.eclipse.egit.github.core.client.NoSuchPageException;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by imtezcan on 2.04.2016.
 */
@Service
public class ScoresConsumer {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RepositoryLanguages repositoryLanguages;
    @Autowired
    private PublicEvents publicEvents;
    @Autowired
    private ScoresHandler scoresHandler;
    @Autowired
    private FutureHandler futureHandler;

    @Scheduled(fixedRateString = "${refresh_rate}")
    public void consume() throws IOException, InterruptedException, ExecutionException {
        final List<Future<List<Language>>> futures = new ArrayList<>();
        try {
            for (Event event : publicEvents.getAllPublicEvents()) {
                logger.info("Event repo name: " + event.getRepo().getName());
                try {
                    final Future<List<Language>> future = repositoryLanguages.getLanguages(event);
                    futures.add(future);
                } catch (RequestException e) {
                    logger.warn(e, e);
                }
            }
        } catch (NoSuchPageException | RequestException e) {
            logger.warn(e, e);
        }
        final List<Language> languages = futureHandler.extractFutures(futures);
        languages.forEach(scoresHandler::putOrUpdateScore);
    }
}
