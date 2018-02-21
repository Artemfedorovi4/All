package model;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Handler;

public class SaxParser {

	private static ObservableList<Person> data = FXCollections.observableArrayList();

	public SaxParser(File file) {

		try {
			Handler handler = new Handler();
			data = handler.getDataList();
			SAXParserFactory parserF = SAXParserFactory.newInstance();
			SAXParser saxParser = parserF.newSAXParser();
			saxParser.parse(file, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Person> getData() {
		return data;
	}

}
