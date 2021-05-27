package com.eum602.streamingstockquoteservice.service;

import com.eum602.streamingstockquoteservice.domain.QuoteHistory;
import com.eum602.streamingstockquoteservice.model.Quote;
import reactor.core.publisher.Mono;

public interface QuoteHistoryService {
    Mono<QuoteHistory> saveQuoteToMongo(Quote quote);

}
