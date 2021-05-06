package c19429514;

public class Particle extends AndrewsVisual {
    AndrewsVisual AndrewsVisual; // Allows PApplet to be used
    float x; // x position
    float y; // y position
    float buffer = 200; // Buffer to spawn and replace particles off-screen
    
    // Constructors
    public Particle(AndrewsVisual andrewsVisual, float x, float y) {
        AndrewsVisual = andrewsVisual;
        this.x = x;
        this.y = y;
    }

    // Allows particles to fall
    public void fall() {
        // Increment speed
        y = y + AndrewsVisual.speed;

        // Respawns particles above screen if they fall below screen
        if (y > AndrewsVisual.height) {
            y = random(-buffer);
        }
    }

    // Allows particles to rise
    public void rise() {
        // Decrement speed
        y = y - AndrewsVisual.speed;

        // Respawns particles below screen if they rise above screen
        if (y < 0) {
            y = random(AndrewsVisual.height, AndrewsVisual.height + buffer);
        }
    }

    // Generates particle visuals
    public void render() {
        AndrewsVisual.colorMode(HSB);
        AndrewsVisual.stroke(30, 255, AndrewsVisual.brightness, AndrewsVisual.brightness);
        AndrewsVisual.line(x, y, x, y-20);
    }
}