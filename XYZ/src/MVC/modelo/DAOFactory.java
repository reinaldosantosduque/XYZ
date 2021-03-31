package MVC.modelo;

import MVC.modelo.interfaces.ClienteDAO;
import MVC.modelo.interfaces.TarjetaDAO;

public abstract class DAOFactory {

	 public static final int MYSQL = 1;
	    public static final int ORACLE = 2;
	    public static final int POSTGRESQL = 3;
	    public static final int ACCESS = 4;
	    public static final int SOCKETS = 5;
	    public static final int CAD = 6;
	    
	    public static DAOFactory getDAOFactory(int whichFactory){

	        switch(whichFactory){
	            case ORACLE:
	                return new OracleDAOFactory();
	            default:
	                return null;
	        }

	    }
	    
	    public abstract ClienteDAO getClienteDAO();
	    public abstract TarjetaDAO gettarjetaDAO();
}
