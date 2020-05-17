package view;

import controller.ProgramManager;

public class ShowDiscountCodeView {

    public void giveOutput(String message){
        System.out.println(message);
    }
    public ShowDiscountCodeView() {
        System.out.println("=== Login/register menu");
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("back")) {
                return command;
            }
            else if (command.matches("view discount code \\.+")) {
                return command;
            }
            else if (command.matches("edit discount code \\.+")) {
                return command;
            }
            else if (command.matches("remove discount code \\.+")) {
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
}
