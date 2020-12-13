package examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.Producer;

public class ProducerDAO {
	private static String dburl = "jdbc:mysql://localhost/dbdesign?serverTimezone=Asia/Seoul";
	private static String dbUser = "meiproject";
	private static String dbpasswd = "dbuser123!";


	public Producer getProducer(Integer producerId) // 1���� select -> id = ? -> ?�� ����
	{
		Producer producer = null;
	
		// (1) JDBC ����̹� �ε�
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// (2) ���ǹ� ����
		String sql = "SELECT producer_id, producer_name, producer_bdate FROM producer WHERE producer_id = ?";
	
		// (3) MySQL ���� �� ���� ����
		// try���� conn, ps�� ���� �ڵ带 ������ finally ������ close�� �����൵ �ȴ� (Closable ��ü)
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql))
		{
			// (4) ���ǹ� ���� �� ResultSet ó�� (����� ���� ���� ���� �� ����)
			ps.setInt(1, producerId);
		
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next())
				{
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String bdate = rs.getString(3);
					producer = new Producer(id, name, bdate);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		//�ؿ� finally ���� ���� �ʾƵ� �ȴ�
	
		return producer;
	}



	public int addProducer(Producer producer) // 2���� insert -> values (?, ?) -> ? 2���� insert
	{
		int insertCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("���� ����!!~");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String sql = "INSERT INTO producer (producer_id, producer_name, producer_bdate) VALUES ( ?, ?, ? )";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setInt(1,  producer.getProducer_id());
			ps.setNString(2,  producer.getProducer_name());
			ps.setNString(3,  producer.getProducer_bdate());
			
		
			insertCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return insertCount;
	}
			
	public int deleteProducer(Integer producerId) // Id 1���� delete
	{
		int deleteCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "DELETE FROM producer WHERE producer_id = ?";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setInt(1, producerId);
			deleteCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return deleteCount;
	}

	public int updateProducer(Producer producer) // 2���� update 
	{
		int updateCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String sql = "update producer set producer_name = ? where producer_id = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setNString(1,  producer.getProducer_name());
			ps.setInt(2,  producer.getProducer_id());
		
			updateCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return updateCount;
	}

	public List<Producer> getProducers() //select�� ��� Producer���� list�� ����� -> list.add(actor)
	{
		//ArrayList ����
		List<Producer> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "SELECT producer_name, producer_id, producer_bdate FROM producer order by producer_id desc";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) 
		{
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					String name = rs.getString(1);
					int id = rs.getInt("producer_id");
					String bdate = rs.getString(3);
					
					Producer producer = new Producer(id, name, bdate);
					list.add(producer); //list�� �ݺ��� ������ �ν��Ͻ��� �����Ͽ� list�� �߰��Ѵ�
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return list;
	}

}
