package com.scaffail.util;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.scaffail.model.Book;
import com.scaffail.model.Comment;

public class ServletUtil {

    private static final Logger LOG = LogManager.getLogger(ServletUtil.class);

    public static Book getBook(int id) {

        LOG.debug("getBook({})", id);

        return DbUtil.getBook(id);
    }

    public static List<Book> getBooks() {

        LOG.debug("getBooks()");

        return DbUtil.getBooks();
    }

    public static List<Comment> getCommentsForBook(int bookId) {

        LOG.debug("getCommentsForBook({})", bookId);

        return DbUtil.getCommentsForBook(bookId);
    }

    public static boolean insertComment(int bookId, String nickname, String comment) {

        LOG.debug("insertComment({}, {}, {})", bookId, nickname, comment);

        return DbUtil.insertComment(bookId, nickname, comment);
    }

    public static List<Book> searchBooks(String searched) {

        LOG.debug("searchBooks({})", searched);

        return DbUtil.searchBooks(searched);
    }

    private ServletUtil() {

    }

}
