package com.muhiptezcan.codebourse.github;

import com.muhiptezcan.codebourse.github.provider.GithubClientProvider;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.service.EventService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by imtezcan on 10.04.2016.
 */
public class PublicEventsTest {

    @Test
    public void getAllPublicEvents() throws Exception {
        final Event event = new Event();

        final PageIterator<Event> eventPage = Mockito.mock(PageIterator.class);
        Mockito.when(eventPage.hasNext()).thenReturn(true).thenReturn(false);
        Mockito.when(eventPage.next()).thenReturn(Collections.singletonList(event));

        final GitHubClient gitHubClient = Mockito.mock(GitHubClient.class);
        Mockito.when(gitHubClient.getRemainingRequests()).thenReturn(5);

        final EventService eventService = Mockito.mock(EventService.class);
        Mockito.when(eventService.pagePublicEvents()).thenReturn(eventPage);
        Mockito.when(eventService.getClient()).thenReturn(gitHubClient);

        final GithubClientProvider githubClientProvider = Mockito.mock(GithubClientProvider.class);
        Mockito.when(githubClientProvider.getEventService()).thenReturn(eventService);

        final PublicEvents publicEvents = new PublicEvents();
        publicEvents.setGithubClientProvider(githubClientProvider);
        final List<Event> events = publicEvents.getAllPublicEvents();
        assertEquals(event, events.get(0));
    }
}
