package examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.Movie;

public class MovieDAO {
	private static String dburl = "jdbc:mysql://localhost/dbdesign?serverTimezone=Asia/Seoul";
	private static String dbUser = "meiproject";
	private static String dbpasswd = "dbuser123!";


	public Movie getMovie(Integer movieId) // 1개를 select ->  movie_id = ? -> ?에 들어간다
	{
		Movie movie = null;
	
		// (1) JDBC 드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// (2) 질의문 설정
		String sql = "SELECT movie_id, title, genre, year FROM movie WHERE movie_id = ?";
	
		// (3) MySQL 연결 및 질의 수행
		// try문에 conn, ps를 여는 코드를 넣으면 finally 블럭에서 close를 안해줘도 된다 (Closable 객체)
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql))
		{
			// (4) 질의문 설정 및 ResultSet 처리 (결과가 여러 개가 있을 수 있음)
			ps.setInt(1, movieId);
		
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next())
				{
					int id = rs.getInt(1);
					String title = rs.getString(2);
					String genre = rs.getString(3);
					int year = rs.getInt(4);
					movie = new Movie(id, title, genre, year);
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
	
		return movie;
	}



	public int addMovie(Movie movie) // 2개를 insert -> values (?, ?) -> ? 2개에 insert
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
		String sql = "INSERT INTO movie (movie_id, title, genre, year) VALUES ( ?, ?, ?, ?)";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setInt(1,  movie.getMovie_id());
			ps.setNString(2,  movie.getTitle());
			ps.setNString(3,  movie.getGenre());
			ps.setInt(4,  movie.getYear());
			
		
			insertCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return insertCount;
	}
			
	public int deleteMovie(Integer movieId) // movieId 1개를 delete
	{
		int deleteCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "DELETE FROM movie WHERE movie_id = ?";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setInt(1, movieId);
			deleteCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return deleteCount;
	}

	public int updateMovie(Movie movie) // 2개를 update 
	{
		int updateCount = 0;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String sql = "update movie set title = ? where movie_id = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql))
		{
			ps.setNString(1,  movie.getTitle());
			ps.setInt(2,  movie.getMovie_id());
			ps.setNString(3,  movie.getGenre());
			ps.setInt(4,  movie.getYear());
		
			updateCount = ps.executeUpdate();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	
		return updateCount;
	}

	public List<Movie> getMovies() //select한 모든 Movies들을 list로 만든다 -> list.add(movie)
	{
		//ArrayList 생성
		List<Movie> list = new ArrayList<>();
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
		String sql = "SELECT title, movie_id, genre, year FROM movie order by movie_id desc";
	
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) 
		{
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					String title = rs.getString(1);
					int id = rs.getInt("movie_id");
					String genre = rs.getString(3);
					int year = rs.getInt("year");
					
					Movie movie = new Movie(id, title, genre, year);
					list.add(movie); //list에 반복할 때마다 인스턴스를 생성하여 list에 추가한다
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
