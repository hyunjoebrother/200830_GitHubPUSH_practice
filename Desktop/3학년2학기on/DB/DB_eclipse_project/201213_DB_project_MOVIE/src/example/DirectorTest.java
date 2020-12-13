package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.dao.DirectorDAO;
import examples.dto.Director;

import java.util.List;

/**
 * Servlet implementation class DirectorTest
 */
@WebServlet("/DirectorTest")
public class DirectorTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirectorTest() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DAO add doGet() 호출!");
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		
		// Director table을 담당하는 DAO를 하나 새로 만들어 준다 -> DAO를 통해서 data를 가져온다
		DirectorDAO dao = new DirectorDAO();
		
		// (1) get 테스트
		Director director = null;
		director = dao.getDirector(4); //B.L
		
		if (director == null)
			out.println("<h1> This is Director page </h1>");
		else {
			out.println("<h1> Director Name is " + director.getDirector_name() + "</h1>"); 
			out.println("<h1> Director number is " + director.getDirector_id() + "</h1>");
			out.println("<h1> Director birth is " + director.getDirector_bdate() + "</h1>");
			}
		
		
		// (2) add 테스트
		Director director1 = new Director(4444, "bongbong", "1958-07-21");
		
		int addCount = dao.addDirector(director1);
		out.println("<h1> insert : " + addCount + "row(s) </h1>");
		
		
		// (3) delete 테스트
		int delCount = dao.deleteDirector(1);
		out.println("<h1> delete : " + delCount + "row(s) </h1>");
		
		
		// (4) update 테스트
		Director director2 = new Director(4445, "bongmei", "1977-02-11");
				
		int updateCount = dao.updateDirector(director2);
		out.println("<h1> update : " + updateCount + "row(s) </h1>");
		
		
		// (5) get Lists 테스트
		List<Director> directorList = dao.getDirectors();
		
		for(Director director3 : directorList)
		{
			out.println("<h1> Director Name is " + director3.getDirector_name() + "</h1>"); 
			out.println("<h1> Director number is " + director3.getDirector_id() + "</h1>");
			out.println("<h1> Director birth is " + director3.getDirector_bdate() + "</h1>");
		}
		out.close();
	}
}
