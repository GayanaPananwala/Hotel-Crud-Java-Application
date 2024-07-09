import java.util.ArrayList;

class Queue{

    // Declaring Queue class variables.
    int size,front,rear;

    //Creating an array list of string type
    ArrayList<String> queue = new ArrayList<String>();

    // Create a class constructor for the Queue class
    Queue(int size)
    {
        // Set the initial value for the  size, front and rear
        this.size = size;
        this.front = this.rear = -1;
    }

    //This method will insert customer names to the queue
    public void enqueue(String name){

        //Check weather queue is full or not
        if((front == 0 && rear == size - 1) ||
                (rear == (front - 1) % (size - 1)))
        {
            System.out.print("Sorry! Queue is Full"+"\n");
        }

        // condition for empty queue.
        else if(front == -1)
        {
            front = 0;
            rear = 0;
            queue.add(rear, name);
            System.out.println("You are added to the waiting list.");
        }

        else if(rear == size - 1 && front != 0)
        {
            rear = 0;
            queue.set(rear, name);
            System.out.println("You are added to the waiting list.");

        }

        else
        {
            rear = (rear + 1);

            // Adding a new name if
            if(front <= rear)
            {
                queue.add(rear, name);
                System.out.println("You are added to the waiting list.");
            }

            // Else updating old value
            else
            {
                queue.set(rear, name);

            }
        }

    }

    // This method will display names of the queue
    public void displayQueue(){

        // check if it an empty queue
        if(front == -1)
        {
            System.out.print("Queue is Empty");
            System.out.println();
            return;
        }

        // If rear has not crossed the max size
        // or queue rear is greater than or equal to
        // front.
        System.out.print("Elements in the " +
                "circular queue are: ");

        if(rear >= front)
        {

            // Loop to print names from
            // front to rear.
            for(int i = front; i <= rear; i++)
            {
                System.out.print(queue.get(i));
                System.out.print(" ");
            }
            System.out.println();
        }

        // If rear crossed the max index and
        // indexing has started in loop
        else
        {

            // Loop for printing names from front to max size or last index
            for(int i = front; i < size; i++)
            {
                System.out.print(queue.get(i));
                System.out.print(" ");
            }

            // Loop for printing names from 0th index till rear position
            for(int i = 0; i <= rear; i++)
            {
                System.out.print(queue.get(i));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    //This method will return the first name of the queue
    public String peek(){

        if (front == -1){
            return null;
        }
        else {
            return queue.get(front);
        }

    }

    //This method will remove name from the queue
    public void dequeue(){

        // Check if it is an empty queue
        if(front == -1)
        {
            System.out.print("Queue is Empty");
        }

        // if the queue contains only one name.
        if(front == rear)
        {
            front = -1;
            rear = -1;
        }

        else if(front == size - 1)
        {
            front = 0;
        }
        else
        {
            front = front + 1;
        }


        }

}