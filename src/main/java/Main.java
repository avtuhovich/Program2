import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    private static Prog p = new Prog();

    public static void main(String[] args) {
        Ciphering prg = p.parse(args);
        if (p.outputName == null) {
            p.outputName = p.inputName + ".avt";
        }
        try {
            if (!new File(p.outputName).exists()) {
                new File(p.outputName).createNewFile();
            }
            PrintWriter out = new PrintWriter(p.outputName);
            out.println(prg.coding());
            out.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    static class Prog {
        @Option
                (name = "-o", usage = "Output file")
        private String outputName;
        @Argument
        private String inputName;
        @Option
                (name = "-c", usage = "Cipher file with key")
        private String ckey = null;

        @Option
                (name = "-d", usage = "Decipher file with key")
        private String dkay = null;

        Ciphering parse(final String[] args) {
            CmdLineParser cmd = new CmdLineParser(this);
            try {
                cmd.parseArgument(args);
            } catch (CmdLineException exception) {
                System.err.print(exception.getMessage());
                cmd.printUsage(System.out);
            }
            String key = ckey != null ? ckey : dkay != null ? dkay : "";
            if (key == "") {
                cmd.printUsage(System.out);
                System.exit(0);
            }
            return new Ciphering(key, inputName);
        }


    }
}

