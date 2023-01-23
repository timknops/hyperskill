package encryptdecrypt.algorithms;

/**
 * Base class for the algorithms.
 *
 * @author tknops
 */

public abstract class Algorithm {

    protected final StringBuilder sb;
    protected final char[] DATA;
    protected final int KEY;

    /**
     * Constructor.
     *
     * @param data  text that needs to be encrypted/decrypted
     * @param key   value needed for the encryption/decryption
     */
    protected Algorithm(String data, int key) {
        this.DATA = data.toCharArray();
        this.KEY = key;
        this.sb = new StringBuilder();
    }

    /**
     * Encrypts the text.
     *
     * @return  the encrypted value of the text that was requested by the user
     */
    public abstract String encrypt();

    /**
     * Decrypts the text.
     *
     * @return  the decrypted value of the text that was requested by the user
     */
    public abstract String decrypt();
}
