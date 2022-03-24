package com.scaffail.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.scaffail.util.ServletUtil;
import com.scaffail.util.Validator;

@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static final String ADD_COMMENT_RESPONSE = "addCommentResponse";
    private static final Logger LOG = LogManager.getLogger(AddCommentServlet.class);

    public AddCommentServlet() {

        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String bookIdString = request.getParameter("bookId");
        String nickname = request.getParameter("nickname");
        String comment = request.getParameter("comment");

        LOG.debug("doPost({}, {}, {})", bookIdString, nickname, comment);

        if (!Validator.isValidInt(bookIdString)) {
            request.setAttribute(ADD_COMMENT_RESPONSE, "false");
        } else {
            try {
                int bookId = Integer.parseInt(bookIdString);
                if (ServletUtil.insertComment(bookId, nickname, comment)) {
                    request.setAttribute(ADD_COMMENT_RESPONSE, "true");
                } else {
                    request.setAttribute(ADD_COMMENT_RESPONSE, "false");
                }
            } catch (NumberFormatException e) {
                LOG.error(e.toString(), e);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("comments.jsp?bookId=" + bookIdString);
        dispatcher.forward(request, response);
    }

}
