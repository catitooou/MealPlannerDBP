package com.example.proyecto_mealplanner.repository;

import com.example.proyecto_mealplanner.entity.ListaCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ListaCompraItemRepository extends JpaRepository<ListaCompra.ListaCompraItem, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM ListaCompraItem i WHERE i.lista.idLista = ?1")
    void deleteByListaId(Long listaId);
}
