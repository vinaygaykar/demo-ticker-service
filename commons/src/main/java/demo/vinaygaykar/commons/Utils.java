package demo.vinaygaykar.commons;

public class Utils {

    private Utils() {
    }

    /**
     * Returns how long to simulate network delay based upon {@link Company#id()}.
     *
     * @param id ID of the company
     * @return length of the delay
     */
    public static long getSimulationDelayInMillis(final int id) {
        return switch (id % 3) {
            case 0 -> 100L;
            case 1 -> 500L;
            case 2 -> 1000L;
            default -> 0L; // this will never happen; we are mod-ing by 3
        };
    }

}
