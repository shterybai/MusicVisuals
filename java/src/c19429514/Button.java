package c19429514;

import processing.core.PVector;

public class Button extends AndrewsVisual{
    AndrewsVisual AndrewsVisual; // Allows PApplet to be used
    float x; // x position
    float y; // y position
    String song; // Song name
    PVector size; // Size of PVector
    boolean hover, carat; // Booleans for hovering and clicking on buttons
    boolean selected = false; // Boolean for if a button is selected or not

    // Constructors
    public Button(AndrewsVisual andrewsVisual, float x, float y, String song, boolean selected) {
        AndrewsVisual = andrewsVisual;
        this.x = x;
        this.y = y;
        this.song = song;
        this.selected = selected;
        size = new PVector(300, 100); // Instanciating PVector for button size
    }

    // Set colours of buttons depending on if they're hovered, selected, or neither
    public void setColor(int r, int g, int b) {
        AndrewsVisual.stroke(r, g, b);
    }

    // Render buttons
    public void render() {
        AndrewsVisual.rectMode(CENTER);
        AndrewsVisual.textAlign(CENTER);
        AndrewsVisual.textSize(24);
        AndrewsVisual.colorMode(HSB);
        AndrewsVisual.noFill();

        // Check if mouse is over buttons and carat isn't visible
        if (AndrewsVisual.mouseX < x + size.x/2 && AndrewsVisual.mouseY < y + size.y/2 && AndrewsVisual.mouseX > x - size.x/2 && AndrewsVisual.mouseY > y - size.y/2 && carat == false) {
            hover = true;

            // Mouse press check
            if (AndrewsVisual.mousePressed == true) {
                selected = true;
                carat = true;
            }
        }

        // Deselects button when another button is clicked
        else if (carat == true && AndrewsVisual.mousePressed == true) {
            carat = false;
            AndrewsVisual.mousePressed = false;
            AndrewsVisual.deselectOtherButtons(this);
        }

        // Disables hover
        else {
            hover = false;
        }

        // Button selected, draws carat
        if (selected == true) {
            AndrewsVisual.stroke(60, 255, 255);
            AndrewsVisual.line(x, y + size.y/2, x-20, y + size.y/2+20);
            AndrewsVisual.line(x, y + size.y/2, x+20, y + size.y/2+20);
        }

        // Colours button when hovered
        else if (hover) {
            AndrewsVisual.stroke(60, 255, 255);
        }

        // Button turns white if not hovered or selected
        else if (!selected || !hover) {
            AndrewsVisual.stroke(255);
        }

        // Generate buttons and text
        AndrewsVisual.pushMatrix();
        AndrewsVisual.rect(x, y, size.x, size.y);
        AndrewsVisual.text(song, x, y);
        AndrewsVisual.popMatrix();
    }
}
