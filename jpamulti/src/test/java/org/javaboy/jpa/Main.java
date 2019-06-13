package org.javaboy.jpa;

import org.javaboy.jpa.bean.Book;
import org.javaboy.jpa.dao.BookDao;
import org.javaboy.jpa.dao2.BookDao2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-06-01 14:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Main {
    @Autowired
    BookDao bookDao;
    @Autowired
    BookDao2 bookDao2;

    @Test
    public void test() {
//        List<Book> all = bookDao.findAll();
//        List<Book> all2 = bookDao2.findAll();
//        System.out.println(all);
//        System.out.println(all2);
        Book book = new Book();
        book.setAuthor("罗贯中");
        bookDao.save(book);
        bookDao2.save(book);
    }

}
