package http.server.demo;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class HttpServerTest {

	private HttpServer httpServer = null;

	public boolean open(int iPort) {
		boolean bool = false;
		try {
			httpServer = HttpServer.create(new InetSocketAddress(iPort), 0);
			httpServer.createContext("/", new HttpRequestHandler());
			httpServer.setExecutor(null);
			httpServer.start();
			bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	public void stop() {
		if (httpServer != null) {
			httpServer.stop(20000);;
		}
	}
}
