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
            else if(command.equals("help")){
                showHelp();
            }
            else if(command.equals("back")){
                return command;
            }
            else {
                giveOutput("Invalid Command!");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\tremove [productId]\n\t");
    }

    public void giveOutput(String message){
        System.out.println(message);
    }
}
