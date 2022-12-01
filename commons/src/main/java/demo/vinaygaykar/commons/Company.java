package demo.vinaygaykar.commons;

import java.util.concurrent.ThreadLocalRandom;

public record Company(int id) {

    /**
     * Returns human-readable name of the company.
     * It is generated using company ID.
     *
     * @return company name
     */
    public String symbol() {
        return "CMP" + id;
    }

    /**
     * Returns last-traded-price (LTP) of this company.
     * This is a randomly generated double value.
     * <p>
     * Before returning random delay is introduced
     * to simulate network latency.
     *
     * @return LTP of this company
     */
    public double tick() {
        return ThreadLocalRandom.current().nextDouble(0, 100);
    }

}