import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ciphering {
    private String key;
    public String text;

    public Ciphering(String key, String text) {
        this.key = key;
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
        byte[] tmp = Files.readAllBytes(Paths.get(text));
        int k = 0;
        do {
            for (int i = 0; i < key.length && k * key.length + i < tmp.length; i++) {
                res.append((char) (tmp[k * key.length + i] ^ key[i]));
            }
            k++;
        }
        while (k * key.length < tmp.length);
        return res.toString();
    }
}


