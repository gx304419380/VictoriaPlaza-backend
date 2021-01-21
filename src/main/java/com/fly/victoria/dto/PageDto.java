package com.fly.victoria.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Data
@Accessors(chain = true)
public class PageDto<T> {

    private Long page;

    private Long size;

    private List<T> list;

    private Long count;

    private Boolean hasMore;

    public PageDto() {
    }

    public <S> PageDto(PageDto<S> pageDto, Function<S, T> map) {

        List<T> targetList = pageDto.getList().stream().map(map).collect(toList());

        this.setList(targetList);
        this.setHasMore(pageDto.hasMore);
        this.setCount(pageDto.page);
        this.setPage(pageDto.page);
        this.setSize(pageDto.size);
    }

    public static <T> PageDto<T> of(Page<T> page) {

        return new PageDto<T>()
                .setPage(page.getCurrent())
                .setSize(page.getSize())
                .setCount(page.getTotal())
                .setList(page.getRecords())
                .setHasMore(page.hasNext());
    }
}
