package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.dao.MovieDAO;
import examples.dto.Movie;

import java.util.List;

/**
 * Servlet implementation class MovieTest
 */
@WebServlet("/MovieTest")
public class MovieTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieTest() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DAO add doGet() ȣ��!");
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		
		// Movie table�� ����ϴ� DAO�� �ϳ� ���� ����� �ش� -> DAO�� ���ؼ� data�� �����´�
		MovieDAO dao = new MovieDAO();
		
		// (1) get �׽�Ʈ
		Movie movie = null;
		movie = dao.getMovie(4); //B.L
		
		if (movie == null)
			out.println("<h1> This is Movie page </h1>");
		else {
			out.println("<h1> Movie Title is " + movie.getTitle() + "</h1>"); 
			out.println("<h1> Movie number is " + movie.getMovie_id() + "</h1>");
			out.println("<h1> Movie genre is " + movie.getGenre() + "</h1>");
			out.println("<h1> Movie year is " + movie.getYear() + "</h1>");
			}
		
		
		// (2) add �׽�Ʈ
		Movie movie1 = new Movie(6661, "avatar", "fantasy", 2014);
		
		int addCount = dao.addMovie(movie1);
		out.println("<h1> insert : " + addCount + "row(s) </h1>");
		
		
		// (3) delete �׽�Ʈ
		int delCount = dao.deleteMovie(1);
		out.println("<h1> delete : " + delCount + "row(s) </h1>");
		
		
		// (4) update �׽�Ʈ
		Movie movie2 = new Movie(6662, "interstella", "sci-fi", 2017);
				
		int updateCount = dao.updateMovie(movie2);
		out.println("<h1> update : " + updateCount + "row(s) </h1>");
		
		
		// (5) get Lists �׽�Ʈ
		List<Movie> movieList = dao.getMovies();
		
		for(Movie movie3 : movieList)
		{
			out.println("<h1> Movie title is " + movie3.getTitle() + "</h1>"); 
			out.println("<h1> Movie number is " + movie3.getMovie_id() + "</h1>");
			out.println("<h1> Movie genre is " + movie3.getGenre() + "</h1>");
			out.println("<h1> Movie year is " + movie3.getYear() + "</h1>");
		}
		out.close();
	}

}
