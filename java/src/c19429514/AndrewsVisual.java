package c19429514;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class AndrewsVisual extends Visual {
    boolean[] keys = new boolean[1024];
    float theta;
    float speed, brightness, saturation;
    Particle[] particles = new Particle[500];

    Button button1;
    Button button2;
    Button button3;

    public void settings()
    {
        // size(1920, 1080, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        fullScreen(P3D, 1);
    }

    public void keyPressed()
    {
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
        keys[keyCode] = true;
    }

    public void setup()
    {
        for(int i = 0; i < particles.length; i++) {
            particles[i] = new Particle(this, random(width), random(height));
        }
        colorMode(HSB);
        
        setFrameSize(256);

        startMinim();
        
        button1 = new Button(this, width/2 - width/3, height/8, "deadmau5 - Glish", true);
        button2 = new Button(this, width/2, height/8, "deadmau5 - Seeya", false);
        button3 = new Button(this, width/2 + width/3, height/8, "me!", false);

        if (button1.selected) {
            loadAudio("glish.mp3");
        }
        if (button2.selected) {
            loadAudio("seeya.mp3");
        }
        if (button3.selected) {
            loadAudio("demo.wav");
        }
        else {
            loadAudio("glish.mp3");
        }
        
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
        background(200, 255, map(getSmoothedAmplitude(), 0, 1, 0, 75));

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

            speed = map(h, 0, 10, 0, 15);
            brightness = map(h, 0, bands.length, 0, 150);
            // saturation = map(h, 0, bands.length, 100, 255);

            pushMatrix();
            translate(x, - h / 2 , z);

            camera(0, -300 + getSmoothedAmplitude()*300, 800 - getSmoothedAmplitude()*300, 0, 0, 0, 0, 1, 0);
            rotateY(theta);

            if (checkKey('y')) {
                camera(0, -300 + getSmoothedAmplitude()*300, 800 - getSmoothedAmplitude()*300, 0, 0, 0, 0, 1, 0);
                rotateY(theta);
            }
            else if (checkKey('x')) {
                camera(0, -600 + getSmoothedAmplitude()*300, 600 - getSmoothedAmplitude()*300, 0, 0, 0, 0, 1, 0);
                rotateX(theta);
            }
            // if (checkKey('a')) {
            //     rotleft = rotright;
            //     rotright = rotleft;
            //     System.out.println("a");
            // }
            if (checkKey('s')) {
                rotright = rotleft;
                rotleft = rotright;
            }
            else {
                rotleft = rotright;
                rotright = rotleft;
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
                stroke(map(getSmoothedAmplitude(), 0, 1, 0, 100), map(getSmoothedAmplitude(), 0, 1, 155, 255), map(getSmoothedAmplitude(), 0, 1, 50, 255)); 
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

        for(int i = 0; i < particles.length; i++) {

            particles[i].render();
            if (checkKey('s')) {
                particles[i].rise();
            }
            else {
                particles[i].fall();
            }
            
        }

        if (getAudioPlayer().isPlaying() == false) {
            checkSelectedButton();
            button1.render();
            button2.render();
            button3.render();
        }

    }

    void checkSelectedButton() {
        if (button1.selected) {
            button2.selected = false;
            button3.selected = false;
        }
        else if (button2.selected) {
            button1.selected = false;
            button3.selected = false;
        }
        else if (button3.selected) {
            button1.selected = false;
            button2.selected = false;
        }
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