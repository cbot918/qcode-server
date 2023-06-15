package yale.code;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import yale.code.util.Util;

import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

	@RequestMapping("/")
	@CrossOrigin("*")
	public String hello(){
		System.out.println("request in");
		return "Hey, Spring Boot 的 Hello World ! ";
	}
	
	
	@RequestMapping("/db/ping")
	@CrossOrigin("*")
	public String db() {

		String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
		String host = "localhost:3306";
		String db = "qcode";
		String username = "root";
		String password = "12345";
		String connString = String.format("jdbc:mysql://%s/%s", host,db);

		// init jdbc_driver
		Util.initDb(DB_DRIVER);
		
		// init connection
		Connection conn = null;
		try { conn = DriverManager.getConnection(connString, username,password); } 
		catch (SQLException e) { e.printStackTrace(); }
		
		// create statement
		try {
			Statement stmt = conn.createStatement();

			String sql = "INSERT INTO users VALUES('testname')";

			stmt.executeUpdate(sql);
			
			System.out.println("row inserted");

		} catch (SQLException e) { e.printStackTrace(); }
		

		
		// connection close
		try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }

		return "db ping successfully";
	}
	
	@RequestMapping("/db/insert")
	@CrossOrigin("*")
	public ResponseEntity<Book> dbInsert() {

		String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
		String host = "localhost:3306";
		String db = "qcode";
		String username = "root";
		String password = "12345";
		String connString = String.format("jdbc:mysql://%s/%s", host,db);

		// init jdbc_driver
		Util.initDb(DB_DRIVER);
		
		// init connection
		Connection conn = null;
		try { conn = DriverManager.getConnection(connString, username,password); } 
		catch (SQLException e) { e.printStackTrace(); }
		
		// create statement
		// try {
		// 	Statement stmt = conn.createStatement();

		// 	String sql = "INSERT INTO users VALUES('testname')";

		// 	stmt.executeUpdate(sql);
			
		// 	System.out.println("row inserted");

		// } catch (SQLException e) { e.printStackTrace(); }
		

		Book book = new Book("test book title", 123, "chinese");


		
		// connection close
		try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }

		return ResponseEntity.ok().body(book);
	}

}