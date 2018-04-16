package main.java;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class ciphering {
    private byte key[];

    @Argument
    private String inputName;
    @Option(name = "outputName", usage = "Output file coding")
    private String outputName;
    @Option(name = "-c", aliases = {"-d"}, metaVar = "key", required = true,
            usage = "Set connect coding/decoding")
    private String keys;

    public ciphering(String[] args) {
        CmdLineParser cmd = new CmdLineParser(this);
        try {
            cmd.parseArgument(args);
        } catch (CmdLineException exception) {
            System.err.print(exception.getMessage());
            cmd.printUsage(System.out);
        }
    }

    private void CiphKey(String keys) {
        int i;
        String text = " ";
        key = new byte[keys.length()];
        Integer n;
        for (i = 0; i < keys.length(); i++) {
            text = keys.substring(i, i++);
            n = Integer.parseInt(text, 16);
            key[i++] = n.byteValue();
        }
    }

    public byte[] Keycoder(byte[] text) {
        int i;
        byte[] res = new byte[text.length];
        for (i = 0; i < text.length; i++) {
            res[i] = (byte) (text[i] ^ key[i / key.length]);
        }
        return res;
    }

    public String getInputName() {
            return this.inputName;
        }

        public String getOutputName() {
            return this.outputName;
        }

        public String getKeys() {
            return this.keys;
        }


}
