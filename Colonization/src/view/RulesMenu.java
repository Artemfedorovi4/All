package view;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RulesMenu extends AnchorPane {

	final private static String rules = "��� ���� ������������ ����� ���� ������ � ������� � �����. ���� ����� ����� ��������, ������ ������. ���������� � ������� ���� ������������� �� ����� ������� �����. ��� ������ ����������� � ���������. ����� �������� �� ����� ����� ������ ����� � ������ ����, � ������� �� ����� (��� ���� ����������� � ������� ����� ����). ��� ���� ������ ���� ����������� �� ����� ��� ����� ���� ���� �� ��������� �����. ����� ����� ���� \"�����������(�������)\" ����� � �������� ����� ����� �� �������� ������, ������� �������� � ��������� ������, ���� ����������� ��������� ����� � ������, ������� ����������� ����� ���� ����. ����� ����������, ������� �������� � �����, � ������� ��� ������ ���, ������������� � ������ ���� ���� �� ���� ����� ������. ���� �������������, ����� ��������� ����� �� ����� ������� ���. ��������� �����, ����� �������� �� ����� ������. � ������ ������� ���������� ����� ���������� �����.";
	public Button back = new Button("�����");

	public RulesMenu(BoxMenu box, int height, int width) {
		VBox vbox = new VBox();
		setVisible(false);
		TextArea rul = new TextArea(rules);
		rul.setMaxSize(height + 100, width + 300);
		rul.setWrapText(true);
		rul.setEditable(false);

		rul.setFont(Font.font("Cambria", 24));
		vbox.getChildren().addAll(rul, back);
		getChildren().addAll(vbox);
		back.setOnMouseClicked(e -> {
			box.setVisible(true);
			setVisible(false);
		});
	}
}
