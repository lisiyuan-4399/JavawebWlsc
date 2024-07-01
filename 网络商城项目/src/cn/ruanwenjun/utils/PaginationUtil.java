package cn.ruanwenjun.utils;

import cn.ruanwenjun.domain.PageBean;

import java.util.List;

public class PaginationUtil {

    // 写一个返回分页对象的方法
    public <T> PageBean<T> getPageBean(Integer currentCount, Integer currentPage, Integer totalCount, Integer totalPage, List<T> obj){
        return new PageBean(currentCount,currentPage,totalCount,totalPage,obj) ;
    }
}
