import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Ciphering {
    private String key;
    public String text;

    public Ciphering(String tkey, String text) {
        this.key = tkey;
        this.text = text;
    }

    private byte[] stringToByte(String string) {
        byte[] res = new byte[string.length() / 2];
        for (int i = 0; i < string.length(); i += 2) {
            res[i / 2] = (byte) ((Character.digit(string.charAt(i), 16) << 4)
                    + Character.digit(string.charAt(i + 1), 16));
        }
        return res;
    }

    public String coding() throws IOException {
        byte[] key = stringToByte(this.key);
        StringBuilder res = new StringBuilder();
        byte[] tmp = new byte[key.length];
        InputStream inp = new FileInputStream(new File(text));
        int k = 0;
        do {
            int sz = inp.read(tmp);
            for (int i = 0; i < key.length && i < sz; i++) {
                res.append((char) (tmp[i] ^ key[i]));
            }
            k += sz;
        }
        while (k < new File(text).length());
        inp.close();
        return res.toString();
    }
}


