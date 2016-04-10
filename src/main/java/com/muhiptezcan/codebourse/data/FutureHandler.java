package com.muhiptezcan.codebourse.data;

import com.muhiptezcan.codebourse.model.Language;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public final class FutureHandler {
    public List<Language> extractFutures(final List<Future<List<Language>>> futures)
            throws InterruptedException, ExecutionException {
        final List<Language> languages = new ArrayList<Language>();
        while (languages.size() < futures.size()) {
            final List<Future<List<Language>>> doneFutures = futures.stream()
                    .filter(Future::isDone)
                    .collect(Collectors.toList());
            for (Future<List<Language>> future : doneFutures) {
                languages.addAll(future.get());
            }
        }
        return languages;
    }
}
