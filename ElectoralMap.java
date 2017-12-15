import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.util.HashMap;

public class ElectoralMap
{   
    HashMap<String, ArrayList<SubRegion>> regions = new HashMap<>();    //
    ArrayList<String> subRegionNames = new ArrayList<>(); //allows for hashmap traverse
    //TO DO:
    //COMPELTE SUB REGION CLASS FIELD CONSTRUCTURO
    // ADD COMMENTS/NOTES TO ELECTORAL MAP CLASS

    // VERIFY THAT ELECTORAL MAP CAN STILL DRAW AN EMPTY MAP (ALSO GET POLYGON WORKING)
    private int xSize = 0;
    private int ySize = 0;
    private  double[] dub1;
    private double[] dub2;
    private class SubRegion
    {
        private String name;
        private double[] xCoords;
        private double[] yCoords;
        private int[] votes;
        private Color col;
        public SubRegion(String nameRegion, double[] xCoord, double[] yCoord)
        {
            name = nameRegion;
            //visualize(nameRegion);
            xCoords = xCoord;
            yCoords = yCoord;
  
        }

        public void changeColor(Color newColor)
        {
            col = newColor;
        }

        public void changeVote(int[] newVote)
        {
            votes = newVote;
        }

    }
    public void main(String regionName, String regionNameYear) throws Exception
    {
        getGeoData(regionName);
        votingFill(regionNameYear);
        draw();
        //Geo data method
        //Voting Fill
        //Draw
        
        
        //SubRegion testLocation = new SubRegion("GA");
        //visualize("GA")
        //votingFill("GA1980");
        //ArrayList<SubRegion> subregions = new ArrayList<>();
       // test.add(
      //  region.put("Georgia", test);
    }
    public void votingFill(String regionNameYear) throws Exception
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
            if(regionVotes[0] > regionVotes[1] && regionVotes[0] > regionVotes[2])
            {
                for(int i = 0; i < tempList.size(); i++)
                {
                    tempList.get(i).changeColor(StdDraw.RED);
                }

            }
            else if(regionVotes[1] > regionVotes[0] && regionVotes[1] > regionVotes[2])
            {
                //ArrayList<SubRegion> tempList = regions.get(stringVotes[0]);
                for(int i = 0; i < tempList.size(); i++)
                {
                    tempList.get(i).changeColor(StdDraw.BLUE);
                }
                //return StdDraw.BLUE();
                //SUB REGION = BLUE
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


    

    public void getGeoData(String regionName) throws Exception
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
            //
            System.out.println(regions.keySet());
            ArrayList<SubRegion> subregions = new ArrayList<>();
            //TO DO: define size at some point
            //While loop witht the number of coordinates, not a flag. Know where scanner is at all time
            //while has next line
            inputObject.nextLine();
            String subRegionName = inputObject.nextLine();   //USE TEMP NAME TO LOOK FOR FILL
            System.out.println(subRegionName);
            //inputObject.nextLine();
            inputObject.nextLine();
            size = Integer.parseInt(inputObject.nextLine());
            count = 0;
            xCoords = new double[size];
            yCoords = new double[size];
            //String[] tempArr = inputObject.nextLine().split("   ");
                
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
            
            ///WATCH OUT FOR THIS???? WHATS SUBREGION'S ROLE
            //ADDS DATA TO A SUBREGION
            SubRegion tempRegion = new SubRegion(subRegionName, xCoords, yCoords); //CREATE SUBREGION
            
            subregions.add(tempRegion);         //ADD TEMPREGION TO ARRAYLIST
            ArrayList<SubRegion> tempList = new ArrayList<>();
            if(regions.containsKey(subRegionName))
            {
                if(regions.get(subRegionName).size() > 1)
                {
                    while(regions.get(subRegionName).size() > 1) //If the array list has multiple values then you do all this
                    {
                        ArrayList<SubRegion> origList = regions.get(subRegionName);
                        tempList.add(origList.get(regions.get(subRegionName).size() - 1));  //Add to templist
                        //regions.remove(subRegionName);               //Remove from original list
                       
                    }
                    System.out.println("AAAAAAAAAAAAAAAAAA");
                    regions.put(subRegionName, tempList);
   
                }
                else    //If the arrayList only has one calue 
                {
                System.out.println("AAAAAAAAAAAAAAAAAA");
                subRegionNames.add(subRegionName);
                tempList.add(regions.get(subRegionName).get(0));
                regions.put(subRegionName, tempList);
                //regions.get(tempName).get(0);
                }
                //GET THE ARRAY LIST AND ITS VALUES. 
                //ADD THOSE VALUES TO A NEW ARRAY LIST
                //ADD NEW ARRAY LIST TO A NEW KEY
            }
              //THIS SETS UP A KEY
            //TEMPNAME GET SUBREGION FROM TEMPLIST AND GET THE COLOR
            
            //OR LOOP THROUGH AND FIND TEMPNAME 
            //StdDraw.polygon(xCoords, yCoords);

        }

        StdDraw.show();
        inputObject.close();

    }
    
    public void draw()
    {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(512*(xSize/ySize), 512);
        StdDraw.setXscale(dub1[0], dub2[0]);
        StdDraw.setYscale(dub1[1], dub2[1]); 

        //TRAVERSE THROUGH HASHMAP
        for(int i = 0; i < subRegionNames.size(); i++)
        {
           String subName = subRegionNames.get(i);
           ArrayList<SubRegion> tempSubArray = regions.get(subName);
           for(int z = 0; z < tempSubArray.size(); z++)
           {
               SubRegion tempSub = tempSubArray.get(z);
               double[] tempXCoords = tempSub.xCoords;
               double[] tempYCoords = tempSub.yCoords;
               Color tempCol = tempSub.col;
               StdDraw.setPenColor(tempCol);
               StdDraw.filledPolygon(tempXCoords, tempYCoords);
               StdDraw.setPenColor(StdDraw.BLACK);
           }
           
            
        }
        
        //Call the visualize function, using the xCoords and yCoords for the polygons
        //Use the votingFill() method to determine the color of the polygon
        //Create polygons 
        //Fill each area with colors using vote array (whichever vote has more)
        //
        //LOOPS THROUGH AND FINDS COLOR DATA AND X/Y DATA AND ACTUALLY MAKES POLYGONS
        
        //StdDraw.polygon(xCoords, yCoords);

    }

}