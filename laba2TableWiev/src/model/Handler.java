package model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Handler extends DefaultHandler {

	Person person;
	private final ObservableList<Person> data = FXCollections.observableArrayList();
	private String element;
	private String fio = "";

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Start parsing");

	}

	@Override
	public void endDocument() throws SAXException {
		//System.out.println(fio);
		System.out.println("End parsing");
	}

	@Override
	public void startElement(String namespace, String localName, String qName, Attributes attr) {
		element = qName;
		//System.out.println(qName);
	}

	public void endElement(String namespace, String localName, String qName) throws SAXException {
		element = "";
	}

	@Override
	public void characters(char[] ch, int start, int end) {
		String str = new String(ch, start, end);
		//System.out.println(str);
		if (element.equals("firstName")) {
			person.setFirstName(str);
			fio += str;
		}
		if (element.equals("lastName")) {
			person = new Person();
			person.setLastName(str);
			fio += str;
		}
		if (element.equals("patronymic")) {
			person.setPatronymic(str);
			fio += str;
			person.setFullName(fio);
			fio = "";
		}
		if (element.equals("compoSition")) {
			person.setCompoSition(str);
		}
		if (element.equals("positKol")) {
			person.setPositKol(str);
		}
		if (element.equals("kindOfSportKol")) {
			person.setKindOfSportKol(str);
		}
		if (element.equals("sportTitleUp")) {
			person.setSportTitleUp(new Integer(str));
		}
		if (element.equals("sportTitleDown")) {
			person.setSportTitleDown(new Integer(str));
			data.add(person);
		}
	}

	public ObservableList<Person> getDataList() {

		return data;
	}

}
