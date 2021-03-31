package MVC.modelo.interfaces;

import java.util.LinkedList;

import MVC.beans.Cliente;
import MVC.beans.MensajeSistema;
import MVC.beans.Tarjeta;

public interface TarjetaDAO {
	
	public MensajeSistema crearTarjeta(Tarjeta t, Cliente c);
	public MensajeSistema eliminarTarjeta(String id);
	public LinkedList<Tarjeta> consultarTarjetas(String cliente);
	public void establecerTarjetaDefault(String emailCliente, String numeroTarjeta);
	

}
