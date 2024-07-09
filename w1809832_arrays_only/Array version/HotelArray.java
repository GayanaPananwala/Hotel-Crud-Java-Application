//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.

//Student ID: 20191136
//Date:  03/04/2021

//importing java packages
import java.io.*;
import java.util.*;

public class HotelArray {

//Creating static variables. Since these variables are relating to entire class.
static String roomName;
static  int roomNum;

    public static void main(String[] args)  {

        //Creating parallel arrays and variables.
        String[] hotel = new String[8];
        int[] guestsInRoom = new int[8];
        String[] guestFirstName = new String[8];
        String[] guestSurname = new String[8];
        int[] guestCreditId = new int[8];
        String option;

        //This can be used to control inputs for giving an accurate output
        String need_to_continue = "n";

        Scanner input = new Scanner(System.in); //Creating a scanner class object.

        initialise(hotel, guestsInRoom, guestFirstName, guestSurname, guestCreditId); //Calling initialise method.

        do {
            //The menu of the program
            System.out.println("Welcome to GoldenBeach Hotel - choose your selection. When prompted, enter first letter of operation");
            System.out.println("---- Operations ---- " + "\n" + " V: View all rooms " + "\n" +
                    " A: Add a customer to a room " + "\n" + " E: Display Empty rooms" + "\n" +
                    " D: Delete customer from room " + "\n" + " F: Find room from customer name" + "\n" +
                    " S: Store program data into a file" + "\n" + " L: Load program data from a file" + "\n" +
                    " O: View guests Ordered alphabetically by name. "+ "\n" +" R: Room additional information");
            System.out.println();
            System.out.println("What would you like to do ?");
            option = input.next().toLowerCase();

            try {
                // switch statement to select one of the code blocks to execute.
                switch (option) {
                    case "v":
                        view(hotel);
                        break; //breaks out of the switch block.
                    case "a":
                        add(input, hotel, guestsInRoom, guestFirstName, guestSurname, guestCreditId);
                        break;
                    case "e":
                        empty(hotel);
                        break;

                    case "d":
                        delete(input, hotel, guestsInRoom, guestFirstName, guestSurname, guestCreditId);
                        break;

                    case "f":
                        find(input, hotel);
                        break;

                    case "s":
                        store(hotel, guestsInRoom, guestFirstName, guestSurname, guestCreditId);
                        break;

                    case "l":
                        load(hotel, guestsInRoom, guestFirstName, guestSurname, guestCreditId);
                        break;

                    case "o":
                        alphabetically(hotel);
                        break;

                    case "r":
                        room_info(hotel, guestsInRoom, guestFirstName, guestSurname, guestCreditId);
                        break;

                    default:
                        //if there is not a match this line will execute.
                        System.out.println("Try Again! There is no such option.");

                }

            }catch (Exception e) {
                //If user enter an invalid input, this line will be executed
                System.out.println("Please try again with valid inputs.");
                break;
            }
            System.out.println("Do you want to continue with the program (y/n) ?");
            need_to_continue = input.next().toLowerCase();

            //printing out different responses for different outputs.
            if (need_to_continue.equals("n")) {
                System.out.println("Thank you! Have a good day..");
            } else if (need_to_continue.equals("y")) {
                continue;
            } else {
                System.out.println("Invalid inputs.Please try again!");
            }

        } while (need_to_continue.equals("y"));

    }

    //This method will initialise the array elements with values.
    static void initialise(String[] hotel, int[] guestsInRoom,
                           String[] guestFirstName, String[] guestSurname, int[] guestCreditId) {

        Arrays.fill(hotel, "empty");
        Arrays.fill(guestsInRoom,0);
        Arrays.fill(guestFirstName,"empty");
        Arrays.fill(guestSurname,"empty");
        Arrays.fill(guestCreditId,0);

    }

    //This method will add a customer to a room successfully.
    private static void add(Scanner input, String[] hotel, int[] guestsInRoom,
                            String[] guestFirstName, String[] guestSurname, int[] guestCreditId) {

        boolean can_add = true;
        int count = 0;

        //counts empty rooms.
        for (String s : hotel) {
            if (s.equals("empty")) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Sorry! Hotel rooms are full.");
            can_add = false;
        }

        if (can_add) {
            System.out.print("Enter room number (1-8): ");
            roomNum = input.nextInt() - 1;
            if (!hotel[roomNum].equals("empty")) {
                System.out.println("Sorry! The Room no." + (roomNum + 1) + " is already booked.");
            } else {
                System.out.print("Enter customer name (single name) for room " + (roomNum + 1) + " : ");
                roomName = input.next().toLowerCase();
                if (checkAlphabet(roomName)) {
                    System.out.print("How many guests are there: ");
                    int noOfGuests = input.nextInt();
                    if (noOfGuests > 5) {
                        System.out.println("Sorry the maximum guests can be added to the room is 5.");
                    }
                    else if (noOfGuests <= 0) {
                        System.out.println("Please try again with the valid inputs.");
                    }
                    else {
                        System.out.print("Payer first name : ");
                        String name = input.next();
                        if (checkAlphabet(name)) {
                            System.out.print("Payer Surname: ");
                            String surname = input.next();
                            if (checkAlphabet(surname)) {
                                System.out.print("Payer credit card number : ");
                                int creditID = input.nextInt();
                                if (creditID <= 0) {
                                    System.out.println("Please enter a valid credit ID number.");
                                } else {
                                    guestFirstName[roomNum] = name;
                                    guestSurname[roomNum] = surname;
                                    guestCreditId[roomNum] = creditID;
                                    guestsInRoom[roomNum] = noOfGuests;
                                    hotel[roomNum] = roomName;
                                }
                            } else {
                                System.out.println("Please enter valid surname.");
                            }

                        } else {
                            System.out.println("Please enter valid first name.");
                        }
                    }
                } else {
                    System.out.println("Please enter valid customer name.");
                }

            }

        }
    }

    //This method will print out all rooms
    private static void view(String[] hotel) {

        for (int x = 0; x < hotel.length; x++) {
            if (hotel[x].equals("empty")){
                System.out.println("Room " + (x + 1) + " occupied by nobody");
            }
            else {
                System.out.println("Room " + (x + 1) + " occupied by " + hotel[x]);
            }
        }
    }

    //This method will print out empty rooms
    private static void empty(String[] hotel) {

        int i = 0;

        for (int x = 0; x < hotel.length; x++) {
            if (hotel[x].equals("empty")) {
                System.out.println("Room " + (x + 1) + " is empty");
                i++;
            }
        }

        if (i==0){
            System.out.println("No empty rooms.");
        }
    }

    //This method will remove a customer according to the room number.
    private static void delete(Scanner input, String[] hotel, int[] guestsInRoom,
                               String[] guestFirstName, String[] guestSurname, int[] guestCreditId) {

        System.out.println("What is the room number to delete? ");
        roomNum = input.nextInt() - 1;

        if (hotel[roomNum].equals("empty")) {
            System.out.println("Sorry! The room is empty.");
        } else {

            hotel[roomNum] = "empty";
            guestsInRoom[roomNum] = 0;
            guestFirstName[roomNum] = "empty";
            guestSurname[roomNum] = "empty";
            guestCreditId[roomNum] = 0;
        }
    }

    //This method will find a customer according to the name
    private static void find(Scanner input, String[] hotel) {

        System.out.print("Enter name to search for: ");
        roomName = input.next().toLowerCase();

        int count = 0;

        if (checkAlphabet(roomName)) {
            for (int x = 0; x < hotel.length; x++) {
                if (hotel[x].equals(roomName)) {
                    System.out.println("The room number is  " + (x + 1));
                    count++;
                }
            }
                if (count == 0){
                    System.out.println("There is no name like that.");
                }
        }
        else {
            System.out.println("Please enter valid inputs.");
        }

    }

    //This method will store data in to a file
    private static void store(String[] hotel, int[] guestsInRoom,
                              String[] guestFirstName, String[] guestSurname, int[] guestCreditId) {

        try {
            PrintWriter myWriter = new PrintWriter("arrayStore.txt");
            for (int x = 0; x < hotel.length; x++) {
                myWriter.println("Name and Room number is: " + hotel[x] + " at : " + (x + 1));
                myWriter.println("No of guests " + guestsInRoom[x]);
                myWriter.println("Payer's first name is " + guestFirstName[x]);
                myWriter.println("Payer's surname is " + guestSurname[x]);
                myWriter.println("Payer's credit card ID " + guestCreditId[x]);
                myWriter.println("-----------------------------------------------------------------");
            }

            myWriter.close();

            System.out.println("All the details Saved.");
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

    //This method will load data to the program from a file
    private static void load(String[] hotel, int[] guestsInRoom,
                             String[] guestFirstName, String[] guestSurname, int[] guestCreditId) throws IOException {

        FileInputStream fs = new FileInputStream("arrayLoad.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int x =0; x< hotel.length; x++) {
           hotel[x]=(br.readLine());
           guestsInRoom[x] = (Integer.parseInt(br.readLine()));
           guestFirstName[x] = (br.readLine());
           guestSurname[x] = (br.readLine());
           guestCreditId[x] = (Integer.parseInt(br.readLine()));
        }
        System.out.println("Successfully loaded the data to the program!");

    }

    //This method will sort customer names according to the alphabetical order
    private static void alphabetically(String[] hotel) {

        String temp;

        for (int i = 0; i < hotel.length; i++) {
            for (int j = i + 1; j < hotel.length; j++) {

                // compare one string with other string
                if (hotel[i].compareTo(hotel[j]) > 0) {
                    // swapping process
                    temp = hotel[i];
                    hotel[i] = hotel[j];
                    hotel[j]= (temp);

                }
            }
        }

        int count = 0;

        for (String s : hotel) {
            if (!s.equals("empty")) {
                count ++;//for check weather all the rooms are empty or not
                System.out.println(s);
            }
        }

        if (count == 0) {
            System.out.println("All the rooms are empty!");
        }
    }

    //This method will take the strings and check if they contain A to Z only
    public static boolean checkAlphabet(String name) {

        char[] ch = name.toCharArray();
        for (char c : ch) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;

    }

    //This method will display the room additional information
    private static void room_info(String[] hotel, int[] guestsInRoom,
                                  String[] guestFirstName, String[] guestSurname, int[] guestCreditId) {

        for (int x = 0; x < hotel.length; x++) {
            System.out.println("Room No." + (x + 1) + "- Additional Information");
            System.out.println("------------------------------------");
            System.out.println("Customer name is " + hotel[x] + ".");
            System.out.println("Room number " + (x + 1) + " has " + guestsInRoom[x] + " guests.");
            System.out.println("The payer's first name is " + guestFirstName[x] + ".");
            System.out.println("The payer's surname is " + guestSurname[x]+ ".");
            System.out.println("The payer's credit card number is " + guestCreditId[x] + ".");
            System.out.println("------------------------------------");
        }

    }
}


