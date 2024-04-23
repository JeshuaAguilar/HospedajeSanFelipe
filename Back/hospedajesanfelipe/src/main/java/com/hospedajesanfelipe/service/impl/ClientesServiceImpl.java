package com.hospedajesanfelipe.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.ClientesDao;
import com.hospedajesanfelipe.entity.ClienteEntity;
import com.hospedajesanfelipe.request.ClienteRequest;
import com.hospedajesanfelipe.response.ClienteResponse;
import com.hospedajesanfelipe.service.ClientesService;
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

@Service
public class ClientesServiceImpl implements ClientesService {
	
	@Autowired
	ClientesDao clientesDao;
	
	@Override
	public List<ClienteResponse> getClienteByNombreApellido(String nombre, String apellido) {
		List<ClienteResponse> response = null;
		List<ClienteEntity> clientes = clientesDao.getClienteByNombreApellido(nombre, apellido);
		
		if (clientes != null && !clientes.isEmpty()) {
			response = new ArrayList<ClienteResponse>();
			for (ClienteEntity clienteEntity : clientes) {
				ClienteResponse cliente = new ClienteResponse();
				cliente = mapperRclienteResponse(clienteEntity);
				response.add(cliente);
			}
		} else {
			response = new ArrayList<ClienteResponse>();
		}
		return response;
	}
	
	@Override
	public List<ClienteResponse> getAllClientes() {
		List<ClienteResponse> response = null;
		List<ClienteEntity> clientes = clientesDao.getAllClientes();
		
		if (clientes != null && !clientes.isEmpty()) {
			response = new ArrayList<ClienteResponse>();
			for (ClienteEntity clienteEntity : clientes) {
				ClienteResponse cliente = new ClienteResponse();
				cliente = mapperRclienteResponse(clienteEntity);
				response.add(cliente);
			}
			
		}
		return response;
	}
	
	@Override
	public ClienteEntity getClienteById(Long idCliente) {
		Optional<ClienteEntity> clienteEntity = clientesDao.getClienteById(idCliente); 
		
		if (clienteEntity.isPresent()) {
			return clienteEntity.get();
		} else {
			return null;
		}
	}
	
	@Override
	public ClienteEntity createCliente(ClienteRequest cliente) {
		/*
		 * Se crea un método mapper, lo que hace este método es convertir el objeto EmpleadoRequest a empleado Entity
		 * El empleado request es la información que manda el front al back y el back pasa esos datos para que el entity 
		 * pueda insertar el nuevo empleado
		 */
		ClienteEntity clienteEntity = mapperCliente(cliente);
		/*
		 * Una vez mapeado el empleado Request con el empleado Entity, podemos mandar a insertar el entity en la base de datos
		 */
		return clientesDao.createCliente(clienteEntity);
	}
	
	@Override
	public ClienteEntity updateCliente(ClienteRequest cliente) {
		/*
		 * También hacemos el mismo mapper, la única diferencia con el método anterior, es que aquí
		 * El EmpleadoRequest sí va a tener un id, todo los demás es lo mismo
		 */
		ClienteEntity clienteEntity = mapperCliente(cliente);
		return clientesDao.createCliente(clienteEntity);
	}
	
	@Override
	public void deleteCliente(Long idCliente) {
		clientesDao.deleteCliente(idCliente);
	}
	
	@Override
	public ByteArrayInputStream getClientesPdf() {
	    List<ClienteResponse> allClientes = getAllClientes();
	    
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
	        float[] columnWidths = {1, 3, 3, 3, 3, 3, 3};
	        Table table = new Table(columnWidths);

	        // Añadir los encabezados de la tabla
	        table.addHeaderCell(createCell("ID", true));
	        table.addHeaderCell(createCell("Nombre", true));
	        table.addHeaderCell(createCell("Apellido Paterno", true));
	        table.addHeaderCell(createCell("Apellidos Materno", true));
	        table.addHeaderCell(createCell("Telefono", true));
	        table.addHeaderCell(createCell("Estado", true));
	        table.addHeaderCell(createCell("Municipio", true));
	        
	        // Añadir filas con los datos de los clientes
	        for (ClienteResponse cliente : allClientes) {
	            table.addCell(createCell(cliente.getIdCliente().toString(), false));
	            table.addCell(createCell(cliente.getNombre(), false));
	            table.addCell(createCell(cliente.getPrimerApellido(), false));
	            table.addCell(createCell(cliente.getSegundoApellido(), false));
	            table.addCell(createCell(cliente.getTelefono(), false));
	            table.addCell(createCell(cliente.getEstado(), false));
	            table.addCell(createCell(cliente.getMunicipio(), false));
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
	
	private ClienteEntity mapperCliente(ClienteRequest cliente) {
		ClienteEntity clienteEntity = null;

		/*
		 * Validamos que el objeto EmpleadoRequest traida un id, si trae un id, quiere decir que el empleado ya existe y hay que actualizarlo
		 */
		if (cliente.getIdCliente() != null && cliente.getIdCliente() > 0) {
			/*
			 * Si cuenta con un id buscamos primero el empleado en la base de datos con el método de getEmpleadoById()
			 */
			clienteEntity = getClienteById(cliente.getIdCliente());
		} else {
			/*
			 * Si el empleado no trae un id, quiere decir que es un nuevo empleado, y creamos una nueva instancia vacía de Empleado entity
			 * Para poder guardarlo en la base de datos
			 */
			clienteEntity = new ClienteEntity();
		}
		
		clienteEntity.setIdCliente(cliente.getIdCliente());
		clienteEntity.setNombre(validaNull(cliente.getNombre(), clienteEntity.getNombre()));
		clienteEntity.setPrimerApellido(validaNull(cliente.getPrimerApellido(), clienteEntity.getPrimerApellido()));
		clienteEntity.setSegundoApellido(validaNull(cliente.getSegundoApellido(), clienteEntity.getSegundoApellido()));
		clienteEntity.setTelefono(validaNull(cliente.getTelefono(), clienteEntity.getTelefono()));
		clienteEntity.setEstado(validaNull(cliente.getEstado(), clienteEntity.getEstado()));
		clienteEntity.setMunicipio(validaNull(cliente.getMunicipio(), clienteEntity.getMunicipio()));
		
		return clienteEntity;
	}
	
	private ClienteResponse mapperRclienteResponse(ClienteEntity cliente) {
		ClienteResponse response = new ClienteResponse();
		
		response.setIdCliente(cliente.getIdCliente());
		response.setNombre(cliente.getNombre());
		response.setPrimerApellido(cliente.getPrimerApellido());
		response.setSegundoApellido(cliente.getSegundoApellido());
		response.setTelefono(cliente.getTelefono());
		response.setEstado(cliente.getEstado());
		response.setMunicipio(cliente.getMunicipio());
		
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
