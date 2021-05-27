package com.eum602.streamingstockquoteservice.service;

import com.eum602.streamingstockquoteservice.model.Quote;
import reactor.core.publisher.Flux;

import java.time.Duration;

public interface QuoteGeneratorService {

    Flux<Quote> fetchQuoteStream(Duration period); //Duration indicates how often we want a stock quote
}
