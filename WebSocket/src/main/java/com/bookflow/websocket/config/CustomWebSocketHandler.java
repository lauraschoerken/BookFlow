package com.bookflow.websocket.config;

import org.springframework.web.socket.WebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class CustomWebSocketHandler extends TextWebSocketHandler {
	

	  // Mapa para almacenar las sesiones y los usuarios
  private static final Map<WebSocketSession, String> userSessions = new HashMap<>();

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
      System.out.println("Mensaje recibido: " + message.getPayload());


      System.out.println(userSessions);
      // Verificar si la sesión ya está registrada (si ya ha iniciado sesión)
      if (userSessions.containsKey(session)) {
    	    String username = userSessions.get(session);
    	    System.out.println("El nombre de usuario es: " + username);
      }
      
      if (message.getPayload().startsWith("{")) {
          // Procesar JSON de login
          String username = message.getPayload().replace("{\"type\":\"LOGIN\",\"username\":\"", "").replace("\"}", "");
          userSessions.put(session, username);
          System.out.println("Usuario identificado: " + username);
          // Enviar bienvenida al usuario
          session.sendMessage(new TextMessage("Bienvenido, " + username));
          return;
      } else {
          // Si no es un mensaje de login, se maneja como un mensaje de usuario
          String user = userSessions.get(session);
          session.sendMessage(new TextMessage("Hola " + (user != null ? user : "Usuario") + ", recibí tu mensaje: " + message.getPayload()));
      }
	    String username = userSessions.get(session);

	    if("Juan".equals(username)) {
	        session.sendMessage(new TextMessage("Juan estas bloqueado"));
	        return;
	    }
      // Mensaje de vuelta (echo)
      session.sendMessage(new TextMessage("Mensaje de vuelta: " + message.getPayload() + " enviado por " +username));
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      System.out.println("Conexión establecida: " + session.getId());
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus closeStatus) throws Exception {
      System.out.println("Conexión cerrada: " + session.getId());
      userSessions.remove(session); // Eliminar la sesión de usuario cuando se cierra la conexión
  }

  public void sendMessageToAllClients(String message) throws IOException {
	    for (Map.Entry<WebSocketSession, String> entry : userSessions.entrySet()) {
	        WebSocketSession session = entry.getKey();
	        String username = entry.getValue();

	        if (session.isOpen()) {
	            String personalizedMessage;

	            if ("Juan".equals(username)) {
	                personalizedMessage = "Hola Juan, este es un mensaje especial para ti.";
	            } else {
	                personalizedMessage = "Hola " + username + ", este es un mensaje general.";
	            }

	            session.sendMessage(new TextMessage(personalizedMessage));
	        }
	    }
	}


}
