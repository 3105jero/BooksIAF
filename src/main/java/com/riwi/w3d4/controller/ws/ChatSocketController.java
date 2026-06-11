package com.riwi.w3d4.controller.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.riwi.w3d4.model.Mensaje;
import com.riwi.w3d4.service.BotIAService;
import com.riwi.w3d4.service.MensajeService;

@Controller
public class ChatSocketController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private BotIAService botIAService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/enviar")
    @SendTo("/tema/mensajes")
    public Mensaje procesarMensajeUsuario(Mensaje mensajeRecibido) {

        // 1. Guardar mensaje del usuario en MongoDB
        Mensaje mensajeGuardado = mensajeService.guardarMensaje(mensajeRecibido);

        // 2. IA responde en hilo separado para no bloquear el chat
        new Thread(() -> {
            Mensaje respuestaIA = botIAService.generarRespuestaIA(mensajeGuardado.getContenido());
            messagingTemplate.convertAndSend("/tema/mensajes", respuestaIA);
        }).start();

        return mensajeGuardado;
    }
}