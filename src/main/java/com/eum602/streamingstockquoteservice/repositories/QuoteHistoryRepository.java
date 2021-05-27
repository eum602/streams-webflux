package com.eum602.streamingstockquoteservice.repositories;


import com.eum602.streamingstockquoteservice.domain.QuoteHistory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface QuoteHistoryRepository extends ReactiveMongoRepository<QuoteHistory, String> {

}
