package com.epam.training.ascii.view;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleView implements View {

    private final Logger logger = LoggerFactory.getLogger(ConsoleView.class);

    @Override
    public void printCharacterMatrix(char[][] characterMatrix) {
        logger.info("\n" + Arrays.deepToString(characterMatrix).replace("], ", "\n").replace("[", "").replace("]]", ""));
    }

}
