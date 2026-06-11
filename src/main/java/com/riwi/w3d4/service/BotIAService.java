package com.riwi.w3d4.service;

import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.w3d4.model.Libro;
import com.riwi.w3d4.model.Mensaje;

@Service
public class BotIAService {

    private final ChatClient chatClient;

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private LibroService libroService;

    public BotIAService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public Mensaje generarRespuestaIA(String preguntaUsuario) {
        // 1. Construir catálogo de libros como contexto
        String catalogoLibros = libroService.obtenerTodos()
            .stream()
            .map(l -> String.format("- \"%s\" de %s (ISBN: %s, Año: %d)",
                    l.getTitulo(), l.getAutor(), l.getIsbn(), l.getAnioPublicacion()))
            .collect(Collectors.joining("\n"));

        // 2. Obtener historial de MongoDB como contexto adicional
        String historialChat = mensajeService.obtenerHistorial()
            .stream()
            .map(m -> m.getRemitente() + ": " + m.getContenido())
            .collect(Collectors.joining("\n"));

        // 3. Construir prompt con catálogo + historial + pregunta
        String prompt = "Eres LibroBot, el asistente inteligente de la Biblioteca Digital.\n\n"
                      + "El catálogo actual de libros disponibles es:\n"
                      + catalogoLibros + "\n\n"
                      + "Historial reciente del chat:\n"
                      + historialChat + "\n\n"
                      + "Responde de forma amable y útil a: " + preguntaUsuario
                      + "\n\nSi preguntan por libros disponibles, usa el catálogo de arriba. "
                      + "Si preguntan por un libro específico, indica si está o no en el catálogo.";

        // 4. Llamar a Spring AI
        String respuestaTexto = chatClient.prompt().user(prompt).call().content();

        // 5. Guardar y retornar el mensaje del Bot
        Mensaje mensajeBot = new Mensaje();
        mensajeBot.setRemitente("LibroBot IA");
        mensajeBot.setContenido(respuestaTexto);
        return mensajeService.guardarMensaje(mensajeBot);
    }
}
