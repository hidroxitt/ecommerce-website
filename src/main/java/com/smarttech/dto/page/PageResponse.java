package com.smarttech.dto.page;

import com.smarttech.utils.PagingUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private int page;
    private int pageSize;
    private int total;
    private List<T> items;

    public PageResponse(PageResponse<?> pageResponse, List<T> items) {
        this(pageResponse.getPage(), pageResponse.getPageSize(), pageResponse.getTotal(), items);
    }

    public List<Object> getPages() {
        return PagingUtil.getPages(this.page, this.getTotalPage());
    }

    public int getTotalPage() {
        return (int) Math.ceil((double)this.total / (double) this.pageSize);
    }
}
