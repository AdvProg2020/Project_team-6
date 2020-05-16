package view;

public class CreateDiscountCodeView {
    public CreateDiscountCodeView(){
        System.out.println("=== Create DiscountCode menu");
    }

    public String getInputCommand(){
        String command;
        String secondCommand;
        while (true) {
            command = Input.getInput();
            /*giveOutput("Enter all discountCode properties except the userIncluded:");
            if(command.matches("create discount code \\.+")){
            return command;
            }*/
            if(command.equals("back")){
                return command;
            }
            else{
                giveOutput("invalid command!");
            }
        }
        }
    public String[] getUserUsualData() {
        String[] discountCodeInfo = new String[5];
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
        while (true) {
            System.out.println("");
            discountCodeInfo[2] = Input.getInput();
            if (discountCodeInfo[2].matches("[0-9]+")) {
                break;
            }
            System.out.println("Wrong phone number");
        }
        return discountCodeInfo;
    }
    public void giveOutput(String message){
        System.out.println(message);
    }
}
