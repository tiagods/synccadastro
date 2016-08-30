package teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factory.ConnectionFactory;

public class TesteConexao {
	public static void main(String[] args) throws SQLException{
		System.out.println("Testanto conexao");
		Connection con = new ConnectionFactory().getConnection();
		if(con!=null){
			System.out.println("bem sucedido");
			System.out.println("lendo registros\n");
			PreparedStatement ps = con.prepareStatement("select * from config");
			ResultSet rs = ps.executeQuery();
			if(rs!=null){
				while(rs.next()){
					System.out.println(rs.getInt(1));
					System.out.println(rs.getString(2));
					System.out.println(rs.getString(3));
					System.out.println(rs.getString(4));
					System.out.println(rs.getString(5));
					System.out.println(rs.getString(6));
					System.out.println(rs.getString(7));
				}
			}
			con.close();
		}
	}
}
