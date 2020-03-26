import java.sql.*;

class JdbcTest
{
	public static void main(String args[]) throws Exception
	{
		Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver");			                  								        //"oracle.jdbc.driver.OracleDriver"
		Connection conn = DriverManager.getConnection("jdbc:odbc:myDSN");
		Statement stat = conn.createStatement();

		stat.executeUpdate("create table greetings (SNAME CHAR(30))");
		stat.execute("insert into greetings values ('Hello World')");
		ResultSet set = stat.executeQuery("select * from greetings");

		while(set.next())
		System.out.println(set.getString(1));


		//stat.execute("drop table greetings");

		stat.close();
		conn.close();
	}
}


  //"jdbc:oracle:thin:@localhost:1521:xe","info","planet"








