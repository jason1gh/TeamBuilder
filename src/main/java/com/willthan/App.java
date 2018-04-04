package com.willthan;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
        TeamBuilder teamBuilder = new TeamBuilder();
        String[] paths = {
  //              "110001", "110000", "001100", "001110", "010110", "101001"
                "110101", "011010", "001100", "001110", "010011", "000011"
        };
        int[] results = teamBuilder.specialLoations(paths);
        System.out.println("Number of locations can access all locations: " + String.valueOf(results[0]));
        System.out.println("Number of locatons can be accessed by all locations: " + String.valueOf(results[1]));
    }
}
