package MVC.beans;

import java.io.Serializable;

public class Tarjeta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7269647583314025974L;
	private String titular;
	private int id;
	private int defecto;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getExpira() {
		return expira;
	}
	public void setExpira(String expira) {
		this.expira = expira;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getDefecto() {
		return defecto;
	}
	public void setDefecto(int defecto) {
		this.defecto = defecto;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	private String numero;
	private String expira;
	private int codigo;
	private String marca;
	
}
