// import
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu ;
import javax.swing.JToolBar; 
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke ; 
import java.awt.event.KeyEvent; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseAdapter ;
import javax.swing.ImageIcon ;
//

public class Fenetre extends JFrame
{
	// variables d'instance pour l'animation
	private Panneau pan = new Panneau() ; 
	private JPanel container = new JPanel() ; 
	private int compteur = 0 ; 
	private boolean animated = true ; 
	private boolean backX, backY ; 
	private int x, y ; 
	private Thread t ; 
	
	// variables d'instance pour le MenuBar, Menu, MenuItem
	private JMenuBar menuBar = new JMenuBar() ; 
	
	private JMenu animation = new JMenu("Animation") ;
	private JMenu forme = new JMenu("Forme") ;
	private JMenu forme_typeForme = new JMenu("Type de forme") ;
	private JMenu aPropos = new JMenu("A propos") ;
	
	private JMenuItem animation_lancer = new JMenuItem("Lancer l'animation") ;
	private JMenuItem animation_arreter = new JMenuItem("Arrêter l'animation") ;
	private JMenuItem animation_quitter = new JMenuItem("Quitter") ;
	private JMenuItem aPropos_item = new JMenuItem("?") ;
	 
	private JRadioButtonMenuItem carre = new JRadioButtonMenuItem("Carré") ;
	private JRadioButtonMenuItem rond = new JRadioButtonMenuItem("Rond") ; 
	
	private ButtonGroup bg = new ButtonGroup() ; 
	
	// variables d'instance pour le JPopupMenu
	private JPopupMenu jpm = new JPopupMenu() ; 
	
	private JMenu background = new JMenu("Couleur du fond") ;  
	private JMenu couleur = new JMenu("Couleur de la forme") ;  
	
	private JMenuItem launch = new JMenuItem("Lancer l'animation") ; 
	private JMenuItem stop = new JMenuItem("Arrêter l'animation") ; 
	
	private JMenuItem rougeForme = new JMenuItem("Rouge") ; 
	private JMenuItem bleuForme = new JMenuItem("Bleu") ; 
	private JMenuItem vertForme = new JMenuItem("Vert") ; 
	private JMenuItem blancForme = new JMenuItem("Blanc") ;
	
	private JMenuItem rougeFond = new JMenuItem("Rouge") ; 
	private JMenuItem bleuFond = new JMenuItem("Bleu") ; 
	private JMenuItem vertFond = new JMenuItem("Vert") ;
	private JMenuItem blancFond = new JMenuItem("Blanc") ; 
	
	// Variables d'instance pour la JToolBar
	private JToolBar toolbar = new JToolBar() ;
	private JButton play = new JButton(new ImageIcon("images/play.png")) ;
	private JButton pause = new JButton(new ImageIcon("images/stop.png")) ; 
	 
	// Listener globaux
	private animation_arreter_Listener arreter_Listener = new animation_arreter_Listener() ; 
	private animation_lancer_Listener lancer_Listener = new animation_lancer_Listener() ; 
	
	private colorFond_Listener colorFond_Listener = new colorFond_Listener() ;
	private colorForme_Listener colorForme_Listener = new colorForme_Listener() ;  
	
	// constructeur Fenetre
	public Fenetre ()
	{
		// Mise en place du JFrame
		this.setTitle("Animation") ;
		this.setSize(300, 300) ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		this.setLocationRelativeTo(null) ;
		
		// Panel Central = Panneau (animation)
		container.setBackground(Color.white) ;
		container.setLayout(new BorderLayout()) ;
		
		stop.setEnabled(false) ;
		stop.addActionListener(arreter_Listener) ;
		launch.addActionListener(lancer_Listener) ;
		
		rougeFond.addActionListener(colorFond_Listener) ;
		bleuFond.addActionListener(colorFond_Listener) ;
		vertFond.addActionListener(colorFond_Listener) ;
		blancFond.addActionListener(colorFond_Listener) ;
		
		rougeForme.addActionListener(colorForme_Listener) ; 
		bleuForme.addActionListener(colorForme_Listener) ; 
		vertForme.addActionListener(colorForme_Listener) ; 
		blancForme.addActionListener(colorForme_Listener) ; 
		
		pan.addMouseListener(new jpmListener()) ;
				 
		container.add(pan, BorderLayout.CENTER) ;
		
		// Mise en place du JFrame, suite 
		this.setContentPane(container) ;
		this.initMenu() ; 
		this.initToolBar() ; 
		this.setVisible(true) ;
	}
	//
	
	// méthode initMenu
	private void initMenu ()
	{
		// Menu ANIMATION
		animation_lancer.addActionListener(lancer_Listener) ;
		animation_lancer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK)) ; 
		animation.add(animation_lancer) ; 
		 
		animation_arreter.addActionListener(arreter_Listener) ;
		animation_arreter.setEnabled(false) ;
		animation_arreter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK)) ;
		animation.add(animation_arreter) ;
		
		animation.addSeparator();
		 
		animation_quitter.addActionListener(new animation_quitter_Listener()) ;
		animation.add(animation_quitter) ;
		
		// Menu FORME
		bg.add(carre) ;
		bg.add(rond) ; 
		
		FormeListener fl = new FormeListener() ; 
		carre.addActionListener(fl) ;
		rond.addActionListener(fl) ;  
		
		rond.setSelected(true) ;
		forme_typeForme.add(carre) ; 
		forme_typeForme.add(rond) ;
		 
		forme.add(forme_typeForme) ; 
		  
		// Menu A PROPOS
		aPropos_item.addActionListener(new aPropos_Listener()); 
		aPropos.add(aPropos_item) ;
		
		// Finalisation du MenuBar, Menu, MenuItem
		animation.setMnemonic('A') ;
		menuBar.add(animation) ;
		forme.setMnemonic('F') ;
		menuBar.add(forme) ;
		aPropos.setMnemonic('P') ;
		menuBar.add(aPropos) ; 
		
		this.setJMenuBar(menuBar) ;
	}
	//
	
	// méthode initToolBar
	private void initToolBar()
	{
		this.pause.setEnabled(false) ;
		this.pause.addActionListener(arreter_Listener) ;
		this.pause.setBackground(Color.white) ;
		this.play.addActionListener(lancer_Listener) ;
		this.play.setBackground(Color.white) ;
		
		this.toolbar.add(play) ; 
		this.toolbar.addSeparator() ;
		this.toolbar.add(pause) ;
		
		this.add(toolbar, BorderLayout.NORTH) ;
	}
	//
	
	// méthode go pour l'animation
	private void go()
	{
	    //Les coordonnées de départ de notre rond
	    x = pan.getPosX();
	    y = pan.getPosY();
	    //Dans cet exemple, j'utilise une boucle while
	    //Vous verrez qu'elle fonctionne très bien
	    while(this.animated)
	    {
	      if(x < 1)backX = false;
	      if(x > pan.getWidth()-50)backX = true;          
	      if(y < 1)backY = false;
	      if(y > pan.getHeight()-50)backY = true;
	      if(!backX)pan.setPosX(++x);
	      else pan.setPosX(--x);
	      if(!backY) pan.setPosY(++y);
	      else pan.setPosY(--y);
	      pan.repaint();
	
	      try 
	      {
	        Thread.sleep(30) ;
	      } 
	      catch (InterruptedException e) 
	      {
	        e.printStackTrace() ;
	      }
	    } 
	}
	
	// classe interne PlayAnimation pour la gestion des thread
	class PlayAnimation implements Runnable
	{
		public void run()
		{
			go() ;
		}
	}
	//
	
	// ACTION LISTENER : animation_lancer, animation_arreter, animation_quitter, animation_lancer, Forme 
	// animation_lancer_Listener
	public class animation_lancer_Listener implements ActionListener
	{
		public void actionPerformed (ActionEvent ae0)
		{
			JOptionPane jop = new JOptionPane() ; 
			int option = jop.showConfirmDialog(null, 
					"Voulez-vous lancer l'animation ?",
					"Lancement de l'animation", 
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE) ;
			
			if (option == JOptionPane.OK_OPTION)
			{
				animation_lancer.setEnabled(false) ; 
				animation_arreter.setEnabled(true) ;
				
				launch.setEnabled(false) ;
				stop.setEnabled(true) ;
				
				play.setEnabled(false) ;
				pause.setEnabled(true) ;
				
				animated = true ;
				t = new Thread(new PlayAnimation()) ;
				t.start() ;  
			}
		}
	}
	//
	
	// animation_arreter_Listener
	class animation_arreter_Listener implements ActionListener
	{
		public void actionPerformed (ActionEvent ae1)
		{
			JOptionPane jop = new JOptionPane() ;
			int option = jop.showConfirmDialog(null, 
					"Voulez-vous arrêter l'animation ?", 
					"Arrêt de l'animation", 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE) ;
			
			if (option == JOptionPane.YES_OPTION)
			{
				animated = false ; 
				
				animation_lancer.setEnabled(true) ;
				animation_arreter.setEnabled(false) ; 
				
				launch.setEnabled(true) ;
				stop.setEnabled(false) ;
				
				play.setEnabled(true) ;
				pause.setEnabled(false) ;
			}
		}
	}
	//
	
	// animation_quitter_Listener
	class animation_quitter_Listener implements ActionListener
	{
		public void actionPerformed (ActionEvent e0)
		{
			System.exit(0) ;
		}
	}
	//
	 
	// aPropos_Listener
	class aPropos_Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae5)
		{
			JOptionPane jop = new JOptionPane() ; 
			String message = "A propos de l'application..." ; 
			jop.showMessageDialog(null, message, "A propos...", JOptionPane.INFORMATION_MESSAGE) ; 
		} 
	}
	//
	
	// FormeListener
	class FormeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae2)
		{
			pan.setForme(((JRadioButtonMenuItem)ae2.getSource()).getText()) ;
		}
	}
	//
	
	// jpmListener
	class jpmListener extends MouseAdapter implements MouseListener
	{
		public void mouseReleased (MouseEvent event)
		{ 
			int buttonDown = event.getButton() ; 
			System.out.println(buttonDown) ; 
			
			if (buttonDown == 3) // buttonDown = clic droit
			{
				System.out.println(buttonDown) ;
				background.add(rougeFond) ; 
				background.add(bleuFond) ; 
				background.add(vertFond) ;
				background.add(blancFond) ; 
				
				couleur.add(rougeForme) ;  
				couleur.add(bleuForme) ; 
				couleur.add(vertForme) ;
				couleur.add(blancForme) ; 
				
				jpm.add(launch) ; 
				jpm.add(stop) ;  
				jpm.add(couleur) ; 
				jpm.add(background) ;
				
				jpm.show(pan, event.getX(), event.getY()) ;
			} 
		} 
	} 
	//
	
	// colorFond_Listener
	class colorFond_Listener implements ActionListener 
	{
		public void actionPerformed (ActionEvent e)
		{
			if (e.getSource() == vertFond) { pan.setCouleurFond(Color.green) ; }
			else if (e.getSource() == bleuFond) { pan.setCouleurFond(Color.blue) ; }
			else if (e.getSource() == rougeFond) { pan.setCouleurFond(Color.red) ; } 
			else { pan.setCouleurFond(Color.white) ; }
		}
		
	}
	//
	
	// colorForme_Listener
	class colorForme_Listener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			if (e.getSource() == vertForme) { pan.setCouleurForme(Color.green) ; }
			else if (e.getSource() == bleuForme) { pan.setCouleurForme(Color.blue) ; }
			else if (e.getSource() == rougeForme) { pan.setCouleurForme(Color.red) ; }
			else { pan.setCouleurForme(Color.white) ; } 
		} 
	}
	//
	
	//
	 
}
  