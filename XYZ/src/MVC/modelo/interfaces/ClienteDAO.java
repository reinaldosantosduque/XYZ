package MVC.modelo.interfaces;
import MVC.beans.*;

public interface ClienteDAO {

	public MensajeSistema ingresarCliente(Cliente cliente);
	public MensajeSistema eliminarCliente(String nit);
	public MensajeSistema actualizarCliente(Cliente cliente);
	public Cliente consultarCliente(String email);
	public boolean esCliente(String email, String password);
	
}
