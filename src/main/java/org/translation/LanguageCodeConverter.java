package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting language codes to their names.
 */
public class LanguageCodeConverter {
    private final Map<String, String> codeToLanguageMap = new HashMap<>();
    private final Map<String, String> languageToCodeMap = new HashMap<>();
    // TODO Task: pick appropriate instance variables to store the data necessary for this class
    private Map<String, String> languageMap = new HashMap<>();

    /**
     * Default constructor which will load the language codes from "language-codes.txt"
     * in the resources folder.
     */
    public LanguageCodeConverter() {
        this("language-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the language code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    @SuppressWarnings({"checkstyle:RightCurly", "checkstyle:SuppressWarnings"})
    public LanguageCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            // TODO Task: use lines to populate the instance variable
            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()) {
                String line = iterator.next();
                // Split the line into language name and code
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    languageMap.put(parts[1].trim(), parts[0].trim());
                }
            }

            // TODO Checkstyle: '}' on next line should be alone on a line.
            for (String line : lines) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    String language = parts[0].trim();
                    String code = parts[1].trim();
                    codeToLanguageMap.put(code, language);
                    languageToCodeMap.put(language, code);
                }
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the language for the given language code.
     * @param code the language code
     * @return the name of the language corresponding to the code
     */
    public String fromLanguageCode(String code) {
        // TODO Task: update this code to use your instance variable to return the correct value

        return codeToLanguageMap.getOrDefault(code, "Unknown language code");

    }

    /**
     * Returns the code of the language for the given language name.
     * @param language the name of the language
     * @return the 2-letter code of the language
     */
    public String fromLanguage(String language) {
        // TODO Task: update this code to use your instance variable to return the correct value
        return languageToCodeMap.getOrDefault(language, "Unknown language");

    }

    /**
     * Returns how many languages are included in this code converter.
     * @return how many languages are included in this code converter.
     */
    public int getNumLanguages() {
        // TODO Task: update this code to use your instance variable to return the correct value

        return codeToLanguageMap.size();
       
    }
}
