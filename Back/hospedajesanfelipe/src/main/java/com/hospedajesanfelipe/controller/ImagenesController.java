package com.hospedajesanfelipe.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.hospedajesanfelipe.response.ImagenesResponse;
import com.hospedajesanfelipe.service.ImagenesService;
import com.hospedajesanfelipe.vo.ImagenesInfoVO;

@Controller
@RequestMapping("/hospedaje/api/imagenes")
public class ImagenesController {

  @Autowired
  ImagenesService imagenesService;

  @PostMapping()
  public ResponseEntity<ImagenesResponse> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      imagenesService.save(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ImagenesResponse(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ImagenesResponse(message));
    }
  }

  @GetMapping()
  public ResponseEntity<List<ImagenesInfoVO>> getListFiles() {
    List<ImagenesInfoVO> fileInfos = imagenesService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(ImagenesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new ImagenesInfoVO(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/getByName/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
      Resource file = imagenesService.load(filename);
      if (file.exists() && file.isReadable()) {
          return ResponseEntity.ok()
                               .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                               .contentType(MediaType.IMAGE_JPEG)
                               .body(file);
      } else {
          return ResponseEntity.notFound().build();
      }
  }
}
