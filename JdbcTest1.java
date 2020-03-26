import java.sql.*;

class JdbcTest1
{
	public static void main(String args[]) throws Exception
	{
	//Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	Connection conn = DriverManager
		.getConnection("jdbc:ucanaccess://c:\\xyz\\planet.accdb");

	Statement stat = conn.createStatement();

	stat.executeUpdate("create table greetings (SNAME CHAR(30))");
	stat.execute("insert into greetings values ('Hello World')");
	ResultSet set = stat.executeQuery("select * from greetings");

	while(set.next())
		System.out.println(set.getString(1));

		stat.close();
		conn.close();
	}
}


  //"jdbc:oracle:thin:@localhost:1521:xe","info","planet"








