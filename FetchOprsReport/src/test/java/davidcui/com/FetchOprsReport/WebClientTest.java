package davidcui.com.FetchOprsReport;

import junit.framework.TestCase;

public class WebClientTest extends TestCase {

	public void testConnect() {
		WebClient webClient = new WebClient();
		int status = webClient.connect("http://www.google.com");
		System.out.println("WebClient.connect() returns " + status + ", connection state="+webClient.getState());
		assertTrue(webClient.getState()==ConnectionState.CONNECTED);
	}

}
