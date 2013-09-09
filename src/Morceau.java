/*
 Création de la classe qui correspond à une session de jeu: il y a tout ce qu'il faut: un tableau de gamme et le découpage temporel qui lui est associé,
 ainsi que l'adresse d'un fichier wav qui servira d'accompagnement.
 Le tout sera joué par JoueurDeSession.
 */
public class Morceau {

	private String nom;
	private Gamme[] proposition;
	private double[] découpage;
	private String accompagnement; 
	
	//Constructeur
	
	public Morceau(String a, Gamme[] b, double[] c, String d) {
		
		nom=a;
		proposition=b;
		découpage=c;
	
		accompagnement=d;
		
	}
	
	//La méthode principale, permettant de renvoyer la gamme associé à un instant précis.
	
	public Gamme renvoyerGammeInstant(double a) {
		int i=0;
		boolean p=false;
		while (p==false) {
			i++;
			if (i+1>découpage.length) {
				p=true; 	
			}
			else if (a<=découpage[i]) {
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
	
	public double[] renvoyerDécoupage() {
		return découpage;
	}
	
	
	
	
	}

