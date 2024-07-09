class Person {
    //The variables are private since they contain sensitive information
    private int NoGuests = 0;
    private String surname = "empty" ;
    private String firstname = "empty";
    private int creditID = 0;

    //public get and set methods to access and update the value of private NoGuests variable
    public void setNoGuests(int guests) {

        NoGuests = guests;
    }

    public int getNoGuests() {

        return NoGuests;
    }
    //public get and set methods to access and update the value of private firstname variable
    public void setFirstname(String firstname) {

        this.firstname = firstname;
    }

    public String getFirstname() {

        return this.firstname;
    }
    //public get and set methods to access and update the value of private surname variable
    public void setSurname(String surname) {

        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;

    }
    //public get and set methods to access and update the value of private credit card variable
    public void setCreditID(int creditID) {
        this.creditID = creditID;

    }

    public int getCreditID() {
        return this.creditID;

    }

}