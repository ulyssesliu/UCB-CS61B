public class NBody {
    public static double readRadius(String path){
        In in = new In(path);

        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);

        int pNum = in.readInt();
        in.readDouble();
        
        Planet[] p_arr = new Planet[pNum];
        for(int i = 0; i < pNum; i++){
            p_arr[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return p_arr;
    }

    public static void main(String[] args){
        // cmd arguments
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        // import initial status for universe and planets 
        double radius = NBody.readRadius(filename);
        Planet[] pArr = NBody.readPlanets(filename);
        String background = "./images/starfield.jpg";

        // runtime count and number of planets
        int runtime = 0;
        int pNum = pArr.length;

        // where the forces stores
        double[] xForces = new double[pNum];
        double[] yForces = new double[pNum];

        // where simulations run
        StdDraw.enableDoubleBuffering();
        while(runtime < T){
            // store the forces
            for (int i = 0; i < pNum; i++){
                xForces[i] = pArr[i].calcNetForceExertedByX(pArr);
                yForces[i] = pArr[i].calcNetForceExertedByY(pArr);
            }
            for (int i = 0; i < pNum; i++){
                pArr[i].update(dt, xForces[i], yForces[i]);
            }

            // draw the background starfield
            StdDraw.picture(0.5,0.5,background);
            // draw the planets and sun.
            Planet.draw(pArr, radius);
            StdDraw.show();
            StdDraw.pause(10);

            runtime+=20000;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}

        
        
    }
    
}
