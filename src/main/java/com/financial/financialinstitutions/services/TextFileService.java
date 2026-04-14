package com.financial.financialinstitutions.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TextFileService {
    private static final Logger LOGGER = LogManager.getLogger(TextFileService.class);

    public void countUniqueWords(String inputFileName, String outputFileName) {
        try {
            URL resource = getClass().getClassLoader().getResource(inputFileName);

            if (resource == null) {
                LOGGER.error("File {} was not found in resources", inputFileName);
                return;
            }

            String text = FileUtils.readFileToString(new File(resource.toURI()), StandardCharsets.UTF_8);

            Set<String> uniqueWords = Arrays.stream(StringUtils.lowerCase(text).split("\\W+"))
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toCollection(TreeSet::new));

            String result = "Unique words count: " + uniqueWords.size() + System.lineSeparator()
                    + String.join(System.lineSeparator(), uniqueWords);

            FileUtils.writeStringToFile(new File(outputFileName), result, StandardCharsets.UTF_8);

            LOGGER.info("Text file {} was processed successfully", inputFileName);
            LOGGER.info("Unique words count: {}", uniqueWords.size());
            LOGGER.info("Result was written to {}", outputFileName);

        } catch (IOException | URISyntaxException e) {
            LOGGER.error("Error while processing file {}", inputFileName, e);
        }
    }
}