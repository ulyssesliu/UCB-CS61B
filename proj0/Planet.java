public class Planet {
    // constant
    static double G = 6.67E-11;

    // variables
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    // constructors
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass  = m;
        this.imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos       = p.xxPos;
        this.yyPos       = p.yyPos;
        this.xxVel       = p.xxVel;
        this.yyVel       = p.yyVel;
        this.mass        = p.mass;
        this.imgFileName = p.imgFileName;
    }

    // methods
    public double calcDistance(Planet p){
        return Math.sqrt((p.xxPos - this.xxPos)*(p.xxPos - this.xxPos) + (p.yyPos - this.yyPos)*(p.yyPos - this.yyPos));
    }
    
    public double calcForceExertedBy(Planet p){
        return Planet.G * this.mass * p.mass / calcDistance(p) / calcDistance(p); 
    }

    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p)*(p.xxPos-this.xxPos)/(calcDistance(p));
    }


    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p)*(p.yyPos-this.yyPos)/(calcDistance(p));
    }

    public double calcNetForceExertedByX(Planet[] p_arr){
        double Force = 0;
        for (int i = 0; i < p_arr.length; i++){
            if(this.equals(p_arr[i]))
                continue;
            else
                Force = Force + calcForceExertedByX(p_arr[i]);
        }
        return Force;
    }

    public double calcNetForceExertedByY(Planet[] p_arr){
        double Force = 0;
        for (Planet p : p_arr){ // using iterator for loop (less verbose)
            if(this.equals(p))
                continue;
            else
                Force = Force + calcForceExertedByY(p);
        }
        return Force;
    }

    public void update(double period, double xForce, double yForce){
        // accerlarations
        double xAcc = xForce/this.mass;
        double yAcc = yForce/this.mass;
        // update the velocities
        this.xxVel += period*xAcc;
        this.yyVel += period*yAcc;
        //update the positions
        this.xxPos += period*xxVel;
        this.yyPos += period*yyVel;
    }

    public static void draw(Planet[] pArr, double R){
        for (Planet p : pArr){
            StdDraw.picture(p.xxPos/2/R + 0.5, p.yyPos/2/R + 0.5, "images/" + p.imgFileName);
        }
    }
    
}
