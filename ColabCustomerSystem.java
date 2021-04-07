/*
 * Date: April 1st - April 8th, 2021
 * Name: Kyle Chong & Angus Whitehead
 * Teacher: Mr. Ho
 * Description: Customer System Assignment
 */

import java.util.Scanner;
// More packages may be imported in the space below

class CustomerSystem{
    public static void main(String[] args){
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below


        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                enterCustomerInfo();
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile();
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void enterCustomerInfo() {
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    //validateCreditCard(reverseCardNum, cardNumber); - Use later
    public static void validatePostalCode(){
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validateCreditCard(String reverseCardNum, String cardNumber){
        int numOfDigits = reverseCardNum.length();
        int numSum = 0;
        boolean evenDigit = false; 
        for (int i = 0; i <= numOfDigits - 1; i++) {
            int n = reverseCardNum.charAt(i) - '0';
            if (evenDigit == true){
                n = n*2;
            }
            numSum += n / 10;
            numSum += n % 10;
            evenDigit = !evenDigit;            
        }
        if (numSum % 10 == 0) {
            System.out.println(cardNumber + " is a valid Credit Card");
        }
        else 
            System.out.println(cardNumber + " is not a valid Credit Card");
    }
    public static void generateCustomerDataFile(){
    }
    
    public static String reverse(String cardNumber){
        int len = cardNumber.length();
        String result = "";
        for (int i = len - 1; i >= 0; i--) {
            result = result + cardNumber.charAt(i);
        }
        return result;
    }
}