package com.eum602.streamingstockquoteservice.service;

import com.eum602.streamingstockquoteservice.domain.QuoteHistory;
import com.eum602.streamingstockquoteservice.model.Quote;
import com.eum602.streamingstockquoteservice.repositories.QuoteHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor //takes responsibility to initialize the attributes -> wire via constructors -> dependency injection
public class QuoteHistoryServiceImpl implements QuoteHistoryService {

    private final QuoteHistoryRepository repository;

    @Override
    public Mono<QuoteHistory> saveQuoteHistory(Quote quote) {
        return repository.save(
                QuoteHistory
                        .builder()
                        .ticker(quote.getTicker())
                        .price(quote.getPrice())
                        .instant(quote.getInstant())
                        .build());
    }
}
