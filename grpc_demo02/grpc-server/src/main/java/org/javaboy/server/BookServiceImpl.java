package org.javaboy.server;

import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import org.javaboy.grpc.demo.Book;
import org.javaboy.grpc.demo.BookOrBuilder;
import org.javaboy.grpc.demo.BookServiceGrpc;
import org.javaboy.grpc.demo.BookSet;

import java.util.*;

public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase {
    private Map<String, Book> bookMap = new HashMap<>();
    private List<Book> books = new ArrayList<>();

    public BookServiceImpl() {
        Book b1 = Book.newBuilder().setId("1").setName("三国演义").setAuthor("罗贯中").setPrice(30).addTags("明清小说").addTags("通俗小说").build();
        Book b2 = Book.newBuilder().setId("2").setName("西游记").setAuthor("吴承恩").setPrice(40).addTags("志怪小说").addTags("通俗小说").build();
        Book b3 = Book.newBuilder().setId("3").setName("水浒传").setAuthor("施耐庵").setPrice(50).addTags("明清小说").addTags("通俗小说").build();
        bookMap.put("1", b1);
        bookMap.put("2", b2);
        bookMap.put("3", b3);
    }

    @Override
    public void addBook(Book request, StreamObserver<StringValue> responseObserver) {
        bookMap.put(request.getId(), request);
        responseObserver.onNext(StringValue.newBuilder().setValue(request.getId()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getBook(StringValue request, StreamObserver<Book> responseObserver) {
        String id = request.getValue();
        Book book = bookMap.get(id);
        if (book != null) {
            responseObserver.onNext(book);
            responseObserver.onCompleted();
        } else {
            responseObserver.onCompleted();
        }
    }

    @Override
    public void searchBooks(StringValue request, StreamObserver<Book> responseObserver) {
        Set<String> keySet = bookMap.keySet();
        String tags = request.getValue();
        for (String key : keySet) {
            Book book = bookMap.get(key);
            int tagsCount = book.getTagsCount();
            for (int i = 0; i < tagsCount; i++) {
                String t = book.getTags(i);
                if (t.equals(tags)) {
                    responseObserver.onNext(book);
                    break;
                }
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Book> updateBooks(StreamObserver<StringValue> responseObserver) {
        StringBuilder sb = new StringBuilder("更新的图书 ID 为：");
        return new StreamObserver<Book>() {
            @Override
            public void onNext(Book book) {
                bookMap.put(book.getId(), book);
                sb.append(book.getId())
                        .append(",");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(StringValue.newBuilder().setValue(sb.toString()).build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StringValue> processBooks(StreamObserver<BookSet> responseObserver) {
        return new StreamObserver<StringValue>() {
            @Override
            public void onNext(StringValue stringValue) {
                Book b = Book.newBuilder().setId(stringValue.getValue()).build();
                books.add(b);
                if (books.size() == 3) {
                    BookSet bookSet = BookSet.newBuilder().addAllBookList(books).build();
                    responseObserver.onNext(bookSet);
                    books.clear();
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                BookSet bookSet = BookSet.newBuilder().addAllBookList(books).build();
                responseObserver.onNext(bookSet);
                books.clear();
                responseObserver.onCompleted();
            }
        };
    }
}
