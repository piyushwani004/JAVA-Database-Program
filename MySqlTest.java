import java.sql.*;

class MySqlTest
{
	public static void main(String args[]) throws Exception
	{
	try{

	Class.forName("com.mysql.jdbc.Driver");

	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql",
	                                  "root","infoplanet");

	//here sonoo is database name, root is username and password

	System.out.println("Connected");

	Statement stmt=con.createStatement();
	stmt.execute("use info");

	ResultSet rs=stmt.executeQuery("select * from emp");

	while(rs.next())
	System.out.println(rs.getInt(1)+"  "+rs.getString(2));

	con.close();

	}catch(Exception e){ System.out.println(e);}


  }

}


  //"jdbc:oracle:thin:@localhost:1521:xe","info","planet"








