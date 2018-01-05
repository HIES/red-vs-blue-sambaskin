import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.util.HashMap;
import java.util.Set;
import java.util.*;

public class ElectoralMap
{   
    //Outer dude. Find GA
    //Just get county voting data, don't even need subregions.
    //
    //private static HashMap<String, ArrayList<SubRegion>> regions = new HashMap<>();   
    private static HashMap<String, HashMap<String, ArrayList<SubRegion>>> usa = new HashMap<>();
    public static class SubRegion
    {

        private String name;
        private double[] xCoords;
        private double[] yCoords;
        private int[] votes;
        private Color col = StdDraw.BLACK;
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

    public static void votingFill(String year) throws Exception
    {
        //get keys and loop over by calling keysety
        //I only need to pass year first time

        for(String state: usa.keySet()){ 
            int[] regionVotes = new int[3];

            File f = new File("./input/"+state+year+".txt");
            Scanner inputObject = new Scanner(f);
            inputObject.nextLine();   //Line1 lat/long min

            while(inputObject.hasNextLine())
            {

                String line = inputObject.nextLine();
                String stringVotes[] = line.split(",");
                regionVotes[0] = Integer.parseInt(stringVotes[1]); //Republican
                regionVotes[1] = Integer.parseInt(stringVotes[2]); //Democrat
                regionVotes[2] = Integer.parseInt(stringVotes[3]); //Independent

                // System.out.println("VotingFill: " + regions.keySet()); 
                

                //ArrayList<SubRegion> tempList = regions.get(stringVotes[0]);
                //System.out.println("DEBUG");
                //System.out.println(usa.get(state));
                System.out.println(stringVotes[0]);
                System.out.println(state);
                if(usa.get(state).containsKey(stringVotes[0]))
                {
                    
                
                
                ArrayList<SubRegion> tempList = usa.get(state).get(stringVotes[0]); //ArrayList of subRegions
                //System.out.println(tempList);

                
                if(regionVotes[1] > regionVotes[0] && regionVotes[1] > regionVotes[2])
                {
                    for(int i = 0; i < tempList.size(); i++) //Go through arrayList of subregion obejcts
                    {
                        tempList.get(i).changeColor(StdDraw.BLUE); //Change color of subRegion object to Blue
                    }

                }
                else if(regionVotes[0] > regionVotes[1] && regionVotes[0] > regionVotes[2])
                {
                    for(int i = 0; i < tempList.size(); i++)
                    {
                        tempList.get(i).changeColor(StdDraw.RED); //Change color of subRegion object to Red
                    }

                }
                else
                {
                    for(int i = 0; i < tempList.size(); i++)
                    {
                        tempList.get(i).changeColor(StdDraw.GREEN);
                    }
                }
            }
        }
        }

    }

    public static void getGeoData(String regionName) throws Exception
    {
        String[] firstLine = new String[2];
        String[] secondLine = new String[2];
        File f = new File("./input/"+regionName+".txt");
        Scanner inputObject = new Scanner(f);
        String line = inputObject.nextLine();   //Line1 lat/long min
        String line2 = inputObject.nextLine();  //Line2 lat/long max
        String line3 = inputObject.nextLine();                                        
        int totSub = Integer.parseInt(line3);   //total numer of subregions
        StdDraw.enableDoubleBuffering();
        double[] dub1 = new double[2];
        double[] dub2 = new double[2];

        firstLine = line.split("   ");
        secondLine = line2.split("   ");
        int count = 0;
        double x = 0;
        double y = 0;

        for(int z = 0; z < firstLine.length; z++)
        {
            dub1[z] = Double.parseDouble(firstLine[z]);
            dub2[z] = Double.parseDouble(secondLine[z]);
        }

        int xSize = (int) (dub2[0] - dub1[0]);
        int ySize = (int) (dub2[1] - dub1[1]);
        int size = 0;
        StdDraw.setCanvasSize(512*(xSize/ySize), 512);
        StdDraw.setXscale(dub1[0], dub2[0]);
        StdDraw.setYscale(dub1[1], dub2[1]);

        int i = 0;
        boolean isFirst = true;

        double[] xCoords;
        double[] yCoords;
        int subCount = 0;
        while(inputObject.hasNextLine() && subCount < totSub) //While the end of the file hasn't been reached
        {
            //DISCARD PARISH OR CITY OR COUNTY FROM SUBANME. CONDITIONAL GOES HERE

            ArrayList<SubRegion> subregions = new ArrayList<>();   

            inputObject.nextLine();
            String subRegionName = inputObject.nextLine(); 
            System.out.println("GeoData:"+ subRegionName);

            String outerRegionName = inputObject.nextLine();
            size = Integer.parseInt(inputObject.nextLine());
            if(subRegionName.contains("city") || subRegionName.contains("county") || subRegionName.contains("Parish"))
            {
                int removeIndex = subRegionName.length(); 
                if(subRegionName.indexOf("city") > subRegionName.indexOf("county") && subRegionName.indexOf("city") > subRegionName.indexOf("Parish"))
                {
                    removeIndex = subRegionName.indexOf("city");
                }
                else if(subRegionName.indexOf("county") > subRegionName.indexOf("city") && subRegionName.indexOf("county") > subRegionName.indexOf("Parish"))
                {
                    removeIndex = subRegionName.indexOf("county");
                }
                else
                {
                    removeIndex = subRegionName.indexOf("Parish");
                }
                subRegionName = subRegionName.substring(removeIndex-1);
            }
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
            SubRegion tempOuterRegion = new SubRegion(outerRegionName,xCoords, yCoords); 
            if(usa.containsKey(outerRegionName))
            {
                if(usa.get(outerRegionName).containsKey(subRegionName)) //IF THE KEY ALREADY EXISTS
                {
                    usa.get(outerRegionName).get(subRegionName).add(tempRegion);
                    //regions.get(subRegionName).add(tempRegion);
                    usa.get(outerRegionName).put(subRegionName, usa.get(outerRegionName).get(subRegionName));

                }
                else    //If the arrayList only has one value 
                {
                    ArrayList<SubRegion> newList = new ArrayList<>();
                    newList.add(tempRegion);
                    usa.get(outerRegionName).put(subRegionName, newList);

                }
            }   
            else
            {
                
                HashMap<String, ArrayList<SubRegion>> innerMap = new HashMap<>();
                ArrayList<SubRegion> subRegionList = new ArrayList<>();
                subRegionList.add(tempRegion);
                innerMap.put(subRegionName, subRegionList);
                usa.put(outerRegionName, innerMap);
            }

            /*if(usa.containsKey(outerRegionName))
            {
            //usa.get(STATE).get(COUNTY)
            if(usa.get(outerRegionName).containsKey(subRegionName))
            {
            usa.get(outerRegionName).get(subRegionName).add(tempRegion);
            //COllection of states - Collection of counties- 
            }
            else
            {
            usa.get(outerRegionName).add(//Fulton)
            //Hashmap <string, arraylist>
            }
            //put(tempOuterRegion);
            }
             */
        }

        StdDraw.show();
        inputObject.close();

    }

    public static void draw(String year) throws Exception
    {

        for(String state : usa.keySet()){ //State 
            //votingFill(state.name, year);
            for(String county : usa.get(state).keySet()) //County
            {
                //  votingFill(county.name, year);
                for(SubRegion c : usa.get(state).get(county))
                {
                    //Each county has a color already. Here we just cycle through counties
                    double[] tempXCoords = c.xCoords;
                    double[] tempYCoords = c.yCoords;
                    //Get state w/ same name + year
                    //votingFill(c.name, year);
                    Color tempCol = c.col;
                    StdDraw.setPenColor(tempCol);
                    StdDraw.filledPolygon(tempXCoords, tempYCoords);
                    StdDraw.setPenColor(StdDraw.BLACK); 
                    System.out.println("Drawing: " + county); 
                }

            }
        }          

        StdDraw.show();

    }

    public static void main(String regionName, String year) throws Exception
    {
        //regionName = usa-county
        //HashMap usa = [usa-county geoData]
        //getGeoData("HOW DO WE GET STATE FOR THIS?" GET THE NAME OF IT IN THE OTHER GEODATA SO IT CALLS THE "GA" ONCE IT SEES IT
        //maybe like a for loop to go through all the stuff for votingFill and whatnot lol
        //votingFill(
        //usa.get(STATE).get(COUNTY)
        getGeoData(regionName);
        votingFill(year);
        draw(year);
        //Have everything below the hashmap already set. Just need to be able to get subRegions

        //Fill GeoData for usa (GA) and create hashmap  usa.get(STATE)
        //That's the region hashmap. Just put that in.
        //Find voting data  usa.get(GA).get(Fulton) = arrayList (cycle through arrayList and fill each with the color)

        //Modifiyng in the geoData to get GA and FULTON GA goes into the USA hashmap and FULTON goes into the GA hashmap
        //we're ignoring states and GA rn. Change it so we do the same as county but with a new thang ;) <3l

    }

}