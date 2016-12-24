package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import model.Message;
import model.MessageDecoder;
import model.MessageEncoder;

@ApplicationScoped
@ServerEndpoint(value="/chat", encoders={MessageEncoder.class}, decoders={MessageDecoder.class})
public class ChatServer {
	
	private static final Map<Session,String> sessions = new HashMap<>();
	
	@OnOpen
	public void open(Session session){
		System.out.println("Connection open");
	}
	
	@OnClose
	public void close(Session session){
		
		if (sessions.containsKey(session)){//if user is previously logged in
			Message message = new Message();
			message.setFrom(sessions.get(session));
			message.setMessage("LOGGED OUT");
			sessions.remove(session);//remove session from Map
			broadcastMessage(message);
			System.out.println("Session removed on close");
		}
		System.out.println("Connection closed");
	}
	
	@OnError
	public void onError(Throwable error){
		System.out.println("Connection error");
	}
	
	@OnMessage
	public void handleMessage(Message message, Session session){
		System.out.println("From: " + message.getFrom() + "---" + message.getMessage());
		
		if (message.getLggin().equals("lggin"))
			handleAuthentication(message, session); //process login
		
		broadcastMessage(message);
	}
	
	public void broadcastMessage(Message message){
		sessions.forEach((k,v) -> { //select each logged in user from Map
			k.getAsyncRemote().sendObject(message); //send message to all of them
		});
	}
	
	void handleAuthentication(Message message, Session session){
		
			if (!sessions.containsKey(session)){//if user not logged in
				System.out.println(message.getFrom() + " added to session");
				sessions.put(session, message.getFrom());//add new user session to Map storage
				message.setMessage("LOGGED IN");//set message to be broadcasted
			}
			/*else{
				message.setLggin("alggin");//user already loggedin
				session.getAsyncRemote().sendObject(message);
			}*/
		
	}

}
