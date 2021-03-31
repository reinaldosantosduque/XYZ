package MVC.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import MVC.beans.Cliente;
import MVC.beans.MensajeSistema;
import MVC.modelo.interfaces.ClienteDAO;

public class OracleClienteDAO implements ClienteDAO{

	@Override
	public MensajeSistema ingresarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		MensajeSistema msj = new MensajeSistema();
		String sql = "INSERT INTO CLIENTE(nombre,email,password,direccion,telefono,nit,regimen) VALUES(?,?,?,' ',' ',' ',0)";
		
		try {
			
			Connection conn = OracleDAOFactory.createConnection();
			PreparedStatement sentencia = conn.prepareStatement(sql);
			sentencia.setString(1, cliente.getNombre());
			sentencia.setString(2, cliente.getEmail());
			sentencia.setString(3, cliente.getPassword());
			
			if(sentencia.executeUpdate()>0)
			{
				msj.setTipo("Exito");
				msj.setMensaje("!Bienvenido a XYZ!\nTe enviaremos a nuestra página de inicio de sesión.");
			}
			else
			{
				msj.setTipo("Error");
				msj.setMensaje("Algo no ha ido bien, intenta de nuevo.");
			}
			conn.close();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return msj;
	}

	@Override
	public MensajeSistema eliminarCliente(String nit) {
		// TODO Auto-generated method stub
		MensajeSistema msj = new MensajeSistema();
		String sql = "DELETE FROM TARJETA WHERE CLIENTE=?";
		
		try {
			
			Connection conn = OracleDAOFactory.createConnection();
			conn.setAutoCommit(false);
			PreparedStatement sentencia = conn.prepareStatement(sql);
			sentencia.setString(1, nit);
			
			sentencia.executeUpdate();
				
			sql= "DELETE FROM CLIENTE WHERE EMAIL=?";
			sentencia = conn.prepareStatement(sql);
			sentencia.setString(1, nit);
					
					if(sentencia.executeUpdate()>0)
					{
					
						msj.setTipo("Exito");
						msj.setMensaje("Ya no perteneces a XYZ.");
					}
					else
					{
						msj.setTipo("Error");
						msj.setMensaje("Algo no ha ido bien, cliente.");
					}
			
			conn.commit();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return msj;
	}

	@Override
	public MensajeSistema actualizarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		MensajeSistema msj = new MensajeSistema();
		String sql = "UPDATE CLIENTE SET NOMBRE=?, NIT=?, REGIMEN=?, TELEFONO=?, DIRECCION=? WHERE EMAIL=? ";
		
		try {
			
			Connection conn = OracleDAOFactory.createConnection();
			PreparedStatement sentencia = conn.prepareStatement(sql);
			sentencia.setString(1, cliente.getNombre());
			sentencia.setString(2, cliente.getNit());
			sentencia.setInt(3, cliente.getRegimen());
			sentencia.setString(4, cliente.getTelefono());
			sentencia.setString(5, cliente.getDireccion());
			sentencia.setString(6, cliente.getEmail());
			
			
			if(sentencia.executeUpdate()>0)
			{
				msj.setTipo("Exito");
				msj.setMensaje("Registros actualizados.");
			}
			else
			{
				msj.setTipo("Error");
				msj.setMensaje("Algo no ha ido bien, intenta de nuevo.");
			}
		conn.commit();
		conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return msj;
	}

	@Override
	public Cliente consultarCliente(String nit) {
		// TODO Auto-generated method stub
		Cliente c = new Cliente();
		String sql = "SELECT * FROM cliente WHERE email=? ";
		
		try {
			
			Connection conn = OracleDAOFactory.createConnection();
			PreparedStatement sentencia = conn.prepareStatement(sql);
			sentencia.setString(1, nit);
			
			ResultSet r = sentencia.executeQuery();
			
			if(r.next())
			{
				c.setNombre(r.getString("NOMBRE"));
				c.setNit(r.getString("NIT"));
				c.setTelefono(r.getString("TELEFONO"));
				c.setDireccion(r.getString("DIRECCION"));
				c.setEmail(nit);
				c.setRegimen(r.getInt("REGIMEN"));
				c.setTarjetas(new OracleTarjetaDAO().consultarTarjetas(nit));
			}
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public boolean esCliente(String email, String password) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM cliente WHERE email=? and password=?";
		
		try {
			
			Connection conn = OracleDAOFactory.createConnection();
			PreparedStatement sentencia = conn.prepareStatement(sql);
			
			sentencia.setString(1, email);
			sentencia.setString(2, password);
			ResultSet r = sentencia.executeQuery();
			
			if(r.next())
				return true;
			else
				return false;
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		return false;
	}

}
