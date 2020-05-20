package korzeniewski.hubert.board.serialization;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Class needed to deserialize images of proper notice from database.
 */

@Service
public class ImagesDeserializer {

    /**
     * Deserialize serialized images of notice.
     *
     * @param bytes serialized images
     * @return list of images as strings
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<String> deserializeByteArrayToImages(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objStream = new ObjectInputStream(byteStream);
        return (List<String>) objStream.readObject();
    }

}

