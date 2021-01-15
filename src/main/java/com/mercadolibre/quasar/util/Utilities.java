package com.mercadolibre.quasar.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilities {

    private static final double EPSILON = 0.001;

    private static final Logger LOG = LoggerFactory.getLogger(Utilities.class);

    public static String[] calculateThreeCircleIntersection(double satellite1X, double satellite1Y, double distance1,
                                                     double satellite2X, double satellite2Y, double distance2,
                                                     double satellite3X, double satellite3Y, double distance3)
    {
        String[] intertectionPosition = new String[]{null,null};
        double a, dx, dy, d, h, rx, ry;
        double point2_x, point2_y;

        /* dx and dy are the vertical and horizontal distances between
         * the circle centers.
         */
        dx = satellite2X - satellite1X;
        dy = satellite2Y - satellite1Y;

        /* Determine the straight-line distance between the centers. */
        d = Math.sqrt((dy*dy) + (dx*dx));

        /* Check for solvability. */
        if (d > (distance1 + distance2))
        {
            /* no solution. circles do not intersect. */
            return intertectionPosition;
        }
        if (d < Math.abs(distance1 - distance2))
        {
            /* no solution. one circle is contained in the other */
            return intertectionPosition;
        }

        /* 'point 2' is the point where the line through the circle
         * intersection points crosses the line between the circle
         * centers.
         */

        /* Determine the distance from point 0 to point 2. */
        a = ((distance1*distance1) - (distance2*distance2) + (d*d)) / (2.0 * d) ;

        /* Determine the coordinates of point 2. */
        point2_x = satellite1X + (dx * a/d);
        point2_y = satellite1Y + (dy * a/d);

        /* Determine the distance from point 2 to either of the
         * intersection points.
         */
        h = Math.sqrt((distance1*distance1) - (a*a));

        /* Now determine the offsets of the intersection points from
         * point 2.
         */
        rx = -dy * (h/d);
        ry = dx * (h/d);

        /* Determine the absolute intersection points. */
        double intersectionPoint1_x = point2_x + rx;
        double intersectionPoint2_x = point2_x - rx;
        double intersectionPoint1_y = point2_y + ry;
        double intersectionPoint2_y = point2_y - ry;

        /* Lets determine if circle 3 intersects at either of the above intersection points. */
        dx = intersectionPoint1_x - satellite3X;
        dy = intersectionPoint1_y - satellite3Y;
        double d1 = Math.sqrt((dy*dy) + (dx*dx));

        dx = intersectionPoint2_x - satellite3X;
        dy = intersectionPoint2_y - satellite3Y;
        double d2 = Math.sqrt((dy*dy) + (dx*dx));

        if(Math.abs(d1 - distance3) < EPSILON) {
            intertectionPosition[0] = String.format("%.1f",intersectionPoint1_x);
            intertectionPosition[1] = String.format("%.1f",intersectionPoint1_y);
            LOG.info("INTERSECTION Circle1 AND Circle2 AND Circle3: (" + String.format("%.1f",intersectionPoint1_x) + "," + String.format("%.1f",intersectionPoint1_y) + ")");
        }
        else if(Math.abs(d2 - distance3) < EPSILON) {
            intertectionPosition[0] = String.format("%.1f",intersectionPoint2_x);
            intertectionPosition[1] = String.format("%.1f",intersectionPoint2_y);
            LOG.info("INTERSECTION Circle1 AND Circle2 AND Circle3: (" + String.format("%.1f",intersectionPoint2_x)+ "," + String.format("%.1f",intersectionPoint2_y) + ")"); //here was an error
        }
        else {
            LOG.info("INTERSECTION Circle1 AND Circle2 AND Circle3: NONE");
        }

        return intertectionPosition;
    }

    public static String[] getMessage(String[]... messages){
        String[] finalMessage = new String[messages[0].length];
        for(int i = 0; i < messages[0].length; i++){
            for(String[] message : messages){
                if(!message[i].isEmpty()){
                    finalMessage[i] = message[i];
                }
            }
        }
        return finalMessage;
    }
}
