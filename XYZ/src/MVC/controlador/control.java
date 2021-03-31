package MVC.controlador;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MVC.beans.Cliente;
import MVC.beans.MensajeSistema;
import MVC.beans.Tarjeta;
import MVC.modelo.DAOFactory;
import MVC.modelo.OracleClienteDAO;
import MVC.modelo.interfaces.ClienteDAO;
import MVC.modelo.interfaces.TarjetaDAO;

/**
 * Servlet implementation class control
 */
@WebServlet("/control")
public class control extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public control() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		
		String accion = request.getParameter("accion");
		System.out.println(accion);
		if((sesion == null || sesion.getAttribute("usuario") == null) && !accion.equalsIgnoreCase("login") && !accion.equalsIgnoreCase("register")){
			
			response.sendRedirect("login.html");
			
		}else{
			
			if(accion != null){
				
				if(accion.equalsIgnoreCase("login")){
					
					String username = request.getParameter("usuario");
					String password = request.getParameter("password");			
					password = Base64.getEncoder().encodeToString(password.getBytes());
					DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
					ClienteDAO clienteModelo = factory.getClienteDAO();
					
				if(clienteModelo.esCliente(username, password)){
						Cliente usuario = clienteModelo.consultarCliente(username);
						sesion = request.getSession(true);
						//sesion.setMaxInactiveInterval(90);
						sesion.setAttribute("usuario", usuario);
						
						response.sendRedirect("PrincipalMenu.jsp");
						
					}else{
						
						response.setContentType("text/html; charset=UTF-8");
						response.getWriter().append("Usuario y/o contraseña no válidos. Intente nuevamente");
						
					}
					
				}if((sesion != null || sesion.getAttribute("usuario") != null) && accion.equalsIgnoreCase("logeado")){
					
					response.sendRedirect("PrincipalMenu.jsp");
					
				}else if(accion.equalsIgnoreCase("logout")){
								
					sesion.invalidate();
					
					response.sendRedirect("login.html");
					
				}else if(accion.equalsIgnoreCase("MenuPrincipal")){
					
					response.sendRedirect("menu.jsp");
					
				}else if(accion.equalsIgnoreCase("register")){
					String username = request.getParameter("email");
					String password = request.getParameter("pass");			
					String name = request.getParameter("name");
					
					Cliente c = new Cliente();
					c.setNombre(name);
					c.setEmail(username);
					c.setPassword(Base64.getEncoder().encodeToString(password.getBytes()));
					
					DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
					ClienteDAO clienteModelo = factory.getClienteDAO();
					
					MensajeSistema msj = clienteModelo.ingresarCliente(c);
					
					response.setContentType("text/html; charset=UTF-8");
					response.getWriter().append(msj.getTipo()+";"+msj.getMensaje());
					
				}else if(accion.equalsIgnoreCase("update")){
					
					String username = request.getParameter("correo");
					String regimen = request.getParameter("regimen");			
					String name = request.getParameter("nombre");
					String telefono = request.getParameter("telefono");
					String direccion = request.getParameter("direccion");			
					String nit = request.getParameter("nit");
					
					Cliente c = new Cliente();
					c.setNombre(name);
					c.setEmail(username);
					c.setDireccion(direccion);
					c.setTelefono(telefono);
					c.setNit(nit);
					c.setRegimen(Integer.parseInt(regimen));
					
					DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
					ClienteDAO clienteModelo = factory.getClienteDAO();
					TarjetaDAO tarjetaModelo = factory.gettarjetaDAO();
					
					MensajeSistema mensaje = clienteModelo.actualizarCliente(c);
				
					c.setTarjetas(tarjetaModelo.consultarTarjetas(username));
					
					sesion.setAttribute("mensaje", mensaje);
					sesion.setAttribute("usuario", c);
					response.sendRedirect("perfil.jsp");
					
				}else if(accion.equalsIgnoreCase("delete")){
					String username = request.getParameter("correo");
					
					DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
					ClienteDAO clienteModelo = factory.getClienteDAO();
					clienteModelo.eliminarCliente(username);
					
					response.sendRedirect("login.html");
					
				}else if(accion.equalsIgnoreCase("addCard")){
					String numero = request.getParameter("numero");
					String titular = request.getParameter("titular");			
					String expira = request.getParameter("mesExp")+"/"+request.getParameter("anoExp");
					String cvv = request.getParameter("codigo");
					String emisor = request.getParameter("emisor");			
					
					Tarjeta t = new Tarjeta();
					t.setNumero(numero);
					t.setExpira(expira);
					t.setMarca(emisor);
					t.setTitular(titular);
					t.setCodigo(Integer.parseInt(cvv));
					
					DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
					ClienteDAO clienteModelo = factory.getClienteDAO();
					TarjetaDAO tarjetaModelo = factory.gettarjetaDAO();
					
					MensajeSistema mensaje = tarjetaModelo.crearTarjeta(t, ((Cliente) sesion.getAttribute("usuario")));
					sesion.setAttribute("mensaje", mensaje);
					sesion.setAttribute("usuario", clienteModelo.consultarCliente(((Cliente) sesion.getAttribute("usuario")).getEmail()));
					response.sendRedirect("PrincipalMenu.jsp");
					
				}else if(accion.equalsIgnoreCase("deleteCard")){
					String numero = request.getParameter("tarjeta");
					
					DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
					ClienteDAO clienteModelo = factory.getClienteDAO();
					TarjetaDAO tarjetaModelo = factory.gettarjetaDAO();
					
					MensajeSistema mensaje = tarjetaModelo.eliminarTarjeta(numero);
					sesion.setAttribute("mensaje", mensaje);
					sesion.setAttribute("usuario", clienteModelo.consultarCliente(((Cliente) sesion.getAttribute("usuario")).getEmail()));
					response.sendRedirect("PrincipalMenu.jsp");
					

					
				}else if(accion.equalsIgnoreCase("defaultCard")){
					
					String numero = request.getParameter("tarjeta");
					
					DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
					ClienteDAO clienteModelo = factory.getClienteDAO();
					TarjetaDAO tarjetaModelo = factory.gettarjetaDAO();
					
					tarjetaModelo.establecerTarjetaDefault(((Cliente) sesion.getAttribute("usuario")).getEmail(), numero);
					
					sesion.setAttribute("usuario", clienteModelo.consultarCliente(((Cliente) sesion.getAttribute("usuario")).getEmail()));
					response.sendRedirect("PrincipalMenu.jsp");
					
					
				}		
				
			}else{
				
				sesion.invalidate();
				
				response.sendRedirect("login.html");
				
			}
			
		}
	}

}
