package com.personalproject.inventorymanagementsystem.configuration;
import com.personalproject.inventorymanagementsystem.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;
@Component
public class StockUpdateHandler extends TextWebSocketHandler {

    private final ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Save the session when a new client connects
        sessionMap.put(session.getId(), session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Remove the session when the connection is closed
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }

    public void sendStockUpdate(String productName, int stockQuantity) {
        // Logic to send real-time stock update to all connected WebSocket clients.
        String updateMessage = "Product: " + productName + ", Stock: " + stockQuantity;
        TextMessage stockUpdateMessage = new TextMessage(updateMessage);

        // Send the message to all connected sessions
        for (WebSocketSession session : sessionMap.values()) {
            try {
                session.sendMessage(stockUpdateMessage); // Send to each session
            } catch (Exception e) {
                e.printStackTrace(); // Handle exceptions
            }
        }
    }
}

