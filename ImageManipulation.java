import javax.imageio.*;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.io.*;

/**
 * ImageManipulation
 */
public class ImageManipulation {

    public static void main(String[] args) {
        // load image
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\Users\\musta\\OneDrive\\Documents\\Classes\\CS 3000\\test.png"));

            // BufferedImage result = reflectY(img);
            // BufferedImage result = reflectX(img);
            // BufferedImage result = rotate90(img);
            // result = rotate90(result);
            // BufferedImage result = rotateAboutX(img);
            // BufferedImage result = rotateAboutY(img);
            BufferedImage result = grayScale(img);

            ImageIO.write(result, "png", new File("C:\\Users\\musta\\OneDrive\\Documents\\Classes\\CS 3000\\outTest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage grayScale(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                
                result.setRGB(i, j, img.getRGB(i, j));
            }
        }
        return result;
    }

    private static BufferedImage rotate90(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getHeight(), img.getWidth(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                
                result.setRGB(j, result.getHeight() - i - 1, img.getRGB(i, j));
            }
        }
        return result;
    }

    private static BufferedImage reflectX(BufferedImage img) {
        BufferedImage result = img.getSubimage(0, 0, img.getWidth(), img.getHeight());
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                
                result.setRGB(i, img.getHeight() - j - 1, img.getRGB(i, j));
            }
        }
        return result;
    }

    private static BufferedImage reflectY(BufferedImage img) {
        BufferedImage result = img.getSubimage(0, 0, img.getWidth(), img.getHeight());
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                
                result.setRGB(img.getWidth() - i - 1, j, img.getRGB(i, j));
            }
        }
        return result;
    }

    private static BufferedImage rotateAboutX(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                
                result.setRGB(i, img.getHeight() - j - 1, img.getRGB(i, j));
            }
        }
        return result;
    }

    private static BufferedImage rotateAboutY(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                
                result.setRGB(img.getWidth() - i - 1, j, img.getRGB(i, j));
            }
        }
        return result;
    }
}