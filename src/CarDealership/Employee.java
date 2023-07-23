import java.io.PrintWriter;

public class Employee implements Comparable<Employee> {
    private String name;
    private int idNumber;
    private int sellsNumber;

    /**
     * constructor that get 2 variables and if there is a problem with one of them it throws an exception
     * @param name
     * @param idNumber
     * @throws Exception
     */
    public Employee (String name, int idNumber) throws Exception{
        for(int i = 0; i<name.length(); i++){
            if(name.charAt(i)>='0' && name.charAt(i)<='9')
                throw new Exception("name must be with only letters");
        }
        this.name = name;
        if(idNumber<100000000 || idNumber>999999999){
            throw new Exception("ID must be 9 digits");
        }
        this.idNumber = idNumber;
    }

    /**
     * method that get 2 variables, adding 1 sell to the employee
     * and call to sell car of class car with print writer object to save the details of the car in the sold car file.
     * @param writer
     * @param car
     */
    public void carSell (PrintWriter writer, Car car)  {
        sellsNumber++;
        car.carSell(writer);
    }

    /**
     * calculate the employee salary and return the result
     * @return int
     */
    public int employeeSalary ()  {
        return 6000+100*sellsNumber;
    }

    /**
     * toString method
     * @return string with all the employee details
     */
    public String toString ()  {
        return String.format("%s %d %d %d", name,idNumber,sellsNumber,employeeSalary());
    }

    /**
     * implements the comparable interface for compare between 2 employees
     * compare the employees by the sellsNumber
     * @param other the object to be compared.
     * @return int
     */
    public int compareTo(Employee other) {
        if (other.sellsNumber == sellsNumber)
            return 0;
        if(other.sellsNumber > sellsNumber)
            return -1;
        return 1;
    }

    /**
     * gets
     * @return
     */
    public String getName() {
        return name;
    }
    public int getIdNumber() {
        return idNumber;
    }

    /**
     * set the sells number and if there is a problem with the variable throws an exception
     * @param num
     * @throws Exception
     */
    public void setSellsNumber(int num) throws Exception {
        if(num<0){
            throw new Exception("must be positive number");
        }
        this.sellsNumber=num;
    }
}