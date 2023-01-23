package encryptdecrypt.algorithms;

/**
 * Sub-Class of Algorithm, encrypts/decrypts based on unicode.
 *
 * @see Algorithm
 * @author tknops
 */

public class AlgorithmUnicode extends Algorithm {

    private static final int MIN_CH = 33;
    private static final int MAX_CH = 126;

    public AlgorithmUnicode(String data, int key) {
        super(data, key);
    }

    @Override
    public String encrypt() {
        for (char ch : DATA) {
            for (int i = 0; i < KEY; i++) {
                if (ch > MAX_CH) {
                    ch = MIN_CH;
                } else {
                    ch++;
                }
            }

            sb.append(ch);
        }

        return sb.toString();
    }

    @Override
    public String decrypt() {
        for (char ch : DATA) {
            for (int i = 0; i < KEY; i++) {
                if (ch < MIN_CH) {
                    ch = MAX_CH;
                } else {
                    ch--;
                }
            }

            sb.append(ch);
        }

        return sb.toString();
    }
}
