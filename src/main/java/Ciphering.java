import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ciphering {
    private String key;
    private String text;

    public Ciphering(String key, String text) {
        this.key = key;
        this.text = text;
    }

    public String coding() throws IOException {
        StringBuilder res = new StringBuilder();
        byte[] tmp = Files.readAllBytes(Paths.get(text));
        int k = 0;
        {
            for (int i = 0; i < key.length() && k * key.length() + i < tmp.length; i++) {
                res.append((char) (tmp[k * key.length() + i] ^ key.charAt(i)));
            }
            k++;
        }
        while (k * key.length() < tmp.length) ;
        return res.toString();
    }
}

