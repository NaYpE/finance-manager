package com.naype.finance_manager.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.naype.finance_manager.repositories.UsuarioRepository;
import com.naype.finance_manager.services.GastoService;
import com.naype.finance_manager.services.IngresoService;
import com.naype.finance_manager.models.Gasto;
import com.naype.finance_manager.models.Ingreso;
import com.naype.finance_manager.models.Usuario;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/finance")
@PreAuthorize("hasRole('USER')") // Aplica a todos los endpoints
public class FinanceController {

    private final GastoService gastoService;
    private final IngresoService ingresoService;
    private final UsuarioRepository usuarioRepository;

    public FinanceController(
            GastoService gastoService,
            IngresoService ingresoService,
            UsuarioRepository usuarioRepository
    ) {
        this.gastoService = gastoService;
        this.ingresoService = ingresoService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/gastos")
    public ResponseEntity<Gasto> registrarGasto(
            @AuthenticationPrincipal Usuario usuario,
            @Valid @RequestBody Gasto gasto
    ) {
        gasto.setUsuario(getCurrentUser(usuario));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(gastoService.registrarGasto(gasto));
    }

    @PostMapping("/ingresos")
    public ResponseEntity<Ingreso> registrarIngreso(
            @AuthenticationPrincipal Usuario usuario,
            @Valid @RequestBody Ingreso ingreso
    ) {
        ingreso.setUsuario(getCurrentUser(usuario));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ingresoService.registrarIngreso(ingreso));
    }

    @GetMapping("/historial")
    public ResponseEntity<List<Gasto>> obtenerHistorial(
            @AuthenticationPrincipal Usuario usuario
    ) {
        return ResponseEntity.ok(
                gastoService.obtenerGastosPorUsuario(getCurrentUser(usuario).getId())
        );
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, String>> getDashboard() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Bienvenido al Dashboard Financiero");
        return ResponseEntity.ok(response);
    }

    private Usuario getCurrentUser(Usuario usuario) {
        return usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                ));
    }
}