import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.util.HashMap;
import java.util.Set;
import java.util.*;

public class ElectoraslMap
{   
    private static HashMap<String, ArrayList<SubRegion>> regions = new HashMap<>();    //
    //private static ArrayList<String> subRegionNames = new ArrayList<>(); //allows for hashmap traverse
    private static int xSize = 0;
    private static int ySize = 0;
    private static double[] dub1;
    private static double[] dub2;
    private static class SubRegion
    {

        private String name;
        private double[] xCoords;
        private double[] yCoords;
        private int[] votes;
        private Color col;
        private double[] dub1;
        private double[] dub2;
        private SubRegion(String nameRegion, double[] xCoord, double[] yCoord)
        {
            name = nameRegion;
            xCoords = xCoord;
            yCoords = yCoord;
            

        }

        private void changeColor(Color newColor)
        {
            col = newColor;
        }

        private void changeVote(int[] newVote)
        {
            votes = newVote;
        }

    }
    public static void main(String regionName, String year) throws Exception
    {
        getGeoData(regionName);
        votingFill(regionName+year);
        draw();

   
    }

    public static void votingFill(String regionNameYear) throws Exception
    {

        int[] regionVotes = new int[3];

        File f = new File("./input/"+regionNameYear+".txt");
        Scanner inputObject = new Scanner(f);
        inputObject.nextLine();   //Line1 lat/long min
        //IGNORE STRING AT FIRST PART
        while(inputObject.hasNextLine())
        {
            //SUB REGION VOTE 
            String line = inputObject.nextLine();
            String stringVotes[] = line.split(",");
            regionVotes[0] = Integer.parseInt(stringVotes[1]);
            regionVotes[1] = Integer.parseInt(stringVotes[2]);
            regionVotes[2] = Integer.parseInt(stringVotes[3]);
            double px = 0;
            double py = 0;
            double x = 0;
            double y = 0;
            int count = 0;
            System.out.println(regions.keySet());
            System.out.println(stringVotes[0]);
            //System.out.println(regions.get(stringVotes[0]).get(0));
            ArrayList<SubRegion> tempList = regions.get(stringVotes[0]);//THIS IS NULL

            if(regionVotes[1] > regionVotes[0] && regionVotes[1] > regionVotes[2])
            {
                System.out.println();
                //ArrayList<SubRegion> tempList = regions.get(stringVotes[0]);
                for(int i = 0; i < tempList.size(); i++)
                {
                    tempList.get(i).changeColor(StdDraw.BLUE);
                }
                //SUB REGION = BLUE
            }
            else if(regionVotes[0] > regionVotes[1] && regionVotes[0] > regionVotes[2])
            {
                for(int i = 0; i < tempList.size(); i++)
                {
                    tempList.get(i).changeColor(StdDraw.RED); //Change color of subRegion object to Red
                }

            }
            else
            {//
                //ArrayList<SubRegion> tempList = regions.get(stringVotes[0]);
                for(int i = 0; i < tempList.size(); i++)
                {
                    tempList.get(i).changeColor(StdDraw.GREEN);
                }
                //return StdDraw.GREEN();
                //SUB REGION = GREEN
            }

            //SUBREGION COLOR = DEPEND
        }
        //R = 0, D = 1, I = 2

        //    HashMap<String, ArrayList<SubRegion>> region = new HashMap<>();
        //    Go through the ArrayList
        //    Count all votes

        //
        //Open voting data file
        //Fill each subRegion with voting data
        //Read document and store info 
        //3 Arrays
    }

    
    public static void getGeoData(String regionName) throws Exception
    {
        String[] firstLine = new String[2];
        String[] secondLine = new String[2];
        File f = new File("./input/"+regionName+".txt");
        Scanner inputObject = new Scanner(f);
        String line = inputObject.nextLine();   //Line1 lat/long min
        String line2 = inputObject.nextLine();  //Line2 lat/long max
        String line3 = inputObject.nextLine();  // # of subregions                                        //Line3 #subregions
        int totSub = Integer.parseInt(line3);
        StdDraw.enableDoubleBuffering();
        double[] dub1 = new double[2];
        double[] dub2 = new double[2];

        firstLine = line.split("   ");
        secondLine = line2.split("   ");
        double px = 0;
        double py = 0;
        double x = 0;
        double y = 0;
        int count = 0;
        for(int z = 0; z < firstLine.length; z++)
        {
            dub1[z] = Double.parseDouble(firstLine[z]);
            dub2[z] = Double.parseDouble(secondLine[z]);
        }

        xSize = (int) (dub2[0] - dub1[0]);
        ySize = (int) (dub2[1] - dub1[1]);
        int size = 0;
        StdDraw.setCanvasSize(512*(xSize/ySize), 512);
        StdDraw.setXscale(dub1[0], dub2[0]);
        StdDraw.setYscale(dub1[1], dub2[1]);

        //StdDraw.setCanvasSize(512*(xSize/ySize), 512);
        //StdDraw.setXscale(dub1[0], dub2[0]);
        //StdDraw.setYscale(dub1[1], dub2[1]); 

        //StdDraw.circle(500, 500, 2000);
        //StdDraw.point(500, 500);
        int i = 0;
        boolean isFirst = true;
        //inputObject.nextLine();
        double[] xCoords;
        double[] yCoords;
        int subCount = 0;
        while(inputObject.hasNextLine() && subCount < totSub) //IT IS NOT CHE
        {
            System.out.println(regions.keySet());
            ArrayList<SubRegion> subregions = new ArrayList<>();

            inputObject.nextLine();
            String subRegionName = inputObject.nextLine();//.toUpperCase();  
            System.out.println(subRegionName);

            inputObject.nextLine();
            size = Integer.parseInt(inputObject.nextLine());
            count = 0;
            xCoords = new double[size];
            yCoords = new double[size];


            while(count < size)
            {
                String[] tempArr = inputObject.nextLine().split("   ");
                x = Double.parseDouble(tempArr[0]);
                y = Double.parseDouble(tempArr[1]);
                xCoords[count] = x;
                yCoords[count] = y;
                count++;

            }
            subCount++;


            SubRegion tempRegion = new SubRegion(subRegionName, xCoords, yCoords); //CREATE SUBREGION

            subregions.add(tempRegion);         //ADD TEMPREGION TO ARRAYLIST
            ArrayList<SubRegion> tempList = new ArrayList<>();
            if(regions.containsKey(subRegionName)) //IF THE KEY ALREADY EXISTS
            {
                    while(regions.get(subRegionName).size() > 1) //If the array list has multiple values then you do all this
                    {
                        ArrayList<SubRegion> origList = regions.get(subRegionName);
                        tempList.add(origList.get(regions.get(subRegionName).size() - 1));  //Add to templist
                        //regions.remove(subRegionName);               //Remove from original list

                    }
                    //System.out.println("AAAAAAAAAAAAAAAAAA");
                    regions.put(subRegionName, tempList);
            }
                else    //If the arrayList only has one value 
                {
                    //System.out.println("AAAAAAAAAAAAAAAAAA");
                    tempList.add(tempRegion);
                    regions.put(subRegionName, tempList);
       
                }
        }

        StdDraw.show();
        inputObject.close();

    }

    public static void draw()
    {
        /*String[] holdKeys = new String[pointsNum]; 
        Set<String> key = regions.keySet();
        holdKeys = key.toArray(holdKeys);
        
        for(int i = 0; i < holdKeys.length; i++)
        {
            holdKeys[i].get(); //OR TRY GOING THROUGH WHILE(NOTEMPTY) AND ANOTHER LOOPS
        }
        */
       

        
        
        //Issue with some counties not getting data filled
        //maybe i'm not standardizing the name. Capitalized 
        //String toUpperCase

        System.out.println("XXXXXXX: " + regions.get("Macon"));
        System.out.println("XXXXXXX2: " + regions.get("Franklin"));
                
        for(String key : regions.keySet())
            {
                //String key = tempKey.toUpperCase();
                for(SubRegion c : regions.get(key))
                {
                //SubRegion tempSub = tempSubArray.get(z);
                double[] tempXCoords = c.xCoords;
                double[] tempYCoords = c.yCoords;
                Color tempCol = c.col;
                StdDraw.setPenColor(tempCol);
                StdDraw.filledPolygon(tempXCoords, tempYCoords);
                StdDraw.setPenColor(StdDraw.BLACK); 
                System.out.println(key); //Not getting processed.
                }
                
            }
        
        StdDraw.show();

    }

}