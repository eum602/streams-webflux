package com.eum602.streamingstockquoteservice.service;

import com.eum602.streamingstockquoteservice.model.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class QuoteGeneratorServiceImplTest {

    QuoteGeneratorService service;

    @BeforeEach
    void setUp() {
        service = new QuoteGeneratorServiceImpl();
    }

    @Test
    void fetchQuoteStream() throws InterruptedException {
        //initializing the quote FLux
        Flux<Quote> quoteFlux = service.fetchQuoteStream(Duration.ofMillis(100l));

        //defining the quote consumer
        Consumer<Quote> quoteConsumer = System.out::println;
        //passing the quotes into the system print line -> each time a new element arrives it is simply printed

        //the error handler subscriber
        Consumer<Throwable> throwableConsumer = e -> System.out.println(e.getMessage());

        CountDownLatch countDownLatch = new CountDownLatch(1);

        //setting a countodwn latch action for the 'done' consumer when the execution is done
        Runnable done = () -> countDownLatch.countDown(); //for this consumer we want to count down latch

        quoteFlux.take(30)
                .subscribe(quoteConsumer, throwableConsumer, done);//passing the defined quote consumer, throwable consumer (error handler), and the done
        //consumer when all is done.

        countDownLatch.await();
    }
}