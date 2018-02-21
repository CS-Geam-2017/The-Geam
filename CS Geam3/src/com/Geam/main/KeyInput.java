package com.Geam.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	public static int Question = 1;
	public static boolean right = false;
	public static int act5 = 1;
	public static boolean check = false;
	private Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		GeamObject tempObject;
		for(int i = 0; i<handler.object.size();i++) {
			tempObject = handler.object.get(i);
			if(tempObject.id==ID.R) {
				if(Question == 1) {
					if(key == KeyEvent.VK_B) {
						tempObject.setText("(CORRECT) Hamlet leaves while in aw that your mother would marry such a man");
						right = true;
					}
					else if(key == KeyEvent.VK_A) {
						tempObject.setText("(INCORRECT) As you dive for Claudius the guards apprehend you and you are executed a dawn");
					}
					else if(key == KeyEvent.VK_C) {
						tempObject.setText("(INCORRECT) Hamlet thought that it just wasn’t worth it and live a very successful life");
					}
					else if(key == KeyEvent.VK_D) {
						tempObject.setText("(INCORRECT) You object to the marriage and demands the throne become his for this Hamlet has committed treason");
					}
				}
				else if(Question == 2) {
					if(key == KeyEvent.VK_A) {
						tempObject.setText("(INCORRECT) HAMLET JUST SAW A GHOST THIS ISN’T POSSIBLE!");
					}
					else if(key == KeyEvent.VK_B) {
						tempObject.setText("(INCORRECT) As Hamlet starts to yell I’M NOT AFRAID OF NO GHOST Hamlet pulls out a proton pack and starts to zap the ghost . . . it doesn’t work because this is Shakespeare and not Ivan Reitman");
					}
					else if(key == KeyEvent.VK_C) {
						tempObject.setText("(CORRECT) Hamlet agrees to avenge the ghost even though he isn’t sure if it is real or not");
						right = true;
					}
					else if(key == KeyEvent.VK_D) {
						tempObject.setText("(INCORRECT) As soon as the ghost comes in front of Hamlet, he just say NOPE and run the other direction");
					}
				}
				else if(Question == 3) {
					if(key == KeyEvent.VK_A) {
						tempObject.setText("(CORRECT) Hamlet decide that creating a play similar to the death of  his father to catch Claudius to prove he killed him");
						right = true;
					}
					else if(key == KeyEvent.VK_B) {
						tempObject.setText("(INCORRECT) As soon a claudius gets back Hamlet plans to drop oil on top of the carriage and light it ablaze unfortunately when setting up one of the oil barrels falls on hamlet and he is discovered");
					}
					else if(key == KeyEvent.VK_C) {
						tempObject.setText("(INCORRECT) Hamlet agrees to avenge the ghost even though he isn’t sure if it is real or not");
					}
					else if(key == KeyEvent.VK_D) {
						tempObject.setText("(INCORRECT) Hamlet decides to sleep on it and before he knows it Claudius is already back and you missed your chance (a.k.a procrastination is bad)");
					}
				}
				else if(Question == 4) {
					if(key == KeyEvent.VK_A) {
						tempObject.setText("(INCORRECT) Not a single one is good enough Hamlet sends them all away and starts to think of something else");
					}
					else if(key == KeyEvent.VK_B) {
						tempObject.setText("(INCORRECT) Hamlet couldn’t think of anything else he said scrap the play and kill claudius when he get here");
					}
					else if(key == KeyEvent.VK_C) {
						tempObject.setText("(INCORRECT) Hamlet has all the Guards stab he players");
					}
					else if(key == KeyEvent.VK_D) {
						tempObject.setText("(CORRECT) Hamlet introduces his play to the players and they agree to preform it");
						right = true;
					}
				}
				else if(Question == 5) {
					if(key == KeyEvent.VK_A) {
						tempObject.setText("(INCORRECT) Hamlet says that he loves Ophelia dearly and wishes to marry her and later they have a very hard marriage and Ophelia ends up killing herself from marital neglect ");
					}
					else if(key == KeyEvent.VK_B) {
						tempObject.setText("(CORRECT) Hamlet thrusts his sword to try and kill claudius but when the curtains are pulled back it is Polonius");
						right = true;
					}
					else if(key == KeyEvent.VK_C) {
						tempObject.setText("(INCORRECT) Hamlet Stabs both Polonius and Gertrude and hides their bodies");
					}
					else if(key == KeyEvent.VK_D) {
						tempObject.setText("(INCORRECT) Hamlet has been found that he couldn’t keep up the charade and jumps from the window");
					}
				}
				else if(Question == 6) {
					if(key == KeyEvent.VK_A) {
						tempObject.setText("(CORRECT) Hamlet Shouts nonsense that the court can’t understand keeping the charde");
						right = true;right = true;
					}
					else if(key == KeyEvent.VK_B) {
						tempObject.setText("(INCORRECT) Hamlet tells the court where the body is and you are instantly sent to England");
					}
					else if(key == KeyEvent.VK_C) {
						tempObject.setText("(INCORRECT) Hamlet is Stabbed in the scuffle");
					}
					else if(key == KeyEvent.VK_D) {
						tempObject.setText("(INCORRECT) Before the guards came Hamlet jumps from the window to his brutal demise");
					}
				}
				else if(Question == 7 && act5 == 1) {
					if(key == KeyEvent.VK_A) {
						tempObject.setText("(CORRECT) Hamlet gets 2 hits of laetes causing Gertrude to drink to him and end up drinking the poisoned cup");
						right = true;
					}
					else if(key == KeyEvent.VK_B) {
						tempObject.setText("(INCORRECT)  Hamlet gets a hit on Laertes and then drinks from the cup and notices something funny about the drink then starts to feel woozy then falls to the ground");
					}
					else if(key == KeyEvent.VK_C) {
						tempObject.setText("(INCORRECT) Hamlet is struck with the poisoned sword of Laetes to early and feels woozy only to collapse and die");
					}
					else if(key == KeyEvent.VK_D) {
						tempObject.setText("(INCORRECT) Hamlet chooses not to duel and goes to England and is murdered in an alleyway and is never seen again");
					}
				}
				else if(Question == 7 && act5 == 2) {
					if(key == KeyEvent.VK_A) {
						tempObject.setText("(CORRECT) Hamlet stabs Laertes with his own sword then proceeds to thrust it into Claudius as well");
						right = true;
					}
					else if(key == KeyEvent.VK_B) {
						tempObject.setText("(INCORRECT) Hamlet just faces death and chooses to succumb to the poison");
						act5 = 1;
					}
					else if(key == KeyEvent.VK_C) {
						tempObject.setText("(INCORRECT) As Hamlet thrusts the sword into Claudius, Laertes comes up and stabs Hamlet in the back again (Probably could have been slightly more efficient)");
						act5 = 1;
					}
					else if(key == KeyEvent.VK_D) {
						tempObject.setText("(INCORRECT) As Hamlet uses his own sword to stab Laertes it seems to be only a flesh wound and Hamlet then dies on the ground");
						act5 = 1;
					}
				}
				if(right&& key==KeyEvent.VK_ENTER && Question<7) {
					Question++;
					right=false;
					tempObject.setText("");
				}
				else if(right&& key==KeyEvent.VK_ENTER && Question==7 && act5<2) {
					act5++;
					tempObject.setText("");
					right=false;
				}
				else if(right&& key==KeyEvent.VK_ENTER && Question==7 && act5==2) {
					Question++;
					tempObject.setText("");
					right = false;
				}
				else if(key==KeyEvent.VK_ENTER && Question==8 && Geam.end) {
					tempObject.setText("");
					right = false;
				}
				else if(Geam.title && key==KeyEvent.VK_ENTER && check) {
					Geam.title = false;
					Question =1;
					act5 = 1;
					right = false;
				}
				
			}
			else if(tempObject.id==ID.A) {
				if(key==KeyEvent.VK_ENTER && Question==8 && Geam.end) {
					tempObject.setX(500);
					Geam.title = true;
					Geam.end = false;
					Question =1;
					act5 = 1;
					check = false;
				}
			}
		}
	}
	

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
	}
}
