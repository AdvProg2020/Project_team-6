package view;

public class ManageUsersView {
    public ManageUsersView(){
        System.out.println("=== Manage Users Menu");
    }
    public String getInputCommand(){
        String command;
        while (true){
            command = Input.getInput();
            if(command.matches("view \\S+")){
                return command;
            }
            else if(command.equals("help")){
                showHelp();
            }
            else if(command.matches("delete user \\S+")){
                return command;
            }
            else if(command.equals("back")){
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
    private void showHelp() {
        System.out.println("List of commands:\n\tview [username]\n\tdelete user [username]");
    }
    public void giveOutput(String message){
        System.out.println(message);
    }
}
