package demo.vinaygaykar;

import demo.vinaygaykar.commons.Exchange;
import demo.vinaygaykar.commons.Quote;
import demo.vinaygaykar.commons.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/tick")
public class Controller {

    private final Exchange exchange;


    public Controller() {
        this.exchange = new Exchange();
    }

    /**
     * Returns LTP and Company symbol as a String.
     * Multiple values are to be returned and their count
     * depends upon request parameter count (default 1).
     * <p>
     * In pre-digital era such a list of LTP values
     * per company was called a "ticker tape".
     *
     * @param count How many times to query each registered company.
     *              So if there are 3 companies, final response will
     *              have (3 * count) number of quote strings
     * @return Quote/LTP of all available companies as String
     */
    @GetMapping(value = "")
    public Flux<Quote> getTickerTape(@RequestParam("count") final int count) {
        final var countOfCompanies = exchange.getCountOfRegisteredCompanies();
        log.info("New request to process {} companies {} times", countOfCompanies, count);

        return Flux.range(0, countOfCompanies * count)
                .map(id -> id % countOfCompanies)
                .log()
                .delayUntil(id -> Mono.delay(Duration.ofMillis(Utils.getSimulationDelayInMillis(id))))
                .doOnNext(id -> log.info("Getting quote for {}", id))
                .flatMap(this::getQuote);
    }

    private Mono<Quote> getQuote(final int id) {
        // Let's pretend that this method is some
        // external service actually get quote from
        // the exchange over some network. Network
        // delays would be accounted for by the
        // Flux#delayElements(Duration) operator
        // in above method.
        return Mono.justOrEmpty(exchange.getQuote(id));
    }

}
