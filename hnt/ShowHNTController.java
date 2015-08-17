 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnt;

import hnt.control.Logic;
import hnt.model.Pan;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author yang
 */
public class ShowHNTController implements Initializable {

    Logic l = new Logic();
    Stack<Pan> source = new Stack<>();
    Stack<Pan> target = new Stack<>();

    private int capcity = 3;

    @FXML
    TextField tfcapcity = new TextField("" + getCapcity());

    @FXML
    public void handleTextChange(final ActionEvent event) {

        TextField tmptf = (TextField) event.getSource();
        //System.out.println("----------------- "+tmptf.getText());
        setCapcity(Integer.parseInt(tmptf.getText()));
        refreshListView();
    }

    public void refreshListView() {

        Stack<Pan> pole = new Stack();
        l.setNewPole(pole, getCapcity());

        obsA = translate2SomeSymbol(pole);
        poleA.setItems(obsA);

        selecteditem.add("A");
        obsB.clear();
        obsC.clear();
        mapAear.put("obsA", obsA);
        mapAear.put("obsB", obsB);
        mapAear.put("obsC", obsC);

        poleA.setItems(mapAear.get("obsA"));
        poleB.setItems(mapAear.get("obsB"));
        poleC.setItems(mapAear.get("obsC"));

        //clear status
        selecteditem = new ArrayList<>();
        source.removeAllElements();
        target.removeAllElements();

        System.out.println(" come to refreshListView()");
    }

    List<String> selecteditem = new ArrayList<>();

    int countMoveTimes = 0;
    @FXML
    Label MoveTimes = new Label("MoveTimes :" + countMoveTimes);

    @FXML
    Button A;

    @FXML
    Button B;

    @FXML
    Button C;

    static ObservableList<Button> obsA = FXCollections.observableArrayList();

    @FXML
    ListView<Button> poleA = new ListView<>(obsA);

    static ObservableList<Button> obsB = FXCollections.observableArrayList();

    @FXML
    ListView<Button> poleB = new ListView<>(obsB);

    static ObservableList<Button> obsC = FXCollections.observableArrayList();

    @FXML
    ListView<Button> poleC = new ListView<>(obsC);

    //  String Aear[]={"obsA","obsB","obsC"};   
    static Map<String, ObservableList> mapAear = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    //  String Aear[]={"obsA","obsB","obsC"};
        //MoveTimes = new Label("MoveTimes");
        Stack<Pan> pole = new Stack();
        l.setNewPole(pole, getCapcity());

        obsA = translate2SomeSymbol(pole);
        poleA.setItems(obsA);

        selecteditem.add("A");

        mapAear.put("obsA", obsA);
        mapAear.put("obsB", obsB);
        mapAear.put("obsC", obsC);
        System.out.println(" come to ShowHNTController");
    }

    //to button
    private ObservableList translate2SomeSymbol(Stack<Pan> pole) {
        ObservableList<Button> obs = FXCollections.observableArrayList();
        int pdelay=pole.size();
        for (Pan pole1 : pole) {
            Button bt = new Button();
            bt.setText(pole1.getPtag());
            bt.setRotate(180);
            bt.setMaxWidth(pole1.getPsize());
            bt.setTranslateX(30);
            obs.add(bt);
        }

        return obs;
    }

    // to SomeSymbol
    private Stack<Pan> retranslate2SomeSymbol(ObservableList<Button> bts) {
        // ObservableList<Button> obs=FXCollections.observableArrayList();
        Stack<Pan> pole = new Stack<>();
        for (Button bt : bts) {
            Pan p = new Pan(bt.getText(), bt.getWidth());
            pole.add(p);
        }
        return pole;
    }

    String buttoncolor = "-fx-base: #b6e7c9;";

    public void ChangeColor(String which, String colorstr) {
       // String  buttoncolor="-fx-base: #b6e7c9;";
        for (String key : mapAear.keySet()) {
            if (key.contains(which) && (mapAear.get(key).size() > 0)) {
                Button bt = (Button) mapAear.get(key).get(mapAear.get(key).size() - 1);
                bt.setStyle(colorstr);
            }
        }

    }

    @FXML
    public void handleClick(final ActionEvent event) {

        Button bt = (Button) event.getSource();
        //System.out.println("  from which  "+bt.getText()+" width "+bt.getWidth());

        if (selecteditem.isEmpty()) {
            selecteditem.add(bt.getText());
            ChangeColor(bt.getText(), buttoncolor);
            return;
        } else {
            if (selecteditem.size() >= 2) {
                selecteditem.remove(0);
                selecteditem.add(bt.getText());
                ChangeColor(bt.getText(), buttoncolor);
            } else {
                selecteditem.add(bt.getText());
                ChangeColor(bt.getText(), buttoncolor);
            }
        }

        // move action 
        if ((selecteditem.size() > 1) && (!selecteditem.get(0).equals("")) && (!selecteditem.get(0).equals(selecteditem.get(1)))) {

            // System.out.println("  selected name  " +obsA.getClass().getName());
            for (String key : mapAear.keySet()) {
                if (key.contains(selecteditem.get(0))) {
                    source = retranslate2SomeSymbol(mapAear.get(key));
                }
                if (key.contains(selecteditem.get(1))) {
                    target = retranslate2SomeSymbol(mapAear.get(key));
                }
            }

            ////===============
            l.MovePan(source, target);

            for (String key : mapAear.keySet()) {
                if (key.contains(selecteditem.get(0))) {
                    mapAear.replace(key, translate2SomeSymbol(source));
                }
                if (key.contains(selecteditem.get(1))) {
                    mapAear.replace(key, translate2SomeSymbol(target));
                }
            }
            ChangeColor(bt.getText(), "");
            poleA.setItems(mapAear.get("obsA"));
            poleB.setItems(mapAear.get("obsB"));
            poleC.setItems(mapAear.get("obsC"));

            //MoveTimes =new Label("MoveTimes");
            countMoveTimes += 1;
            MoveTimes.setText("MoveTimes:" + countMoveTimes);

            //clear status
            selecteditem = new ArrayList<>();
            source.removeAllElements();
            target.removeAllElements();
        }

    }

    /**
     * @return the capcity
     */
    public int getCapcity() {
        return capcity;
    }

    /**
     * @param capcity the capcity to set
     */
    public void setCapcity(int capcity) {
        this.capcity = capcity;
    }

}
