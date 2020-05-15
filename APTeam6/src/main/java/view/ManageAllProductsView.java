package view;

public class ManageAllProductsView {
    public ManageAllProductsView(){
        System.out.println("=== ManageAllProducts menu");
    }
    public String getInputCommand(){
        String command;
        while (true) {
            command = Input.getInput();
            if(command.matches("remove \\.+")){
                return command;
            }
            else if(command.equals("back")){
                return command;
            }
            else {
                giveOutput("Invalid Command!");
            }
        }
    }
    public void giveOutput(String message){
        System.out.println(message);
    }
}
