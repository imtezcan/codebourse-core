package com.muhiptezcan.codebourse.github;

import com.muhiptezcan.codebourse.github.provider.GithubClientProvider;
import com.muhiptezcan.codebourse.model.Language;
import com.muhiptezcan.codebourse.util.RepoUtil;
import org.apache.log4j.Logger;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class RepositoryLanguages {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RepoUtil repoUtil;
    @Autowired
    private GithubClientProvider githubClientProvider;

    @Async
    public Future<List<Language>> getLanguages(final Event event) throws IOException {
        final RepositoryService repositoryService = githubClientProvider.getRepositoryService();
        final RepositoryId repositoryId = repoUtil.getRepositoryId(event);
        AsyncResult<List<Language>> result;
        if (repositoryService.getClient().getRemainingRequests() > 0) {
            try {
                result = new AsyncResult<>(repoUtil.createLanguageList(repositoryService.getLanguages(repositoryId)));
            } catch (RequestException e) {
                logger.warn(e);
                result = new AsyncResult<>(Collections.emptyList());
            }
        } else {
            result = new AsyncResult<>(Collections.emptyList());
        }
        return result;
    }

    public void setGithubClientProvider(final GithubClientProvider githubClientProvider) {
        this.githubClientProvider = githubClientProvider;
    }

    public void setRepoUtil(final RepoUtil repoUtil) {
        this.repoUtil = repoUtil;
    }
}
