package com.itbaizhan.restful.dao;

import com.itbaizhan.restful.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "bs",collectionResourceRel = "bs",itemResourceRel = "b")
public interface BookDao extends JpaRepository<Book,Integer> {
    @RestResource(path = "byname",rel = "byname")
    Book getBookByNameContaining(@Param("name") String name);
}