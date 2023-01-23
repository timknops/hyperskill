package encryptdecrypt.parsing;

/**
 * Parses all the arguments that are given in the command line.
 *
 * @author tknops
 */
public class ArgParser {

    private final String[] ARGS;
    private String mode;
    private String data;
    private String alg;
    private String out;
    private String in;
    private int key;

    /**
     * Constructor.
     *
     * @param args  command line arguments
     */
    public ArgParser(String[] args) {
        this.ARGS = args;
        this.mode = "enc";
        this.data = "";
        this.alg = "shift";
        this.out = null;
        this.in = null;
        this.key = 0;
        parse();
    }

    /** Parses the arguments given. */
    public void parse() {
        for (int i = 0; i < ARGS.length; i++) {
            switch (ARGS[i]) {
                case "-mode" -> mode = ARGS[i + 1];
                case "-key" ->  key = Integer.parseInt(ARGS[i + 1]);
                case "-data" -> data = ARGS[i + 1];
                case "-out" -> out = ARGS[i + 1];
                case "-in" -> in = ARGS[i + 1];
                case "-alg" -> alg = ARGS[i + 1];
            }
        }

        // If there is both a -data and an -in argument, data is prioritized.
        if (!data.equals("") && in != null) {
            in = null;
        }
    }

    public String getMode() {
        return mode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOut() {
        return out;
    }

    public String getIn() {
        return in;
    }

    public int getKey() {
        return key;
    }

    public String getAlg() {
        return alg;
    }
}
