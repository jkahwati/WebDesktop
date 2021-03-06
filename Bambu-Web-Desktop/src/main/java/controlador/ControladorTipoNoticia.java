package controlador;

import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox.ClickEvent;

import modelo.Maestrico;
import modelo.Servicio;
import modelo.TipoNoticia;
import modeloDAO.TipoNoticiaDAO;

public class ControladorTipoNoticia extends SelectorComposer<Component>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Wire
	private Textbox titulo;
	@Wire
	private Textbox descripcion;
	
	@Wire
	private Image imagen;
	@Wire
	private Media media;
	@Wire
	private Button uploadImagen;
	@Wire
	private Listbox listaTipoNoticia;
	
	
	TipoNoticia tipo = new TipoNoticia();
	TipoNoticiaDAO dao = new TipoNoticiaDAO();
	
	

	@Listen("onUpload = #uploadImagen")
	public void onUpload$uploadImagen(UploadEvent e)
	{
		media = e.getMedia();
	    imagen.setContent((org.zkoss.image.Image) media);
	    uploadImagen.setVisible(false);
	}
	
	@Listen("onCreate = #registrarDatos")
	public void cargarTabla(){
		List<TipoNoticia> tipoNoticias = dao.listaNoticia();
		listaTipoNoticia.setModel(new ListModelList<TipoNoticia>(tipoNoticias));
	}
	
	
	
	@Listen("onClick = #guardar")
	public void guargar(){
		String dir="";
		 if(media instanceof org.zkoss.image.Image) {
	             try {
	                
	            	 String carpeta = "C:\\Users\\Andres\\Documents\\GitHub\\WebDesktop\\Bambu-Web-Desktop\\src\\main\\webapp\\WebContent\\assets\\imagenesSlider";
	            	 FileOutputStream fileOutputStream=new FileOutputStream(carpeta+"\\"+media.getName());
	                 dir="Bambu-Web-Desktop/WebContent/assets/imagenesSlider/"+media.getName();
	            	 fileOutputStream.write(media.getByteData());
	            	 

		         		

		         			//Date fecha = new Date(System.currentTimeMillis());
	            	        tipo = new TipoNoticia(dao.TotalRegistros(),descripcion.getText(),"Activo",dir);
		         			dao.agregarTipoNoticia(tipo);
		         			Messagebox.show("Datos Guardados Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
		         			limpiarCampos();
		         			cargarTabla();
		         	
		         			 
		         		fileOutputStream.close();
		         		
	            	
	            	
	             }catch (Exception ex) {
	                 Logger.getLogger(ControladorNoticia.class.getName()).log(Level.SEVERE, null, ex);
	             }
	             
		
	             	

		}
		
	}
	
	public void limpiarCampos()
	{
		
	    descripcion.setText("");
		imagen.setContent((org.zkoss.image.Image) null);
	}
	
	
	
	@Listen("onDelete = #tipo")
	public void eliminarTipoServicio(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		tipo = (TipoNoticia)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	dao.eliminarTipoNoticia(tipo.getCodigo());
	            	 cargarTabla();
					   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar esta imagen?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}

}
