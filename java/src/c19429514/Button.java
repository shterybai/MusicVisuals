package c19429514;

import processing.core.PVector;

public class Button extends AndrewsVisual{
    AndrewsVisual AndrewsVisual;
    float x;
    float y;
    String song;
    PVector size;

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
        AndrewsVisual.stroke(255);
        AndrewsVisual.rect(x, y, size.x, size.y);
        AndrewsVisual.text(song, x, y);

        AndrewsVisual.popMatrix();
    }
}
