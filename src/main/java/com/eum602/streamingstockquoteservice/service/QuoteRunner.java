package com.eum602.streamingstockquoteservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuoteRunner implements CommandLineRunner {

    //automatically autowired by string thanks to '@RequiredArgsConstructor'
    private final QuoteGeneratorService quoteGeneratorService;
    private final QuoteHistoryService quoteHistoryService;

    @Override
    public void run(String... args) throws Exception {
        quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100l))
                .take(55) //take whatever you want -> limiting to only 50 quotes
                .log("Got Quote: ")
                .flatMap(quoteHistoryService::saveQuoteToMongo)
                .subscribe(savedQuote -> { //puts a back pressure
                    log.debug("Saved Quote: " +  savedQuote);
                }, throwable -> {
                    // handle error here
                    log.error("An error has occurred ... " ,  throwable);
                }, () -> {
                    log.debug("done!!!!!!!!!!");
                });
    }
}
