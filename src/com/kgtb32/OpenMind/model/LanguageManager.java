package com.kgtb32.OpenMind.model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import com.kgtb32.OpenMind.model.xml.LangXMLParser;
import com.kgtb32.OpenMind.model.xml.TradXMLParser;


/**
 * Fichier LanguageManager
 * Permet de charger en mémoire un fichier de langue pour traduire l'application
 * @author kevin - 2021 
 */

public class LanguageManager {
	
	private String LanguagePackPath; //Chemin du fichier de langue
	private ArrayList<String> LanguagesAvailable; //ArrayList des langes disponibles dans le fichier de langue
	private String SelectedLanguagePack; //Langue sélectionnée 
	private HashMap<String,String> LanguageMap; //HashMap : représentation sous forme de tuples clé/valeurs de l'ensemble des traductions de l'application
	/**
	 * String : UID de la traduction : ID servant à identifier de manière unique un texte dans l'application (ex : MainWindow.btn_new)
	 * String : traduction effective : Texte qui sera affiché dans le widget 
	 */
	
	/**
	 * COnstructeur par défaut
	 * Permet d'instancier le language manager et d'aller récupérer les informations de langues stockées 
	 */
	public LanguageManager() {
		this.LanguagePackPath = "config/lang/default.xml"; //fichier par défaut
		this.LanguagesAvailable = new ArrayList<>(); //instanciation de l'arraylist
		this.LanguageMap = new HashMap<>(); //instanciation de la hashmap
		this.SelectedLanguagePack = "fr"; //séléction de la langue par défaut
	}
	
	
	/**
	 * Méthode getAllAvailableLanguages
	 * Permet de récupérer l'ensemble des langues disponibles dans le fichier XML
	 * Remplacera LanguagesAvailable 
	 */
	public void getAllAvailableLanguages() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance(); //instanciation d'un nouveau SAXParserFactory
			SAXParser saxParser = factory.newSAXParser(); //récupération d'un parser XML 
			DefaultHandler handler = new LangXMLParser(this); //Initialisation de l'objet LangXMLParser
			saxParser.parse("config/lang/default.xml",handler); //parse du fichier default.xml TODO modification fichier XML 
		}
		catch(Exception e) { //dans le cas ou une exception est levée, on affiche sa trace 
			e.printStackTrace(); //TODO better message 
		}
	}
	
	/**
	 * Méthode getAllTradsForLang
	 * Permet de récupérer l'ensemble des traductions pour la langue choisie par l'user
	 */
	public void getAllTradsForLang() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance(); //instanciation d'un nouveau SAXParserFactory
			SAXParser saxParser = factory.newSAXParser(); //récupération d'un parser XML
			DefaultHandler handler = new TradXMLParser(this); //initialisation de l'objet TradXMLParser
			saxParser.parse("config/lang/default.xml",handler); //parse du fichier default.xml TODO modification du fichier XML
		}
		catch(Exception e) { //dans le cas ou une exception est levée, on affiche sa trace
			e.printStackTrace(); //TODO better message
		}
	}
	
	//TODO debug
	public static void main(String[] args) {
		LanguageManager man = new LanguageManager();
		man.getAllAvailableLanguages();
		man.getAllTradsForLang();
		for(String s: man.getLanguagesAvailable()) {
			System.out.println(s);
		}
		for(String s:man.getLanguageMap().keySet()) {
			System.out.println(man.getLanguageMap().get(s) + " : "+s);
		}
		
	}

	
	/**
	 * Getters & Setters
	 */

	public String getLanguagePackPath() {
		return LanguagePackPath;
	}


	public void setLanguagePackPath(String languagePackPath) {
		LanguagePackPath = languagePackPath;
	}


	public ArrayList<String> getLanguagesAvailable() {
		return LanguagesAvailable;
	}


	public void setLanguagesAvailable(ArrayList<String> languagesAvailable) {
		LanguagesAvailable = languagesAvailable;
	}


	public String getSelectedLanguagePack() {
		return SelectedLanguagePack;
	}


	public void setSelectedLanguagePack(String selectedLanguagePack) {
		SelectedLanguagePack = selectedLanguagePack;
	}


	public HashMap<String, String> getLanguageMap() {
		return LanguageMap;
	}


	public void setLanguageMap(HashMap<String, String> languageMap) {
		LanguageMap = languageMap;
	}	
	
	
}
