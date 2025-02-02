package com.naype.finance_manager.services;

import com.naype.finance_manager.models.Gasto;
import com.naype.finance_manager.repositories.GastoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastoService {

    private final GastoRepository gastoRepository;

    public GastoService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    public Gasto registrarGasto(Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    public List<Gasto> obtenerGastosPorUsuario(Long usuarioId) {
        return gastoRepository.findByUsuarioId(usuarioId);
    }
}
