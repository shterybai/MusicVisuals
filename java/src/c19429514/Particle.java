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
    }

    public void render() {
        // x = width/2;
        // y = 0;
        // yspeed = 1;
        AndrewsVisual.colorMode(HSB);
        AndrewsVisual.stroke(30, 255, 255);
        AndrewsVisual.line(x, y, x, y+10);
    }
}