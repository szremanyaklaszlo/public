package com.epam.training.ascii.service;

import com.epam.training.ascii.domain.Image;

public interface ImageService {

    void readImage(String imageName);
    Image findImage();
    char[][] calculateCharacterMatrix(Image image, int resolutionX, int resolutionY);

}
