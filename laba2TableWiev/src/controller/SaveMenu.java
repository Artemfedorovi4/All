package controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DataStore;
import view.AlertMes;

public class SaveMenu {

	public static void saveDoc(Stage stage, DataStore data) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();

		Document document = documentBuilder.newDocument();

		AlertMes gg;
		Element personEl = document.createElement("Person");
		document.appendChild(personEl);
		for (int i = 0; i < data.getDataList().size(); i++) {
			Element person2El = document.createElement("person");
			personEl.appendChild(person2El);

			Attr attrPerson = document.createAttribute("id");
			attrPerson.setValue(i + "");
			person2El.setAttributeNode(attrPerson);
			System.out.println(data.getDataList().get(i).getFullName());
			Element lNameEl = document.createElement("lastName");
			person2El.appendChild(lNameEl);
			lNameEl.appendChild(document.createTextNode(data.getDataList().get(i).getLastName()));

			Element fNameEl = document.createElement("firstName");
			person2El.appendChild(fNameEl);
			fNameEl.appendChild(document.createTextNode(data.getDataList().get(i).getFirstName()));

			Element patrEl = document.createElement("patronymic");
			person2El.appendChild(patrEl);

			patrEl.appendChild(document.createTextNode(data.getDataList().get(i).getPatronymic()));

			Element charactEl = document.createElement("characteristic");
			person2El.appendChild(charactEl);

			Element compositEl = document.createElement("compoSition");
			charactEl.appendChild(compositEl);
			compositEl.appendChild(document.createTextNode(data.getDataList().get(i).getCompoSition()));

			Element positEl = document.createElement("positKol");
			charactEl.appendChild(positEl);
			positEl.appendChild(document.createTextNode(data.getDataList().get(i).getPositKol()));

			Element kindEl = document.createElement("kindOfSportKol");
			charactEl.appendChild(kindEl);
			kindEl.appendChild(document.createTextNode(data.getDataList().get(i).getKindOfSportKol()));

			Element spTUEl = document.createElement("sportTitleUp");
			charactEl.appendChild(spTUEl);
			spTUEl.appendChild(document.createTextNode("" + data.getDataList().get(i).getSportTitleUp()));

			Element spTDEl = document.createElement("sportTitleDown");
			charactEl.appendChild(spTDEl);
			spTDEl.appendChild(document.createTextNode("" + data.getDataList().get(i).getSportTitleDown()));

		}
		TransformerFactory factoryTr = TransformerFactory.newInstance();
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save XML file");
			// fileChooser.setInitialDirectory(new
			// File(System.getProperty("E:\\Programs\\javawork\\laba2TableWiev")));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML file", "*.xml"));
			File file = fileChooser.showSaveDialog(stage);

			if (file != null) {
				Transformer transformer = factoryTr.newTransformer();
				DOMSource domSourse = new DOMSource(document);
				StreamResult streamFile = new StreamResult(file);
				transformer.transform(domSourse, streamFile);
			} else
				gg = new AlertMes("Ne robit");

		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
