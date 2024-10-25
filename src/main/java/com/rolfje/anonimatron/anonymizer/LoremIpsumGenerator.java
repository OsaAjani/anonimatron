package com.rolfje.anonimatron.anonymizer;

import com.rolfje.anonimatron.synonyms.StringSynonym;
import com.rolfje.anonimatron.synonyms.Synonym;


public class LoremIpsumGenerator implements Anonymizer {
    private static final String[] WORDS = {
        "lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", 
        "elit", "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", 
        "et", "dolore", "magna", "aliqua", "ut", "enim", "ad", "minim", "veniam", 
        "quis", "nostrud", "exercitation", "ullamco", "laboris", "nisi", "ut", 
        "aliquip", "ex", "ea", "commodo", "consequat", "duis", "aute", "irure", 
        "dolor", "in", "reprehenderit", "in", "voluptate", "velit", "esse", 
        "cillum", "dolore", "eu", "fugiat", "nulla", "pariatur", "excepteur", 
        "sint", "occaecat", "cupidatat", "non", "proident", "sunt", "in", 
        "culpa", "qui", "officia", "deserunt", "mollit", "anim", "id", "est", 
        "laborum"
    };

    @Override
    public Synonym anonymize(Object from, int size, boolean shortlived) {
        String originalText = (String) from;
        String loremIpsumText = generateLoremIpsum(originalText);

        return new StringSynonym(
                getType(),
                originalText,
                loremIpsumText,
                shortlived
        );
    }

    @Override
    public String getType() {
        return "LOREM_IPSUM";
    }

    private String generateLoremIpsum(String text) {
        String[] words = text.split("\\s+"); // Split the input text into words
        StringBuilder loremIpsum = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            // Replace each word with the next word from the WORDS array
            int loremIndex = i % WORDS.length; // Cycle through the WORDS array
            loremIpsum.append(WORDS[loremIndex]).append(" ");
        }

        return loremIpsum.toString().trim(); // Return the generated Lorem Ipsum text
    }
}
