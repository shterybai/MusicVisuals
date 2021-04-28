package ie.tudublin;

import example.CubeVisual;
// import example.CubeVisual1;
import example.MyVisual;
import example.RotatingAudioBands;

import c19429514.AndrewsVisual;
// import c19429514.RotatingAudioBands;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new AndrewsVisual());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}