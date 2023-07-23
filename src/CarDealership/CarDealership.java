import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
public class CarDealership {

    /**
     * generic method that get array of T and sort the array from the biggest to the smallest.
     * T must be Employee object or object who inherits from Employee
     * @param TArr
     */
    public static <T extends Comparable<Employee>> void sortArray (T [] TArr){
        T temp;
        for (int i = 0; i < TArr.length; i++) {
            for(int j = i+1; j<TArr.length ; j++){
                if (TArr[j-1].compareTo((Employee) TArr[j]) == -1){
                    temp = TArr[j-1];
                    TArr[j-1] = TArr[j];
                    TArr[j] = temp;
                }
            }
        }
    }

    // scanner input from the user
    public static Scanner input = new Scanner(System.in);
    // files CarDealership and Employee
    public static File fileCarDealership = new File("CarDealership.txt");
    public static File fileEmployee = new File("Employee.txt");

    public static void main(String[] args) {
        File fileSold = new File("Sold.txt");
        //create file sold if it doesn't exist
        if(!fileSold.exists()){
            try{
                fileSold.createNewFile();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        // create PrintWriter that write to the file "sold"
        PrintWriter soldWriter = null;
        try{
            soldWriter = new PrintWriter( new FileWriter(fileSold,true));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //create Car and Employee arrays
        Car [] cars =deleteNullObjects(createCarArray());
        Employee [] employees = deleteNullObjects(createEmployeeArray());
        sortArray(employees);
        //call to the switch case menu
        switchCaseMenu(cars,employees, soldWriter);
    }

    /**
     * method that create car array and return the array
     * and check if the input from the file is correct
     * @return car array
     */
    public static Car [] createCarArray (){
        try{
            Scanner inputCar = new Scanner(fileCarDealership);
            Car [] cars = new Car [inputCar.nextInt()];
            for(int i = 0; i<cars.length; i++) {
                try{
                    cars[i] = new Car(inputCar.nextInt(), inputCar.nextInt(), inputCar.next(), inputCar.nextInt(), inputCar.nextInt());
                }catch (Exception e){
                    System.out.println("---can't create the Car in index: " + i + "---");
                    if(i!=cars.length-1)
                        inputCar.nextLine();
                }
            }
            return cars;
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("---can't create the cars array---");
            return null;
        }
    }

    /**
     * method that create employee array and return the array
     * and check if the input from the file is correct
     * @return employee array
     */
    public static Employee [] createEmployeeArray (){
        try{
            Scanner inputEmployee = new Scanner(fileEmployee);
            Employee [] employees = new Employee [inputEmployee.nextInt()];
            for(int i = 0; i<employees.length; i++){
                try {
                    employees[i] = new Employee(inputEmployee.next(), inputEmployee.nextInt());
                    try {
                        employees[i].setSellsNumber(inputEmployee.nextInt());
                    } catch (Exception e) {
                        System.out.println("---can't save the sells number of Employee in index: " + i + "---");
                        if(e.getMessage()==null)
                            inputEmployee.next();
                    }
                }catch (Exception e) {
                    System.out.println("---can't create the Employee in index: " + i + "---");
                    if(i!=employees.length-1)
                        inputEmployee.nextLine();
                }
            }
            return employees;
        }catch(Exception e){
            System.out.println("---can't create the employees array---");
            return null;
        }
    }

    /**
     * method that delete all the null from the array and return the array
     * @param array
     * @return array
     */
    public static <T> T [] deleteNullObjects(T [] array) {
        int counter = 0;
        for(T temp: array){
            if(null!=temp){
                counter++;
            }
        }
        T [] arrayWithoutNull = Arrays.copyOf(array, counter);
        counter = 0;
        for(T temp: array){
            if(null!=temp){
                arrayWithoutNull[counter] = temp;
                counter++;
            }
        }
        return arrayWithoutNull;
    }

    /**
     * get input from the user and check if it's number
     * if it is number the method return this number
     * @return int
     */
    public static int checkInt (){
        boolean flag = true;
        int option = 0;
        while(flag){
            try{
                option = input.nextInt();
                flag = false;
            }catch (Exception e){
                System.out.print("Input incorrect!, please enter only digit: ");
                input.next();
            }
        }
        return option;
    }

    /**
     * menu method that print all the option that the user can choose
     * and return the option that the user chose
     * @return int
     */
    public static int menu (){
        System.out.println("1. Show all the employee in the car dealership");
        System.out.println("2. Show the cars at the car dealership");
        System.out.println("3. Sell car from the car dealership");
        System.out.println("4. Add new car to the car dealership");
        System.out.println("5. Exit");
        System.out.print("Please choose an option: ");
        int option = checkInt();
        while (option<1 || option>5){
            System.out.print("Input incorrect!, please choose an option between 1 to 5: ");
            option = checkInt();
        }
        return option;
    }

    /**
     * switch case method that get 3 variables
     * the method ask from the user to pick the option he needs to do
     * and send it to another method to do what he wants
     * @param cars - array of cars
     * @param employees - array of employees
     * @param soldWriter - PrintWriter object
     */
    public static void switchCaseMenu (Car [] cars, Employee [] employees,PrintWriter soldWriter){
        boolean flag = true;
        while (flag){
            //call menu to get the user choice
            int option = menu();
            switch (option){
                case 1:
                    System.out.println("\nThe employees in the car dealership: \n------------------------------------------------------");
                    showArray (employees);
                    break;
                case 2:
                    System.out.println("\nThe cars in the car dealership: \n------------------------------------------------------");
                    showArray(cars);
                    break;
                case 3:
                    System.out.println("\nsell car: \n------------------------------------------------------");
                    cars = sellCar(cars, employees, soldWriter);
                    sortArray(employees);
                    break;
                case 4:
                    System.out.println("\nadd car: \n------------------------------------------------------");
                    cars = addCar (cars);
                    break;
                case 5:
                    flag = false;
                    saveCarDealership(cars, employees);
                    System.out.println("\nend");
                    break;
            }
            System.out.println("------------------------------------------------------\n");
        }
    }

    /**
     * generic method that get array and print it
     * @param array
     */
    public static <T> void showArray (T [] array){
        for(T temp : array){
            System.out.println(temp);
        }
    }

    /**
     * method that get 3 variables
     * the method return the car array without the car who has sold
     * and add one sell to the employee who sell this car
     * @param cars - array
     * @param employees - array
     * @param soldWriter - PrintWriter object
     * @return Car array
     */
    public static Car [] sellCar (Car [] cars, Employee [] employees, PrintWriter soldWriter)  {
        if(cars.length==0){
            System.out.println("can't sell car because there are no cars in the array");
            return cars;
        }
        // print all the employees, and the user choose who sell the car from the list by ID number
        showArray(employees);
        System.out.print("Please choose ID number: ");
        int id = checkInt();
        while (id<100000000 || id>999999999 || !checkIfExistIdNumber(id,employees)){
            try{
                throw new Exception ("Input incorrect!, please choose new ID number from the list: ");
            }catch (Exception e){
                System.out.print(e.getMessage());
                id = checkInt();
            }
        }
        System.out.println();
        // print all the cars, and the user choose which car has been sold from the list by license number
        showArray(cars);
        System.out.print("Please choose car license number: ");
        int licenseNumber = checkInt();
        while (licenseNumber<100000 || licenseNumber>999999 || !checkIfExistLicenseNumber(licenseNumber,cars)) {
            try {
                throw new Exception("Input incorrect!, please choose new license number from the list: ");
            } catch (Exception e) {
                System.out.print("Input incorrect!, please choose new license number from the list: ");
                licenseNumber = checkInt();
            }
        }

        //save the employee object
        Employee pickedEmployee = null;
        for (Employee temp:employees){
            if(temp.getIdNumber() == id)
                pickedEmployee = temp;
        }
        //save the car object
        Car pickedCar = null;
        for (Car temp:cars){
            if(temp.getLicenseNumber() == licenseNumber)
                pickedCar = temp;
        }
        //call carSell method to add 1 sell to the employee and save the car details in the sold file
        pickedEmployee.carSell(soldWriter, pickedCar);
        System.out.println("\nCar has been successfully sold");
        return deleteCarFromArray (pickedCar,cars);
    }

    /**
     * check if there is ID number in the array of employee
     * @param option
     * @param employees
     * @return boolean
     */
    private static boolean checkIfExistIdNumber (int option,Employee [] employees ){
        for (Employee temp : employees){
            if(temp.getIdNumber() == option){
                return true;
            }
        }
        return false;
    }

    /**
     * check if there is license number in the array of car
     * @param option
     * @param cars
     * @return boolean
     */
    private static boolean checkIfExistLicenseNumber (int option,Car [] cars ){
        for (Car temp : cars){
            if(temp.getLicenseNumber() == option){
                return true;
            }
        }
        return false;
    }

    /**
     * create new array in the original array length - 1
     * and copy all the cars from the original array except for the chosen car
     * @param pickedCar
     * @param cars - the chosen car
     * @return - car array
     */
    private static Car [] deleteCarFromArray (Car pickedCar,Car [] cars ){
        Car [] tempCars = new Car [cars.length-1];
        int counter=0;
        for (int i = 0; i<cars.length; i++){
            if(cars[i].getLicenseNumber() != pickedCar.getLicenseNumber()){
                tempCars[counter]=cars[i];
                counter++;
            }
        }
        return tempCars;
    }

    /**
     * get from the user all the details about the car and create it
     * add the new car to the cars array and return it
     * if there is a problem with one of the input don't create the car
     * @param cars - car array
     * @return - car array
     */
    private static Car [] addCar (Car [] cars){
        System.out.println("please enter car details:");
        System.out.print("license number: ");
        int licenseNumber = checkInt();
        System.out.print("manufacture year: ");
        int manufactureYear = checkInt();
        System.out.print("manufacturer: ");
        String manufacturer = input.next();
        System.out.print("kilometer: ");
        int kilometer = checkInt();
        System.out.print("price: ");
        int price = checkInt();
        try{
            Car newCar = new Car(licenseNumber,manufactureYear,manufacturer,kilometer,price);
            Car [] newCars = Arrays.copyOf(cars,cars.length+1);
            newCars[newCars.length-1]= newCar;
            System.out.println("\nCar has been successfully created");
            return newCars;
        }catch (Exception e){
            System.out.print("\ncan't create the car because ");
            System.out.println(e.getMessage());
        }
        return cars;
    }

    /**
     * get the 2 arrays after all the changes and save the information in the files
     * @param cars - car array
     * @param employees - employee array
     */
    public static void saveCarDealership (Car [] cars, Employee [] employees){
        try{
            PrintWriter CarDealershipWriter = new PrintWriter (fileCarDealership);
            CarDealershipWriter.println(cars.length);
            for (Car temp:cars){
                CarDealershipWriter.println(temp.toString());
            }
            CarDealershipWriter.close();
        }catch (Exception e){
            System.out.println("can't save the cars array is the CarDealership file");
        }

        try{
            PrintWriter employeesWriter = new PrintWriter (fileEmployee);
            employeesWriter.println(employees.length);
            int lastSpace;
            for (Employee temp:employees){
                lastSpace = temp.toString().lastIndexOf(" ");
                employeesWriter.println(temp.toString().subSequence(0, lastSpace));
            }
            employeesWriter.close();
        }catch (Exception e){
            System.out.println("can't save the employees array is the Employee file");
        }
    }
}