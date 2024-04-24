package com.hospedajesanfelipe.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospedajesanfelipe.dao.ReservacionesDao;
import com.hospedajesanfelipe.entity.CatEstadoHabitacionEntity;
import com.hospedajesanfelipe.entity.CatPrecioEspecialEntity;
import com.hospedajesanfelipe.entity.ClienteEntity;
import com.hospedajesanfelipe.entity.ComentarioEntity;
import com.hospedajesanfelipe.entity.EmpleadoEntity;
import com.hospedajesanfelipe.entity.HabitacionEntity;
import com.hospedajesanfelipe.entity.ReservacionEntity;
import com.hospedajesanfelipe.entity.ReservacionHabitacionEntity;
import com.hospedajesanfelipe.request.EmpleadoRequest;
import com.hospedajesanfelipe.request.ReservacionRequest;
import com.hospedajesanfelipe.response.ReservacionResponse;
import com.hospedajesanfelipe.service.ReservacionesService;
import com.hospedajesanfelipe.vo.ReservacionHabitacionIdVO;
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
public class ReservacionesServiceImpl implements ReservacionesService {
	
	@Autowired
	ReservacionesDao reservacionesDao;
	
	@Override
	public List<ReservacionResponse> getAllReservaciones() {
		List<ReservacionResponse> response = null;
		List<ReservacionEntity> reservaciones = reservacionesDao.getAllReservaciones();
		
		if (reservaciones != null && !reservaciones.isEmpty()) {
			response = new ArrayList<ReservacionResponse>();
			for (ReservacionEntity reservacionEntity : reservaciones) {
				ReservacionResponse reservacion = reservacionResponseMap(reservacionEntity);
				response.add(reservacion);
			}
		}
		
		return response;
	}

	@Override
	public ReservacionEntity getReservacion(Long idReservacion) {
		Optional<ReservacionEntity> reservacionEntity = reservacionesDao.getReservacion(idReservacion); 
		if (reservacionEntity.isPresent()) {
			ReservacionResponse reservacion = new ReservacionResponse();
			
			reservacion.setIdReservacion(idReservacion);
			
			return reservacionEntity.get();
		} else {
			return null;
		}
	}
	
	@Override
	public ReservacionResponse saveReservaciones(ReservacionRequest reservacionRequest) {
		ReservacionEntity reservacionEntity = mapRequestReservacion(reservacionRequest);
		ReservacionEntity reservacionSaved = reservacionesDao.saveReservaciones(reservacionEntity); 

		List<ReservacionHabitacionEntity> rhs = reservacionHabitacionMapper(reservacionSaved.getIdReservacion(), reservacionRequest.getHabitaciones());
		List<ReservacionHabitacionEntity> rhsSaved = reservacionesDao.saveReservacionHabitacion(rhs);
		
		ReservacionResponse response = reservacionResponseMap(reservacionSaved);
		
		response.setRhs(rhsSaved);
		
		return response;
	}
	
	@Override
	public ReservacionResponse updateReservaciones(ReservacionRequest reservacionRequest) {
		ReservacionEntity reservacionEntity = mapRequestReservacion(reservacionRequest);
		ReservacionEntity reservacionSaved = reservacionesDao.saveReservaciones(reservacionEntity);
		
		List<ReservacionHabitacionEntity> rhsActual = reservacionesDao.getAllReservacionHabitacion(reservacionSaved.getIdReservacion());
		List<ReservacionHabitacionEntity> rhsNew = reservacionHabitacionMapper(reservacionSaved.getIdReservacion(), reservacionRequest.getHabitaciones());
		
		List<ReservacionHabitacionEntity> rhsUpdate = reservacionHabitacionEditMapper(rhsActual, rhsNew);
		
		
		List<ReservacionHabitacionEntity> rhsUpdated = reservacionesDao.updateReservacionHabitacion(rhsUpdate);
		
		ReservacionResponse response = reservacionResponseMap(reservacionSaved);
		response.setRhs(rhsUpdated);
		return response;
	}
	
	@Override
	public void deleteReservaciones(Long idReservacion) {
		reservacionesDao.deleteReservaciones(idReservacion);
		ReservacionHabitacionIdVO rhv = new ReservacionHabitacionIdVO();
		
		rhv.setReservacion(idReservacion);
		
		reservacionesDao.deleteReservacionHabitacionEntity(rhv);
	}
	
	@Override
	public ByteArrayInputStream getReservacionesPdf() {
	    List<ReservacionResponse> allReservaciones = getAllReservaciones();
	    
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
	        float[] columnWidths = {1, 3, 3, 1, 1, 3, 3, 3};
	        Table table = new Table(columnWidths);
	        table.setFontSize(9);
	        
	        // Añadir los encabezados de la tabla
	        table.addHeaderCell(createCell("ID", true));
	        table.addHeaderCell(createCell("Cliente", true));
	        table.addHeaderCell(createCell("Empleado", true));
	        table.addHeaderCell(createCell("No. personas", true));
	        table.addHeaderCell(createCell("No. extra", true));
	        table.addHeaderCell(createCell("Entrada", true));
	        table.addHeaderCell(createCell("Salida", true));
	        table.addHeaderCell(createCell("Total", true));
	        
	        // Añadir filas con los datos de los clientes
	        for (ReservacionResponse reservacion : allReservaciones) {
	            table.addCell(createCell(reservacion.getIdReservacion().toString(), false));
	            table.addCell(createCell(reservacion.getCliente().getNombre().concat(" ".concat(reservacion.getCliente().getPrimerApellido().concat(" ".concat(reservacion.getCliente().getSegundoApellido())))), false));
	            table.addCell(createCell(reservacion.getEmpleado().getNombre().concat(" ".concat(reservacion.getEmpleado().getPrimerApellido().concat(" ".concat(reservacion.getEmpleado().getSegundoApellido())))), false));
	            table.addCell(createCell(reservacion.getNoPersonas()+"", false));
	            table.addCell(createCell(reservacion.getNoPersonaExtra()+"", false));
	            table.addCell(createCell(reservacion.getFechaEntrada().toString(), false));
	            table.addCell(createCell(reservacion.getFechaEntrada().toString(), false));
	            table.addCell(createCell("$".concat(reservacion.getTotal().toString()), false));
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
	
	private ReservacionEntity mapRequestReservacion(ReservacionRequest reservacion) {
		
		ReservacionEntity entity = null;
		if (reservacion.getIdReservacion() != null && reservacion.getIdReservacion() > 0) {
			entity = getReservacion(reservacion.getIdReservacion());
		} else {
			entity = new ReservacionEntity();
		}
		
		entity.setFechaEntrada(reservacion.getFechaEntrada());
		entity.setFechaSalida(reservacion.getFechaSalida());
		entity.setNoPersonas(reservacion.getNoPersonas());
		entity.setNoPersonaExtra(reservacion.getNoPersonaExtra());
		entity.setTotal(reservacion.getTotal());
		entity.setEstado(new CatEstadoHabitacionEntity());
		entity.getEstado().setIdEstado(reservacion.getEstado());
		entity.setCliente(new ClienteEntity());
		entity.getCliente().setIdCliente(reservacion.getIdCliente());
		entity.setEmpleado(new EmpleadoEntity());
		entity.getEmpleado().setIdEmpleado(reservacion.getIdEmpleado());

		if (reservacion.getComentario() != null) {
			entity.setComentario(new ComentarioEntity());
			entity.getComentario().setIdComentario(reservacion.getComentario());
		}
		
		if (reservacion.getPrecioEspecial() != null) {
			entity.setPrecioEspecial(new CatPrecioEspecialEntity());
			entity.getPrecioEspecial().setIdPrecioEspecial(reservacion.getPrecioEspecial());
		}
		
		return entity;
	}

	
	private List<ReservacionHabitacionEntity> reservacionHabitacionMapper(Long idReservacion, List<HabitacionEntity> habitaciones) {
		List<ReservacionHabitacionEntity> rhs = new ArrayList<ReservacionHabitacionEntity>();
		
		for (HabitacionEntity habitacion : habitaciones) {
			ReservacionHabitacionEntity rh = new ReservacionHabitacionEntity();
			
			ReservacionEntity reservacion =  new ReservacionEntity();
			reservacion.setIdReservacion(idReservacion);
			
			rh.setReservacion(reservacion);
			rh.setHabitacion(habitacion);
			rh.setNoPersonas(habitacion.getNoOcupantes());
			rh.setNoPersonasExtra(habitacion.getNoMaxExtras());
			
			rhs.add(rh);
		}
		
		return rhs;
	}

	private List<ReservacionHabitacionEntity> reservacionHabitacionEditMapper(List<ReservacionHabitacionEntity> existingHabitaciones, List<ReservacionHabitacionEntity> updatedHabitaciones) {
		for (ReservacionHabitacionEntity updated : updatedHabitaciones) {
            for (ReservacionHabitacionEntity existing : existingHabitaciones) {
                if (existing.getHabitacion().getIdHabitacion().equals(updated.getHabitacion().getIdHabitacion())) {
                    existing.setNoPersonas(updated.getNoPersonas());
                    existing.setNoPersonasExtra(updated.getNoPersonasExtra());
                    // actualizar otros campos si es necesario
                }
            }
        }
		
		return existingHabitaciones;
	}
	
	private ReservacionResponse reservacionResponseMap(ReservacionEntity reservacionEntity) {
		ReservacionResponse reservacion = new ReservacionResponse();
		
		reservacion.setIdReservacion(reservacionEntity.getIdReservacion());
		reservacion.setFechaEntrada(reservacionEntity.getFechaEntrada());
		reservacion.setFechaSalida(reservacionEntity.getFechaSalida());
		reservacion.setNoPersonas(reservacionEntity.getNoPersonas());
		reservacion.setNoPersonaExtra(reservacionEntity.getNoPersonaExtra());
		reservacion.setTotal(reservacionEntity.getTotal());
		reservacion.setEstado(reservacionEntity.getEstado().getDescripcion());
		reservacion.setCliente(reservacionEntity.getCliente());
		EmpleadoRequest empleado = new EmpleadoRequest();
		empleado.setIdEmpleado(reservacionEntity.getEmpleado().getIdEmpleado());
		empleado.setNombre(reservacionEntity.getEmpleado().getNombre());
		empleado.setPrimerApellido(reservacionEntity.getEmpleado().getPrimerApellido());
		empleado.setSegundoApellido(reservacionEntity.getEmpleado().getSegundoApellido());
		reservacion.setEmpleado(empleado);
		reservacion.setComentario(reservacionEntity.getComentario());
		reservacion.setPrecioEspecial(reservacionEntity.getPrecioEspecial());
		reservacion.setRhs(reservacionesDao.getAllReservacionHabitacion(reservacionEntity.getIdReservacion()));
		
		return reservacion;
	}
}
