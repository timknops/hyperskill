package encryptdecrypt.algorithms;

/**
 * Sub-Class of Algorithm, encrypts/decrypts from a-z, A-Z. Does not include any characters that aren't in the alphabet.
 *
 * @see Algorithm
 * @author tknops
 */

public class AlgorithmShift extends Algorithm {

    public AlgorithmShift(String data, int key) {
        super(data, key);
    }

    @Override
    public String encrypt() {
        for (char ch : DATA) {
            for (int i = 0; i < KEY; i++) {
                if (ch == ' ' || ch == '!') {
                    break;
                } else if (ch == 'z') {
                    ch = 'a';
                } else if (ch == 'Z') {
                    ch = 'A';
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
                if (ch == ' ' || ch == '!') {
                    break;
                } else if (ch == 'a') {
                    ch = 'z';
                } else if (ch == 'A') {
                    ch = 'Z';
                } else {
                    ch--;
                }
            }

            sb.append(ch);
        }

        return sb.toString();
    }
}
