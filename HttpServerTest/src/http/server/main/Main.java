package http.server.main;
	
import com.sun.net.httpserver.HttpServer;

import http.server.demo.HttpServerTest;

public class Main{
	
	public static void main(String[] args) {
		HttpServerTest http = new HttpServerTest();
		if(http.open(8080)) {
			System.out.println("Everything is OK. Server start on 8080 port");
		}
		else {
			System.out.println("Server start error");
		}
	}
}
