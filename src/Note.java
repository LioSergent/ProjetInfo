
/*Cr�ation d'une classe dont les instances sont des notes.
Sert � associer des donn�es de th�orie musicale et l'adresse du fichier son qui sera jou� lors de l'appui sur la touche
par AePlayWave

*/
public class Note {

	public String nom;
	public String adresse; // Correspond � l'adresse sur l'ordinateur du fichier
							// wav.
	public int octave;
	public int intervalle; // corespond � la place de la note sur le clavier:
							// Do=0 Do#=1...Si=11
	

	//Constructeur classique
	
	public Note(String a, String b, int i, int j) {
		nom = a;
		adresse = b;
		octave = i;
		intervalle = j;
	
	}

	// Constructeur servant uniquement � ne pas avoir besoin de sp�cifier le nom de la note
	public Note(String b, int i, int j) {
		adresse = b;
		octave = i;
		intervalle = j;
		switch (j) {
		case 0:
			nom = "Do" + "i";
			break;
		case 1:
			nom = "Do#" + "i";
			break;
		case 2:
			nom = "R�" + "i";
			break;
		case 3:
			nom = "R�#" + "i";
			break;
		case 4:
			nom = "Mi" + "i";
			break;
		case 5:
			nom = "Fa" + "i";
			break;
		case 6:
			nom = "Fa#" + "i";
			break;
		case 7:
			nom = "Sol" + "i";
			break;
		case 8:
			nom = "Sol#" + "i";
			break;
		case 9:
			nom = "La" + "i";
			break;
		case 10:
			nom = "La#" + "i";
			break;
		case 11:
			nom = "Si" + "i";
			break;
		default:
			nom = "inconnu";

		}

	}

	//Quelques m�thodes d'affiche et de renvoi. 
	
	public void afficher() {
		System.out.println("note:" + nom + "    contenu � :  " + adresse
				+ "   r�pertori�:    " + intervalle + "x" + octave);
	}

	public int renvoyerIntervalle() {
		return intervalle;
	}

	public String renvoyerFichier() {
		return this.adresse;
	}
	
	public String renvoyerNom() {
		return this.nom;
	}
	
	//Appele le thread de lecture � jouer la note.
	
	public void jouer() {
		Thread playWave = new AePlayWave(this);
		playWave.start();
	
	}


		
        
}
	
	


