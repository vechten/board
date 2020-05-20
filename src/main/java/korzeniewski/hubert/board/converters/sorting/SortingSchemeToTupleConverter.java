package korzeniewski.hubert.board.converters.sorting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

/**
 * Service needed to convert sorting scheme as String from front-end application to Pair<String, Sort.Direction> to be acceptable by repository.
 */
@Service
public class SortingSchemeToTupleConverter {

    @Value("${sorting.scheme.separator}")
    private String sortingSchemeSeparator;

    /**
     * Converts sorting scheme as String to Pair<String, Sort.Direction> to be acceptable by repository.
     *
     * @param sortingScheme as String
     * @return sortingScheme as Pair<String, Sort.Direction>
     */
    public Pair<String, Sort.Direction> convertSortingSchemeToTuple(String sortingScheme) {
        String[] splittedSortScheme = sortingScheme.split(this.sortingSchemeSeparator);
        if (splittedSortScheme[1].toLowerCase().contains("asc")) {
            return new Pair<>(splittedSortScheme[0], Sort.Direction.ASC);
        }
        return new Pair<>(splittedSortScheme[0], Sort.Direction.DESC);
    }

}
