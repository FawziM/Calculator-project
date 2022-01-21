package com.example.calculatorproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

    private Double number1;
    private String operator="";
    private boolean start =true;

    @FXML
    private Text output;


    @FXML
    private void processNumPad(ActionEvent event) {
        if (start) {
            output.setText("");
            start=false;
        }

        String value =((Button) event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    private void clearOutput(ActionEvent event) {
        output.setText("0");
        operator="";
        start=true;

    }

    @FXML
    private void processOperator(ActionEvent event) {
        if (output.getText().equals("ERROR")){
            output.setText("");
            start=false;
            return ;
        }
        String value =((Button) event.getSource()).getText();
        if (!value.equals("=")){
            if (!operator.isEmpty()) {
                return;
            }
            operator=value;
            number1=Double.parseDouble(output.getText());
            output.setText("");
        } else {
            if (operator.isEmpty()){
                return;
            }
            if (output.getText().isEmpty()){
                output.setText("ERROR");
                operator="";
                start=true;
                return;

            }
            output.setText(calculate(number1, Double.parseDouble(output.getText()),operator));
            operator="";

        }

    }

    private String calculate(Double num1, Double num2, String op) {

        switch (op) {
            case "+":
                return String.valueOf( (num1 + num2));

            case "-":
                return String.valueOf((num1 - num2));

            case "*":
                return String.valueOf( (num1 * num2));

            case "/":
                if(num2==0) {
                    return "ERROR";
                }
                return String.valueOf((num1 / num2));
        }
        return "ERROR";
    }
}