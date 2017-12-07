import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;
import java.awt.Font;
public class EmptyMap
{
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

        StdDraw.setCanvasSize(512*(xSize/ySize), 512);
        StdDraw.setXscale(dub1[0], dub2[0]);
        StdDraw.setYscale(dub1[1], dub2[1]); 

        StdDraw.circle(500, 500, 2000);
        StdDraw.point(500, 500);
        while(inputObject.hasNextLine())
        {

            String[] tempArr = inputObject.nextLine().split("   ");
            if(tempArr.length > 1)
            {
                if(count > 0)
                {
                    px = x;
                    py = y;
                    
                    x = Double.parseDouble(tempArr[0]);
                    y = Double.parseDouble(tempArr[1]);
                    StdDraw.line(px, py, x, y);
                }
                else
                {
                 x = Double.parseDouble(tempArr[0]);
                 y = Double.parseDouble(tempArr[1]);
                 StdDraw.line(x, y, x, y);              
                 count++;   
                }
            }
            else
            {
                count = 0;
            }

        }
        inputObject.close();

        StdDraw.show();
    }
}