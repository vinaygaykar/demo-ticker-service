package demo.vinaygaykar.commons;

import java.time.Instant;

public record Quote(String symbol, Double price, Instant timestamp) {
}
