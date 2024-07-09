//I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
//Any code taken from other sources is referenced within my code solution.

//Student ID: 20191136
//Date:  03/04/2021

//importing java packages
import java.io.*;
import java.util.*;

public class Hotel {

    //Creating static variables. Since these variables are relating to entire class.
    static String roomName;
    static int roomNum;

    public static void main(String[] args)  {

        //These can be used to control inputs for giving an accurate output
        String need_to_continue = "y";
        String option;

        //Creating an array of room objects and allocating memory for 8 objects
        Room[] myHotel = new Room[8];

        // Creating actual room objects
        myHotel[0] = new Room();
        myHotel[1] = new Room();
        myHotel[2] = new Room();
        myHotel[3] = new Room();
        myHotel[4] = new Room();
        myHotel[5] = new Room();
        myHotel[6] = new Room();
        myHotel[7] = new Room();

        Scanner sc = new Scanner(System.in); //Creating a scanner class object.

        Queue obj = new Queue(5); //Creating an object for queue class of size five

        initialise(myHotel); //Calling initialise method

        do {
            //The menu of the program
            System.out.println("Welcome to GoldenBeach Hotel - choose your selection." + "\n" + " When prompted, enter first letter of operation");
            System.out.println("---- Operations ---- " + "\n" + " V: View all rooms " + "\n" +
                    " A: Add a customer to a room " + "\n" + " E: Display Empty rooms" + "\n" +
                    " D: Delete customer from room " + "\n" + " F: Find room from customer name" + "\n" +
                    " S: Store program data into file" + "\n" + " L: Load program data from file" + "\n" +
                    " O: View guests Ordered alphabetically by name" + "\n" + " R: Room additional information" + "\n" + " Q: Display the waiting list");
            System.out.println();

            System.out.println("What would you like to do ?");
            option = sc.next().toLowerCase();

            try {
                // switch statement to select one of the code blocks to execute.
                switch (option) {

                    case "v":
                        view(myHotel);
                        break; //breaks out of the switch block

                    case "a":
                        add(myHotel, sc, obj);
                        break;

                    case "e":
                        empty(myHotel);
                        break;

                    case "d":

                        delete(myHotel, sc, obj);
                        break;

                    case "f":

                        find(myHotel, sc);
                        break;

                    case "s":
                        store(myHotel);
                        break;


                    case "l":
                        load(myHotel);
                        break;

                    case "o":
                        alphabetically(myHotel);
                        break;

                    case "r":
                        room_info(myHotel);
                        break;

                    case "q":
                        obj.displayQueue();
                        break;
                    default:
                        //if there is not a match this line will execute.
                        System.out.println("Try Again! There is no such option.");
                }
            } catch (Exception e) {
                //If user enter an invalid input, this line will be executed
                System.out.println("Please try again with valid inputs.");
                break;
            }

            System.out.println("Do you want to continue with the program (y/n) ?");
            need_to_continue = sc.next().toLowerCase();

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
    private static void initialise(Room[] myHotel) {

        for (Room room : myHotel) {
            room.setName("empty");
        }

    }

    //This method will add a customer to a room if the rooms are full, customer will added to a queue.
    private static void add(Room[] myHotel, Scanner sc, Queue obj) {

        boolean can_add = true;
        int count = 0;

        //Counts empty rooms
        for (Room room : myHotel) {
            if (room.getName().equals("empty")) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Sorry! Hotel rooms are full.");
            can_add = false;

            System.out.println("Sorry for the inconvenience.We are adding you to a waiting list.");
            System.out.println("Enter customer name for room ? ");
            String queue_name = sc.next().toLowerCase();
            if (checkAlphabet(queue_name)) {
                    obj.enqueue(queue_name);
            }
            else{
                    System.out.println("Please enter a valid customer name.");
                }
        }

        if (can_add == true) {
            System.out.print("Enter room number (1-8): ");
            roomNum = sc.nextInt() - 1;
            if (!myHotel[roomNum].getName().equals("empty")) {
                System.out.println("Sorry! The Room no." + (roomNum + 1) + " is already booked.");
            } else {
                System.out.print("Enter customer name (single name) for room " + (roomNum + 1) + " : ");
                roomName = sc.next().toLowerCase();
                if (checkAlphabet(roomName)) {
                    System.out.print("How many guests are there: ");
                    int noOfGuests = sc.nextInt();
                    if (noOfGuests > 5) {
                        System.out.println("Sorry the maximum guests can be added to the room is 5.");
                    }
                    else if (noOfGuests <= 0) {
                        System.out.println("Please try again with the valid inputs.");
                    }
                    else {
                        System.out.print("Payer first name : ");
                        String name = sc.next();

                        if (checkAlphabet(name)) {
                            System.out.print("Payer Surname: ");
                            String surname = sc.next();
                            if (checkAlphabet(surname)) {
                                System.out.print("Payer credit card number : ");
                                int creditID = sc.nextInt();
                                if (creditID<=0){
                                    System.out.println("Please enter a valid credit ID number.");
                                }
                                else {
                                    myHotel[roomNum].getPerson().setFirstname(name);
                                    myHotel[roomNum].getPerson().setSurname(surname);
                                    myHotel[roomNum].getPerson().setCreditID(creditID);
                                    myHotel[roomNum].getPerson().setNoGuests(noOfGuests);
                                    myHotel[roomNum].setName(roomName);
                                }
                            } else {
                                System.out.println("Please enter valid surname.");
                            }

                        } else {
                            System.out.println("Please enter valid first name.");
                        }
                    }
                }
                else {
                    System.out.println("Please enter valid customer name.");
                }

            }

        }

    }

    //This method will display the room additional information
    private static void room_info(Room[] myHotel) {
        for (int x = 0; x < myHotel.length; x++) {
            System.out.println("Room No." + (x + 1) + "- Additional Information");
            System.out.println("-------------------------------------------------");
            System.out.println("Customer name is " + myHotel[x].getName() + ".");
            System.out.println("Room number " + (x + 1) + " has " + myHotel[x].getPerson().getNoGuests() + " guests.");
            System.out.println("The payer's first name is " + myHotel[x].getPerson().getFirstname() + ".");
            System.out.println("The payer's surname is " + myHotel[x].getPerson().getSurname() + ".");
            System.out.println("The payer's credit card number is " + myHotel[x].getPerson().getCreditID() + ".");
            System.out.println("-----------------------------------------------------");
        }
    }

    //This method will print out all rooms
    private static void view(Room[] myHotel) {
        for (int x = 0; x < myHotel.length; x++) {
            if (myHotel[x].getName().equals("empty")){
                System.out.println("Room " + (x + 1) + " occupied by nobody");
            }
            else {
                System.out.println("Room " + (x + 1) + " occupied by " + myHotel[x].getName());
            }

        }
    }

    //This method will print out empty rooms
    private static void empty(Room[] myHotel) {

        int i = 0;

        for (int x = 0; x < myHotel.length; x++) {
            if (myHotel[x].getName().equals("empty")) {
                System.out.println("Room " + (x + 1) + " is empty");
                i++;
            }
        }

        if (i==0){
            System.out.println("No empty rooms.");
        }
    }

    //This method will remove a customer according to the room number and replace with the first person in queue
    private static void delete(Room[] myHotel, Scanner sc, Queue obj) {


        System.out.println("What is the room number to delete? ");
        roomNum = sc.nextInt() - 1;

        if (myHotel[roomNum].getName().equals("empty")) {
            System.out.println("Sorry! The room is empty.");
        }

         else {
            myHotel[roomNum].setName("empty");
            myHotel[roomNum].getPerson().setNoGuests(0);
            myHotel[roomNum].getPerson().setFirstname("empty");
            myHotel[roomNum].getPerson().setSurname("empty");
            myHotel[roomNum].getPerson().setCreditID(0);

            if (obj.peek() != null) {
                myHotel[roomNum].setName(obj.peek());
                System.out.println(obj.peek() + " is replaced with the room no. " + (roomNum + 1) + ".Please provide the additional details.");
                obj.dequeue();
                System.out.print("How many guests are there: ");
                int noOfGuests = sc.nextInt();
                if (noOfGuests > 5) {
                    System.out.println("Sorry the maximum guests can be added to the room is 5.");
                }
                else if (noOfGuests <= 0) {
                    System.out.println("Please try again with the valid inputs.");
                }
                else {
                    System.out.print("Payer first name : ");
                    String name = sc.next();

                    if (checkAlphabet(name)) {
                        System.out.print("Payer Surname: ");
                        String surname = sc.next();
                        if (checkAlphabet(surname)) {
                            System.out.print("Payer credit card number : ");
                            int creditID = sc.nextInt();
                            if (creditID<=0){
                                System.out.println("Please enter a valid credit ID number.");
                            }
                            else {
                                myHotel[roomNum].getPerson().setFirstname(name);
                                myHotel[roomNum].getPerson().setSurname(surname);
                                myHotel[roomNum].getPerson().setCreditID(creditID);
                                myHotel[roomNum].getPerson().setNoGuests(noOfGuests);

                            }
                        } else {
                            System.out.println("Please enter valid surname.");
                        }

                    } else {
                        System.out.println("Please enter valid first name.");
                    }
                }

            }
        }

    }

    //This method will find a customer according to the name
    private static void find(Room[] myHotel, Scanner sc) {

        System.out.print("Enter name to search for: ");
        roomName = sc.next().toLowerCase();
        int count = 0;
        if (checkAlphabet(roomName)) {
            for (int x = 0; x < myHotel.length; x++) {
                if (myHotel[x].getName().equals(roomName)) {
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
    private static void store(Room[] myHotel) {

        try {
            PrintWriter myWriter = new PrintWriter("classStore.txt");
            for (int x = 0; x < myHotel.length; x++) {
                myWriter.println("Name and Room number is: " + myHotel[x].getName() + " at : " + (x + 1));
                myWriter.println("No of guests " + myHotel[x].getPerson().getNoGuests());
                myWriter.println("Payer's first name is " + myHotel[x].getPerson().getFirstname());
                myWriter.println("Payer's surname is " + myHotel[x].getPerson().getSurname());
                myWriter.println("Payer's credit card ID " + myHotel[x].getPerson().getCreditID());
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
    private static void load(Room[] myHotel) throws IOException {

        FileInputStream fs = new FileInputStream("classLoad.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (Room x : myHotel) {
            x.setName(br.readLine());
            x.getPerson().setNoGuests(Integer.parseInt(br.readLine()));
            x.getPerson().setFirstname(br.readLine());
            x.getPerson().setSurname(br.readLine());
            x.getPerson().setCreditID(Integer.parseInt(br.readLine()));
        }
        System.out.println("Successfully loaded the data to the program!");

    }

    //This method will sort customer names according to the alphabetical order
    private static void alphabetically(Room[] myHotel) {

        String temp;

        for (int i = 0; i < myHotel.length; i++) {
            for (int j = i + 1; j < myHotel.length; j++) {

                // compare one string with other string
                if (myHotel[i].getName().compareTo(myHotel[j].getName()) > 0) {
                    // swapping process
                    temp = myHotel[i].getName();
                    myHotel[i].setName(myHotel[j].getName());
                    myHotel[j].setName(temp);

                }
            }
        }

        int count = 0;

        for (Room s : myHotel) {
            if (!s.getName().equals("empty")) {
                count++; //for check weather all the rooms are empty or not
                System.out.println(s.getName());
            }
        }
        if (count == 0) {

            System.out.println("All the rooms are empty!");
        }
    }

    //This method will take the strings and check if they contain A to Z only
    private static boolean checkAlphabet(String name) {

        char[] ch = name.toCharArray();
        for (char c : ch) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;

    }

}






