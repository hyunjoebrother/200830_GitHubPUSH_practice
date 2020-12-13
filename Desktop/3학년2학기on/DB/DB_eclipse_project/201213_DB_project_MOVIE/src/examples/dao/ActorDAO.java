package examples.dao;

// DAO (Data Access Object)

// DB 관련 작업을 전담하는 클래스
// DB에 연결하여 CRUD 작업을 하는 클래스

// getJob, addJob, deleteJob, updateJob, getJobs 클래스를 만들어주자 (5개 필수)

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


	public Actor getActor(Integer actorId) // 1개를 select -> actor_id = ? -> ?에 들어간다
	{
		Actor actor = null;
	
		// (1) JDBC 드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// (2) 질의문 설정
		String sql = "SELECT actor_id, actor_name, actor_bdate FROM actor WHERE actor_id = ?";
	
		// (3) MySQL 연결 및 질의 수행
		// try문에 conn, ps를 여는 코드를 넣으면 finally 블럭에서 close를 안해줘도 된다 (Closable 객체)
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql))
		{
			// (4) 질의문 설정 및 ResultSet 처리 (결과가 여러 개가 있을 수 있음)
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
		//밑에 finally 블럭을 쓰지 않아도 된다
	
		return actor;
	}



	public int addActor(Actor actor) // 2개를 insert -> values (?, ?) -> ? 2개에 insert
	{
		int insertCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("연결 성공!!~");
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
			
	public int deleteActor(Integer actorId) // actorId 1개를 delete
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

	public int updateActor(Actor actor) // 2개를 update 
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

	public List<Actor> getActors() //select한 모든 Actor들을 list로 만든다 -> list.add(actor)
	{
		//ArrayList 생성
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
					list.add(actor); //list에 반복할 때마다 Job 인스턴스를 생성하여 list에 추가한다
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