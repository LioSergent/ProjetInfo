//Classe définissant ce qu'est une gamme: un tableau de couleur, plus le nom de la gamme et le nom de sa tonique.

import java.awt.Color;


public class Gamme {

	private Color[] contenu; //Tableau de 12 cases, do=premiere case (0). Red: Note hors de la gamme, Blue:Tonique, Green: Note de la gamme.
	private String nom;
	private Note tonique;
	
//Constructeur
	public Gamme(String a, Color[] b) {
		
		nom = a;
		contenu = b;
	    tonique = null;
	}
	    
	    public Color[] renvoyerContenu() {
	    	return contenu;
	    	
	    }
	    
	    public void afficher() {
	    	
	   	for (int i=0; i<12; i++) {
	    			
	 System.out.print(renvoyerContenu()[i]+"/");
	    			}

	    		}
	    
	    //Méthode de vérification pour ne pas utiliser de mauvais tableau.
	    public boolean verifier() {
	    	if (contenu.length!=12) {
	    		 System.out.print("c'est pas buen"); return false;}
	    	else { System.out.print("c'est buen"); return true;}
	    }
	    
	    	}

	   
	