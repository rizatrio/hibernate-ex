package com.geekbrains.hibernate;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @OneToOne(mappedBy = "author_id")
//    private Book book;
//
//    public Book getBook() {
//        return book;
//    }
//
//    public void setBook(Book book) {
//        this.book = book;
//    }


    //    @Basic(fetch = FetchType.LAZY)
//    @OneToMany(mappedBy = "author_id")
////    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
//    private List<Book> books;

//    @OneToMany(mappedBy = "author_id")
//    private List<Book> books;
//
//
//    public List<Book> getBooks() {
//        return books;
//    }
//
//    public void setBooks(List<Book> books) {
//        this.books = books;
//    }


//    public void setBooks(List<Book> books) {
//        this.books = books;
//    }
//
//    public List<Book> getBooks() {
//        return books;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public Author() {
//    }
//
//    @Override
//    public String toString() {
//        String allBooks = "";
//        for (Book o : books) {
//            allBooks += o.getTitle() + " ";
//        }
//        return "Author [" + id + " " + name + " " + allBooks + "]";
//       // return "Author [" + id + " " + name + "]";
//    }

//        @Override
//    public String toString() {
//        return "Author [" + id + " " + name + " " + book.toString() + "]";
//    }

    @Override
    public String toString() {
        return "Author [" + id + " " + name + "]";
    }
}