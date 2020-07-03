package client.view;

import server.controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import server.model.product.DiscountCode;

import java.io.FileInputStream;
import java.util.Scanner;

public class CreateDiscountCodeView extends Application {
    public CreateDiscountCodeView() {
        System.out.println("=== Create DiscountCode menu");
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.startsWith("create discount code")) {
                return command;
            } else if (command.equals("help")) {
                showHelp();
            }
            if (command.equals("back")) {
                return command;
            } else {
                giveOutput("invalid command!");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\tcreate discount code [discountCodeInfo]\n\t");
    }

    public String[] getUserUsualData(DiscountCode discountCode) {
        String[] discountCodeInfo = new String[4];
        System.out.println("Enter the discountCode:");
        discountCodeInfo[0] = Input.getInput();
        System.out.println("Enter the startDate:");
        discountCodeInfo[1] = Input.getInput();
        System.out.println("Enter the endDate:");
        discountCodeInfo[2] = Input.getInput();
        System.out.println("Enter the percentage:");
        discountCodeInfo[3] = Input.getInput();
        System.out.println("Enter the repetition time:");
        discountCodeInfo[4] = Input.getInput();
        System.out.println("Enter the username of Users included in discount code: (when finished enter 'end')");
        while (!Input.getInput().equalsIgnoreCase("end")) {
            String user = Input.getInput();
            discountCode.addToArrayList(ProgramManager.getProgramManagerInstance().getAccountByUsername(user));
        }
        return discountCodeInfo;
    }

    public void giveOutput(String message) {
        System.out.println(message);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //TODO

        VBox vBox = new VBox(9);
        stage.setTitle("Create discount code");
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/client/view/pictures/icon.png")));


        Label codeLabel = new Label();
        codeLabel.setText("Code : ");
        Label startDateLabel = new Label();
        startDateLabel.setText("Start date : ");
        Label endDateLabel = new Label();
        endDateLabel.setText("End date : ");
        Label percentageLabel = new Label();
        percentageLabel.setText("Percentage : ");
        Label repetitionTimeLabel = new Label();
        repetitionTimeLabel.setText("Repetition time : ");
        Label usersIncluded = new Label();
        usersIncluded.setWrapText(true);
        usersIncluded.setText("Enter the username of Users included in discount code: (each user in one line)");

        Label label1 = new Label("Please fill this field");
        Label label2 = new Label("Please fill this field");
        Label label3 = new Label("Please fill this field");
        Label label4 = new Label("Please fill this field");
        Label label5 = new Label("Please fill this field");
        Label label6 = new Label("Please fill this field");

        label1.setTextFill(Color.RED);
        label2.setTextFill(Color.RED);
        label3.setTextFill(Color.RED);
        label4.setTextFill(Color.RED);
        label5.setTextFill(Color.RED);
        label6.setTextFill(Color.RED);

        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);

        label1.setFont(new Font("Arial",12));
        label2.setFont(new Font("Arial",12));
        label3.setFont(new Font("Arial",12));
        label4.setFont(new Font("Arial",12));
        label5.setFont(new Font("Arial",12));
        label6.setFont(new Font("Arial",12));


        TextField codeTextField = new TextField();
        TextField startDateTextField = new TextField();
        TextField endDateTextField = new TextField();
        TextField percentageTextField = new TextField();
        TextField repetitionTimeTextField = new TextField();
        TextArea usersIncludedTextArea = new TextArea();

        codeLabel.setWrapText(true);
        Button create = new Button("Create ");
        Button back = new Button("Back ");

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(codeLabel,label1, codeTextField, startDateLabel,label2, startDateTextField,
                endDateLabel,label3, endDateTextField, percentageLabel,label4, percentageTextField,
                repetitionTimeLabel,label5, repetitionTimeTextField,
                usersIncluded,label6, usersIncludedTextArea, create, back);

        Scene scene = new Scene(vBox, 450, 700);
        vBox.setAlignment(Pos.CENTER);
        stage.setScene(scene);
        stage.show();



        create.setOnAction(actionEvent -> {

            //analyze data:
            //TODO
            // check date format

            label1.setVisible(codeTextField.getText().equals(""));
            label2.setVisible(startDateTextField.getText().equals(""));
            label3.setVisible(endDateTextField.getText().equals(""));
            label4.setVisible(percentageTextField.getText().equals(""));
            label5.setVisible(repetitionTimeTextField.getText().equals(""));
            label6.setVisible(usersIncludedTextArea.getText().equals(""));

            if(!(codeTextField.getText().equals("")||startDateTextField.getText().equals("")||
                    endDateTextField.getText().equals("")||percentageTextField.getText().equals("")||
                    repetitionTimeTextField.getText().equals("")||usersIncludedTextArea.getText().equals(""))){

                DiscountCode discountCode;
                ProgramManager instance = ProgramManager.getProgramManagerInstance();
                discountCode = new DiscountCode(codeTextField.getText(),
                        instance.parsingStringToDate(startDateTextField.getText()),
                        instance.parsingStringToDate(endDateTextField.getText()),
                        Integer.parseInt(percentageTextField.getText()),
                        Integer.parseInt(repetitionTimeTextField.getText()));
                ProgramManager.getProgramManagerInstance().addDiscountCodeToArrayList(discountCode);
                Scanner scanner = new Scanner(usersIncludedTextArea.getText());
                while (scanner.hasNextLine()) {
                    String user = scanner.nextLine();
                    discountCode.addToArrayList(ProgramManager.getProgramManagerInstance().getAccountByUsername(user));
                }
                try {
                    new Alert().showAlert("Discount Code created!","Ok",0,null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                codeTextField.setText("");
                startDateTextField.setText("");
                endDateTextField .setText("");
                percentageTextField.setText("");
                repetitionTimeTextField.setText("");
                usersIncludedTextArea.setText("");

            }

        });

        back.setOnAction(actionEvent -> {
            try {
                new PersonalInfoMenuView().start(new Stage());
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
