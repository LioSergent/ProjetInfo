import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonListener;

import java.awt.Color;


public class IHMBasique extends JFrame implements Constantes {
	
	//Ajout d'un commentaire USELESS


	private String filename;
    private Morceau partie;
    public JoueurDeSession playSess=null;                                          //Le Thread de lecture de l'accompagnement
    
    //Attributs boutons, j'en ai besoin en tant qu'attributs pour les appeler dans certaines m�thodes.
    private JProgressBar jprogressbar = null;                                  //Barre d'affichage de la progression
	private JButton jButton_pause = null;                                      //Le bouton lan�ant pause()
	private JButton jButton_Do= new javax.swing.JButton();
	private JButton jButton_Dod= new javax.swing.JButton();
	private JButton jButton_Re= new javax.swing.JButton();
	private JButton jButton_Red= new javax.swing.JButton();
	private JButton jButton_Mi= new javax.swing.JButton();
	private JButton jButton_Fa= new javax.swing.JButton();
	private JButton jButton_Fad= new javax.swing.JButton();
	private JButton jButton_Sol= new javax.swing.JButton();
	private JButton jButton_Sold= new javax.swing.JButton();
	private JButton jButton_La= new javax.swing.JButton();
	private JButton jButton_Lad= new javax.swing.JButton();
	private JButton jButton_Si= new javax.swing.JButton();
    private JSlider JSlider_AugmenterVolume= new javax.swing.JSlider(-80, 6);            //Le bouton r�glant le volume
    
	
	public IHMBasique(Morceau sess) {
		
		
		partie=sess;
		filename=partie.renvoyerAccompagnement();
		//Cr�ation de la fen�tre
		
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(400, 275);
	        setLocation(200, 200);
	        setTitle("Impro sur " + filename);
	        //Panel avec son gestionnaire de pr�sentation
	        JPanel buttonpanel = new JPanel(new GridBagLayout());
	        GridBagConstraints x = new GridBagConstraints();
	      
	 //Un premier bouton un peu particulier: le slider pour r�gler le volume
	        
	      JSlider JSlider_AugmenterVolume=new javax.swing.JSlider(-10, 6);  //Palette de volume -10dB � +6 dB.
	      
	      SliderListener sliderlisten = new SliderListener() {                      //Cr�ation d'un ChangeListener Abstrait pour le Slider, d�finit dans une autre classe
	    	  public void stateChanged(ChangeEvent e) {
	    	        JSlider source = (JSlider)e.getSource();
	    	        if (!source.getValueIsAdjusting()) {
	    	              changerVolume((int)source.getValue());                    //Appel � une m�thode de IHMBasique puis de JoueurDeSession
	    	            
	    	        }    
	    	    }
	      };
	      
	      
	      JSlider_AugmenterVolume.addChangeListener(sliderlisten);                   //On ajoute le Change Listener au slider
	        
	       	 
	         
	        x.fill = GridBagConstraints.HORIZONTAL;                                //Caract�ristiques pour le GridBagLayout
	        x.gridx = 2;
	        x.gridy = 4;
	        x.ipady=0;
	        x.ipadx=0;
	        x.gridwidth=8;
	        Insets insetGlobal=new Insets(10,0,0,0);
	        x.insets=insetGlobal;
	        JSlider_AugmenterVolume.setName("Volume");
	        JSlider_AugmenterVolume.setBorder(new TitledBorder("Volume"));
	        buttonpanel.add(JSlider_AugmenterVolume,x);                             //Ajout du Slider au Panel
	      getContentPane().add(buttonpanel);
	            
	      //Bouton play
	        JButton jButton_play = new javax.swing.JButton();
	        jButton_play.setText("Play");
	        jButton_play.addMouseListener(new java.awt.event.MouseAdapter() {
	 
	            public void mousePressed(java.awt.event.MouseEvent evt) {
	            if (playSess==null||playSess.isCanceled==true) {
	            	playSess = new JoueurDeSession(partie);
	            playSess.start();                                       //Appel � m�thode du thread JoueurDeSession
	            }
	        	
	        		
	            
	            /*
	             Ajout � l'int�rieur du mouseclicked un timer qui va permettre de rafra�chir les �l�ments graphiques.
	             Ce timer est ici parce que l'action qu'il doit engendrer r�guli�rement n'a de sens que si le thread playSess est lanc�,
	             � cause des variables que playSess initialise.
	             */
	        		
	        		         ActionListener taskPerformer = new ActionListener() {
			           

					public void actionPerformed(ActionEvent evt) {
		         //   	Tout ce qui doit �tre actualis�.
				        jprogressbar.setValue(sortirPourcentage());
				        jButton_Do.setBackground(sortirCouleurInstant(0));
				        jButton_Dod.setBackground(sortirCouleurInstant(1));
		            	 jButton_Re.setBackground(sortirCouleurInstant(2));
		            	 jButton_Red.setBackground(sortirCouleurInstant(3));
		            	 jButton_Mi.setBackground(sortirCouleurInstant(4));
		            	 jButton_Fa.setBackground(sortirCouleurInstant(5));
		            	 jButton_Fad.setBackground(sortirCouleurInstant(6));
		            	 jButton_Sol.setBackground(sortirCouleurInstant(7));
		            	 jButton_Sold.setBackground(sortirCouleurInstant(8));
		            	 jButton_La.setBackground(sortirCouleurInstant(9));
		            	 jButton_Lad.setBackground(sortirCouleurInstant(10));
		            	 jButton_Si.setBackground(sortirCouleurInstant(11));
		            	 

		            };}
		         ;
		        
		      new Timer(100, taskPerformer) .start();     //Cr�ation et lancement du timer, qui va lancer lancer l'actualisation tous les dixi�mes de seconde
		              
		 
	        		
	            }
	        });
	        
	        //Ajout du composant graphique
	        
	       x.fill = GridBagConstraints.HORIZONTAL;
	        x.gridx = 5;
	        x.gridy = 0;
	        x.ipady=0;
	        x.ipadx=0;
	        x.gridwidth=2;
	        buttonpanel.add(jButton_play,x);  
	      getContentPane().add(buttonpanel);
	        
	      //Le bouton pause
	      
	        jButton_pause = new javax.swing.JButton();
	        jButton_pause.setText("Pause");
	        jButton_pause.addMouseListener(new java.awt.event.MouseAdapter() {
	 
	           
				

				public void mousePressed(java.awt.event.MouseEvent evt) {
				
	            	pause();                                                    //M�thode de l'IHM
	            }
	        });
	       
	        x.gridx = 3;
	        x.gridy = 0;
	        x.ipadx=0;
	        buttonpanel.add(jButton_pause,x);
	        getContentPane().add(buttonpanel);
	       
	  //Cr�ation de tous les touches de piano, une par une "� la main".
	    
	        jButton_Do.setText(c.renvoyerNom());                       //Le bouton Do est not� Do
	        jButton_Do.setBackground(partie.renvoyerGammeInstant(0).renvoyerContenu()[0]);       //La couleur est associ�e celle de la premi�re
	                                                                                             //Case de la Gamme � l'instant 0, et sera actualis�e.
	        
	       jButton_Do.addMouseListener(new java.awt.event.MouseAdapter() {                       //On fait jouer Do lorsqu'il y a un mouseClicked.
	    	
	    	   
	            public void mousePressed(java.awt.event.MouseEvent evt) {
	               c.jouer();
	            }
	        });
	        
	       x.fill = GridBagConstraints.HORIZONTAL;
	        x.gridx = 0;
	        x.gridy = 1;
	        x.ipady=100;
	        x.ipadx=0;
	        x.gridwidth=1;
	       
	        buttonpanel.add(jButton_Do,x);
	      getContentPane().add(buttonpanel);
	        
	  
	        jButton_Dod.setText(cd.renvoyerNom());
	        jButton_Dod.setBackground(partie.renvoyerGammeInstant(0).renvoyerContenu()[1]);
	        
	       jButton_Dod.addMouseListener(new java.awt.event.MouseAdapter() {
	    	
	    	   
	            public void mousePressed(java.awt.event.MouseEvent evt) {
	               cd.jouer();
	            }
	        });
	        
	       x.fill = GridBagConstraints.HORIZONTAL;
	        x.gridx = 1;
	        x.gridy = 1;
	        x.ipady=70;
	        x.anchor=GridBagConstraints.PAGE_START;
	        x.ipadx=0;
	        buttonpanel.add(jButton_Dod,x);
	      getContentPane().add(buttonpanel);
	        
	        
	        
	        jButton_Re.setText(d.renvoyerNom());
	        jButton_Re.setBackground(partie.renvoyerGammeInstant(1).renvoyerContenu()[2]);
	       jButton_Re.addMouseListener(new java.awt.event.MouseAdapter() {
	 
	            public void mousePressed(java.awt.event.MouseEvent evt) {
	               d.jouer();
	               
	            }
	        });
	        
	       x.fill = GridBagConstraints.HORIZONTAL;
	        x.gridx = 2;
	        x.gridy = 1;
	        x.ipady=100;
	        x.ipadx=0;
	       
	        buttonpanel.add(jButton_Re,x);
	      getContentPane().add(buttonpanel);
		        
	        
	   
	        jButton_Red.setText(dd.renvoyerNom());
	        jButton_Red.setBackground(partie.renvoyerGammeInstant(1).renvoyerContenu()[3]);
	       jButton_Red.addMouseListener(new java.awt.event.MouseAdapter() {
	 
	            public void mousePressed(java.awt.event.MouseEvent evt) {
	               dd.jouer();
	               
	            }
	        });
	       x.fill = GridBagConstraints.HORIZONTAL;
	        x.gridx = 3;
	        x.gridy = 1;
	        x.ipady=70;
	        x.ipadx=0;
	       
	       buttonpanel.add(jButton_Red,x);
	        getContentPane().add(buttonpanel);
	
	        jButton_Mi.setText(e.renvoyerNom());
	        jButton_Mi.setBackground(partie.renvoyerGammeInstant(1).renvoyerContenu()[4]);
	       jButton_Mi.addMouseListener(new java.awt.event.MouseAdapter() {
	 
	            public void mousePressed(java.awt.event.MouseEvent evt) {
	               e.jouer();
	               
	            }
	        });
	       
	       x.fill = GridBagConstraints.HORIZONTAL;
	        x.gridx = 4;
	        x.gridy = 1;
	        x.ipady=100;
	        x.ipadx=0;
	       
	       buttonpanel.add(jButton_Mi,x);
	       getContentPane().add(buttonpanel);
	        
	        

		
		        jButton_Fa.setText(f.renvoyerNom());
		        jButton_Fa.setBackground(partie.renvoyerGammeInstant(1).renvoyerContenu()[5]);
		        
		       jButton_Fa.addMouseListener(new java.awt.event.MouseAdapter() {
		    	
		    	   
		            public void mousePressed(java.awt.event.MouseEvent evt) {
		               f.jouer();
		            }
		        });
		       x.fill = GridBagConstraints.HORIZONTAL;
		        x.gridx = 5;
		        x.gridy = 1;
		        x.ipady=100;
		        x.ipadx=0; 
		       
		       
		        buttonpanel.add(jButton_Fa,x);
		     getContentPane().add(buttonpanel);
	        
	        
		 
		        jButton_Fad.setText(fd.renvoyerNom());
		        jButton_Fad.setBackground(partie.renvoyerGammeInstant(1).renvoyerContenu()[6]);
		       jButton_Fad.addMouseListener(new java.awt.event.MouseAdapter() {
		    	
		    	   
		            public void mousePressed(java.awt.event.MouseEvent evt) {
		               fd.jouer();
		            }
		        });
		       x.fill = GridBagConstraints.HORIZONTAL;
		        x.gridx = 6;
		        x.gridy = 1;
		        x.ipady=70;
		        x.ipadx=0; 
		       
		       
		        buttonpanel.add(jButton_Fad,x);
		       getContentPane().add(buttonpanel);
	        
		       
		        jButton_Sol.setText(g.renvoyerNom());
		        jButton_Sol.setBackground(partie.renvoyerGammeInstant(1).renvoyerContenu()[7]);
		       jButton_Sol.addMouseListener(new java.awt.event.MouseAdapter() {
		    	
		    	   
		            public void mousePressed(java.awt.event.MouseEvent evt) {
		               g.jouer();
		            }
		        });
		       x.fill = GridBagConstraints.HORIZONTAL;
		        x.gridx = 7;
		        x.gridy = 1;
		        x.ipady=100;
		        x.ipadx=0; 
		       
		       
		        buttonpanel.add(jButton_Sol,x);
		    getContentPane().add(buttonpanel);
		        
		        JButton jButton_Sold = new javax.swing.JButton();
		        jButton_Sold.setText(gd.renvoyerNom());
		        jButton_Sold.setBackground(partie.renvoyerGammeInstant(1).renvoyerContenu()[8]);
		        
		       jButton_Sold.addMouseListener(new java.awt.event.MouseAdapter() {
		    	
		    	   
		            public void mousePressed(java.awt.event.MouseEvent evt) {
		               gd.jouer();
		            }
		        });
		       x.fill = GridBagConstraints.HORIZONTAL;
		        x.gridx = 8;
		        x.gridy = 1;
		        x.ipady=70;
		        x.ipadx=0; 
		       
		       
		       buttonpanel.add(jButton_Sold,x);
		       getContentPane().add(buttonpanel);
		        		        
		        jButton_La.setText(a.renvoyerNom());
		        jButton_La.setBackground(partie.renvoyerGammeInstant(1).renvoyerContenu()[9]);
		        
		       jButton_La.addMouseListener(new java.awt.event.MouseAdapter() {
		    	
		    	   
		            public void mousePressed(java.awt.event.MouseEvent evt) {
		               a.jouer();
		            }
		        });
		       x.fill = GridBagConstraints.HORIZONTAL;
		        x.gridx = 9;
		        x.gridy = 1;
		        x.ipady=100;
		        x.ipadx=0;
		       buttonpanel.add(jButton_La,x);
		       getContentPane().add(buttonpanel);
	        
		        
		        JButton jButton_Lad = new javax.swing.JButton();
		        jButton_Lad.setText(ad.renvoyerNom());
		        jButton_Lad.setBackground(partie.renvoyerGammeInstant(1).renvoyerContenu()[10]);
		        
		       jButton_Lad.addMouseListener(new java.awt.event.MouseAdapter() {
		    	
		    	   
		            public void mousePressed(java.awt.event.MouseEvent evt) {
		               ad.jouer();
		            }
		        });
		        
		       x.fill = GridBagConstraints.HORIZONTAL;
		        x.gridx = 10;
		        x.gridy = 1;
		        x.ipady=70;
		        x.ipadx=0;
		       
		       buttonpanel.add(jButton_Lad,x);
		       getContentPane().add(buttonpanel);
		        
		    
		        jButton_Si.setText(b.renvoyerNom());
		        jButton_Si.setBackground(partie.renvoyerGammeInstant(1).renvoyerContenu()[11]);
		        
		       jButton_Si.addMouseListener(new java.awt.event.MouseAdapter() {
		    	
		    	   
		            public void mousePressed(java.awt.event.MouseEvent evt) {
		               b.jouer();
		            }
		        });
		       x.fill = GridBagConstraints.HORIZONTAL;
		        x.gridx = 11;
		        x.gridy = 1;
		        x.ipady=100;
		        x.ipadx=0;
		       buttonpanel.add(jButton_Si,x);
		     getContentPane().add(buttonpanel);
		        
		        //Ajout d'une JProgressBar, pour suivre 
		        
		        jprogressbar = new JProgressBar();
	
		        x.fill = GridBagConstraints.HORIZONTAL;
		        x.gridx = 0;
		        x.gridy = 2;
		        x.ipady=0;
		        x.ipadx=0;   
		        x.gridwidth=12;
		        
		        buttonpanel.add(jprogressbar,x);
		        
		        //Ajout du bouton Stop, pour pouvoir redemarrer ensuite depuis le d�but.
		        
		        JButton jButton_stop = new javax.swing.JButton();
		        jButton_stop.setText("Stop");
		        jButton_stop.addMouseListener(new java.awt.event.MouseAdapter() {
		 
		            public void mousePressed(java.awt.event.MouseEvent evt) {
		                stop();
		            }
		        });
		        x.fill = GridBagConstraints.HORIZONTAL;
		        x.gridx = 7;
		        x.gridy = 0;
		        x.ipady=0;
		        x.ipadx=0;   
		        x.gridwidth=2;
		        buttonpanel.add(jButton_stop,x);
		     getContentPane().add(buttonpanel);       
		           
		 
		        
		        }
	       
	
	
	
	
	
	
	
	
	
	
	public void pause(){                                         //M�thode pour faire la pause sur playSess et changer le texte sur le bouton.
		                                                         //Permet de faire la pause dans l' ActionListener
 if (playSess != null) {
	 if (jButton_pause.getText()=="Reprendre") {
		 
	 }
            if (jButton_pause.getText()=="Pause") {
               jButton_pause.setText("Reprendre");
            }
 else {
                jButton_pause.setText("Pause");
       }
            this.playSess.pause();
            
        } 
        
        
    }

	  public void stop() {
	        if (this.playSess != null) {
	            this.playSess.cancel();
	         
	        }
	    }
	
	  public Color sortirCouleurInstant(int b) {                    //M�thode permettant d'avoir acc�s � la m�thode getColorInstant dans les 
			return this.playSess.getColorInstant(b);              //Action Listeners
		}
		
	  
	 
	public int sortirPourcentage() {                                //M�me id�e, mais pour le pourcentage
		return this.playSess.getPourcentage();
	}
	 
	public double sortirAvancement() {                             //Idem
		return this.playSess.getAvancement(); 
	}
	
	public void augmenterVolume() {                                //Idem
		this.playSess.setGain(this.playSess.getGain().getValue()+4);
	}
	public void changerVolume(int a) {                            //Idem
		this.playSess.setGain((float) (a));
	}
	
	public void diminuerVolume() {                                      //Iden
		this.playSess.setGain((float) (this.playSess.getGain().getValue()-4));
	}
	
		      public Thread getPlaySess() {                      //Renvoie le thread playSess
		    	  return this.playSess;
		      }
	
		        }
	       

