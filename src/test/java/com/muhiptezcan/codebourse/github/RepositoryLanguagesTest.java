package com.muhiptezcan.codebourse.github;

import com.muhiptezcan.codebourse.github.provider.GithubClientProvider;
import com.muhiptezcan.codebourse.util.RepoUtil;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

/**
 * Created by imtezcan on 10.04.2016.
 */
public class RepositoryLanguagesTest {

    @Test
    public void getLanguages() throws Exception {
        final GitHubClient gitHubClient = Mockito.mock(GitHubClient.class);
        Mockito.when(gitHubClient.getRemainingRequests()).thenReturn(5);

        final RepoUtil repoUtil = Mockito.mock(RepoUtil.class);
        final GithubClientProvider githubClientProvider = Mockito.mock(GithubClientProvider.class);

        final RepositoryService repositoryService = Mockito.mock(RepositoryService.class);
        Mockito.when(repositoryService.getClient()).thenReturn(gitHubClient);
        Mockito.when(githubClientProvider.getRepositoryService()).thenReturn(repositoryService);

        final Event event = new Event();

        final RepositoryLanguages repositoryLanguages = new RepositoryLanguages();
        repositoryLanguages.setRepoUtil(repoUtil);
        repositoryLanguages.setGithubClientProvider(githubClientProvider);
        assertNotNull(repositoryLanguages.getLanguages(event));
    }
}
