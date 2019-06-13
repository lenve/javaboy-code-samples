package org.javaboy.jpa;

import org.javaboy.jpa.bean.Book;
import org.javaboy.jpa.dao.BookDao;
import org.javaboy.jpa.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

    @Autowired
    BookDao bookDao;

    @Test
    public void contextLoads() {
        Book book = new Book();
        book.setAuthor("罗贯中");
        book.setName("三国演义");
        book.setId(99L);
        bookDao.save(book);
    }

    @Test
    public void test1() {
        Book book = bookDao.findById(1L).get();
        book.setAuthor("罗贯中222");
        bookDao.saveAndFlush(book);
    }

    @Test
    public void test2() {
        List<Book> all = bookDao.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC,"id")));
        System.out.println(all);
    }

    @Test
    public void test3() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Book> page = bookDao.findAll(pageable);
        System.out.println(page.getNumberOfElements());//当前记录数
        System.out.println(page.getNumber());//当前页数
        System.out.println(page.getSize());//每页应该查到的记录数
        System.out.println(page.isLast());//是否是最后一页
        System.out.println(page.isFirst());//是否是首页
        System.out.println(page.getTotalPages());//总页数
        System.out.println(page.getTotalElements());//总记录数
        System.out.println(page.getContent());//真正查到的数据
    }

    @Test
    public void test4() {
        bookDao.deleteById(3L);
    }

    @Test
    public void test5() {
        List<Book> list = bookDao.findBookByIdGreaterThanEqual(3L);
        System.out.println(list);
    }

    @Test
    public void test6() {
        Long aLong = bookDao.totalCount();
        System.out.println(aLong);
    }

    @Autowired
    BookService bookService;
    @Test
    public void test7() {
        bookService.updateBookByName("鲁迅333", "故事新编");
    }
}
