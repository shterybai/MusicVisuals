package c19429514;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class AndrewsVisual extends Visual {
    boolean[] keys = new boolean[1024];
    float theta;
    float speed, brightness, saturation; // Control particle attributes via frequencies
    Particle[] particles = new Particle[500]; // Instanciate 500 Particles

    Button button1; // First button - "deadmau5 - Glish"
    Button button2; // Second button - "deadmau5 - Seeya"
    Button button3; // Third button - "me!"

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
        }
        keys[keyCode] = true;
    }

    public void setup()
    {
        // Spawn particles
        for(int i = 0; i < particles.length; i++) {
            particles[i] = new Particle(this, random(width), random(height));
        }
        colorMode(HSB);
        
        setFrameSize(256);

        startMinim();
        
        // Instanciate buttons
        button1 = new Button(this, width/2 - width/3, height/8, "deadmau5 - Glish", true);
        button2 = new Button(this, width/2, height/8, "deadmau5 - Seeya", false);
        button3 = new Button(this, width/2 + width/3, height/8, "me!", false);

        loadAudio("glish.mp3");
    }

    float radius = 200;

    float smoothedBoxSize = 0;

    float rotleft = 0; // Rotate boxes right
    float rotright = 0; // Rotate rectangles right

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

        // Background turns slightly purple when volume is loud
        background(200, 255, map(getSmoothedAmplitude(), 0, 1, 0, 75));

        noFill();
        stroke(255);
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255); // Recangle colour

        rotleft += getAmplitude() / 16.0f; // Increment rotation
        rotright -= getAmplitude() / 16.0f; // Decrement rotation

        float[] bands = getSmoothedBands();

        for(int i = 0 ; i < bands.length ; i ++) {
            theta = map(i, 0, bands.length, 0, TWO_PI);

            stroke(map(i, 0, bands.length, 0, 255), 255, 255);
            float x = sin(theta) * radius;
            float z = cos(theta) * radius;
            float h = bands[i];

            // Change particle speed and brightness depending on music
            speed = map(h, 0, 10, 0, 15);
            brightness = map(h, 0, bands.length, 0, 150);

            // Translation for rectangles
            pushMatrix();
            translate(x, - h / 2 , z);

            // Default rotation of Y, camera changes position depending on amplitude
            camera(0, -300 + getSmoothedAmplitude()*300, 800 - getSmoothedAmplitude()*300, 0, 0, 0, 0, 1, 0);
            rotateY(theta);

            // Rotate X by theta when "x" is held, camera parameters changed slightly
            if (checkKey('x')) {
                camera(0, -600 + getSmoothedAmplitude()*300, 600 - getSmoothedAmplitude()*300, 0, 0, 0, 0, 1, 0);
                rotateX(theta);
            }

            // rotright and rotleft swapped when "s" is held
            if (checkKey('s')) {
                rotright = rotleft;
                rotleft = rotright;
            }
            else {
                rotleft = rotright;
                rotright = rotleft;
            }

            // Outer rectangles
            rotateY(rotright);
            box(700, h*2, 700);

            // Second from out
            rotateY(rotleft*2);
            box(500, h*3, 500);

            // Second from in
            rotateY(rotright*2);
            box(300, h*4, 300);

            // Inner rectangles
            rotateY(rotleft*2);
            box(100, h*5, 100);
            
            // End rectangles
            popMatrix();
        }

        noFill();

        // Second translation for sphere of dots
        pushMatrix();
        translate(width/2, height/2);
        float r = 2000; // radius of sphere

        int total = 100; // number of dots
        colorMode(HSB);

        // Change colour, brightness, and saturation of dots depending on amplitude
        for (int j = 0; j < bands.length; j++) {
            stroke(map(getSmoothedAmplitude(), 0, 1, 0, 100), map(getSmoothedAmplitude(), 0, 1, 155, 255), map(getSmoothedAmplitude(), 0, 1, 50, 255)); 
            strokeWeight(2);
        }

        // Generate dots in spherical pattern
        for (int j = 0; j < total; j++) {
            float lon = map(j, 0, total, -PI, PI);
            for (int k = 0; k < total; k++) {
                float lat = map(k, 0, total, -HALF_PI, HALF_PI);
                float x = r * sin(lon) * cos(lat);
                float y = r * sin(lon) * sin(lat);
                float z = r * cos(lon);
                point(x, y, z);
            }
        }

        // End translation
        popMatrix();

        // Render particles
        for(int i = 0; i < particles.length; i++) {
            particles[i].render();

            // Particles rise if "s" held, otherwise fall
            if (checkKey('s')) {
                particles[i].rise();
            }
            else {
                particles[i].fall();
            }
        }

        // Render buttons if music not playing
        if (getAudioPlayer().isPlaying() == false) {
            button1.render();
            button2.render();
            button3.render();
        }
    }

    // Aid in deselecting buttons, ensures only one button selected at a time
    void deselectOtherButtons(Button button) {
        if (button.song == button1.song) {
            button2.selected = false;
            button3.selected = false;
            loadAudio("glish.mp3");
        }
        else if (button.song == button2.song) {
            button1.selected = false;
            button3.selected = false;
            loadAudio("seeya.mp3");
        }
        else if (button.song == button3.song) {
            button1.selected = false;
            button2.selected = false;
            loadAudio("demo.wav");
        }
    }

    // Checks what keys are being held down
    boolean checkKey(int k) {
        if (keys.length >= k) {
            return keys[k] || keys[Character.toUpperCase(k)];
        }
        return false;
    }

    // Frees up checkKey when key isn't being held down
    public void keyReleased() {
        keys[keyCode] = false;
    }

}