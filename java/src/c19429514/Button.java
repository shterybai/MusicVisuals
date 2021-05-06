package c19429514;

import java.lang.reflect.Member;

import processing.core.PVector;

public class Button extends AndrewsVisual{
    AndrewsVisual AndrewsVisual;
    float x;
    float y;
    String song;
    PVector size;
    boolean hover, circumflex;
    boolean selected = false;

    public Button(AndrewsVisual andrewsVisual, float x, float y, String song, boolean selected) {
        AndrewsVisual = andrewsVisual;
        this.x = x;
        this.y = y;
        this.song = song;
        song = "deadmau5 - Glish";
        this.selected = selected;
        // song = "deadmau5 - Glish";
        size = new PVector(300, 100);
    }

    public void setColor(int r, int g, int b) {
        AndrewsVisual.stroke(r, g, b);
    }

    public void render() {

        AndrewsVisual.rectMode(CENTER);
        AndrewsVisual.textAlign(CENTER);
        AndrewsVisual.textSize(24);
        AndrewsVisual.colorMode(HSB);
        AndrewsVisual.noFill();

        if (AndrewsVisual.mouseX < x + size.x/2 && AndrewsVisual.mouseY < y + size.y/2 && AndrewsVisual.mouseX > x - size.x/2 && AndrewsVisual.mouseY > y - size.y/2 && circumflex == false) {
            // AndrewsVisual.stroke(60, 255, 255);
            hover = true;
            if (AndrewsVisual.mousePressed == true) {
                selected = true;
                // deselectOtherButtons(this);
                circumflex = true;
            }
        }
        // else if (circumflex == true && hover == true) {
        //     selected = true;

        //     // if (circumflex == true && AndrewsVisual.mousePressed == true) {
        //     //     circumflex = false;
        //     //     // AndrewsVisual.mousePressed = false;
        //     //     AndrewsVisual.stroke(255);
        //     // }
        // }
        else if (circumflex == true && AndrewsVisual.mousePressed == true) {
            circumflex = false;
            AndrewsVisual.mousePressed = false;
            // selected = false;
            AndrewsVisual.deselectOtherButtons(this);
        }
        else {
            hover = false;
        }

        if (selected) {
            AndrewsVisual.stroke(60, 255, 255);
            AndrewsVisual.line(x, y + size.y/2, x-20, y + size.y/2+20);
            AndrewsVisual.line(x, y + size.y/2, x+20, y + size.y/2+20);
        }
        else if (hover) {
            AndrewsVisual.stroke(60, 255, 255);
        }
        else if (!selected || !hover) {
            AndrewsVisual.stroke(255);
        }
        
        
        

        AndrewsVisual.pushMatrix();

        AndrewsVisual.rect(x, y, size.x, size.y);
        AndrewsVisual.text(song, x, y);

        AndrewsVisual.popMatrix();
    }

    

    public void mouseClicked() {
       
    }
}
