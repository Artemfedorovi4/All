package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class HttpConnection {
	InetAddress addr = null;
	Socket socket = null;
	boolean autoflush = true;
	PrintWriter out = null;
	BufferedReader in = null;
	int port = 80;

	Request req;

	String[] s = null;
	String[] sA = null;
	StringBuilder sb = new StringBuilder(8096);

	public void connect() throws IOException {
		System.out.println("URL" + req.getUrl());
		this.addr = InetAddress.getByName(req.getUrl());
		this.socket = new Socket(this.addr, this.port);
		this.autoflush = true;
		this.out = new PrintWriter(socket.getOutputStream(), this.autoflush);
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		sendRequest();
		getAnswer();
	}

	public void prepareReq(String method, String url) {
		this.req = new Request(method, url);
	}

	public void setRequest(String req) {
		if (this.req == null)
			this.req = new Request();
		this.req.setTextRequest(req);
	}

	public OutputStream getOutStream() throws IOException {
		return this.socket.getOutputStream();
	}

	public InputStreamReader getInputStream() throws IOException {
		return new InputStreamReader(this.socket.getInputStream());
	}

	public void getAnswer() throws IOException {
		sb = new StringBuilder();
		out.println();
		boolean loop = true;
		while (loop) {
			if (in.ready()) {
				int i = 0;
				while (i != -1) {
					i = in.read();
					sb.append((char) i);
				}
				loop = false;
			}
		}
		req.setAnswer(sb.toString());
		sA = sb.toString().split("\r\n");
		req.statusProc(sA[0]);
	}

	public String getSB() {
		return sb.toString();
	}

	public String getTextRequest() {
		return req.getTextRequest();
	}

	public Request getRequest() {
		return req;
	}

	public void sendRequestPost(String str) {
		s = str.split("\r\n");
		for (int i = 0; i < s.length; i++)
			out.println(s[i]);
	}

	public void sendRequest() {
		s = req.getTextRequest().split("\r\n");
		for (int i = 0; i < s.length; i++)
			out.println(s[i]);
	}
}
