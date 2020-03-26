import java.sql.*;
import java.util.*;

class JdbcTest2
{
	public static void main(String args[]) throws Exception
	{
		Scanner in = new Scanner(System.in);


	//Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	Connection conn = DriverManager.getConnection("jdbc:ucanaccess://c:\\xyz\\planet.accdb");

	Statement stat = conn.createStatement();
	System.out.println("Enter the emp_number ");
	int n = in.nextInt();
	System.out.println("Enter the name ");
	String name = in.next();


	String sql = "insert into emp values ("+n+", '"+name+"')";
	System.out.println(sql);
	stat.execute(sql);



	ResultSet set = stat.executeQuery("select * from emp");

	while(set.next())
		System.out.println(set.getInt(1)+"\t\t"+set.getString(2));

System.out.println("========================================================");

		stat.close();
		conn.close();
	}
}


  //"jdbc:oracle:thin:@localhost:1521:xe","info","planet"








