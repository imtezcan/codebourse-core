package com.muhiptezcan.codebourse.util;

import com.muhiptezcan.codebourse.model.Language;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RepoUtil {
    public RepositoryId getRepositoryId(final Event event) {
        final EventRepository eventRepository = event.getRepo();
        final String[] tokens = eventRepository.getName().split("/");
        return RepositoryId.create(tokens[0], tokens[1]);
    }

    public List<Language> createLanguageList(final Map<String, Long> languageMap) {
        return languageMap.entrySet()
                .stream()
                .map(e -> new Language(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
