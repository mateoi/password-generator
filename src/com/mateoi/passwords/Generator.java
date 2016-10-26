package com.mateoi.passwords;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

public class Generator {

    private static final Path PATH = Paths.get("corncob_lowercase.txt");

    public static void main(String[] args) {
        final int num = args.length > 0 ? Integer.parseInt(args[0]) : 5;
        final Path path = args.length > 1 ? Paths.get(args[1]) : PATH;
        List<String> words;
        try {
            words = Files.readAllLines(path);
            final SecureRandom random = SecureRandom.getInstanceStrong();
            final StringBuilder sb = new StringBuilder();
            random.ints(num, 0, words.size()).boxed().map(i -> words.get(i)).forEach(w -> sb.append(w + " "));
            System.out.println(sb);

        } catch (final IOException e) {
            System.err.println("File not found!");
            System.exit(1);
        } catch (final NoSuchAlgorithmException e) {
            System.err.println("Error creating a secure RNG!");
            System.exit(2);
        }
    }
}
