package com.kgtb32.OpenMind.model.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.kgtb32.OpenMind.model.LanguageManager;

/**
 * LangXMLParser
 * @author kevin - 2021
 *
 */

public class LangXMLParser extends DefaultHandler{
	
		private boolean lang = false; //condition, permettant de test si on est bien sur la lang
		private LanguageManager languageManager; //Référence du language manager
		
		/**
		 * Constructeur par défaut
		 * Permet d'instancier le xml parser
		 * @param languageManager : LanguageManager : référence du stockage des informations sur la langue
		 */
		public LangXMLParser(LanguageManager languageManager) {
			super();
			this.languageManager = languageManager; //récupération du languagemanager par référence
			this.languageManager.getLanguagesAvailable().clear(); //réinitalisation de l'ensemble des langues disponibles
		}
		
		/**
		 * Méthode startElement
		 * Permet de récupérer la balise de début
		 */
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			if(qName.equals("lang")) { //si le nom de la balise est égal à lang on doit la traiter
				this.lang = true; //passage de la confition de traitement à true
			}
		}
		
		/**
		 * Méthode characters
		 * Permet de récupérer le contenu 
		 */
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			super.characters(ch, start, length);
			if(this.lang) { //si la condition de traitement lang est levée
				this.languageManager.getLanguagesAvailable().add(new String(ch,start,length)); //ajout dans l'arraylist des langues disponibles, la langue
				this.lang = false; //traitement
			}
		}


		
		
		
	
}
