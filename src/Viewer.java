/*
	Órla Keating
	15205679
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 */
public class Viewer extends JPanel {
	private long CurrentAnimationTime = 0;
	private int FeathersCount = 0;
	private boolean Gameover = false;
	private boolean Gamefinished = false;

	Model gameworld = new Model();
	private File textureToLoad;


	public Viewer(Model World) {
		this.gameworld = World;
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {

		this.repaint();
		// TODO Auto-generated method stub

	}

	public void setGameover(boolean gameover) {
		Gameover = gameover;
	}

	public void setGamefinished(boolean gamefinished) {
		Gamefinished = gamefinished;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		CurrentAnimationTime++; // runs animation time step 
		if(Gameover) {
			drawBackground("res/hovercatdied.png", g);
		}
		else if (Gamefinished) {
			drawBackground("res/endscreen.png", g);
		}
		else {
			//Draw player Game Object
			int width = (int) gameworld.getPlayer().getWidth();
			int height = (int) gameworld.getPlayer().getHeight();
			int x = (int) gameworld.getPlayer().getCentre().getX() - (width / 2);
			int y = (int) gameworld.getPlayer().getCentre().getY() - (height / 2);
			String texture = gameworld.getPlayer().getTexture();

			//Draw background
			drawBackground("res/background800.png", g);

			//Draw player
			drawPlayer(x, y, width, height, texture, g);

			gameworld.getDoggos().forEach((temp) ->
			{
				drawDoggo((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(),
						(int) temp.getHeight(), temp.getTexture(), g);
			});
			gameworld.getIntruderCats().forEach((temp) ->
			{
				drawPlayer((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(),
						(int) temp.getHeight(), temp.getTexture(), g);
			});

			gameworld.getEagles().forEach((temp) ->
			{
				drawEagle((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(),
						(int) temp.getHeight(), temp.getTexture(), g);

			});
			gameworld.getFeathers().forEach((temp) ->
			{
				if (FeathersCount < 4) {
//			if(CurrentAnimationTime - FeathersCount < 4) {
					drawFeathers((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(),
							(int) temp.getHeight(), "res/feathers.png", g, FeathersCount);
					FeathersCount++;
				} else {
					gameworld.removeFeathers();
					FeathersCount = 0;
				}
			});
		}
	}

	private void drawEagle(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);
		int drawX = x - (width / 2);
		int drawY = y - (height / 2);
		try {
			Image eagle = ImageIO.read(TextureToLoad);
			int currentPositionInAnimation = ((int) (CurrentAnimationTime % 1) * 1050);
			g.drawImage(eagle, drawX, drawY, drawX + width, drawY + height, currentPositionInAnimation, 0,
					currentPositionInAnimation + 1049, 899, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void drawFeathers(int x, int y, int width, int height, String texture, Graphics g, int counter) {
		File textureToLoad = new File(texture);
		int drawX = x - (width / 2);
		int drawY = y - (height / 2);
		try {
			Image feathers = ImageIO.read(textureToLoad);
			int currentPositionInAnimation = ((int) (counter)%4 * 1050);
			g.drawImage(feathers, drawX, drawY, drawX + width, drawY + height, currentPositionInAnimation, 0,
					currentPositionInAnimation + 1049, 899, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void drawDoggo(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);
		int drawX = x - (width / 2);
		int drawY = y - (height / 2);
		try {
			Image doggo = ImageIO.read(TextureToLoad);
			int currentPositionInAnimation = ((int) (CurrentAnimationTime % 2) * 700);
			g.drawImage(doggo, drawX, drawY, drawX + width, drawY + height, currentPositionInAnimation, 0,
					currentPositionInAnimation + 699, 449, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void drawBackground(String background, Graphics g) {
		File TextureToLoad = new File(background);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			g.drawImage(myImage, 0, 0, 1067, 800, 0, 0, 1067, 800, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawPlayer(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues
		// depending your eclipse install or if your running this without an IDE
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation = ((int) ((CurrentAnimationTime % 3))) * 1500; //slows down animation so every
			// 10 frames we get another frame so every 100ms
			g.drawImage(myImage, x, y, x + width, y + height, currentPositionInAnimation, 0,
					currentPositionInAnimation + 1499, 499, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer));
		//Lighnting Png from https://opengameart.org/content/animated-spaceships  its 32x32 thats why I know to
		// increament by 32 each time
		// Bullets from https://opengameart.org/forumtopic/tatermands-art 
		// background image from https://www.needpix.com/photo/download/677346/space-stars-nebula-background-galaxy-universe-free-pictures-free-photos-free-images

	}


}


/*
 * 
 * 
 *              VIEWER HMD into the world                                                             
                                                                                
                                      .                                         
                                         .                                      
                                             .  ..                              
                               .........~++++.. .  .                            
                 .   . ....,++??+++?+??+++?++?7ZZ7..   .                        
         .   . . .+?+???++++???D7I????Z8Z8N8MD7I?=+O$..                         
      .. ........ZOZZ$7ZZNZZDNODDOMMMMND8$$77I??I?+?+=O .     .                 
      .. ...7$OZZ?788DDNDDDDD8ZZ7$$$7I7III7??I?????+++=+~.                      
       ...8OZII?III7II77777I$I7II???7I??+?I?I?+?+IDNN8??++=...                  
     ....OOIIIII????II?I??II?I????I?????=?+Z88O77ZZO8888OO?++,......            
      ..OZI7III??II??I??I?7ODM8NN8O8OZO8DDDDDDDDD8DDDDDDDDNNNOZ= ......   ..    
     ..OZI?II7I?????+????+IIO8O8DDDDD8DNMMNNNNNDDNNDDDNDDNNNNNNDD$,.........    
      ,ZII77II?III??????DO8DDD8DNNNNNDDMDDDDDNNDDDNNNDNNNNDNNNNDDNDD+.......   .
      7Z??II7??II??I??IOMDDNMNNNNNDDDDDMDDDDNDDNNNNNDNNNNDNNDMNNNNNDDD,......   
 .  ..IZ??IIIII777?I?8NNNNNNNNNDDDDDDDDNDDDDDNNMMMDNDMMNNDNNDMNNNNNNDDDD.....   
      .$???I7IIIIIIINNNNNNNNNNNDDNDDDDDD8DDDDNM888888888DNNNNNNDNNNNNNDDO.....  
       $+??IIII?II?NNNNNMMMMMDN8DNNNDDDDZDDNN?D88I==INNDDDNNDNMNNMNNNNND8:..... 
   ....$+??III??I+NNNNNMMM88D88D88888DDDZDDMND88==+=NNNNMDDNNNNNNMMNNNNND8......
.......8=+????III8NNNNMMMDD8I=~+ONN8D8NDODNMN8DNDNNNNNNNM8DNNNNNNMNNNNDDD8..... 
. ......O=??IIIIIMNNNMMMDDD?+=?ONNNN888NMDDM88MNNNNNNNNNMDDNNNMNNNMMNDNND8......
........,+++???IINNNNNMMDDMDNMNDNMNNM8ONMDDM88NNNNNN+==ND8NNNDMNMNNNNNDDD8......
......,,,:++??I?ONNNNNMDDDMNNNNNNNNMM88NMDDNN88MNDN==~MD8DNNNNNMNMNNNDND8O......
....,,,,:::+??IIONNNNNNNDDMNNNNNO+?MN88DN8DDD888DNMMM888DNDNNNNMMMNNDDDD8,.... .
...,,,,::::~+?+?NNNNNNNMD8DNNN++++MNO8D88NNMODD8O88888DDDDDDNNMMMNNNDDD8........
..,,,,:::~~~=+??MNNNNNNNND88MNMMMD888NNNNNNNMODDDDDDDDND8DDDNNNNNNDDD8,.........
..,,,,:::~~~=++?NMNNNNNNND8888888O8DNNNNNNMMMNDDDDDDNMMNDDDOO+~~::,,,.......... 
..,,,:::~~~~==+?NNNDDNDNDDNDDDDDDDDNNND88OOZZ$8DDMNDZNZDZ7I?++~::,,,............
..,,,::::~~~~==7DDNNDDD8DDDDDDDD8DD888OOOZZ$$$7777OOZZZ$7I?++=~~:,,,.........   
..,,,,::::~~~~=+8NNNNNDDDMMMNNNNNDOOOOZZZ$$$77777777777II?++==~::,,,......  . ..
...,,,,::::~~~~=I8DNNN8DDNZOM$ZDOOZZZZ$$$7777IIIIIIIII???++==~~::,,........  .  
....,,,,:::::~~~~+=++?I$$ZZOZZZZZ$$$$$777IIII?????????+++==~~:::,,,...... ..    
.....,,,,:::::~~~~~==+?II777$$$$77777IIII????+++++++=====~~~:::,,,........      
......,,,,,:::::~~~~==++??IIIIIIIII?????++++=======~~~~~~:::,,,,,,.......       
.......,,,,,,,::::~~~~==+++???????+++++=====~~~~~~::::::::,,,,,..........       
.........,,,,,,,,::::~~~======+======~~~~~~:::::::::,,,,,,,,............        
  .........,.,,,,,,,,::::~~~~~~~~~~:::::::::,,,,,,,,,,,...............          
   ..........,..,,,,,,,,,,::::::::::,,,,,,,,,.,....................             
     .................,,,,,,,,,,,,,,,,.......................                   
       .................................................                        
           ....................................                                 
               ....................   .                                         
                                                                                
                                                                                
                                                                 GlassGiant.com
                                                                 */
