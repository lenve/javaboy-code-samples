package org.javaboy.restfultools.controller;

import org.javaboy.restfultools.model.Book;
import org.javaboy.restfultools.model.RespBean;
import org.springframework.web.bind.annotation.*;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class BookController {
    @PostMapping("/book")
    public RespBean addBook(Book book) {
        System.out.println(book);
        return RespBean.ok("添加成功");
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable Integer id) {
        Book book = new Book();
        book.setId(id);
        return book;
    }

    @PutMapping("/book")
    public RespBean updateBook(@RequestBody Book book) {
        return RespBean.ok("更新成功");
    }

    @DeleteMapping("/book")
    public RespBean deleteBookById(Integer id) {
        return RespBean.ok("删除成功");
    }
}
