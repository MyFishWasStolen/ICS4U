public class Hockey

{

   public static void main (String [] args)
   
   {
   
      Puck snoopy = new Puck(3.0, 1.5, 4.0, true, false);
      
      System.out.println(snoopy.toString());
      System.out.println(snoopy.getDivision()); 
   
   }

}