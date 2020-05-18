package korzeniewski.hubert.board.initializers;

import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.model.notice.NoticeBuilder;
import korzeniewski.hubert.board.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class NoticeDataInitializer {

    private NoticeRepository noticeRepository;
    private NoticeBuilder noticeBuilder;

    @Autowired
    public NoticeDataInitializer(NoticeRepository noticeRepository, NoticeBuilder noticeBuilder) {
        this.noticeRepository = noticeRepository;
        this.noticeBuilder = noticeBuilder;
    }

    /**
     * Creates example notices and save them to database by using NoticeRepository.
     */
    @PostConstruct
    public void initializeExampleNotices() {
        Notice notice1 = noticeBuilder.withAuthor("User1").withDate(new Date().toString()).withTitle("Notice No. 1").withContent("Content No. 1").buildMessage();
        noticeRepository.save(notice1);

        Notice notice2 = noticeBuilder.withAuthor("User2").withDate(new Date().toString()).withTitle("Notice No. 2").withContent("Content No. 2").buildMessage();
        noticeRepository.save(notice2);

        Notice notice3 = noticeBuilder.withAuthor("User3").withDate(new Date().toString()).withTitle("Notice No. 3").withContent("Content No. 3").buildMessage();
        noticeRepository.save(notice3);

        Notice notice4 = noticeBuilder.withAuthor("User4").withDate(new Date().toString()).withTitle("Notice No. 4").withContent("Content No. 4").buildMessage();
        noticeRepository.save(notice4);
    }

}

