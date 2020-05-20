package korzeniewski.hubert.board.serialization;


import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Serialize images of notice to allow application to put them to database.
 */

@Service
public class ImagesSerializer {

    /**
     * Serialize images of notice to make possible saving them in database.
     *
     * @param images images as list of strings
     * @return byte[] of serialized images
     * @throws IOException
     */
    public byte[] serializeStringifiedImages(List<String> images) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objStream = new ObjectOutputStream(byteStream);
        objStream.writeObject(images);
        return byteStream.toByteArray();
    }

}
