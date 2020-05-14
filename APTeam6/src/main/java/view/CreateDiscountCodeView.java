package view;

public class CreateDiscountCodeView {
    public CreateDiscountCodeView(){
        System.out.println("=== Create DiscountCode menu");
    }

    public String getInputCommand(){
        String command;
        while (true) {
            command = Input.getInput();
            if(command.matches("create discount code \\.+")){
            return command;
            }
            else{
                giveOutput("invalid command!");
            }
        }
        }
    public void giveOutput(String message){
        System.out.println(message);
    }
}
