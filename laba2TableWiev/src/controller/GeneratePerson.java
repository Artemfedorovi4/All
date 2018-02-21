package controller;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import javafx.stage.Stage;
import model.DataStore;
import model.Person;

public class GeneratePerson {

	static DataStore data = new DataStore();
	static Person pers;
	static String[] mas = { "������", "����", "���������", "�������", "�������", "��������", "������", "�����", "����",
			"�������", "�������", "�����","������", "������", "�����", "�������", "��������", "�������", "�����", "��������",
			"���������", "������", "�������", "��������", "��������", "����", "������", "�������", "��������",
			"�������", "�����", "�������", "����", "�����", "������", "����", "�����", "�������", "������", "Ը���",
			"����", "������", "�����", "������", "����", "�������", "����" };

	static String[] mas2 = { "�������", "��������", "�������", "���������", "�������", "�����", "��������", "��������",
			"������", "��������", "�������", "��������", "��������", "������", "������", "�����", "������" };
	static String[] mas3 = { "���������", "����������", "���������", "����������", "��������", "�����������",
			"����������", "�����������", "����������", "���������", "�����������", "���������", "��������",
			"�����������" };
	static String[] mas4 = { "���������", "���������", "�������", "�������", "����", "������ ����", "��������",
			"�������", "�����", "������", "��������", "������", "��������", "������" };
	static String[] mas5 = { "��������", "����������", "�������", "������������" };
	static String[] mas6 = { "��������", "��������" };

	public static void generatePers() {

		ArrayList<String> masName = new ArrayList<String>();
		ArrayList<String> masSName = new ArrayList<String>();
		ArrayList<String> masLName = new ArrayList<String>();
		ArrayList<String> kindSp = new ArrayList<String>();
		ArrayList<String> posit = new ArrayList<String>();
		ArrayList<String> composit = new ArrayList<String>();

		for (int i = 0; i < mas.length; i++) {
			masName.add(mas[i]);
		}
		for (int i = 0; i < mas2.length; i++) {
			masSName.add(mas2[i]);
		}
		for (int i = 0; i < mas3.length; i++) {
			masLName.add(mas3[i]);
		}
		for (int i = 0; i < mas4.length; i++) {
			kindSp.add(mas4[i]);
		}
		for (int i = 0; i < mas5.length; i++) {
			posit.add(mas5[i]);
		}
		for (int i = 0; i < mas6.length; i++) {
			composit.add(mas6[i]);
		}
		for (int i = 0; i < 200; i++) {
			pers = new Person();
			Random dice = new Random();
			int number;
			number = dice.nextInt(masName.size());
			pers.setFirstName(masName.get(number));
			number = dice.nextInt(masSName.size());
			pers.setLastName(masSName.get(number));
			number = dice.nextInt(masLName.size());
			pers.setPatronymic(masLName.get(number));
			number = dice.nextInt(kindSp.size());
			pers.setKindOfSportKol(kindSp.get(number));
			number = dice.nextInt(posit.size());
			pers.setPositKol(posit.get(number));
			number = dice.nextInt(composit.size());
			pers.setCompoSition(composit.get(number));

			number = dice.nextInt(2);
			pers.setSportTitleUp(number);
			number = dice.nextInt(2) + 2;
			pers.setSportTitleDown(number);
			data.getDataList().add(pers);
		}
		Stage stage = new Stage();
		try {
			SaveMenu.saveDoc(stage, data);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}