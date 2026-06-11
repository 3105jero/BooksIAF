package com.riwi.w3d4.controller.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.riwi.w3d4.service.MensajeService;

@Controller
@RequestMapping("/chat")
public class ChatUIController {

    @Autowired
    private MensajeService mensajeService;

    @GetMapping
    public String mostrarChat(Model model) {
        model.addAttribute("historial", mensajeService.obtenerHistorial());
        return "chat/sala";
    }
}