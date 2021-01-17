package com.kgtb32.OpenMind.model.xml;

import org.xml.sax.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.kgtb32.OpenMind.model.LanguageManager;

/**
 * Fichier TradXMLParser
 * Parser permettant de charger les traductions qui sont au format XML 
 * @author kevin - 2021 
 *
 */

public class TradXMLParser extends DefaultHandler{

	private boolean trad = false; //condition, permettant de test si on est bien sur la trad
	private boolean selectedLang = false; //condition, permetant de récupérer uniquement les traductions correspondant à la langue choisie
	
	private String textID = new String(); //textID : ID du text à remplacer : (ex : MainWindow.btn_new)
	
	private LanguageManager languageManager; //Référence du language manager
	
	/**
	 * Constructeur par défaut
	 * Permet d'instancier le xml parser
	 * @param languageManager : LanguageManager : référence du stockage des informations sur la langue
	 */
	public TradXMLParser(LanguageManager languageManager) {
		super();
		this.languageManager = languageManager; //récupération du languagemanager par référence
		this.languageManager.getLanguageMap().clear(); //réinitialisation de la map de langage
	}

	/**
	 * Méthode startElement
	 * permet de récupérer la balise de départ
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(qName.equals(this.languageManager.getSelectedLanguagePack())) {//si qName (balise) est égal à la langue sélectionnée par l'user :
			this.selectedLang = true; //passage de selectedLang à true : début du traitement 
		}
		if(qName.equals("trad") && this.selectedLang) { //si qName (balise) est égal à trad ET que on est bien dans la langue choisie
			this.trad = true; //passage de trad à true :: traitement à réaliser
			this.textID = attributes.getValue("textid"); //récupération du textID
		}
	}
	
	
	/**
	 * Méthode characters 
	 * permet de récupérer le contenu
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if(this.trad && this.selectedLang)  { //si on est dans le cas d'une balise trad et que l'on est dans la langue choisie par l'user:
			if(this.textID != null) { //si le textID est défini : 
				this.languageManager.getLanguageMap().put(this.textID, new String(ch,start,length)); //ajout de la clé / valeur dans le hashmap
				/**
				 * Clé : textID
				 * Valeur : contenu hors balise
				 */
			}
			else { //si le textID n'est pas défini 
				System.err.println("[!] com.kgtb32.OpenMind.model.xml.TradXMLParser" 
						+ "\nno TextID Supplied for value "+new String(ch,start,length)
						+ "\nPlease add in the language pack XML a textID for that value."
						+ "\n-----------");	 //affichage d'une erreur dans le stdErr 
			}
			this.trad = false; //passage de trad à false: continuer le traitement
		}
	}

	/**
	 * Méthode endElement
	 * Permet de récupérer la balise de fin
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(qName.equals(this.languageManager.getSelectedLanguagePack())) { //si qName (balise) est égale à la langue choisie par l'user
			this.selectedLang = false; //passage de selectedLang à false : fin du traitement
		}
	}
	
	

	
	
	
}
