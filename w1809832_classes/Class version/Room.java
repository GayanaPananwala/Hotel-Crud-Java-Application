class Room {

    private String mainName= "empty";

    //Creating an object for person class
    Person person = new Person();

    public void setName(String aName) {

        mainName = aName;
    }

    public String getName() {

        return mainName;
    }

    //Can be used to Access person class private variables through getPerson.
    //Can be used to store additional information in each room object.
    public Person getPerson(){

        return person;
    }

}