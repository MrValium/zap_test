<%@page import="com.scaffail.servlet.AddCommentServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jspf"/>

<h1>Comments</h1>

<%@page import="java.lang.Integer"%>
<%@page import="java.util.List"%>
<%@page import="com.scaffail.model.Book"%>
<%@page import="com.scaffail.model.Comment"%>
<%@page import="com.scaffail.util.ServletUtil"%>
<%@page import="com.scaffail.util.Validator"%>

<%
	String addCommentResponse = (String) request.getAttribute(AddCommentServlet.ADD_COMMENT_RESPONSE);
	if(addCommentResponse != null && "false".equals(addCommentResponse)) {
%>
<p class="lead"><strong>ERROR:</strong> Wrong parameters for inserted comment!</p>
<%
	}

	String bookIdString = request.getParameter("bookId");
	if(!Validator.isValidInt(bookIdString)) {
%>
<p class="lead">No book found.</p>
<%
	} else {
	    int bookId = Integer.parseInt(bookIdString);
		Book book = ServletUtil.getBook(bookId);
		
		if(book != null) {
%>    
<table>
	<caption>Available book.</caption>
	<tr><th id="id">ID</th><th id="title">Title</th><th id="author">Author</th><th id="description">Description</th></tr>
	<tr><td><a href="comments.jsp?bookId=<%= book.getId() %>"><%= book.getId() %></a></td><td><%= book.getTitle() %></td><td><%= book.getAuthor() %></td><td><%= book.getDescription() %></td></tr>
</table>

<br /><hr><br />

<%
			List<Comment> comments = ServletUtil.getCommentsForBook(book.getId());
			if(comments == null || comments.isEmpty()) {
%>
<p class="lead">No comments found.</p>
<%
			} else {
%>    
<table>
	<caption>Comments for the book.</caption>
	<tr><th id="id">ID</th><th id="nickname">Nickname</th><th id="comment">Comment</th></tr>
<%
				for(Comment comment: comments) {
%>
	<tr><td><%= comment.getId() %></td><td><%= comment.getNickname() %></td><td><%= comment.getText() %></td></tr>
<% 
				}
%>
</table>
<%
			}
%>

<br /><hr><br />

<form action="addComment" method="POST">
	<label for="nickname">Nickname:</label><br />
	<input type="text" id="nickname" name="nickname" /><br />
	<label for="comment">Comment:</label><br />
	<textarea id="comment" name="comment" rows="10" cols="100">
	</textarea><br />
	<input type="hidden" id="bookId" name="bookId" value="<%= book.getId() %>" />
	<input type="submit" value="Add Comment" />
</form>

<%
		} else {
%>
<p class="lead">No book found.</p>
<%
		}
	}
%>

<jsp:include page="footer.jspf"/>