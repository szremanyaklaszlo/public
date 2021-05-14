package com.epam.training.ascii.service;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.training.ascii.domain.Image;

public class DefaultImageService implements ImageService {

    private static final String SOURCE_PATH = "src/main/resources/";
    private final Logger logger = LoggerFactory.getLogger(DefaultImageService.class);

    private Image image;

    @Override
    public Image findImage() {
        if (image == null) {
            throw new NullPointerException("Does not have image in " + DefaultImageService.class.getCanonicalName());
        }
        return image;
    }

    @Override
    public void readImage(String imageName) {
        try {
            image = new Image(ImageIO.read(new File(SOURCE_PATH + imageName)));
        } catch (IOException ex) {
            logger.error(DefaultImageService.class.getCanonicalName() + " cannot read image from " + SOURCE_PATH + imageName + " ", ex);
        }
    }

    @Override
    public char[][] calculateCharacterMatrix(Image image, int resolutionX, int resolutionY) {
        ImageConverter converter = new ImageConverter(image);
        return converter.convertImageToCharacterMatrix(resolutionX, resolutionY);
    }

}
