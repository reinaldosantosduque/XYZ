package MVC.beans;

import java.io.Serializable;
import java.util.LinkedList;

public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6403474874042535769L;
	private LinkedList<Tarjeta> tarjetas;
	private String telefono;
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	private String direccion;
	public Cliente()
	{
		this.tarjetas = new LinkedList<Tarjeta>(); 
	}
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRegimen() {
		return regimen;
	}
	public void setRegimen(int regimen) {
		this.regimen = regimen;
	}
	public LinkedList<Tarjeta> getTarjetas() {
		return tarjetas;
	}
	public void setTarjetas(LinkedList<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}
	private String nombre;
	private String nit;
	private String email;
	private String password;
	private int regimen;

}
