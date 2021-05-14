package com.epam.training.ascii;

import com.epam.training.ascii.service.DefaultImageService;
import com.epam.training.ascii.service.ImageService;
import com.epam.training.ascii.view.ConsoleView;
import com.epam.training.ascii.view.View;

/**
 * This application read an image and create a character matrix from it.
 * This matrix is use different characters to representing the pixel brightness.
 *
 * @author László Szremanyák
 * @since 1.0.0
 */
public final class AdvancedAscii {

    private static final int RESOLUTION_Y = 140;
    private static final int RESOLUTION_X = 200;
    private static ImageService service;
    private static View view;

    public AdvancedAscii(ImageService service, View view) {
        AdvancedAscii.service = service;
        AdvancedAscii.view = view;
    }

    public static void main(final String[] args) {
        AdvancedAscii app = new AdvancedAscii(new DefaultImageService(), new ConsoleView());
        app.processImage();
    }

    private void processImage() {
        service.readImage("pair_hiking.png");
        char[][] characterMatrix = service.calculateCharacterMatrix(service.findImage(), RESOLUTION_X, RESOLUTION_Y);
        view.printCharacterMatrix(characterMatrix);
    }

}
