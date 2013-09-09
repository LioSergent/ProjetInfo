import java.awt.Color;


public class DatMain {
	

	
	public static void main(String[] args) {
	
		
		//Création des gammes
		       Color t1es[] = { Color.GREEN, Color.RED, Color.GREEN, Color.RED, Color.GREEN, Color.RED, Color.RED,Color.GREEN,
				Color.RED,Color.BLUE,Color.RED,Color.RED};
			 Color t2es[] = { Color.GREEN, Color.RED, Color.BLUE, Color.RED, Color.RED, Color.GREEN, Color.RED,Color.GREEN,
				Color.RED,Color.GREEN,Color.RED,Color.RED};
				Gamme pentalam=new Gamme("pentaLam",t1es);
				Gamme pentarem=new Gamme("pentaRem",t2es);
          //Création du découpage
				
				double tabes[]={0,40};
           //Création du morceau
           Gamme[] gammeses={pentalam,pentarem};
           Morceau es=new Morceau("es",gammeses,tabes,"C:\\Users\\Lionel\\workspace\\ProjetInfo\\src\\Accompagnements\\es.wav" );
		
           //Lancement d'une partie avec le morceau précédent.
           IHMBasique player = new IHMBasique(es);
        player.setVisible(true);
		
		
		
		
		
		
		
		
		
		
		
		
		
	/*	Note p = new Note("Ré", "D:/dos", 4, 2);
		Color t1[] = { Color.GREEN, Color.GREEN, Color.RED, Color.BLUE, Color.GREEN, Color.RED, Color.GREEN,Color.GREEN,
				Color.RED,Color.GREEN,Color.RED,Color.RED};
		Gamme penta = new Gamme("Penta", t1);
		penta.afficher();
		penta.verifier();
		Note r = new Note("CM","C:\\Users\\Lionel\\workspace\\ProjetInfo\\src\\Accompagnements\\ARock.wav", 3,2);
		Note f = new Note("ta gueule", 1, 1);
		f.afficher();
		
		Gamme[] pentatot={penta};
		System.out.println(pentatot.length);
		double tab[]={0};
		System.out.println(tab.length);
		Morceau rock =new Morceau("rock", pentatot, tab,"C:\\Users\\Lionel\\workspace\\ProjetInfo\\src\\Accompagnements\\ARock.wav" );
		
	IHMBasique player = new IHMBasique(rock);
          player.setVisible(true);
	*/	
	/*	test de sortie de données
	 * JoueurDeSession essai=new JoueurDeSession(rock);
		System.out.println(essai.getTempsTotal());
		
		Thread loul=new JoueurDeSession(rock);
		loul.start(); */
		
          //            Préparation d'une partie avec un vrai agencement: es
         
		
		
           
           
           	
	
		
	}
}
