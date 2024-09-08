package com.scm.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2;
    private static final int MAX_WIDTH = 1920;
    private static final int MAX_HEIGHT = 1080;

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File cannot be empty").addConstraintViolation();
            return true ;
        }

        if (file.getSize() > MAX_FILE_SIZE) {
          //  context.disableDefaultConstraintViolation();
           // context.buildConstraintViolationWithTemplate("File size exceeded. Should be less than 2 MB").addConstraintViolation();
            return false;
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("File is not a valid image").addConstraintViolation();
                return false;
            }
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            if (width > MAX_WIDTH || height > MAX_HEIGHT) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("Image dimensions exceeded. Max dimensions are %d x %d pixels", MAX_WIDTH, MAX_HEIGHT)).addConstraintViolation();
                return false;
            }
        } catch (IOException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Error reading image file").addConstraintViolation();
            return false;
        }
        return true;
    }
}
