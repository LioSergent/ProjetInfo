/*
 Cr�ation de la classe qui correspond � une session de jeu: il y a tout ce qu'il faut: un tableau de gamme et le d�coupage temporel qui lui est associ�,
 ainsi que l'adresse d'un fichier wav qui servira d'accompagnement.
 Le tout sera jou� par JoueurDeSession.
 */
public class Morceau {

	private String nom;
	private Gamme[] proposition;
	private double[] d�coupage;
	private String accompagnement; 
	
	//Constructeur
	
	public Morceau(String a, Gamme[] b, double[] c, String d) {
		
		nom=a;
		proposition=b;
		d�coupage=c;
	
		accompagnement=d;
		
	}
	
	//La m�thode principale, permettant de renvoyer la gamme associ� � un instant pr�cis.
	
	public Gamme renvoyerGammeInstant(double a) {
		int i=0;
		boolean p=false;
		while (p==false) {
			i++;
			if (i+1>d�coupage.length) {
				p=true; 	
			}
			else if (a<=d�coupage[i]) {
				p=true;
			}
			
		}
		return proposition[i-1];
	}
	
	public Gamme[] renvoyerProposition() {
		return proposition;
		
	}
	
	public String renvoyerAccompagnement() {
		return accompagnement;
		
	}
	
	public double[] renvoyerD�coupage() {
		return d�coupage;
	}
	
	
	
	
	}

