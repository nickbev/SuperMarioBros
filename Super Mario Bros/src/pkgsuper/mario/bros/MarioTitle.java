// Nick Bevacqua
//package Classes;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.Properties;
import java.util.Arrays;

import javax.swing.*;

// Panel which will contain the main menu
public class MarioTitle extends JPanel implements ActionListener, KeyListener
{
    private int speed = 5; // Speed of timer
    private Timer time = new Timer (speed,this); // Timer that controls movement
    private int highScore = 0;
    private boolean showInstructions = false;
    private MarioFrame window;
    
    // Images
    private Image back;
    private Image mario;
    private Image c1;
    
    
    File highscoresource = new File("highscore.txt");
    
    
    public MarioTitle (MarioFrame x) // Starts the timer and listeners
    {
        back = new ImageIcon(getClass().getClassLoader().getResource("Pics/Level/main.gif")).getImage();
        mario =  new ImageIcon(getClass().getClassLoader().getResource("Pics/Mario/smallstillright.png")).getImage();
        c1 = new ImageIcon(getClass().getClassLoader().getResource("Pics/Coin/coin1.png")).getImage();
        
        try
        {
            InputStream score = new FileInputStream(highscoresource);
            Scanner scan = new Scanner(score);
            highScore = scan.nextInt();
        }
        catch (Exception e)
        {
            System.out.print("No High Score File Found");
        }
                
        window = x;
        time.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
    }
    
    public int getHighScore()
    {
        return highScore;
    }
    
    public void setHighScore(int x)
    {
        if(x > highScore)
        {
            highScore = x;
            try
            {
                OutputStream wr = new FileOutputStream(highscoresource);
                String str = "" + highScore;
                wr.write(str.getBytes());
            }
            catch (Exception e)
            {
                System.err.println("1");
                System.err.println(e);
                System.err.println("\n2");
                System.err.println(e.getMessage());
                System.err.println("\n3");
                System.err.println(e.getLocalizedMessage());
                System.err.println("\n4");
                System.err.println(e.getCause());
                System.err.println("\n5");
                System.err.println(Arrays.toString(e.getStackTrace()));
                System.err.println("\n6");
                e.printStackTrace();
            }
        }
    }
    
    public void actionPerformed (ActionEvent e)
    {
        repaint();
    }
    
    public void keyPressed(KeyEvent e)
    {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_Q) // Moving left
        {
            setInstructions();
        }
        if (c == KeyEvent.VK_ENTER) // Moving right
        {
            start();
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        
    }
    
    public void keyTyped(KeyEvent e)
    {
        
    }
    
    public void paintComponent (Graphics g) // Fills in objects
    {
        super.paintComponent(g);
        g.drawImage(back, 0, 0, 1000, 720, null);
        g.drawImage(mario, 50, 596, 36, 50, null);
        g.setColor(new Color(255,255,255));
        Font top = new Font("Monospaced", Font.PLAIN, 20);
        g.setFont(top);
        // Displays score
        g.drawString("MARIO", 75, 25);
        g.drawString("0000000" , 75, 45);
        // Displays coins collected
        g.drawImage(c1, 185, 8, 10, 20, null);
        g.drawString(" * 00" , 200, 25);
        // Displays level
        g.drawString("WORLD", 700, 25);
        g.drawString("1-1", 700, 45);
        // Displays time left
        g.drawString("TIME", 900, 25);
        // Displays options
        if(showInstructions)
        {
            g.setColor(Color.BLACK);
            g.fillRect(200, 430, 600, 100);
            g.setColor(new Color(255,255,255));
            g.drawString("Press LEFT and RIGHT to move sideways.", 220, 460);
            g.drawString("Press UP to jump and DOWN to crouch.", 220, 480);
            g.drawString("Press P to pause.", 220, 500);
            g.drawString("Press SPACE to throw fireballs with the powerup.", 220, 520);
            g.setColor(Color.YELLOW);
            g.drawString("Made by Nick Bevacqua", 0,715);
        }
        else
        {
            g.setColor(Color.BLACK);
            g.fillRect(200, 430, 600, 100);
            g.setColor(new Color(255,255,255));
            g.drawString("Press ENTER to Play.", 220, 460);
            g.drawString("Press Q for Instructions.", 220, 480);
            g.drawString("High Score: " + highScore, 220, 500);
        }
    }
    
    public void setInstructions()
    {
        if(showInstructions)
            showInstructions = false;
        else
            showInstructions = true;
    }
    
    public void start()
    {
        removeKeyListener(this);
        window.toLevelScreen(3, 0, 0, "1-1", 1);
    }
    
}