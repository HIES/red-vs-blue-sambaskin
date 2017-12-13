import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.util.HashMap;

public class ElectoralMap
{   
    HashMap<String, ArrayList<SubRegion>> region = new HashMap<>();
    //TO DO:
    //COMPELTE SUB REGION CLASS FIELD CONSTRUCTURO
    // ADD COMMENTS/NOTES TO ELECTORAL MAP CLASS

    // VERIFY THAT ELECTORAL MAP CAN STILL DRAW AN EMPTY MAP (ALSO GET POLYGON WORKING)

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
            xCoords = xCoord;
            yCoords = yCoord;
  
        }

        public void changeColor(Color newColor)
        {
            color = newColor;
        }

        public void changeVote(int[] newVote)
        {
            votes = newVote;
        }

    }
    public static void main()
    {
        SubRegion testLocation = new SubRegion testLocation();
        ArrayList<SubRegion> subregions = new ArrayList<>();
        test.add(
        region.put("Georgia", test);
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
            if(regionVotes[0] > regionVotes[1] && regionVotes[0] > regionVotes[2])
            {
                //SUB REGION = RED
            }
            else if(regionVotes[1] > regionVotes[0] && regionVotes[1] > regionVotes[2])
            {
                //SUB REGION = BLUE
            }
            else
            {
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

    public static void coordsFill()
    {
        //USE HASHMAP HERE SINCE YOU NEED T OFILL ONE COLOR OR OTHER
        //fills xCoords and yCoords in each subRegion 
        //
        //(similar to emptyMap code) 

    }

    public static void draw()
    {
        //Call the visualize function, using the xCoords and yCoords for the polygons
        //Use the votingFill() method to determine the color of the polygon
        //Create polygons 
        //Fill each area with colors using vote array (whichever vote has more)
        //

    }

    public static void visualize(String regionName) throws Exception
    {
        String[] firstLine = new String[2];
        String[] secondLine = new String[2];
        File f = new File("./input/"+regionName+".txt");
        Scanner inputObject = new Scanner(f);
        String line = inputObject.nextLine();   //Line1 lat/long min
        String line2 = inputObject.nextLine();  //Line2 lat/long max
        String line3 = inputObject.nextLine();  // # of subregions                                        //Line3 #subregions
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

        int xSize = (int) (dub2[0] - dub1[0]);
        int ySize = (int) (dub2[1] - dub1[1]);
        int size = 0;

        StdDraw.setCanvasSize(512*(xSize/ySize), 512);
        StdDraw.setXscale(dub1[0], dub2[0]);
        StdDraw.setYscale(dub1[1], dub2[1]); 

        StdDraw.circle(500, 500, 2000);
        StdDraw.point(500, 500);
        int i = 0;
        boolean isFirst = true;
        //inputObject.nextLine();
        double[] xCoords;
        double[] yCoords;
        while(inputObject.hasNextLine())
        {
            ArrayList<SubRegion> subregions = new ArrayList<>();
            //TO DO: define size at some point
            //While loop witht the number of coordinates, not a flag. Know where scanner is at all time
            //while has next line
            inputObject.nextLine();
            String tempName = inputObject.nextLine();
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
            
            SubRegion tempRegion = new SubRegion(tempName, xCoords, yCoords); //CREATE SUBREGION
            subregions.add(tempRegion);         //ADD TEMPREGION TO ARRAYLIST
            if(regions.containsKey(tempName))
            {
                //GET THE ARRAY LIST AND ITS VALUES. 
                //ADD THOSE VALUES TO A NEW ARRAY LIST
                //ADD NEW ARRAY LIST TO A NEW KEY
            }
            regions.put(tempName, subRegions);  //ADD TO HASH MAP
            //YO SAM I'M WORKING DOWN HERE!!!!! THIS IS WHERE YOU LEFT OFF, SILLY WILLY! (left off up there^)
            //Also conditional to see if the key already has a value, if it does take the value and add it back again 
            //
            StdDraw.polygon(xCoords, yCoords);

        }

        StdDraw.show();
        inputObject.close();

    }

}