package yale.code;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import yale.code.util.Util;

import java.sql.* ;  // for standard JDBC programs

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@CrossOrigin("*")
	public String hello(){
		System.out.println("request in");
		return "Hey, Spring Boot 的 Hello World ! ";
	}
	
	@RequestMapping(value = "/db/ping", method = RequestMethod.GET)
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


	@RequestMapping(value = "/echo_request", method = RequestMethod.POST)
	@CrossOrigin("*")
	public ResponseEntity<Book> echoRequest(@RequestBody Book book) {

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


		// // create statement
		// String sql = String.format(
		// 	"INSERT INTO book(title, isbn, language) VALUES('%s','%s','%s')",
		// 	book.getTitle(),book.getIsbn(),book.getLanguage()
		// );

		// try {
		// 	Statement stmt = conn.createStatement();

		// 	stmt.executeUpdate(sql);
			
		// 	System.out.println("row inserted");

		// } catch (SQLException e) { e.printStackTrace(); }

		Book retBook = new Book(book.getTitle(), book.getIsbn(), book.getLanguage());
		
		// connection close
		try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }

		return ResponseEntity.ok().body(retBook);
	}


	@RequestMapping(value = "/book/create", method = RequestMethod.POST)
	@CrossOrigin("*")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {

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
		String sql = String.format(
			"INSERT INTO book(title, isbn, language) VALUES('%s','%s','%s')",
			book.getTitle(),book.getIsbn(),book.getLanguage()
		);

		try {
			Statement stmt = conn.createStatement();

			stmt.executeUpdate(sql);
			
			System.out.println("row inserted");

		} catch (SQLException e) { e.printStackTrace(); }




		Book retBook = new Book(book.getTitle(), book.getIsbn(), book.getLanguage());
		
		// connection close
		try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }

		return ResponseEntity.ok().body(retBook);
	}

}