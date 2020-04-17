package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private TextField inputField;

    @FXML
    private Button btn;

    @FXML
    private Text outputText;

    public void initialize() {
        btn.setOnMouseClicked((event -> {
            String[] s = inputField.getText().split(" ");
            MyLinkedList<Integer> linkedList = new MyLinkedList<>();
            for (String item : s) {
                linkedList.addLast(Integer.parseInt(item));
            }
            linkedList = linkedList.process();
            StringBuilder textStr = new StringBuilder();
            for (int i = 0; i < linkedList.size(); i++) {
                try {
                    textStr.append(linkedList.getValue(i));
                    if (i != linkedList.size() - 1)
                        textStr.append(", ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            outputText.setText(textStr.toString());
        }));
    }
}
