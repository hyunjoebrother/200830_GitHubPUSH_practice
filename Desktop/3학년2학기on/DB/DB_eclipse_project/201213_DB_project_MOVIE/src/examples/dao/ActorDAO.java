package examples.dao;

// DAO (Data Access Object)

// DB ���� �۾��� �����ϴ� Ŭ����
// DB�� �����Ͽ� CRUD �۾��� �ϴ� Ŭ����

// getJob, addJob, deleteJob, updateJob, getJobs Ŭ������ ��������� (5�� �ʼ�)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.Actor;

public class ActorDAO {
	private static String dburl = "jdbc:mysql://localhost/dbdesign?serverTimezone=Asia/Seoul";
	private static String dbUser = "meiproject";
	private static String dbpasswd = "dbuser123!";


	public Actor getActor(Integer actorId) // 1���� select -> actor_id = ? -> ?�� ����
	{
		Actor actor = null;
	
		// (1) JDBC ����̹� �ε�
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// (2) ���ǹ� ����
		String sql = "SELECT actor_id, actor_name, actor_bdate FROM actor WHERE actor_id = ?";
	
		// (3) MySQL ���� �� ���� ����
		// try���� conn, ps�� ���� �ڵ带 ������ finally ������ close�� �����൵ �ȴ� (Closable ��ü)
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql))
		{
			// (4) ���ǹ� ���� �� ResultSet ó�� (����� ���� ���� ���� �� ����)
			ps.setInt(1, actorId);
		
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next())
				{
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String bdate = rs.getString(3);
					actor = new Actor(id, name, bdate);
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
	
		return actor;
	}



	public int addActor(Actor actor) // 2���� insert -> values (?, ?) -> ? 2���� insert
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
		String sql = "INSERT INTO actor (actor_id, actor_name, actor_bdate) VALUES ( ?, ?, ? )";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setInt(1,  actor.getActor_id());
			ps.setNString(2,  actor.getActor_name());
			ps.setNString(3,  actor.getActor_bdate());
			
		
			insertCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return insertCount;
	}
			
	public int deleteActor(Integer actorId) // actorId 1���� delete
	{
		int deleteCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "DELETE FROM actor WHERE actor_id = ?";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setInt(1, actorId);
			deleteCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return deleteCount;
	}

	public int updateActor(Actor actor) // 2���� update 
	{
		int updateCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String sql = "update actor set actor_name = ? where job_id = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setNString(1,  actor.getActor_name());
			ps.setInt(2,  actor.getActor_id());
		
			updateCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return updateCount;
	}

	public List<Actor> getActors() //select�� ��� Actor���� list�� ����� -> list.add(actor)
	{
		//ArrayList ����
		List<Actor> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "SELECT actor_name, actor_id, actor_bdate FROM actor order by actor_id desc";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) 
		{
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					String name = rs.getString(1);
					int id = rs.getInt("actor_id");
					String bdate = rs.getString(3);
					
					Actor actor = new Actor(id, name, bdate);
					list.add(actor); //list�� �ݺ��� ������ Job �ν��Ͻ��� �����Ͽ� list�� �߰��Ѵ�
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