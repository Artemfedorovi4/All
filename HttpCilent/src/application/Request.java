package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Request {

	private SimpleStringProperty method = new SimpleStringProperty("");
	private SimpleStringProperty url = new SimpleStringProperty("");
	private SimpleStringProperty fullUrl = new SimpleStringProperty("");
	private SimpleStringProperty requestTail = new SimpleStringProperty("");
	private SimpleIntegerProperty status = new SimpleIntegerProperty(0);
	private StringBuilder requestStr = new StringBuilder("");
	private SimpleStringProperty answer = new SimpleStringProperty("");

	public String getMethod() {
		return method.get();
	}

	public void setMethod(String method) {
		this.method.set(method);
	}

	public String getFullUrl() {
		return fullUrl.get();
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl.set(fullUrl);
	}

	public String getRequestTail() {
		return requestTail.get();
	}

	public void setRequestTail(String requestTail) {
		this.requestTail.set(requestTail);
	}

	public void setUrl(String url) {
		this.url.set(url);
	}

	public void setStatus(Integer status) {
		this.status.set(status);
	}

	public Request(String method, String fullUrl) {
		this.method = new SimpleStringProperty(method);
		this.fullUrl = new SimpleStringProperty(fullUrl);
		this.url = new SimpleStringProperty("");
		this.requestTail = new SimpleStringProperty("");
		this.status = new SimpleIntegerProperty(0);
		this.requestStr = new StringBuilder("");
		parseURL();
		getWWW();
		makeRequest();
	}

	public Request() {
		this.method = new SimpleStringProperty("");
		this.fullUrl = new SimpleStringProperty("");
		this.url = new SimpleStringProperty("");
		this.requestTail = new SimpleStringProperty("");
		this.status = new SimpleIntegerProperty(0);
		this.requestStr = new StringBuilder("");
	}

	public String getUrl() {
		return this.url.get();
	}

	public void setStatus(int status) {
		this.status.set(status);
	}

	public int getStatus() {
		return this.status.get();
	}

	private void getWWW() {
		if (this.fullUrl.get() != null) {
			System.out.println("url NOT NULL");
			int endURL = fullUrl.get().indexOf('/');
			if (endURL > 0) {
				// System.out.println("Get only url " + fullUrl.get().substring(0, endURL));
				url.set(fullUrl.get().substring(0, endURL));
				requestTail.set(fullUrl.get().substring(endURL, fullUrl.get().length()));
			} else {
				requestTail.set("/");
				url.set(fullUrl.get());
			}
		}
	}

	private void parseURL() {
		if (fullUrl.get() != null) {

			if (fullUrl.get().startsWith("http://")) {
				fullUrl.set(fullUrl.get().substring(7));
				int num = url.get().indexOf('/');
				if (num >= 0) {
					// System.out.println("requestTail " + requestTail);
					requestTail.set(fullUrl.get().substring(num, fullUrl.get().length()));
					fullUrl.set(fullUrl.get().substring(0, num));
				} else {
					// System.out.println("requestTail null " + requestTail);
					requestTail.set("/");
				}
			}
		}
	}

	public void changeRequest(String method, String fullUrl) {
		this.method = new SimpleStringProperty(method);
		this.fullUrl = new SimpleStringProperty(fullUrl);
		parseURL();
		getWWW();
		makeRequest();
	}

	public void setAnswer(String ans) {
		answer.set(ans);
	}

	public String getAnswer() {
		return answer.get();
	}

	public void replacerequest(String request) {
		requestStr = new StringBuilder(request);
	}

	public void parceRequest() {

	}

	public void makeRequest() {
		requestStr = new StringBuilder();
		switch (this.method.get()) {
		case "GET":
			requestStr = new StringBuilder("GET " + this.requestTail.get() + " HTTP/1.0\r\n" + "Host: " + this.url.get()
					+ "\r\n" + "User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:18.0) Gecko/20100101 Firefox/18.0\r\n"
					+ "Accept: */*");
			break;
		case "HEAD":
			requestStr = new StringBuilder(
					"HEAD " + this.requestTail.get() + " HTTP/1.0\r\n" + "Host: " + this.url.get() + "\r\n"
							+ "User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:18.0) Gecko/20100101 Firefox/18.0\r\n"
							+ "Accept: */*");
			break;

		case "POST":
			requestStr = new StringBuilder("POST " + this.fullUrl.get() + " HTTP/1.0\r\n" + "Host: " + this.url.get()
					+ "\r\n" + "User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:18.0) Gecko/20100101 Firefox/18.0\r\n"
					+ "Accept: */*");
			break;
		}
	}

	public void statusProc(String str) {
		String num = "";
		System.out.println("STER " + str);
		for (int i = 8; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				num += str.charAt(i);
			}
		}
		System.out.println("NUUUM " + num);
		setStatus(Integer.parseInt(num, 10));
	}

	public String getTextRequest() {
		return requestStr.toString();
	}

	public void setTextRequest(String str) {
		if (str != "") {
			System.out.println("STR REQUEST "+str);
			String[] buf = str.split("\n");
			System.out.println("STR BUFF"+buf[1].toString()+" BUFF[1] ");
			for (int j = 0; j < buf[0].length(); j++) {
				int count = 0;
				int first = 0;
				int last = 0;
				if (buf[0].charAt(j) == ' ') {
					this.method.set(buf[0]);
					count++;
					first = j;
				}

				if (count == 2) {
					this.fullUrl.set(buf[0].substring(first, j));
					break;
				}
			}
			for (int j = 0; j < buf[1].length(); j++) {
				if (buf[1].startsWith("Host:")) {
					if(!url.get().startsWith("www"))
						url.set("www."+url);
					url.set(buf[1].substring(5, buf[1].length()));
				}
			}

			this.requestStr = new StringBuilder(str);
		}
	}

}
