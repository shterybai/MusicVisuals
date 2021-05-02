package c19429514;

public class Particle extends AndrewsVisual {
    AndrewsVisual AndrewsVisual;
    float x;
    float y;
    float yspeed;

    public Particle(AndrewsVisual andrewsVisual, float x, float y, float yspeed) {
        AndrewsVisual = andrewsVisual;
        this.x = x;
        this.y = y;
        this.yspeed = yspeed;
    }

    public void fall() {
        y = y + yspeed;

        if (y > AndrewsVisual.height) {
            y = random(-200);
        }
    }

    public void render() {
        // x = width/2;
        // y = 0;
        // yspeed = 1;
        float b = AndrewsVisual.map(getSmoothedAmplitude(), 0, 1, 150, 255);
        AndrewsVisual.colorMode(HSB);
        AndrewsVisual.stroke(30, 255, b);
        AndrewsVisual.line(x, y, x, y+10);
    }
}