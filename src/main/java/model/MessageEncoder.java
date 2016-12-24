package model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(Message message) throws EncodeException {
		// TODO Auto-generated method stub
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("from", message.getFrom())
				.add("lggin", message.getLggin())
				.add("message", message.getMessage()).build();
		return jsonObject.toString();
	}

	
}
