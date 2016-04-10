package com.muhiptezcan.codebourse;

import com.muhiptezcan.codebourse.consumer.ScoresConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class CodebourseCoreApplication implements CommandLineRunner {
    @Autowired
    private ScoresConsumer consumer;

    public static void main(final String[] args) {
        SpringApplication.run(CodebourseCoreApplication.class, args);
    }

    @Override
    public final void run(final String... args) throws Exception {
        consumer.consume();
    }
}
