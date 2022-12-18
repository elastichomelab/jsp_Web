package iducs.javaweb.blog202112004.repository;

import iducs.javaweb.blog202112004.model.Blog;
import iducs.javaweb.blog202112004.util.Pagination;

import java.util.List;

//crud: create read update delete
//http method: post, get, put, delete
public interface BlogDAO {
    int create(Blog blog);
    Blog read(Blog blog);
    List<Blog> readList();


    int update(Blog blog);
    int delete(Blog blog);

    int readTotalRows();


    List<Blog> asc();

//    List<Blog> readListPagination(Pagination pagination);
//    List<Blog> sortListPaginationDT(Pagination pagination);
//    List<Blog> sortListPaginationAT(Pagination pagination);
//    List<Blog> sortListPaginationDE(Pagination pagination);
//    List<Blog> sortListPaginationAE(Pagination pagination);
}
