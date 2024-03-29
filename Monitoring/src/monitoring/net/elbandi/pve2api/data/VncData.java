package net.elbandi.pve2api.data;

import org.json.JSONException;
import org.json.JSONObject;

public class VncData {
	private String cert;
	private int port;
	private String ticket;
	private String upid;
	private String user;

	public VncData(JSONObject data) throws JSONException {
		cert = data.getString("cert");
		port = data.getInt("port");
		ticket = data.getString("ticket");
		upid = data.getString("upid");
		user = data.getString("user");
	}

	public String getCert() {
		return cert;
	}

	public int getPort() {
		return port;
	}

	public String getTicket() {
		return ticket;
	}

	public String getUpid() {
		return upid;
	}

	public String getUser() {
		return user;
	}
        
        @Override
        public String toString(){
            return "cert="+cert+"\n"+"port="+port+"\n"+"ticket"+ticket+"\n"+"upid"+upid+"\n"+"user="+user;
        }
}
