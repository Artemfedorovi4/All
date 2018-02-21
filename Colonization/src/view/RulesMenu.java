package view;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RulesMenu extends AnchorPane {

	final private static String rules = "Для игры используются фишки двух цветов — красные и синие. Один игрок ходит красными, другой синими. Изначально в угловые поля выкладываются по фишке каждого цвета. Ход игрока заключается в следующем. Игрок выбирает на доске фишку своего цвета и пустое поле, в которое он ходит (оба поля указываются с помощью клика мыши). Это поле должно быть расположено не далее чем через одно поле от выбранной фишки. Игрок может либо \"клонировать(удвоить)\" фишку — положить новую фишку на соседнюю клетку, которое граничит с выбранной фишкой, либо переместить выбранную фишку в клетку, которая расположена через одно поле. Фишки противника, которые граничат с полем, в которое был сделан ход, захватываются — меняют свой цвет на цвет фишек игрока. Игра заканчивается, когда очередной игрок не может сделать ход. Побеждает игрок, фишек которого на доске больше. В случае равного количества фишек признается ничья.";
	public Button back = new Button("Назад");

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
