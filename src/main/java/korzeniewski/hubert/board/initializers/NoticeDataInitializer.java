package korzeniewski.hubert.board.initializers;

import korzeniewski.hubert.board.model.images.ImagesOfNotice;
import korzeniewski.hubert.board.model.images.ImagesOfNoticeBuilder;
import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.model.notice.NoticeBuilder;
import korzeniewski.hubert.board.repository.notices.NoticeRepository;
import korzeniewski.hubert.board.security.model.User;
import korzeniewski.hubert.board.security.repository.UserRepository;
import korzeniewski.hubert.board.serialization.ImagesSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * Initializes/creates example notices.
 */
@Component
public class NoticeDataInitializer {

    private NoticeRepository noticeRepository;
    private UserRepository userRepository;
    private ExampleImageReader exampleImageReader;
    private ImagesSerializer imagesSerializer;

    @Autowired
    public NoticeDataInitializer(NoticeRepository noticeRepository, UserRepository userRepository, ExampleImageReader exampleImageReader, ImagesSerializer imagesSerializer) {
        this.noticeRepository = noticeRepository;
        this.userRepository = userRepository;
        this.exampleImageReader = exampleImageReader;
        this.imagesSerializer = imagesSerializer;
    }

    /**
     * Creates example notices and save them to database by using NoticeRepository.
     */
    @PostConstruct
    public void initializeExampleNotices() throws IOException, ParseException {
        List<String> exampleImages = exampleImageReader.readExampleImage();
        byte[] bytesOfExampleImages;
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            for (int i = 1; i <= 10; i++) {
                Date creationDateAndTime = new Date();
                creationDateAndTime.setYear(110 + i);
                Date creationDate = getDate(creationDateAndTime);
                Date creationTime = getTime(creationDateAndTime);
                Collections.shuffle(exampleImages, new Random(i));
                bytesOfExampleImages = imagesSerializer.serializeStringifiedImages(exampleImages);
                Notice notice = new NoticeBuilder().withAuthor(user).withCreationDate(creationDate).withCreationTime(creationTime).withCreationDateAndTime(creationDateAndTime).withTitle("Notice No. " + i).withContent(generateTextContent(i)).buildNotice();
                ImagesOfNotice imagesOfNotice = new ImagesOfNoticeBuilder().withRandomId().withImages(bytesOfExampleImages).withNotice(notice).buildImagesOfNotice();
                notice.setImages(imagesOfNotice);
                user.getNotices().add(notice);
                noticeRepository.save(notice);
            }
            userRepository.save(user);
        }
    }

    /**
     * Gets date (dd/MM/yyyy) of given object of Date class.
     *
     * @param date to be salvaged
     * @return date with time as 00:00
     * @throws ParseException
     */
    public Date getDate(Date date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateWithoutTime = formatter.parse(formatter.format(date));
        return dateWithoutTime;
    }

    /**
     * Gets time of given object of Date class
     *
     * @param date to be salvaged
     * @return time with date at starting point i.e. 1970
     * @throws ParseException
     */
    public Date getTime(Date date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Date dateWithoutTime = formatter.parse(formatter.format(date));
        return dateWithoutTime;
    }

    /**
     * Generates example text content
     * @param i iteration
     * @return generated content
     */
    public String generateTextContent(int i) {
        String content = " Content No. " + i;
        for(int j = 0; j<=i; j++){
            content+=content;
        }
        return content.trim();
    }

}

