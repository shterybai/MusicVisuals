package c19429514;

import processing.core.PVector;

public class Button extends AndrewsVisual{
    AndrewsVisual AndrewsVisual;
    float x;
    float y;
    String song;
    PVector size;
    boolean circumflex;

    public Button(AndrewsVisual andrewsVisual, float x, float y, String song) {
        AndrewsVisual = andrewsVisual;
        this.x = x;
        this.y = y;
        this.song = song;
        size = new PVector(300, 100);
    }

    public void render() {
        AndrewsVisual.pushMatrix();

        AndrewsVisual.rectMode(CENTER);
        AndrewsVisual.textAlign(CENTER);
        AndrewsVisual.textSize(24);
        AndrewsVisual.colorMode(HSB);
        AndrewsVisual.noFill();

        if (AndrewsVisual.mouseX < x + size.x/2 && AndrewsVisual.mouseY < y + size.y/2 && AndrewsVisual.mouseX > x - size.x/2 && AndrewsVisual.mouseY > y - size.y/2) {
            AndrewsVisual.stroke(60, 255, 255);
            AndrewsVisual.line(x, y + size.y/2, x-20, y + size.y/2+20);
            AndrewsVisual.line(x, y + size.y/2, x+20, y + size.y/2+20);
        }
        else {
            AndrewsVisual.stroke(255);
        }

        AndrewsVisual.rect(x, y, size.x, size.y);
        AndrewsVisual.text(song, x, y);

        AndrewsVisual.popMatrix();
    }

    // public void mouseClicked() {
    //     if (AndrewsVisual.mouseX < x + size.x/2 && AndrewsVisual.mouseY < y + size.y/2 && AndrewsVisual.mouseX > x - size.x/2 && AndrewsVisual.mouseY > y - size.y/2) {
    //         AndrewsVisual.line(x, y + size.y/2, x-20, y + size.y/2+20);
    //         AndrewsVisual.line(x, y + size.y/2, x+20, y + size.y/2+20);
    //         System.out.println("true");
    //     }
    // }
}
