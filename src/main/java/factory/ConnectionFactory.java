package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	private String password = System.getenv("MySQL_Password");
	private DataSource dataSource;
	
	public ConnectionFactory(){
		var pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/demo_tienda1?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword(password);
		pooledDataSource.setMaxPoolSize(15);
		
		this.dataSource = pooledDataSource;
	}
	
	
	public Connection recuperarConexion(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
