import java.io.PrintWriter;

public class Car {
   private int licenseNumber;
   private int manufactureYear;
   private String manufacturer;
   private int kilometer;
   private int price;

    /**
     * constructor that get 5 variables and if there is a problem with one of them it throws an exception
     * @param licenseNumber
     * @param manufactureYear
     * @param manufacturer
     * @param kilometer
     * @param price
     * @throws Exception
     */
   Car (int licenseNumber, int manufactureYear, String manufacturer, int kilometer, int price) throws Exception {
       if(licenseNumber<100000 || licenseNumber>999999){
           throw new Exception("license number must be a 6 digit number");
       }
       this.licenseNumber = licenseNumber;
       if(manufactureYear<2017 || manufactureYear>2023){
           throw new Exception("manufacture year must be between 2017 to 2023");
       }
       this.manufactureYear = manufactureYear;
       this.manufacturer = manufacturer;
       if(kilometer<0){
           throw new Exception("kilometer must be a positive number");
       }
       this.kilometer = kilometer;
       if(price<0){
           throw new Exception("price must be a positive number");
       }
       this.price = price;
   }

    /**
     * toString method
     * @return String with all the details about the car
     */
   public String toString (){
       return String.format("%d %d %s %d %d", licenseNumber, manufactureYear, manufacturer, kilometer, price);
   }

    /**
     * get int percent variable and decrease the price by the percent
     * if the decrease is above 4999 or the percent is negative it throws an exception
     * @param percentValueDecrease
     * @throws Exception
     */
   public void valueDecrease (double percentValueDecrease) throws Exception {
       if(percentValueDecrease<0){
           throw new Exception ("Percent value decrease can't be negative");
       }
       if(price*(percentValueDecrease/100) > 4999){
           throw new Exception ("Value decrease is to high");
       }
       price -= price*(percentValueDecrease/100);
    }

    /**
     * save the car detail in the sold car file
     * @param writer
     */
    public void carSell(PrintWriter writer)  {
       writer.println(toString());
       writer.close();
    }

    /**
     * get
     * @return int
     */
    public int getLicenseNumber(){
       return licenseNumber;
    }
}