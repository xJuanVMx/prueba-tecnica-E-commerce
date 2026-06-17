package com.JuanD.carvajal.repository;

import com.JuanD.carvajal.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}