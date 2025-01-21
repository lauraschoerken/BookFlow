package com.bookflow.websocket.config;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint("/ws")
public class WebSocketServer {
    private static final Map<Session, String> userSessions = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Nueva conexión: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Mensaje recibido: " + message);
        if (message.startsWith("{")) {
            // Procesar JSON de login
            String username = message.replace("{\"type\":\"LOGIN\",\"username\":\"", "").replace("\"}", "");
            userSessions.put(session, username);
            System.out.println("Usuario identificado: " + username);
            session.getBasicRemote().sendText("Bienvenido, " + username);
        } else {
            // Mensaje de usuario
            String user = userSessions.get(session);
            session.getBasicRemote().sendText("Hola " + (user != null ? user : "Usuario") + ", recibí tu mensaje: " + message);
        }
    }

    @OnClose
    public void onClose(Session session) {
        userSessions.remove(session);
        System.out.println("Conexión cerrada: " + session.getId());
    }
}


