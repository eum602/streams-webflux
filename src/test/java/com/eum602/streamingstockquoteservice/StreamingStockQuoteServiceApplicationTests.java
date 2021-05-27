package com.eum602.streamingstockquoteservice;

import com.eum602.streamingstockquoteservice.model.Quote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StreamingStockQuoteServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

	@Test
	public void testFetchQuotes(){
		webTestClient
				.get()
				.uri("/quotes?size=20")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Quote.class)
				.hasSize(20)
				.consumeWith(allQuotes -> {
					assertThat(allQuotes.getResponseBody())
							.allSatisfy(quote -> assertThat(quote.getPrice()).isPositive());
					assertThat(allQuotes.getResponseBody()).hasSize(20);
				});
	}

	@Test
	public void testStreamQuotes() throws InterruptedException{
		CountDownLatch countDownLatch = new CountDownLatch(10);

		webTestClient
				.get()
				.uri("/quotes")
				.accept(MediaType.APPLICATION_NDJSON)
				.exchange()
				.returnResult(Quote.class)
				.getResponseBody()
				.take(10) //take 10 quotes in a stream way
				.subscribe(quote -> {
					assertThat(quote.getPrice()).isPositive();
					countDownLatch.countDown();
				});
		countDownLatch.await();
	}

}
