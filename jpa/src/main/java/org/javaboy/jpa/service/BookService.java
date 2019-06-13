package org.javaboy.jpa.service;

import org.javaboy.jpa.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-06-01 11:27
 */
@Service
@Transactional
public class BookService {
    @Autowired
    BookDao bookDao;

    public Integer updateBookByName(String author, String name) {
        return bookDao.updateBookByName(author, name);
    }
}
