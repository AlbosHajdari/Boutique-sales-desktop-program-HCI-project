
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class connectionClass {
	Connection conn=null;
	public static Connection connectDb()
	{
		try 
		{
			Class.forName("org.h2.Driver");
Connection conn=
		DriverManager.getConnection("jdbc:h2:~/test","root","root");
			return conn;
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate lidhjes me DB. "+e.getMessage());
			return null;
		}
	}

}
