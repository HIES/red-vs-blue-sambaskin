import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.util.HashMap;
import java.util.Set;
import java.util.*;

public class BlendedAmerica
{   

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

                //System.out.println(stringVotes[0]);
               // System.out.println(state);

                if(usa.get(state).containsKey(stringVotes[0]))
                {
                    ArrayList<SubRegion> tempList = usa.get(state).get(stringVotes[0]);   

                    //System.out.println(tempList);
                    double totV = regionVotes[0] + regionVotes[1] + regionVotes[2]; 
                    int rV = (int) (255.0*(regionVotes[0]*1.0/totV));
                    int bV = (int) (255.0*(regionVotes[1]*1.0/totV));
                    int gV = (int) (255.0*(regionVotes[2]*1.0/totV));
                    Color tempCol = new Color(rV, gV, bV);

                    for(int i = 0; i < tempList.size(); i++)
                    {
                        tempList.get(i).changeColor(tempCol);
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
            ArrayList<SubRegion> subregions = new ArrayList<>();   
            inputObject.nextLine();
            String subRegionName = inputObject.nextLine(); 
            //System.out.println("GeoData:"+ subRegionName);
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
                subRegionName = subRegionName.substring(0, removeIndex-1);
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

        }
        StdDraw.show();
        inputObject.close();
    }

    public static void draw(String year) throws Exception
    {
        for(String state : usa.keySet()){ //State 
            for(String county : usa.get(state).keySet()) //County
            {
                for(SubRegion c : usa.get(state).get(county))
                {
                    double[] tempXCoords = c.xCoords;
                    double[] tempYCoords = c.yCoords;
                    Color tempCol = c.col;
                    StdDraw.setPenColor(tempCol);
                    StdDraw.filledPolygon(tempXCoords, tempYCoords);
                    StdDraw.setPenColor(StdDraw.BLACK); 
                }
            }
        }          
        StdDraw.show();
    }

    public static void main(String regionName, String year) throws Exception
    {
        getGeoData(regionName);
        votingFill(year);
        draw(year);
    }

    }

