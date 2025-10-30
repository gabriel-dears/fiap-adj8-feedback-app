package fiap_adj8.feedback_platform.feedback_app.application.model;

import java.util.List;

public class ApplicationPage<T> {

    private final List<T> items;
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;
    private final boolean last;
    private final boolean first;

    public ApplicationPage(List<T> items, int page, int size, long totalElements, int totalPages, boolean last, boolean first) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
        this.first = first;
    }

    private int calculateTotalPages(int size, long totalElements) {
        return size > 0 ? (int) Math.ceil((double) totalElements / size) : 0;
    }

    private boolean isLastPage(int currentPage, int totalPages) {
        // Assuming page index is 0-based
        return totalPages == 0 || currentPage >= totalPages - 1;
    }

    public List<T> getItems() {
        return items;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean isFirst() {
        return first;
    }

}
