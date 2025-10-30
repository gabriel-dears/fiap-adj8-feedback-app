package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto;


import java.util.List;

public record ApplicationPageDto<T>(
        List<T> items,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last,
        boolean first
) {
}
