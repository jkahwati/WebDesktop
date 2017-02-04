package modelo;

public class Usuario {
	
   int id_usuario;
   int id_rol;
   String usuario;
   String clave;
   char estatus;
   
   
   
public Usuario(int id_usuario, int id_rol, String usuario, String clave, char estatus) {
	super();
	this.id_usuario = id_usuario;
	this.id_rol = id_rol;
	this.usuario = usuario;
	this.clave = clave;
	this.estatus = estatus;
}


public int getId_usuario() {
	return id_usuario;
}
public void setId_usuario(int id_usuario) {
	this.id_usuario = id_usuario;
}
public int getId_rol() {
	return id_rol;
}
public void setId_rol(int id_rol) {
	this.id_rol = id_rol;
}
public String getUsuario() {
	return usuario;
}
public void setUsuario(String usuario) {
	this.usuario = usuario;
}
public String getClave() {
	return clave;
}
public void setClave(String clave) {
	this.clave = clave;
}
public char getEstatus() {
	return estatus;
}
public void setEstatus(char estatus) {
	this.estatus = estatus;
}
   
   

}