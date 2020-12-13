package examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.Director;


public class DirectorDAO {
	private static String dburl = "jdbc:mysql://localhost/dbdesign?serverTimezone=Asia/Seoul";
	private static String dbUser = "meiproject";
	private static String dbpasswd = "dbuser123!";


	public Director getDirector(Integer directorId) // 1���� select -> id = ? -> ?�� ����
	{
		Director director = null;
	
		// (1) JDBC ����̹� �ε�
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// (2) ���ǹ� ����
		String sql = "SELECT director_id, director_name, director_bdate FROM dierector WHERE director_id = ?";
	
		// (3) MySQL ���� �� ���� ����
		// try���� conn, ps�� ���� �ڵ带 ������ finally ������ close�� �����൵ �ȴ� (Closable ��ü)
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql))
		{
			// (4) ���ǹ� ���� �� ResultSet ó�� (����� ���� ���� ���� �� ����)
			ps.setInt(1, directorId);
		
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next())
				{
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String bdate = rs.getString(3);
					director = new Director(id, name, bdate);
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
	
		return director;
	}



	public int addDirector(Director director) // 2���� insert -> values (?, ?) -> ? 2���� insert
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
		String sql = "INSERT INTO director (director_id, director_name, director_bdate) VALUES ( ?, ?, ? )";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setInt(1,  director.getDirector_id());
			ps.setNString(2,  director.getDirector_name());
			ps.setNString(3,  director.getDirector_bdate());
			
		
			insertCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return insertCount;
	}
			
	public int deleteDirector(Integer directorId) // Id 1���� delete
	{
		int deleteCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "DELETE FROM director WHERE director_id = ?";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setInt(1, directorId);
			deleteCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return deleteCount;
	}

	public int updateDirector(Director actor) // 2���� update 
	{
		int updateCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String sql = "update director set director_name = ? where director_id = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setNString(1,  actor.getDirector_name());
			ps.setInt(2,  actor.getDirector_id());
		
			updateCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return updateCount;
	}

	public List<Director> getDirectors() //select�� ��� Director���� list�� ����� -> list.add(director)
	{
		//ArrayList ����
		List<Director> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "SELECT director_name, director_id, director_bdate FROM actor order by director_id desc";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) 
		{
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					String name = rs.getString(1);
					int id = rs.getInt("director_id");
					String bdate = rs.getString(3);
					
					Director director = new Director(id, name, bdate);
					list.add(director); //list�� �ݺ��� ������ �ν��Ͻ��� �����Ͽ� list�� �߰��Ѵ�
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
