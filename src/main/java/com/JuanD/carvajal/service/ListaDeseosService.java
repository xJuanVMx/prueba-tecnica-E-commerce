package com.JuanD.carvajal.service;

import com.JuanD.carvajal.dto.ListaDeseosRequestDTO;
import com.JuanD.carvajal.dto.ListaDeseosResponseDTO;
import com.JuanD.carvajal.entity.Historico;
import com.JuanD.carvajal.entity.ListaDeseos;
import com.JuanD.carvajal.entity.Producto;
import com.JuanD.carvajal.repository.HistoricoRepository;
import com.JuanD.carvajal.repository.ListaDeseosRepository;
import com.JuanD.carvajal.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListaDeseosService {

    private final ListaDeseosRepository listaDeseosRepository;
    private final ProductoRepository productoRepository;
    private final HistoricoRepository historicoRepository;

    public List<ListaDeseosResponseDTO> getAll(Integer usuarioId) {
        List<ListaDeseos> list = listaDeseosRepository.findByUsuarioId(usuarioId);

        return list.stream().map(item -> {
            Producto p = item.getProducto();
            boolean outOfStock = p.getCantidadStock() <= 0;
            return new ListaDeseosResponseDTO(
                    item.getId(),
                    p.getId(),
                    p.getNombre(),
                    p.getPrecio(),
                    item.getCantidad(),
                    p.getCantidadStock(),
                    outOfStock);
        }).collect(Collectors.toList());
    }

    public ListaDeseos save(ListaDeseosRequestDTO request) {
        Producto product = productoRepository.findById(request.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + request.getProductoId()));

        ListaDeseos item = new ListaDeseos();
        item.setUsuarioId(request.getUsuarioId());
        item.setProducto(product);
        item.setCantidad(request.getCantidad());

        ListaDeseos saved = listaDeseosRepository.save(item);
        saveHistory(request.getUsuarioId(), product, "AGREGADO");

        return saved;
    }

    public ListaDeseos update(Integer id, ListaDeseosRequestDTO request) {
        ListaDeseos item = listaDeseosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado: " + id));

        item.setCantidad(request.getCantidad());
        ListaDeseos updated = listaDeseosRepository.save(item);
        saveHistory(item.getUsuarioId(), item.getProducto(), "ACTUALIZADO");

        return updated;
    }

    public void delete(Integer id) {
        ListaDeseos item = listaDeseosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado: " + id));

        saveHistory(item.getUsuarioId(), item.getProducto(), "ELIMINADO");
        listaDeseosRepository.deleteById(id);
    }

    private void saveHistory(Integer usuarioId, Producto product, String action) {
        Historico history = new Historico();
        history.setUsuarioId(usuarioId);
        history.setProductoId(product.getId());
        history.setNombreProducto(product.getNombre());
        history.setAccion(action);
        historicoRepository.save(history);
    }
}