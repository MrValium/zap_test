package com.scaffail.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.scaffail.model.Book;
import com.scaffail.model.Comment;

public class DbUtil {

    private static final Logger LOG = LogManager.getLogger(DbUtil.class);

    private static final String BOOK_ID = "book_id";
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String DESCRIPTION = "description";
    private static final String COMMENT_ID = "comment_id";
    private static final String BOOK_FK = "book_fk";
    private static final String NICKNAME = "nickname";
    private static final String COMMENT_TEXT = "comment_text";

    private static DbUtil instance = null;

    public static Book getBook(int id) {

        LOG.debug("getBook({})", id);

        String query = "SELECT * FROM book WHERE book_id = ?";
        Book book = null;

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int bookId = resultSet.getInt(BOOK_ID);
                    String title = resultSet.getString(TITLE);
                    String author = resultSet.getString(AUTHOR);
                    String description = resultSet.getString(DESCRIPTION);
                    book = new Book(bookId, title, author, description);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            LOG.error(e.toString(), e);
        }

        return book;

    }

    public static List<Book> getBooks() {

        LOG.debug("getBooks()");

        String query = "SELECT * FROM book ORDER BY title ASC, author ASC";
        List<Book> books = new ArrayList<>();

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(BOOK_ID);
                    String title = resultSet.getString(TITLE);
                    String author = resultSet.getString(AUTHOR);
                    String description = resultSet.getString(DESCRIPTION);
                    Book book = new Book(id, title, author, description);
                    books.add(book);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            LOG.error(e.toString(), e);
        }

        return books;
    }

    public static List<Comment> getCommentsForBook(int bookId) {

        LOG.debug("getCommentsForBook({})", bookId);

        String query = "SELECT * FROM comment WHERE book_fk = ? ORDER BY comment_id ASC";
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bookId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(COMMENT_ID);
                    int bookFk = resultSet.getInt(BOOK_FK);
                    String nickname = resultSet.getString(NICKNAME);
                    String text = resultSet.getString(COMMENT_TEXT);
                    Comment comment = new Comment(id, bookFk, nickname, text);
                    comments.add(comment);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            LOG.error(e.toString(), e);
        }

        return comments;
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {

        LOG.debug("getConnection()");

        if (instance == null) {
            LOG.info("New DB instance.");
            instance = new DbUtil();
        }

        if (instance.connection == null || instance.connection.isClosed()) {
            LOG.info("Connection not valid, creating another.");
            Class.forName("com.mysql.jdbc.Driver");
            instance.connection = DriverManager.getConnection(instance.url, instance.username, instance.password);
        }

        return instance.connection;
    }

    public static boolean insertComment(int bookId, String nickname, String comment) {

        LOG.debug("insertComment({}, {}, {})", bookId, nickname, comment);

        boolean result = true;

        if (bookId < 1 || !Validator.isValidString(nickname) || !Validator.isValidString(comment) ||
            nickname.length() > 100 || comment.length() > 500) {
            result = false;
        } else {
            String query = "INSERT INTO comment (book_fk, nickname, comment_text) VALUES (?, ?, ?)";

            try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, bookId);
                preparedStatement.setString(2, nickname.trim());
                preparedStatement.setString(3, comment.trim());
                preparedStatement.executeUpdate();

            } catch (SQLException | ClassNotFoundException e) {
                LOG.error(e.toString(), e);
                result = false;
            }

        }

        return result;
    }

    public static List<Book> searchBooks(String searched) {

        LOG.debug("searchBooks({})", searched);

        String query = "SELECT * FROM book WHERE title LIKE '%" + searched +
            "%' OR author LIKE '%" + searched + "%' OR description LIKE '%" + searched +
            "%' ORDER BY title ASC, author ASC";
        List<Book> books = new ArrayList<>();

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(BOOK_ID);
                    String title = resultSet.getString(TITLE);
                    String author = resultSet.getString(AUTHOR);
                    String description = resultSet.getString(DESCRIPTION);
                    Book book = new Book(id, title, author, description);
                    books.add(book);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            LOG.error(e.toString(), e);
        }

        return books;
    }

    private Connection connection;
    private String url;
    private String username;
    private String password;

    private DbUtil() {

        try {
            LOG.debug("Reading environment variables.");
            this.connection = null;
            this.url = System.getenv("JDBC_URL");
            this.username = System.getenv("JDBC_USER");
            this.password = System.getenv("JDBC_PASS");
        } catch (Exception e) {
            LOG.fatal(e.toString(), e);
        }

    }

}
