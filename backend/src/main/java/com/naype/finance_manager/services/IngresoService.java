package com.naype.finance_manager.services;

import com.naype.finance_manager.models.Ingreso;
import com.naype.finance_manager.repositories.IngresoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngresoService {

    private final IngresoRepository ingresoRepository;

    public IngresoService(IngresoRepository ingresoRepository) {
        this.ingresoRepository = ingresoRepository;
    }

    public Ingreso registrarIngreso(Ingreso ingreso) {
        return ingresoRepository.save(ingreso);
    }

    public List<Ingreso> obtenerIngresosPorUsuario(Long usuarioId) {
        return ingresoRepository.findByUsuarioId(usuarioId);
    }
}
