import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


 

/*Le joueur de session est le thread de lecture l'accompagnement, permettant de lire les instance de Morceau
Il s'agit d'une version améliorée d'AePlayWave, permmettant notamment de suivre l'avancement, de faire pause et de redémarrer.
*/


public class JoueurDeSession extends Thread {

	
	
	
	public Morceau session;                             //Le morceau à jouer, correspondant à une session de jeu
	private Gamme gammeCourante;                        //La gamme valable à un instant donné
	private double avancement;                          //L'avancement de la lecture en secondes
	private int pourcentage;                            //L'avancement de la lecture en %
	private double  tempsTotal;                         //La durée en secondes du fichier Wav
	private final int EXTERNAL_BUFFER_SIZE = 10000;     //La taille du paquet envoyé au mixer
	private boolean isSuspend = false;                  //Variable de pause
	public boolean isCanceled = false;                  //Variable d'arrêt
	int frameSize;                                      //Taille de l'échantillon du fichier wav
	float frameRate;                                    //Fréquence d'échantillonage du fichier wav
	public FloatControl gainControl;                    //Variable réglage du volume
   
	
	//constructeur
	
public JoueurDeSession(Morceau a)  {
		
	session=a;
	gammeCourante=session.renvoyerProposition()[0];
	avancement=0;
	pourcentage=0;
	
	//Je cherche maintenant frameSize, frameRate, TempsTotal
	
	File soundFile = new File(session.renvoyerAccompagnement());
	AudioInputStream audioInputStream = null;
	
	
	
         try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	 AudioFormat format = audioInputStream.getFormat();
	frameSize=format.getFrameSize();                                //On récupére le temps          
	frameRate=format.getFrameRate();      
	 int audiolength = (int) soundFile.length();
	 
	 tempsTotal=audiolength/(frameRate*frameSize);
	 	
	}


//Méthodes de renvoi ou de changement des attributs pour l'IHM

public double getTempsTotal() {
	return this.tempsTotal;
	
}

public double getAvancement() {
	return this.avancement;
}

public int getPourcentage() {
	return this.pourcentage;
}

public FloatControl getGain() {
	return this.gainControl;

}

public void setGain(float g) {                       //Méthode appelée par la méthode changerVolume de l'IHM
	gainControl.setValue(g);
	
}

public Gamme getGammeInstant() {
	return this.session.renvoyerGammeInstant(this.avancement);
}

public Color getColorInstant(int a) {
	return this.getGammeInstant().renvoyerContenu()[a];
}


/**
 * Mettre en pause ou reprendre la lecture
 */
public synchronized void pause() {
   if (this.isSuspend == true) {
       this.isSuspend = false;
  } else {
        this.isSuspend = true;
    }

}

public synchronized void cancel() {
    this.isCanceled = true;

}

//Le thread de lecture du fond sonore

public void run() {
	
//On crée le fichier
	 File soundFile = new File(session.renvoyerAccompagnement());
     if (!soundFile.exists()) {
         System.err.println("Wave file not found: " + session.renvoyerAccompagnement());
         return;
     }

     
     //On Initialise le stream (Récupère les données du fichiers)
        AudioInputStream audioInputStream = null;

         try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
     
     //On obtient des informations sur la forme des données, puis on crée la source de données pour le mixer. 
     
     AudioFormat format = audioInputStream.getFormat();
     SourceDataLine auline = null;
    DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
     System.out.print(info);

     
 
         try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   //crée un mixer adapté
         try {
			auline.open(format);
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}                                   //ouvre ce mixer
 
     //réglage Volume
     
  //   System.out.println(auline.isControlSupported(FloatControl.Type.MASTER_GAIN));
     if (auline.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
    	 gainControl = (FloatControl) auline.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-5);
     }   
 
     
     
     

     
     
     auline.start();                                          //démarre le mixeur
     int nBytesRead = 0;
     byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];          //Initialise le tableau de données
     long audioreaded = 0;
     pourcentage=0;     
     try {
         while (nBytesRead != -1 && this.isCanceled == false) {
             nBytesRead = audioInputStream.read(abData, 0, abData.length);     //Range dans AbData les données du stream
             
             if (nBytesRead >= 0) {
                 //calcul de la progression
                avancement= audioreaded/(frameRate*frameSize);
                pourcentage=  (int) (100*avancement/tempsTotal);
                 auline.write(abData, 0, nBytesRead);                          //Envoie dans le mixer AbData
                 audioreaded += nBytesRead;
         //    System.out.println(avancement);
         //      System.out.println(audioreaded);
             }
             /**
              * Annuler la lecture
              */
             if (this.isCanceled == true) {
                 System.out.print("Lecture abandonnée");
                 interrupt();                                      //méthode issu de Thread.
             }
            /**
              * Mettre en pause
              */
             if (this.isSuspend == true) {
            	 System.out.println("Pause!!");
                 while (this.isSuspend == true) {
          
                         try {
							Thread.sleep(250);                         //tant que l'attribut isSuspend est vrai, on laisse le thread dormir.

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}                                                      if (this.isCanceled == true) {
                             System.out.print("La lecture est abandonnée");
                         
                         }
  
                 }
             }}
     
     
     }
             catch (IOException e) {
                 e.printStackTrace();
                 return;
             } finally {
                 auline.drain();                     //oblige le mixeur a finir ce qu'il a dans son buffeur
                 auline.close();                     //Ferme le flux de données vers le mixeur
             }
     
     
}
}



