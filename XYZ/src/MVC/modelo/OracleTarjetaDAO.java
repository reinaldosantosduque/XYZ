package MVC.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import MVC.beans.Cliente;
import MVC.beans.MensajeSistema;
import MVC.beans.Tarjeta;
import MVC.modelo.interfaces.TarjetaDAO;

public class OracleTarjetaDAO implements TarjetaDAO{

	@Override
	public MensajeSistema crearTarjeta(Tarjeta t, Cliente c) {
		// TODO Auto-generated method stub
		MensajeSistema msj = new MensajeSistema();
		String sql = "INSERT INTO TARJETA(numero,expira,codigo,marca,cliente,defecto,titular) VALUES(?,?,?,?,?,0,?)";
		
		try {
			
			Connection conn = OracleDAOFactory.createConnection();
			PreparedStatement sentencia = conn.prepareStatement(sql);
			sentencia.setString(1, t.getNumero());
			sentencia.setString(2, t.getExpira());
			sentencia.setInt(3, t.getCodigo());
			sentencia.setString(4, t.getMarca());
			sentencia.setString(5, c.getEmail());
			sentencia.setString(6, t.getTitular());
			
			if(sentencia.executeUpdate()>0)
			{
				msj.setTipo("Exito");
				msj.setMensaje("Tarjeta registrada correctamente.");
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
	public MensajeSistema eliminarTarjeta(String id) {
		// TODO Auto-generated method stub
		MensajeSistema msj = new MensajeSistema();
		String sql = "DELETE FROM TARJETA WHERE id=?";
		
		try {
			
			Connection conn = OracleDAOFactory.createConnection();
			conn.setAutoCommit(false);
			PreparedStatement sentencia = conn.prepareStatement(sql);
			sentencia.setString(1, id);
			
			if(sentencia.executeUpdate()>0)
			{
				msj.setTipo("Exito");
				msj.setMensaje("Tarjeta eliminada correctamente.");
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
	public LinkedList<Tarjeta> consultarTarjetas(String cliente) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM TARJETA WHERE CLIENTE=?";
		LinkedList<Tarjeta> lista = new LinkedList<Tarjeta>();
		try {
			
			Connection conn = OracleDAOFactory.createConnection();
			PreparedStatement sentencia = conn.prepareStatement(sql);
			sentencia.setString(1, cliente);
		
			ResultSet r = sentencia.executeQuery();
			
			while(r.next())
			{
				Tarjeta t = new Tarjeta();
				t.setId(r.getInt("ID"));
				t.setNumero(r.getString("NUMERO"));
				t.setExpira(r.getString("EXPIRA"));
				t.setMarca(r.getString("MARCA"));
				t.setCodigo(r.getInt("CODIGO"));
				t.setDefecto(r.getInt("DEFECTO"));
				t.setTitular(r.getString("TITULAR"));
				lista.add(t);	
			}
			
			conn.close();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void establecerTarjetaDefault(String emailCliente, String numeroTarjeta) {
		// TODO Auto-generated method stub
		String sql = "UPDATE TARJETA SET DEFECTO=1 WHERE CLIENTE=? AND ID=?";
		String sql1 = "UPDATE TARJETA SET DEFECTO=0 WHERE CLIENTE=? ";
		try {
			
			Connection conn = OracleDAOFactory.createConnection();
			conn.setAutoCommit(false);
			PreparedStatement sentencia = conn.prepareStatement(sql1);
			sentencia.setString(1, emailCliente);
			
			sentencia.executeUpdate();
			
				sentencia = conn.prepareStatement(sql);
				sentencia.setString(1, emailCliente);
				sentencia.setString(2, numeroTarjeta);
				sentencia.executeUpdate();
			
			
			conn.commit();
			conn.close();	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
