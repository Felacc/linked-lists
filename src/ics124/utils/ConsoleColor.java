package ics124.utils;

/** 
 * A fancy enum for colourizing our output.  This is gold-plating, and is just
 * a bit of syntactic sugar so we encapsulate these ugly escape codes away from
 * the unit tests.
 * 
 * Package-local, because we don't want to export this to the rest of the world.
 *
 * @author W3Schools via StackOverflow
 */
enum ConsoleColor {
    //Color end string, color reset
    RESET("\033[0m"),

    // Regular Colors. Normal color, no bold, background color etc.
    BLACK("\033[0;30m"),    // BLACK
    RED("\033[0;31m"),      // RED
    GREEN("\033[0;32m"),    // GREEN
    YELLOW("\033[0;33m"),   // YELLOW
    BLUE("\033[0;34m"),     // BLUE
    MAGENTA("\033[0;35m"),  // MAGENTA
    CYAN("\033[0;36m"),     // CYAN
    WHITE("\033[0;37m");    // WHITE

    private final String code;

    /**
     * Create an enumerated value with the given ANSI escape code
     * 
     * @param code the ANSI escape code
     */
    ConsoleColor(String code) {
        this.code = code;
    }

    /**
     * Returns the ANSI escape code associated with the named
     * enumeration value.  Suitable for sticking into a string and
     * printing on the console.
     *
     * @return  the ANSI escape code 
     */
    @Override
    public String toString() {
        return code;
    }
}