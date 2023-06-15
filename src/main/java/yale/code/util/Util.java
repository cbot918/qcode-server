package yale.code.util;

public class Util {
  public Util(){}

  public static void initDb(String driver){
    try {
			Class.forName(driver);
		}
		catch(ClassNotFoundException ex) {
				System.out.println("Error: unable to load driver class!");
				System.exit(1);
		}
  }
}
