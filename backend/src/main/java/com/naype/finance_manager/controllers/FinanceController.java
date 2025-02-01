package com.naype.finance_manager.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('USER')")
    public Map<String, String> getDashboard() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Bienvenido al Dashboard Financiero");
        return response;
    }
}
