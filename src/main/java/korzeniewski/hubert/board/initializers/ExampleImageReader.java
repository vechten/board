package korzeniewski.hubert.board.initializers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates example image.
 */
@Service
public class ExampleImageReader {

    private static final Logger logger = Logger.getLogger(ExampleImageReader.class.getName());

    @Value("${backend.application.example.image.path}")
    private String imgPathPattern;
    private String[] fileNames;
    private String[] imgPaths;

    /**
     * Reads names of the images.
     */
    @PostConstruct
    public void prepareImages() {
        this.imgPaths = new String[3];
        this.fileNames = new String[3];
        for(int i =1; i <= 3; i++){
            String generatedImgPath = imgPathPattern.replace("picture", "picture"+i);
            this.imgPaths[i-1] = generatedImgPath;
            String[] splitedPath = generatedImgPath.split("/");
            this.fileNames[i-1] = splitedPath[splitedPath.length - 1];
        }
    }

    /**
     * Reads image file and creates image as string with Base64 encoder.
     *
     * @return example image in list of images as strings
     */
    public List<String> readExampleImage() {
        List<String> images = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            try (FileInputStream imageStream = new FileInputStream(this.imgPaths[i])) {
                byte[] bytesOfImage = imageStream.readAllBytes();
                String encodeBase64 = Base64.getEncoder().encodeToString(bytesOfImage);
                String extension = this.fileNames[i];
                images.add("data:image/" + extension + ";base64," + encodeBase64);
            } catch (Exception ex) {
                logger.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return images;
    }

}
