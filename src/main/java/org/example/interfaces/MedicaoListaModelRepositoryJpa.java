package org.example.interfaces;

import org.example.models.MedicaoListaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MedicaoListaModelRepositoryJpa extends JpaRepository<MedicaoListaModel, Integer> {

    @Query("SELECT m.resultado FROM MedicaoListaModel m ORDER BY m.id DESC")
    public List<Double> findUltimoResultadoList();

}