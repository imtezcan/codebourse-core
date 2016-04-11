package com.muhiptezcan.codebourse.github.provider;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.EventService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by imtezcan on 7.04.2016.
 */
@Service
public class GithubClientProvider {

    @Value(value = "${oauth_token}")
    private String oauthToken;

    private EventService eventService;
    private RepositoryService repositoryService;

    public GithubClientProvider() {
        final GitHubClient gitHubClient = new GitHubClient().setOAuth2Token(oauthToken);
        repositoryService = new RepositoryService(gitHubClient);
        eventService = new EventService(gitHubClient);
    }

    public EventService getEventService() {
        return eventService;
    }

    public RepositoryService getRepositoryService() {
        return repositoryService;
    }
}
