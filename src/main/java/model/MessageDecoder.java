package model;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Message decode(String message) throws DecodeException {
		
		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
		Message msg = new Message();
		msg.setFrom(jsonObject.getString("from"));
		msg.setMessage(jsonObject.getString("message"));
		msg.setLggin(jsonObject.getString("lggin"));
		return msg;
	}

	@Override
	public boolean willDecode(String message) {
		try {
			JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
			return true;
		} catch (Exception e) {
			//System.out.println("error in decode: " + e.getMessage());
			return false;
		}
	}

}
