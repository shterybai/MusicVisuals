package c19429514;

public class Particle extends AndrewsVisual {
    AndrewsVisual AndrewsVisual;
    float x;
    float y;
    float buffer = 200;

    public void setup() {
        setFrameSize(128);
    }
    
    public Particle(AndrewsVisual andrewsVisual, float x, float y) {
        AndrewsVisual = andrewsVisual;
        this.x = x;
        this.y = y;
    }

    public void fall() {
        y = y + AndrewsVisual.speed;

        if (y > AndrewsVisual.height) {
            y = random(-buffer);
        }
    }

    public void rise() {
        y = y - AndrewsVisual.speed;

        if (y < 0) {
            y = random(AndrewsVisual.height, AndrewsVisual.height + buffer);
        }
    }

    public void render() {
        // x = width/2;
        // y = 0;
        // yspeed = 1;
        AndrewsVisual.colorMode(HSB);
        AndrewsVisual.stroke(30, 255, AndrewsVisual.brightness);
        AndrewsVisual.line(x, y, x, y-20);
    }
}