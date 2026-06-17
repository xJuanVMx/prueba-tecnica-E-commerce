package com.JuanD.carvajal.repository;

import com.JuanD.carvajal.entity.ListaDeseos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaDeseosRepository extends JpaRepository<ListaDeseos, Integer> {

    List<ListaDeseos> findByUsuarioId(Integer usuarioId);
}