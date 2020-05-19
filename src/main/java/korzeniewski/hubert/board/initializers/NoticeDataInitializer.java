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
        for(int i = 1; i <= 100; i++){
            Notice notice = noticeBuilder.withAuthor("User" + i).withDate(new Date().toString()).withTitle("Notice No. " + i).withContent("Content No. " + i).buildNotice();
            noticeRepository.save(notice);
        }
    }

}

