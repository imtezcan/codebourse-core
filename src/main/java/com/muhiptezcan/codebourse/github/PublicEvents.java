package com.muhiptezcan.codebourse.github;

import com.muhiptezcan.codebourse.github.provider.GithubClientProvider;
import org.apache.log4j.Logger;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imtezcan on 7.04.2016.
 */
@Service
public class PublicEvents {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private GithubClientProvider githubClientProvider;

    public final List<Event> getAllPublicEvents() {
        final List<Event> allEvents = new ArrayList<>();
        final EventService eventService = githubClientProvider.getEventService();
        final PageIterator<Event> eventPages = eventService.pagePublicEvents();
        // TODO Each page should be processed once complete. Results can later be aggregated.
        allEvents.addAll(eventPages.next());
        return allEvents;
    }

    public void setGithubClientProvider(final GithubClientProvider githubClientProvider) {
        this.githubClientProvider = githubClientProvider;
    }
}
