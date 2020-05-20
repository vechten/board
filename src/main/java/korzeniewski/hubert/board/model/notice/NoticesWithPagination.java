package korzeniewski.hubert.board.model.notice;

import java.util.ArrayList;
import java.util.List;

/**
 * Object of this class are send to fronted.
 */
public class NoticesWithPagination {

    private long totalNumberOfAllNotices;
    private List<Notice> noticesFromPage = new ArrayList<>();
    private String sortingScheme;

    public List<Notice> getNoticesFromPage() {
        return noticesFromPage;
    }

    public void setNoticesFromPage(List<Notice> noticesFromPage) {
        this.noticesFromPage = noticesFromPage;
    }

    public long getTotalNumberOfAllNotices() {
        return totalNumberOfAllNotices;
    }

    public void setTotalNumberOfAllNotices(long totalNumberOfAllNotices) {
        this.totalNumberOfAllNotices = totalNumberOfAllNotices;
    }

    public String getSortingScheme() {
        return sortingScheme;
    }

    public void setSortingScheme(String sortingScheme) {
        this.sortingScheme = sortingScheme;
    }

}


