package com.JuanD.carvajal.controller;

import com.JuanD.carvajal.dto.HistoricoResponseDTO;
import com.JuanD.carvajal.dto.HttpGlobalResponse;
import com.JuanD.carvajal.dto.ListaDeseosRequestDTO;
import com.JuanD.carvajal.dto.ListaDeseosResponseDTO;
import com.JuanD.carvajal.entity.ListaDeseos;
import com.JuanD.carvajal.service.ListaDeseosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista-deseos")
@RequiredArgsConstructor
public class ListaDeseosController {

    private final ListaDeseosService listaDeseosService;

    @GetMapping
    public ResponseEntity<HttpGlobalResponse<List<ListaDeseosResponseDTO>>> getAll(@RequestParam Integer usuarioId) {
        try {
            List<ListaDeseosResponseDTO> list = listaDeseosService.getAll(usuarioId);
            return ResponseEntity.ok(new HttpGlobalResponse<>(200, "Lista de deseos obtenida", list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new HttpGlobalResponse<>(400, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<HttpGlobalResponse<HistoricoResponseDTO>> save(@RequestBody ListaDeseosRequestDTO request) {
        try {
            ListaDeseos saved = listaDeseosService.save(request);
            HistoricoResponseDTO response = new HistoricoResponseDTO(
                    saved.getId(),
                    saved.getProducto().getId(),
                    saved.getProducto().getNombre(),
                    "AGREGADO",
                    saved.getFechaAgregado());
            return ResponseEntity.ok(new HttpGlobalResponse<>(200, "Producto agregado a la lista de deseos", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new HttpGlobalResponse<>(400, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpGlobalResponse<HistoricoResponseDTO>> update(@PathVariable Integer id,
            @RequestBody ListaDeseosRequestDTO request) {
        try {
            ListaDeseos updated = listaDeseosService.update(id, request);
            HistoricoResponseDTO response = new HistoricoResponseDTO(
                    updated.getId(),
                    updated.getProducto().getId(),
                    updated.getProducto().getNombre(),
                    "ACTUALIZADO",
                    updated.getFechaAgregado());
            return ResponseEntity.ok(new HttpGlobalResponse<>(200, "Lista de deseos actualizada", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new HttpGlobalResponse<>(400, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpGlobalResponse<Void>> delete(@PathVariable Integer id) {
        try {
            listaDeseosService.delete(id);
            return ResponseEntity.ok(new HttpGlobalResponse<>(200, "Producto eliminado de la lista de deseos", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new HttpGlobalResponse<>(400, e.getMessage(), null));
        }
    }
}