package example;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class RotatingAudioBands extends Visual {
    boolean[] keys = new boolean[1024];
    float theta;

    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        //fullScreen(P3D, SPAN);
    }

    public void keyPressed()
    {

        // if (getAudioPlayer().position() == 0) {
        //     key = 'y';
        // }
        if (key == ' ')
        {
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
            }
            else {
                getAudioPlayer().play();
            }
            // getAudioPlayer().cue(0);
            
        }

        // if (checkKey('y')) {
        //     camera(0, -300 + getSmoothedAmplitude()*500, 800 - getSmoothedAmplitude()*500, 0, 0, 0, 0, 1, 0);
        //     rotateY(theta);
        //     System.out.println("y");
        // }
        // else if (checkKey('x')) {
        //     camera(0, -600 + getSmoothedAmplitude()*400, 600 - getSmoothedAmplitude()*400, 0, 0, 0, 0, 1, 0);
        //     rotateX(theta);
        //     System.out.println("x");
        // }
        // if (checkKey('a')) {
        //     rotleft = rotright;
        //     rotright = rotleft;
        //     System.out.println("a");
        // }
        // else if (checkKey('d')) {
        //     rotright = rotleft;
        //     rotleft = rotright;
        //     System.out.println("d");
        // }

        keys[keyCode] = true;
 
    }

    public void setup()
    {
        colorMode(HSB); 
        // key = 'y';
        
        setFrameSize(256);

        startMinim();
        loadAudio("glish.mp3");
        // getAudioPlayer().play();
        //startListening();S
        
    }

    float radius = 200;

    float smoothedBoxSize = 0;

    float rotleft = 0;
    float rotright = 0;

    int c;

    boolean spinLeft = false;

    public void draw()
    {
        calculateAverageAmplitude();
        try
        {
            calculateFFT();
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        // background(0);
        background(200, 255, map(getSmoothedAmplitude(), 0, 1, 0, 100));

        noFill();
        stroke(255);
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);

        // rotateY(PI / 6);
        // camera(-pmouseX, -pmouseY, 500, 0, 0, 0, 0, 1, 0);
        // camera(0, -600, 600, 0, 0, 0, 0, 1, 0);
        // translate(0, 0, -250);

        rotleft += getAmplitude() / 16.0f;
        rotright -= getAmplitude() / 16.0f;

        float[] bands = getSmoothedBands();

        // camera(0, -300 + getSmoothedAmplitude()*500, 800 - getSmoothedAmplitude()*500, 0, 0, 0, 0, 1, 0);

        for(int i = 0 ; i < bands.length ; i ++) {
            // background(200, 255, map(getSmoothedAmplitude(), 0, 1, 0, 100));

            theta = map(i, 0, bands.length, 0, TWO_PI);

            stroke(map(i, 0, bands.length, 0, 255), 255, 255);
            float x = sin(theta) * radius;
            float z = cos(theta) * radius;
            float h = bands[i];
            pushMatrix();
            translate(x, - h / 2 , z);

            camera(0, -300 + getSmoothedAmplitude()*300, 800 - getSmoothedAmplitude()*300, 0, 0, 0, 0, 1, 0);
            rotateY(theta);

            if (checkKey('y')) {
                camera(0, -300 + getSmoothedAmplitude()*300, 800 - getSmoothedAmplitude()*300, 0, 0, 0, 0, 1, 0);
                rotateY(theta);
                System.out.println("y");
            }
            else if (checkKey('x')) {
                camera(0, -600 + getSmoothedAmplitude()*300, 600 - getSmoothedAmplitude()*300, 0, 0, 0, 0, 1, 0);
                rotateX(theta);
                System.out.println("x");
            }
            // if (checkKey('a')) {
            //     rotleft = rotright;
            //     rotright = rotleft;
            //     System.out.println("a");
            // }
            if (checkKey('s')) {
                rotright = rotleft;
                rotleft = rotright;
                System.out.println("s");
            }
            else {
                rotleft = rotright;
                rotright = rotleft;
                System.out.println("a");
            }

            rotateY(rotright);
            box(700, h*2, 700);

            rotateY(rotleft*2);
            box(500, h*3, 500);

            rotateY(rotright*2);
            box(300, h*4, 300);

            rotateY(rotleft*2);
            box(100, h*5, 100);
            
            popMatrix();
        }

        // for(int i = 0 ; i < bands.length ; i ++) {
            noFill();
            pushMatrix();
            translate(width/2, height/2);
            float r = 2000;

            int total = 100;
            colorMode(HSB);

            for (int j = 0; j < bands.length; j++) {
                float b = map(j, 0, width, 0, 100);
                stroke(0 , map(getSmoothedAmplitude(), 0, 1, 0, 255), b); 
                strokeWeight(2);
            }
            rotateX(PI);
            for (int j = 0; j < total; j++) {
                float lon = map(j, 0, total, -PI, PI);
                for (int k = 0; k < total; k++) {
                    float lat = map(k, 0, total, -HALF_PI, HALF_PI);
                    float x = r * sin(lon) * cos(lat);
                    float y = r * sin(lon) * sin(lat);
                    float z = r * cos(lon);
                    // stroke(255, 100, 100);
                    point(x, y, z);
                    // circle(width / 2, height / 2, lon);
                }
            }
            popMatrix();
        // }

    }

    float angle = 0;

    boolean checkKey(int k) {
        if (keys.length >= k) {
            return keys[k] || keys[Character.toUpperCase(k)];
        }
        return false;
    }

    public void mousePressed() {
    }

    public void keyReleased() {
        keys[keyCode] = false;
    }

}