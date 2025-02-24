package com.naype.finance_manager.repositories;

import com.naype.finance_manager.models.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    List<Gasto> findByUsuarioId(Long usuarioId);
}
