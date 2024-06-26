package com.hospedajesanfelipe.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.EmpleadosDao;
import com.hospedajesanfelipe.entity.CatRolEntity;
import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.request.EmpleadoRequest;
import com.hospedajesanfelipe.response.EmpleadoResponse;
import com.hospedajesanfelipe.service.EmpleadosService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

/*
 * Este servicio es el implement, y tiene que implementar la interfaz de empleados en este caso
 * También tienen que implementarse todos los método definidos que tiene la interfaz
 * Y esta clase puede contener lógica de negocio, o mapeos de clases
 */

/*
 * Se agrega la anotación @Service, para que Srring sepa que es un servicio y pueda ser inyectado en otra clase
 * En este caso e n el controller
 */
@Service
public class EmpleadosServiceImpl implements EmpleadosService {

	//Con esta anotación intectamos la clase dao para poder usar sus métodos
	@Autowired
	EmpleadosDao empleadosDao;
	
	@Override
	public EmpleadoEntity getEmpleadoByUserName(String userName) {
		Optional<EmpleadoEntity> entity = empleadosDao.getEmpleadoByUserName(userName);
		
		if (entity.isPresent()) {
			return entity.get();
		} else {
			return null;
		}
	}
	
	//Con el @Override implementamos el métod de la interfaz
	@Override
	public List<EmpleadoResponse> getAllEmpleados() {
		List<EmpleadoResponse> response = null;
		List<EmpleadoEntity> empleados = empleadosDao.getAllEmpleados();
		
		if (empleados != null && !empleados.isEmpty()) {
			response = new ArrayList<EmpleadoResponse>();
			for (EmpleadoEntity empleadoEntity : empleados) {
				EmpleadoResponse empleado = new EmpleadoResponse();
				empleado = mapperRempleadoResponse(empleadoEntity);
				response.add(empleado);
			}
			
		}
		return response;
	}

	/*
	 * Este método busca a un empleado por su id, y devuelve un empleado entity
	 */
	@Override
	public EmpleadoEntity getEmpleadoById(Long idEmpleado) {
		Optional<EmpleadoEntity> empleadoEntity = empleadosDao.getEmpleadoById(idEmpleado); 
		
		if (empleadoEntity.isPresent()) {
			return empleadoEntity.get();
		} else {
			return null;
		}
	}

	/*
	 * Este método crea un nuevo empleado en la base de datos, recibe como parámetro un objeto de tipo empleado
	 * Y también devuelve un objeto de tipo empleado
	 */
	@Override
	public EmpleadoEntity createEmpleado(EmpleadoRequest empleado) {
		/*
		 * Se crea un método mapper, lo que hace este método es convertir el objeto EmpleadoRequest a empleado Entity
		 * El empleado request es la información que manda el front al back y el back pasa esos datos para que el entity 
		 * pueda insertar el nuevo empleado
		 */
		EmpleadoEntity empleadoEntity = mapperEmpeado(empleado);
		/*
		 * Una vez mapeado el empleado Request con el empleado Entity, podemos mandar a insertar el entity en la base de datos
		 */
		return empleadosDao.createEmpleado(empleadoEntity);
	}

	/*
	 * Este método es para actualizar a un empleado, es casi igual que el método anterior de crear a un empleado
	 */
	@Override
	public EmpleadoEntity updateEmpleado(EmpleadoRequest empleado) {
		/*
		 * También hacemos el mismo mapper, la única diferencia con el método anterior, es que aquí
		 * El EmpleadoRequest sí va a tener un id, todo los demás es lo mismo
		 */
		EmpleadoEntity empleadoEntity = mapperEmpeado(empleado);
		return empleadosDao.createEmpleado(empleadoEntity);
	}

	/*
	 * Este es el método para eliminar un empleado, recibe como parámetro un id de empleado para que sepa cuál liminar
	 */
	@Override
	public void deleteEmpleado(Long idEmpleado) {
		empleadosDao.deleteEmpleado(idEmpleado);
	}
	
	@Override
	public ByteArrayInputStream getEmpleadosPdf() {
	    List<EmpleadoResponse> allEmpleados = getAllEmpleados();
	    
	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    try (PdfWriter writer = new PdfWriter(out);
	         PdfDocument pdf = new PdfDocument(writer);
	         Document document = new Document(pdf)) {
	        
	        // Cargar la imagen del logo
	        InputStream logoStream = getClass().getResourceAsStream("/static/images/sf.png");
	        byte[] logoBytes = logoStream.readAllBytes();
	        ImageData logoData = ImageDataFactory.create(logoBytes);
	        Image logo = new Image(logoData).setWidth(50).setHeight(50);  // Ajusta el tamaño según sea necesario
	        logo.setHorizontalAlignment(HorizontalAlignment.CENTER);
	        document.add(logo);
	        
	        // Añadir el título
	        Paragraph title = new Paragraph("Hospedaje San Felipe De Jesús")
	            .setTextAlignment(TextAlignment.CENTER)
	            .setFontSize(20)
	            .setBold()
	            .setFontColor(ColorConstants.BLACK)
	            .setMarginBottom(20);  // Añade un margen debajo del título
	        document.add(title);
	        
	        // Crear una tabla con el número correcto de columnas
	        float[] columnWidths = {1, 3, 3, 3, 3, 3};
	        Table table = new Table(columnWidths);

	        // Añadir los encabezados de la tabla
	        table.addHeaderCell(createCell("ID", true));
	        table.addHeaderCell(createCell("Nombre", true));
	        table.addHeaderCell(createCell("Apellido Paterno", true));
	        table.addHeaderCell(createCell("Apellidos Materno", true));
	        table.addHeaderCell(createCell("Telefono", true));
	        table.addHeaderCell(createCell("rol", true));
	        
	        // Añadir filas con los datos de los clientes
	        for (EmpleadoResponse empleado : allEmpleados) {
	            table.addCell(createCell(empleado.getIdEmpleado().toString(), false));
	            table.addCell(createCell(empleado.getNombre(), false));
	            table.addCell(createCell(empleado.getPrimerApellido(), false));
	            table.addCell(createCell(empleado.getSegundoApellido(), false));
	            table.addCell(createCell(empleado.getNoTelefono(), false));
	            table.addCell(createCell(empleado.getRol().getDescripcion(), false));
	        }

	        // Añadir la tabla al documento
	        document.add(table);

	        // Cerrar el documento
	        document.close();
	        
	        // Devolver el ByteArrayOutputStream como ByteArrayInputStream
	        return new ByteArrayInputStream(out.toByteArray());
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	// Método auxiliar para crear celdas
	private Cell createCell(String content, boolean isHeader) {
	    Cell cell = new Cell().add(new Paragraph(content));
	    if (isHeader) {
	        cell.setBackgroundColor(new DeviceRgb(224, 224, 224)); // Bootstrap light gray color
	        cell.setFontColor(ColorConstants.BLACK);
	        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    } else {
	        cell.setFontColor(ColorConstants.BLACK);
	    }
	    cell.setTextAlignment(TextAlignment.CENTER);
	    return cell;
	}
	
	private EmpleadoEntity mapperEmpeado(EmpleadoRequest empleado) {
		EmpleadoEntity empleadoEntity = null;

		/*
		 * Validamos que el objeto EmpleadoRequest traida un id, si trae un id, quiere decir que el empleado ya existe y hay que actualizarlo
		 */
		if (empleado.getIdEmpleado() != null && empleado.getIdEmpleado() > 0) {
			/*
			 * Si cuenta con un id buscamos primero el empleado en la base de datos con el método de getEmpleadoById()
			 */
			empleadoEntity = getEmpleadoById(empleado.getIdEmpleado());
		} else {
			/*
			 * Si el empleado no trae un id, quiere decir que es un nuevo empleado, y creamos una nueva instancia vacía de Empleado entity
			 * Para poder guardarlo en la base de datos
			 */
			empleadoEntity = new EmpleadoEntity();
			
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(empleado.getContrasenia());
			empleado.setContrasenia(encodedPassword);
		}
		
		
		
		empleadoEntity.setIdEmpleado(empleado.getIdEmpleado());
		empleadoEntity.setUserName(validaNull(empleado.getUserName(), empleadoEntity.getUserName()));
		empleadoEntity.setContrasenia(validaNull(empleado.getContrasenia(), empleadoEntity.getContrasenia()));
		empleadoEntity.setNombre(validaNull(empleado.getNombre(), empleadoEntity.getNombre()));
		empleadoEntity.setPrimerApellido(validaNull(empleado.getPrimerApellido(), empleadoEntity.getPrimerApellido()));
		empleadoEntity.setSegundoApellido(validaNull(empleado.getSegundoApellido(), empleadoEntity.getSegundoApellido()));
		empleadoEntity.setNoTelefono(validaNull(empleado.getNoTelefono(), empleadoEntity.getNoTelefono()));
		/*
		 * Rol, es un objeto, y cuando hacemos la instancia de un new EmpleadoEntity(), este objeto es nulo
		 * Por eso tenermos que crearle una nueva instancia de CatRolEntity()
		 */
		if (empleado.getRol() != null && empleado.getRol() > 0) {
			empleadoEntity.setRol(new CatRolEntity());
			/*
			 * Una vez creada la instancia de Rol ya ya no es nula, podemos asignarle el rol correspondiente
			 */
			empleadoEntity.getRol().setIdRol(empleado.getRol());
			
			
		}
		
		empleadoEntity.setUrlFoto(empleado.getUrlFoto());
		
		/*
		 * Retornamos el empleado entity que es lo que se va a guardar en la base de datos.
		 */
		return empleadoEntity;
	}
	
	private EmpleadoResponse mapperRempleadoResponse(EmpleadoEntity empleado) {
		EmpleadoResponse response = new EmpleadoResponse();
		
		response.setIdEmpleado(empleado.getIdEmpleado());
		response.setUserName(empleado.getUserName());
		response.setNombre(empleado.getNombre());
		response.setPrimerApellido(empleado.getPrimerApellido());
		response.setSegundoApellido(empleado.getSegundoApellido());
		response.setNoTelefono(empleado.getNoTelefono());
		response.setRol(empleado.getRol());
		
		return response;
	}
	
	private String validaNull(String parametro, String actual) {
		if (parametro != null && !parametro.isBlank()) {
			return parametro;
		} else {
			return actual;
		}
	}
}
