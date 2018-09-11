package davidcui.com.FetchOprsReport;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class WebClient {
	CloseableHttpClient httpclient = HttpClients.createDefault();
	ConnectionState state = ConnectionState.IDLE;
	String urlString;
	
	public WebClient(){
		
	}
	
	public int connect(String url){
		urlString = url;
		HttpGet httpget = new HttpGet(urlString);
		CloseableHttpResponse response;
		try {
			response = httpclient.execute(httpget);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpStatus.SC_BAD_REQUEST;
		} 
		
		
		int status = HttpStatus.SC_NOT_FOUND;
		try{
			status = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			EntityUtils.consume(entity);
			state = ConnectionState.CONNECTED;
		} catch (IOException e) {
			e.printStackTrace();
			return HttpStatus.SC_BAD_REQUEST;
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return status;
	}
	
	public void disconnect(){
		try {
			httpclient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ConnectionState getState(){
		return state;
	}
}

enum ConnectionState{
	IDLE(0),
	CONNECTED(1);
	
	int state;
	
	private ConnectionState(int state){
		this.state = state;
	}
	
	@Override
	public String toString(){
		return this.name();
	}
}
