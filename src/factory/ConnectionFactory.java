package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection(){
		try{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			return DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.200:1433/cadastro", "sa", "B0qbxRw9TL3xYTrHXOULROnH1cMu9JRx");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
