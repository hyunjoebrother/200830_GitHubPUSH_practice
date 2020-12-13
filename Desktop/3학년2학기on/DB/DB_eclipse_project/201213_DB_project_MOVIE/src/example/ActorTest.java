package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.dao.ActorDAO;
import examples.dto.Actor;

import java.util.List;

/**
 * Servlet implementation class ActorTest
 */
@WebServlet("/ActorTest")
public class ActorTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActorTest() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DAO add doGet() ȣ��!");
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		
		// Actor table�� ����ϴ� DAO�� �ϳ� ���� ����� �ش� -> DAO�� ���ؼ� data�� �����´�
		ActorDAO dao = new ActorDAO();
		
		// (1) get �׽�Ʈ
		Actor actor = null;
		actor = dao.getActor(4); //B.L
		
		if (actor == null)
			out.println("<h1> This is Actor page </h1>");
		else {
			out.println("<h1> Actor Name is " + actor.getActor_name() + "</h1>"); 
			out.println("<h1> Actor number is " + actor.getActor_id() + "</h1>");
			out.println("<h1> Actor birth is " + actor.getActor_bdate() + "</h1>");
			}
		
		
		// (2) add �׽�Ʈ
		Actor actor1 = new Actor(2222, "joejoe", "1998-08-31");
		
		int addCount = dao.addActor(actor1);
		out.println("<h1> insert : " + addCount + "row(s) </h1>");
		
		
		// (3) delete �׽�Ʈ
		int delCount = dao.deleteActor(1);
		out.println("<h1> delete : " + delCount + "row(s) </h1>");
		
		
		// (4) update �׽�Ʈ
		Actor actor2 = new Actor(3333, "meimei", "2004-08-31");
				
		int updateCount = dao.updateActor(actor2);
		out.println("<h1> update : " + updateCount + "row(s) </h1>");
		
		
		// (5) get Lists �׽�Ʈ
		List<Actor> actorList = dao.getActors();
		
		for(Actor actor3 : actorList)
		{
			out.println("<h1> Actor Name is " + actor3.getActor_name() + "</h1>"); 
			out.println("<h1> Actor number is " + actor3.getActor_id() + "</h1>");
			out.println("<h1> Actor birth is " + actor3.getActor_bdate() + "</h1>");
		}
		out.close();
	}
}
