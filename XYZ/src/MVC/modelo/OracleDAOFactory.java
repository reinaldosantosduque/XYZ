package MVC.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import MVC.modelo.interfaces.ClienteDAO;
import MVC.modelo.interfaces.TarjetaDAO;


public class OracleDAOFactory extends DAOFactory{
	
	public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:";
    public static final String DBNAME = "xyz";
    public static final String USER = "SYSTEM";
    public static final String PASSWORD = "Adm1n$ant0$";

    //metodo para crear la conexion a MySql 
    
	public static Connection createConnection(){
        
        Connection conexion = null;
        
        try {
            
            Class.forName(DRIVER);
         
            conexion = DriverManager.getConnection(DBURL + DBNAME, USER, PASSWORD);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OracleDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex) {
          Logger.getLogger(OracleDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conexion;

    }
	
	
	public static void main(String[] args) {
		
		Connection conn = createConnection();
		
		if(conn!=null)
		{
			System.out.println("Conectado...");
		}else
		{
			System.out.println("Revisa el log...");
		}
		
	}


	@Override
	public ClienteDAO getClienteDAO() {
		// TODO Auto-generated method stub
		return new OracleClienteDAO();
	}


	@Override
	public TarjetaDAO gettarjetaDAO() {
		// TODO Auto-generated method stub
		return new OracleTarjetaDAO();
	}

}
