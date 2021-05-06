# Music Visualiser Project

Name: Andrew Kennan

Student Number: C19429514

# Description of the assignment
This assignment is a visual and auditory experience that the user can freely interact with and explore at thier own pace. The visuals react directly with the music, and change their size, colour, and speed depending on the amplitude of certain frequencies. The main visual is a colection of intersecting rectangles of various colours. The colours corrospond to the frequency they react to, and the size of the rectangles increases the closer to the center they are. These rectangles are also arranged in 4 distinct "orbits" that revolve around the center in alternating directions. There are an array of dots in a circular pattern in the background that change brightness and colour depending on the amplitude of the frequencies. Lastly, there are particles that fall randomly from the top of the screen, that increase brightness and opacity depending on the amplitude of the frequencies.

# Instructions
- Spacebar - play/pause music.
- x - rotate rectangles in x-axis (must hold down key).
- s - change rotation of rectangles and reverse particle direction (must hold down key).

When the music is paused/not playing, click on a button to change the song. The song by default is "Glish", however you can also choose between "Seeya" and a demo of one of my very own songs.

# How it works
X

# What I am most proud of in the assignment
X

# Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

This is a table:

| Heading 1 | Heading 2 |
|-----------|-----------|
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |

