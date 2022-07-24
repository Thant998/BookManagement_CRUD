package bookmanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import bookmanagement.dto.BookRequestDTO;
import bookmanagement.dto.BookResponseDTO;


public class BookDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}
	
	public void insertData(BookRequestDTO dto) {
		String sql="INSERT INTO book(book_code,book_title,book_author,book_price)"+
						"values(?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,dto.getBookCode());
			ps.setString(2,dto.getBookTitle());
			ps.setString(3,dto.getBookAuthor());
			ps.setDouble(4,dto.getBookPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("database error");
		}
	}
	
	public void updateData(BookRequestDTO dto) {
		String sql="UPDATE book SET book_title=?,book_author=?,book_price=?"+
						"WHERE book_code=?";			
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,dto.getBookTitle());
			ps.setString(2,dto.getBookAuthor());
			ps.setDouble(3,dto.getBookPrice());
			ps.setString(4,dto.getBookCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("database error");
		}
	}
	
	public void deleteData(BookRequestDTO dto) {
		String sql="DELETE FROM book WHERE book_code=?";			
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,dto.getBookCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("database error");
		}
	}
	
	public BookResponseDTO selectOne(BookRequestDTO dto) {
		BookResponseDTO res=new BookResponseDTO();
		String sql="SELECT * FROM book WHERE book_code=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,dto.getBookCode());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res.setBookCode(rs.getString("book_code"));
				res.setBookTitle(rs.getString("book_title"));
				res.setBookAuthor(rs.getString("book_author"));
				res.setBookPrice(rs.getDouble("book_price"));
			}
		} catch (SQLException e) {
			System.out.println("database error");
		}
		return res;
	}
	
	public ArrayList<BookResponseDTO> selectAll(){
		ArrayList<BookResponseDTO> l=new ArrayList<>();
		String sql="SELECT * FROM book";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				BookResponseDTO res=new BookResponseDTO();
				res.setBookCode(rs.getString("book_code"));
				res.setBookTitle(rs.getString("book_title"));
				res.setBookAuthor(rs.getString("book_author"));
				res.setBookPrice(rs.getDouble("book_price"));
				l.add(res);
			}
		} catch (SQLException e) {
			System.out.println("database error");
		}
		return l;
	}
}
