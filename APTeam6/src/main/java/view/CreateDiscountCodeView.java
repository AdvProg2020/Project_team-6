package view;

import controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.product.DiscountCode;

import java.io.FileInputStream;

public class CreateDiscountCodeView extends Application {
    public CreateDiscountCodeView(){
        System.out.println("=== Create DiscountCode menu");
    }

    public String getInputCommand(){
        String command;
        while (true) {
            command = Input.getInput();
            if(command.startsWith("create discount code"))
            {
                return command;
            }
            else if(command.equals("help")){
                showHelp();
            }
            if(command.equals("back")){
                return command;
            }
            else{
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
        while (!Input.getInput().equalsIgnoreCase("end")){
            String user = Input.getInput();
            discountCode.addToArrayList(ProgramManager.getProgramManagerInstance().getAccountByUsername(user));
        }
        return discountCodeInfo;
    }
    public void giveOutput(String message){
        System.out.println(message);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //TODO

        VBox vBox = new VBox();
        stage.setTitle("Create discount code");
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
        Scene scene = new Scene(vBox,300,600);
        vBox.setAlignment(Pos.CENTER);
        stage.setScene(scene);
        stage.show();


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
