package Ucentral.Arquitecturall_monitoreo.controlador.roboflow;

import Ucentral.Arquitecturall_monitoreo.servicio.roboflow.RoboflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/detecciones")
@RequiredArgsConstructor
public class DeteccionController {

    private final RoboflowService roboflowService;

    @PostMapping(
            value = "/imagen",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> detectarEnImagen(@RequestParam("file") MultipartFile file) {

        System.out.println(">>> LLEGÓ AL ENDPOINT /api/detecciones/imagen <<<");

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "El archivo está vacío"));
        }

        String resultadoJson = roboflowService.detectarEnImagen(file);
        return ResponseEntity.ok(resultadoJson);
    }
}