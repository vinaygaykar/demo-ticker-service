package demo.vinaygaykar.commons;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Exchange {

    // Collection of registered companies to this exchange.
    // Companies are stored as key-value pair with key being
    // the Company ID and value being the Company itself
    private final Map<Integer, Company> registeredCompanies;


    public Exchange() {
        // lets register some companies
        this.registeredCompanies = IntStream.range(0, 6)
                .mapToObj(Company::new)
                .collect(Collectors.toMap(Company::id, Function.identity()));
    }

    /**
     * Returns LTP for a {@link Company}.
     * If there is no company registered with given ID
     * then expect empty response.
     *
     * @param id ID of the company to query
     * @return LTP of the company if ID is valid or
     * else {@link Optional#empty()}
     */
    public Optional<Quote> getQuote(final int id) {
        return Optional.ofNullable(registeredCompanies.get(id))
                .map(company -> new Quote(company.symbol(), company.tick(), Instant.now()));
    }

    public int getCountOfRegisteredCompanies() {
        return registeredCompanies.size();
    }

}
