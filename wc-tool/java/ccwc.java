import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ccwc {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("usage: Custom Word Count Tool by @abidmuin [-h] [-c] [-l] [-w] [-m] [file]");
            System.exit(1);
        }

        String option = args[0];
        String filename = (args.length > 1) ? args[1] : null;

        try {
            if (filename != null) {
                processFile(option, filename);
            } else {
                processStdin(option);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void processFile(String option, String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename, Charset.defaultCharset()))) {
            switch (option) {
                case "-c":
                    countBytes(reader, filename);
                    break;
                case "-l":
                    countLines(reader, filename);
                    break;
                case "-w":
                    countWords(reader, filename);
                    break;
                case "-m":
                    countCharacters(reader, filename);
                    break;
                default:
                    printDefaultStats(reader, filename);
                    break;
            }
        }
    }

    private static void processStdin(String option) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            switch (option) {
                case "-c":
                    countBytes(reader, null);
                    break;
                case "-l":
                    countLines(reader, null);
                    break;
                case "-w":
                    countWords(reader, null);
                    break;
                case "-m":
                    countCharacters(reader, null);
                    break;
                default:
                    printDefaultStats(reader, null);
                    break;
            }
        }
    }

    private static void countBytes(BufferedReader reader, String filename) throws IOException {
        int count = 0;
        int value;
        while ((value = reader.read()) != -1) {
            count++;
        }
        printResult(count, filename);
    }

    private static void countLines(BufferedReader reader, String filename) throws IOException {
        int count = 0;
        while (reader.readLine() != null) {
            count++;
        }
        printResult(count, filename);
    }

    private static void countWords(BufferedReader reader, String filename) throws IOException {
        int count = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");
            count += words.length;
        }
        printResult(count, filename);
    }

    private static void countCharacters(BufferedReader reader, String filename) throws IOException {
        int count = 0;
        int value;
        while ((value = reader.read()) != -1) {
            count++;
        }
        printResult(count, filename);
    }

    private static void printDefaultStats(BufferedReader reader, String filename) throws IOException {
        int lineCount = 0;
        int wordCount = 0;
        int byteCount = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            lineCount++;
            String[] words = line.split("\\s+");
            wordCount += words.length;
            byteCount += line.length();
        }
        printResult(lineCount, wordCount, byteCount, filename);
    }

    private static void printResult(int count, String filename) {
        if (filename != null) {
            System.out.printf("%8d %s%n", count, filename);
        } else {
            System.out.printf("%8d%n", count);
        }
    }

    private static void printResult(int lines, int words, int bytes, String filename) {
        if (filename != null) {
            System.out.printf("%8d %8d %8d %s%n", lines, words, bytes, filename);
        } else {
            System.out.printf("%8d %8d %8d%n", lines, words, bytes);
        }
    }
}