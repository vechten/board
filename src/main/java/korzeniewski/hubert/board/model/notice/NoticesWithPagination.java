package korzeniewski.hubert.board.model.notice;

import java.util.ArrayList;
import java.util.List;

/**
 * Object of this class are send to fronted.
 */
public class NoticesWithPagination {

    private int totalNumberOfPages;
    private List<Notice> noticesFromPage = new ArrayList<>();

    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public void setTotalNumberOfPages(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public List<Notice> getNoticesFromPage() {
        return noticesFromPage;
    }

    public void setNoticesFromPage(List<Notice> noticesFromPage) {
        this.noticesFromPage = noticesFromPage;
    }

}
