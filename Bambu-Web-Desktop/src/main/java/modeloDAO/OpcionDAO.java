package modeloDAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;

import bean.Conexion;
import modelo.Opcion;




public class OpcionDAO extends ConexionDAO{
	



	public OpcionDAO(){
		super();
	}
	
     public ArrayList<Opcion> buscarPadre(String idrol){
		ArrayList<Opcion> padres = new ArrayList<Opcion>();
		String tiraSQL = "select b.codigo_opcion, a.codigo_padre, a.vinculo, a.texto, a.status, a.icono, a.tabla from tb_opcion a, tb_opcion_rol b where a.codigo = b.codigo_opcion and b.codigo_rol='"+idrol+"' and a.codigo_padre = '0' and b.status='true' order by b.codigo_opcion";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String id_opcion = resultSet.getString("codigo_opcion");
				String id_padre = resultSet.getString("codigo_padre");
				String vinculo = resultSet.getString("vinculo");
				String texto = resultSet.getString("texto");
				Boolean status = resultSet.getBoolean("status");
				String icono = resultSet.getString("icono");
				String tabla = resultSet.getString("tabla");
				Opcion padre = new Opcion(id_opcion, id_padre, vinculo, texto, status, icono, tabla);
				padres.add(padre);
			}
			resultSet.close();
		} catch (SQLException e) {
			Messagebox.show("Ha expirado su sesi�n");
			Executions.sendRedirect("login.zul");
		}
		return padres;
	}
	
	public ArrayList<Opcion> buscarHijos(String idrol, String idopcion){
		ArrayList<Opcion> hijos = new ArrayList<Opcion>();
		String tiraSQL ="select b.codigo_opcion, a.codigo_padre, a.vinculo, a.texto, a.status, a.icono, a.tabla from tb_opcion a, tb_opcion_rol b where a.codigo = b.codigo_opcion and b.codigo_rol ='"+idrol+"' and a.codigo_padre='"+idopcion+"' and b.status='true' order by b.codigo_opcion";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String fk_id_opcion = resultSet.getString("codigo_opcion");
				String id_padre = resultSet.getString("codigo_padre");
				String vinculo = resultSet.getString("vinculo");
				String texto = resultSet.getString("texto");
				Boolean estatus = resultSet.getBoolean("status");
				String icono = resultSet.getString("icono");
				String tabla = resultSet.getString("tabla");
				Opcion hijo = new Opcion(fk_id_opcion, id_padre, vinculo, texto, estatus, icono, tabla);
				hijos.add(hijo);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hijos;
	}
	
	public ArrayList<Opcion> buscarNietos(String idrol, String idopcion){
		ArrayList<Opcion> nietos = new ArrayList<Opcion>();
		String tiraSQL ="select b.codigo_opcion, a.codigo_padre, a.vinculo, a.texto, a.status, a.icono, a.tabla from tb_opcion a, tb_opcion_rol b where a.codigo = b.codigo_opcion and b.codigo_rol ='"+idrol+"' and a.codigo_padre='"+idopcion+"' and b.status='true' order by b.codigo_opcion";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String fk_id_opcion = resultSet.getString("codigo_opcion");
				String id_padre = resultSet.getString("codigo_padre");
				String vinculo = resultSet.getString("vinculo");
				String texto = resultSet.getString("texto");
				Boolean estatus = resultSet.getBoolean("status");
				String icono = resultSet.getString("icono");
				String tabla = resultSet.getString("tabla");
				Opcion nieto = new Opcion(fk_id_opcion, id_padre, vinculo, texto, estatus, icono, tabla);
				nietos.add(nieto);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nietos;
	}
	
	public ArrayList<Opcion> ObtenerTodos(){
		ArrayList<Opcion> padres = new ArrayList<Opcion>();
		String tiraSQL ="select * from tb_opcion where status='false' order by codigo";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String fk_id_opcion = resultSet.getString("codigo");
				String id_padre = resultSet.getString("codigo_padre");
				String vinculo = resultSet.getString("vinculo");
				String texto = resultSet.getString("texto");
				Boolean estatus = resultSet.getBoolean("status");
				String icono = resultSet.getString("icono");
				String tabla = resultSet.getString("tabla");
				Opcion padre = new Opcion(fk_id_opcion, id_padre, vinculo, texto, estatus, icono, tabla);
				padres.add(padre);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return padres;
	}
}
