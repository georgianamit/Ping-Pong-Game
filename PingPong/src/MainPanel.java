import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel {
	ScorePanel s;
	
	public MainPanel(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS) );
		s= new ScorePanel();
		GamePanel p = new GamePanel();
		this.add(s);
		this.add(p);
		
	}
	
	public class ScorePanel extends JPanel {
		
		int scorepanelwidth=1280,scorepanelheight=220;
		int player1score=0, player2score=0;
		int aset[] = new int[3];
		int bset[] = new int[3];
		
		int awon=0,bwon=0;
		
		public ScorePanel(){
			
			this.setPreferredSize(new Dimension(scorepanelwidth,scorepanelheight));
			this.setBorder(BorderFactory.createLineBorder(Color.cyan));
			this.setBackground(Color.BLUE);
			
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman",Font.PLAIN,48));
			g.drawString("PING - PONG", scorepanelwidth/2- 120, scorepanelheight/2-50);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman",Font.PLAIN,36));
			g.drawString("Player 1", 50, 120);
			
			g.drawString("Player 2", scorepanelwidth-170, 120);
			
			g.setColor(Color.green);
			g.fill3DRect(75, 150, 60, 50,true);
			g.fill3DRect(scorepanelwidth-80-60, 150, 60, 50,true);
			
			g.setColor(Color.WHITE);
			g.drawRect(75, 150, 60, 50);
			g.drawRect(scorepanelwidth-80-60, 150, 60, 50);
			
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(player1score), 90, 185);
			g.drawString(Integer.toString(player2score), scorepanelwidth-80-45, 185);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman",Font.PLAIN,30));
			g.drawString("Player 1", scorepanelwidth/2-220, 115);
			
			g.drawString("Player 2", scorepanelwidth/2-220, 175);
			
			for(int i=1;i<=3;i++){
				g.setColor(Color.GREEN);
				g.fill3DRect(scorepanelwidth/2-170+70*i, 80, 70, 60,true);
				g.fill3DRect(scorepanelwidth/2-170+70*i, 140, 70, 60,true);
				g.setColor(Color.white);
				g.draw3DRect(scorepanelwidth/2-170+70*i, 80, 70, 60,true);
				g.draw3DRect(scorepanelwidth/2-170+70*i, 140, 70, 60,true);
			}
			
			for(int i=1;i<=3;i++){
				g.setColor(Color.white);
				g.setFont(new Font("Times new roman",Font.PLAIN,46));
				g.drawString(Integer.toString(aset[i-1]), scorepanelwidth/2-170+70*i+15, 125);
				g.drawString(Integer.toString(bset[i-1]), scorepanelwidth/2-170+70*i+15, 185);
				
			}
			
			g.setColor(Color.GREEN);
			g.fill3DRect(scorepanelwidth/2-190+80*4, 80, 70, 60,true);
			g.fill3DRect(scorepanelwidth/2-190+80*4, 140, 70, 60,true);
			g.setColor(Color.white);
			g.draw3DRect(scorepanelwidth/2-190+80*4, 80, 70, 60,true);
			g.draw3DRect(scorepanelwidth/2-190+80*4, 140, 70, 60,true);
			
			g.setColor(Color.white);
			g.setFont(new Font("Times new roman",Font.PLAIN,46));
			g.drawString(Integer.toString(awon), scorepanelwidth/2-190+70*4+55, 125);
			g.drawString(Integer.toString(bwon), scorepanelwidth/2-190+70*4+55, 185);
			
		}
		
		
	}
	
	
	
	
	public class GamePanel extends JPanel implements ActionListener,KeyListener{
		
		int screenwidth=1280,screenheight=720;
		
		int batwidth=10,batheight=140;
		int batax=20,batay= screenheight/2 - batheight/2;
		int batavel=0;
		
		int batbx=screenwidth-30,batby=screenheight/2 - batheight/2;
		int batbvel=0;
		
		int ballrad=30;
		int ballx=screenwidth/2-ballrad/2,bally=screenheight/2-ballrad/2;
		int ballxvel=2,ballyvel=2;
		
		int set=0;
		
		boolean gamestart=false;
		boolean winner=false;
		
		String winningplayer;
		
		boolean acollide=false;
		boolean bcollide=false;
		
		
		Timer t;
		
		public GamePanel(){
			t= new Timer(1,this);
			this.setPreferredSize(new Dimension(screenwidth,screenheight));
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			this.setBackground(Color.CYAN);
			this.addKeyListener(this);
			setFocusable(true);
			this.setFocusTraversalKeysEnabled(false);
			t.start();
			
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			g.setColor(Color.orange);
			g.fill3DRect(batax, batay, batwidth, batheight,true);
			
			g.fill3DRect(batbx, batby, batwidth, batheight,true);
			
			g.setColor(Color.blue);
			g.fillOval(ballx, bally, ballrad, ballrad);
			
			if(!gamestart){
				drawStartGame(g);
			}
			
			if(winner){
				drawWinner(g);
				t.stop();
			}
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(gamestart){
				ballx += ballxvel;
				bally += ballyvel;
			}
				batay += batavel;
				batby += batbvel;
				
				
				if(batay<0 || batay+batheight>screenheight){
					batavel=0;
				}
				if(batby<0 || batby+batheight>screenheight){
					batbvel=0;
				}
			
				
				
				if(bally < 0 || bally+ballrad > screenheight){
					ballyvel = -ballyvel;
				}
				if(ballx < 0 ){
					s.player2score++;
					ballx=screenwidth/2-ballrad/2;
					bally=screenheight/2-ballrad/2;
					
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(ballx+ballrad > screenwidth){
					s.player1score++;
					ballx=screenwidth/2-ballrad/2;
					bally=screenheight/2-ballrad/2;
					
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
				if(s.player1score == 10 && s.player2score == 10){
						s.player1score=7;
						s.player2score=7;
				}
				
				if(s.player1score == 11){
					s.aset[set]=s.player1score;
					s.bset[set]=s.player2score;
					s.awon++;
					s.player1score=0;
					s.player2score=0;
					set++;
				}
				
				if(s.player2score == 11){
					
						s.aset[set]=s.player1score;
						s.bset[set]=s.player2score;
						s.bwon++;
						s.player1score=0;
						s.player2score=0;
						set++;
				}
				
				
				if(set==3){
					winner=true;
					if(s.awon > s.bwon){
						winningplayer="Player 1";
					}else{
						winningplayer="Player 2";
					}
				}
				
				Rectangle bata = new Rectangle(batax,batay,batwidth,batheight);
				Rectangle batb = new Rectangle(batbx,batby,batwidth,batheight);
				Rectangle ball = new Rectangle(ballx,bally,ballrad,ballrad);
				
				if(!acollide){
				if(bata.intersects(ball)){
					ballxvel = -ballxvel;
					acollide = true;
				}
				}
				
				if(!bcollide){
				if(batb.intersects(ball)){
					ballxvel = -ballxvel;
					bcollide=true;
				}
				}
				
				if(acollide){
					if(!ball.intersects(bata)){
						acollide=false;
					}
				}
				
				if(bcollide){
					if(!ball.intersects(batb)){
						bcollide=false;
					}
				}
				
			repaint();
			s.repaint();
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_SPACE){
				gamestart=true;
			}
			
			if(key == KeyEvent.VK_ESCAPE){
				System.exit(0);
			}
			
			if(key == KeyEvent.VK_UP){
				if(batby > 0){
				batbvel = -2;
			}else
				batbvel =0;
			}
			
			if(key == KeyEvent.VK_DOWN){
					if(batby+batheight < screenheight){
					batbvel = 2;
				}else
					batbvel = 0;
			}
			
				
			if(key == KeyEvent.VK_W){
				if(batay > 0 ){
				batavel = -2;
			}else
				batavel =0;
			}
			
			
			if(key == KeyEvent.VK_S){
					if(batay+batheight < screenheight){
					batavel = 2;
				}else
					batavel = 0;
			}
				
			if(key == KeyEvent.VK_R){
					 
					 batax=20;batay= screenheight/2 - batheight/2;
					 batavel=0;
					
					 batbx=screenwidth-20;batby=screenheight/2 - batheight/2;
					 batbvel=0;
					
					
					 ballx=screenwidth/2-ballrad/2;bally=screenheight/2-ballrad/2;
					 ballxvel=2;ballyvel=2;
					
					 set=0;
					
					 gamestart=false;
					 winner=false;
					
					 winningplayer=null;
					 
					 s.player1score=0;
					 s.player2score=0;
					 s.awon=0;
					 s.bwon=0;
					 for(int i=0;i<3;i++){
						 s.aset[i]=0;
						 s.bset[i]=0;
					 }
					 repaint();
					 s.repaint();
				}
			
			}
		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
				batbvel=0;
			}
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_S){
				batavel=0;
			}
			
			
		}
		@Override
		public void keyTyped(KeyEvent arg0) {}
		
		public void drawWinner(Graphics g){
			g.setColor(Color.green);
			g.setFont(new Font("Times new roman",Font.ITALIC,42));
			g.drawString(winningplayer +" won by " + Integer.toString(s.awon) + " - " + Integer.toString(s.bwon), screenwidth/2-200, screenheight/2 - 100);
			g.setColor(Color.ORANGE);
			g.drawString("Press [ESC] to exit the game", screenwidth/2-280, screenheight/2 - 50);
			g.drawString("Press [R] to restart the game", screenwidth/2-280, screenheight/2 - 10);
		}
		
		public void drawStartGame(Graphics g){
			g.setColor(Color.orange);
			g.setFont(new Font("Times new roman",Font.ITALIC,42));
			g.drawString("Press [SPACE] to start the game", screenwidth/2-280, screenheight/2 - 100);
			
			

		
		}
	}
	
}
