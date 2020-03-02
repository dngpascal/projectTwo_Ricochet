// import 
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D; 
import javax.swing.JPanel;
//


// class Panneau
public class Panneau extends JPanel 
{
	// Variables d'instance
	private int posX = -50 ; 
	private int posY = -50 ; 
	private String forme = "Rond" ;
	private Color couleurForme = Color.red ; 
	private Color couleurFond = Color.white ;
	//	
	
	// méthode paintComponent
	public void paintComponent(Graphics g)
	{
		g.setColor(couleurFond) ; // Quand on "set" une Color, elle n'est utilisée qu'une fois, ici pour le Rectangle dessous
		g.fillRect(0 , 0, this.getWidth(), this.getHeight()) ;
		
		g.setColor(couleurForme) ; // Ici, on "set" à nouveau une Color qui sera utilisée qu'une fois, pour le Cercle plus loin
		draw(g) ;  
	}
	//
	
	// méthode draw 
	private void draw(Graphics g)
	{
		if (this.forme.equals("Rond"))
		{
			g.fillOval(posX, posY, 50, 50) ;  
		}
		else if (this.forme.equals("Carré"))
		{
			g.fillRect(posX, posY, 50, 50) ;  	
		}
	}
	//
	
	// méthode setForme
	public void setForme(String pForme)
	{
		this.forme = pForme ; 
	}
	//
	
	// méthode setCouleurFond
	public void setCouleurFond(Color color)
	{
		this.couleurFond = color ; 
	}
	//
	
	// méthode setCouleurForme
	public void setCouleurForme(Color color)
	{
		this.couleurForme = color ; 
	}
	//
	
	// accesseurs et mutateurs posX et posY
	public int getPosX()
	{
		return posX ;
	}
	
	public void setPosX(int pPosX)
	{
		this.posX = pPosX ;
	}
	
	public int getPosY()
	{
		return posY ;
	}
	
	public void setPosY(int pPosY)
	{
		this.posY = pPosY ; 
	}
	// 
}
//
