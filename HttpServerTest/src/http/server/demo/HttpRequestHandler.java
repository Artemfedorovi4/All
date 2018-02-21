package http.server.demo;

import java.io.IOException;
import java.io.InputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HttpRequestHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(arg0.getRequestMethod() + "\r\n");
		
		InputStream in = arg0.getRequestBody();
		
		int iAvailable = in.available();
		while(iAvailable > 0){
			byte[] btBuffer = new byte[iAvailable];
			int iBytesRead = in.read(btBuffer, 0, btBuffer.length);
			System.out.println(new String(btBuffer) + "\r\n");
		}
		System.out.println();	
	}
}
