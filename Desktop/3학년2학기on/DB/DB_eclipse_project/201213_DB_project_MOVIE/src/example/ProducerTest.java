package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.dao.ProducerDAO;
import examples.dto.Producer;

import java.util.List;

/**
 * Servlet implementation class ProducerTest
 */
@WebServlet("/ProducerTest")
public class ProducerTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProducerTest() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DAO add doGet() ȣ��!");
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		
		// Producer table�� ����ϴ� DAO�� �ϳ� ���� ����� �ش� -> DAO�� ���ؼ� data�� �����´�
		ProducerDAO dao = new ProducerDAO();
		
		// (1) get �׽�Ʈ
		Producer producer = null;
		producer = dao.getProducer(4); //B.L
		
		if (producer == null)
			out.println("<h1> This is Producer page </h1>");
		else {
			out.println("<h1> Producer Name is " + producer.getProducer_name() + "</h1>"); 
			out.println("<h1> Producer number is " + producer.getProducer_id() + "</h1>");
			out.println("<h1> Producer birth is " + producer.getProducer_bdate() + "</h1>");
			}
		
		
		// (2) add �׽�Ʈ
		Producer producer1 = new Producer(5555, "mingku", "1997-02-17");
		
		int addCount = dao.addProducer(producer1);
		out.println("<h1> insert : " + addCount + "row(s) </h1>");
		
		
		// (3) delete �׽�Ʈ
		int delCount = dao.deleteProducer(1);
		out.println("<h1> delete : " + delCount + "row(s) </h1>");
		
		
		// (4) update �׽�Ʈ
		Producer producer2 = new Producer(5556, "dkdk", "1997-01-11");
				
		int updateCount = dao.updateProducer(producer2);
		out.println("<h1> update : " + updateCount + "row(s) </h1>");
		
		
		// (5) get Lists �׽�Ʈ
		List<Producer> producerList = dao.getProducers();
		
		for(Producer producer3 : producerList)
		{
			out.println("<h1> Producer Name is " + producer3.getProducer_name() + "</h1>"); 
			out.println("<h1> Producer number is " + producer3.getProducer_id() + "</h1>");
			out.println("<h1> Producer birth is " + producer3.getProducer_bdate() + "</h1>");
		}
		out.close();
	}

}
