package korzeniewski.hubert.board.converters.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service of converting images from frontend.
 */
@Service
public class ImageFileToStringConverter {

    private static final Logger logger = Logger.getLogger(ImageFileToStringConverter.class.getName());

    /**
     * Converts Multipart files of images from fronted to list of strings.
     *
     * @param files images from frontend
     * @return images as list of strings
     */
    public List<String> convertImageFilesToListOfString(List<MultipartFile> files) {
        List<String> images = new ArrayList<>();
        files.stream().forEach(multipartFile -> {
            String imageName = multipartFile.getOriginalFilename();
            String imageContent = null;
            try {
                imageContent = Base64.getEncoder().encodeToString(multipartFile.getBytes());
            } catch (IOException ex) {
                logger.log(Level.WARNING, ex.getMessage(), ex);
            }
            String stringifiedImage = ("data:image/" + imageName + ";base64," + imageContent);
            images.add(stringifiedImage);
        });
        return images;
    }

}
