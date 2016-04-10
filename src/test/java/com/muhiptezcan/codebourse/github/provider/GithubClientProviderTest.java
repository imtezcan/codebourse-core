package com.muhiptezcan.codebourse.github.provider;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by imtezcan on 10.04.2016.
 */
public class GithubClientProviderTest {

    @Test
    public final void getEventService() throws Exception {
        final GithubClientProvider githubClientProvider = new GithubClientProvider();
        assertNotNull(githubClientProvider.getEventService());
        assertNotNull(githubClientProvider.getEventService().getClient());
    }

    @Test
    public final void getRepositoryService() throws Exception {
        final GithubClientProvider githubClientProvider = new GithubClientProvider();
        assertNotNull(githubClientProvider.getRepositoryService());
        assertNotNull(githubClientProvider.getRepositoryService().getClient());
    }
}
