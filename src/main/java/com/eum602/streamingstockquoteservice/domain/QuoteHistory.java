package com.eum602.streamingstockquoteservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document //this tells Spring data that this is a mongo database document
public class QuoteHistory {

    @Id
    private String id;
    private String ticker;
    private BigDecimal price;
    private Instant instant;
}
