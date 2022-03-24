<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="header.jspf"/>

<h1>Books</h1>

<form action="books.jsp" method="GET">
	<input type="text" id="searched" name="searched" />&nbsp;
	<input type="submit" value="Search" />
</form>

<br /><hr><br />

<%@page import="java.util.List"%>
<%@page import="com.scaffail.model.Book"%>
<%@page import="com.scaffail.util.ServletUtil"%>
<%@page import="com.scaffail.util.Validator"%>

<%
	List<Book> books = null;
	String searched = request.getParameter("searched");
	if(Validator.isValidString(searched)) {
	    books = ServletUtil.searchBooks(searched);
	} else {
	    books = ServletUtil.getBooks();
	}
	
	if(books == null || books.isEmpty()) {
%>

<p class="lead">No books found.</p>

<%	
	} else {
%>    

<table>
	<caption>Available books.</caption>
	<tr><th id="id">ID</th><th id="title">Title</th><th id="author">Author</th><th id="description">Description</th></tr>
<%
		for(Book book: books) {
%>
	<tr><td><a href="comments.jsp?bookId=<%= book.getId() %>"><%= book.getId() %></a></td><td><%= book.getTitle() %></td><td><%= book.getAuthor() %></td><td><%= book.getDescription() %></td></tr>
<%	    
		}
%>

</table>

<%
	}
%>

<jsp:include page="footer.jspf"/>