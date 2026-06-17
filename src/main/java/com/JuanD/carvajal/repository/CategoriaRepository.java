package com.JuanD.carvajal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JuanD.carvajal.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
