import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;

public class ElectoralMap
{   

    //TO DO:
    //COMPELTE SUB REGION CLASS FIELD CONSTRUCTURO
    // ADD COMMENTS/NOTES TO ELECTORAL MAP CLASS

    // VERIFY THAT ELECTORAL MAP CAN STILL DRAW AN EMPTY MAP (ALSO GET POLYGON WORKING)

    private class subRegion
    {
        String name;
        double[] xCoords;
        double[] yCoords;
        int[] votes;
        public subRegion(String nameRegion, double[] xCoord, double[] yCoord, Color colorName, int[] voteArray)
        {
            String name = nameRegion;
            xCoords = xCoord;
            yCoords = yCoord;
            votes = voteArray;
            Color col = colorName;
        }

        public void changeName(String newName)
        {
            name = newName;
        }

        public void changeVote(int[] newVote)
        {
            vote = newVote;
        }
        

    }

    
    public static void votingFill()
    {
        //Open voting data file
        //Fill each subRegion with voting data
        //
    }

    public static void coordsFill()
    {
        
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

        //TAKE DATA FROM X AND Y COORDS
        String[] firstLine = new String[2];
        String[] secondLine = new String[2];
        File f = new File("./input/"+regionName+".txt");
        Scanner inputObject = new Scanner(f);
        String line = inputObject.nextLine();   //Line1 lat/long min
        String line2 = inputObject.nextLine();  //Line2 lat/long max
        String line3 = inputObject.nextLine();  // # of subregions                                        //Line3 #subregions
        //StdDraw.enableDoubleBuffering();
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
        while(inputObject.hasNextLine())
        {
            double[] xCoords = new double[size];
            double[] yCoords = new double[size];
            String[] tempArr = inputObject.nextLine().split("   ");
            
            if(tempArr.length > 1)
            {
                //TO DO: define size at some point
                if(count > 0)
                {
                    px = x;
                    py = y;
                    
                    x = Double.parseDouble(tempArr[0]);
                    y = Double.parseDouble(tempArr[1]);
                    xCoords[i] = x;
                    yCoords[i] = y;
                    i++;
                    System.out.println("B");
                    //StdDraw.line(px, py, x, y);
                }
                else
                {
                 xCoords = new double[size];
                 yCoords = new double[size];
                 
                 x = Double.parseDouble(tempArr[0]);
                 y = Double.parseDouble(tempArr[1]);
                 //StdDraw.line(x, y, x, y);   
                 
                 count++;   
                }
            }
            else
            {
                inputObject.nextLine();
                inputObject.nextLine();
            
                size = Integer.parseInt(inputObject.nextLine());
            }
                count = 0;
                System.out.println("A");
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.polygon(xCoords, yCoords);
                StdDraw.show();
            }

        StdDraw.show();
        inputObject.close();

        
    }

}