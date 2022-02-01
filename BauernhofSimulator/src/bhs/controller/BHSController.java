package bhs.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bhs.view.MainFrame;
import bhs.view.MainTabbedPane;

public class BHSController {
	private MainFrame frame;
	private MainTabbedPane mtp;
	private JList<String> pflanzenListe;
	private JList<String> produktListe;
	private JList<String> tierListe;
	private JList<Integer> produktAnzahl;
	private JList<Integer> tierAnzahl;
	private JList<Integer> pflanzenAnzahl;
	private JLabel lblkonto;
	private JLabel lblFutter;
	private JLabel lblSilo;
	private JLabel lblRunde;
	private JButton btnNewRound;
	
	public BHSController() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					init();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void init() {
		// Attribute setzen
		this.frame = new MainFrame();
		this.mtp = frame.getMtp();
		this.pflanzenListe = frame.getMtp().getPnlUebersicht().getPflanzenListe();
		this.produktListe = frame.getMtp().getPnlUebersicht().getProduktListe();
		this.tierListe = frame.getMtp().getPnlUebersicht().getTierListe();
		this.pflanzenAnzahl = frame.getMtp().getPnlUebersicht().getPflanzenAnzahl();
		this.tierAnzahl = frame.getMtp().getPnlUebersicht().getTierAnzahl();
		this.produktAnzahl = frame.getMtp().getPnlUebersicht().getProduktAnzahl();
		this.lblkonto = frame.getLblkonto();
		this.lblFutter = frame.getLblFutter();
		this.lblSilo = frame.getLblSilo();
		this.lblRunde = frame.getLblRunde();
		this.btnNewRound = frame.getBtnNewRound();
		// Daten
		this.setPflanzen();
		this.setTiere();
		this.setProdukte();
		this.setStatus();
		// Listeners
		this.setTabChangeListener();
		this.setNewRoundAction();
	}
	
	public void setPflanzen() {
//		Daten der Pflanzen holen
		String[] pflanzen = {"Chinakohl", "Karotten", "Birne", "Kartoffel", "Apfel"};
		Integer[] pflanzenAnzahl = {1,2,3,4,5};
		
		this.pflanzenListe.setModel(new AbstractListModel<String>() {
			String[] values = pflanzen;
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		this.pflanzenAnzahl.setModel(new AbstractListModel<Integer>() {
			Integer[] values = pflanzenAnzahl;
			public int getSize() {
				return values.length;
			}
			public Integer getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	public void setTiere() {
//		Daten f�r Tiere holen
		String[] tiere = {"Kuh", "Schaf", "Schwein"};
		Integer[] anzahl = {1,2,3};
		this.tierListe.setModel(new AbstractListModel<String>() {
			String[] values = tiere;
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		this.tierAnzahl.setModel(new AbstractListModel<Integer>() {
			Integer[] values = anzahl;
			public int getSize() {
				return values.length;
			}
			public Integer getElementAt(int index) {
				return values[index];
			}
		});
	}
	
	public void setProdukte() {
//		Daten f�r Produkte holen
		String[] produkte = {"Milch", "Wolle", "Speck"};
		Integer[] anzahl = {1,2,3};
		this.produktListe.setModel(new AbstractListModel<String>() {
			String[] values = produkte;
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		this.produktAnzahl.setModel(new AbstractListModel<Integer>() {
			Integer[] values = anzahl;
			public int getSize() {
				return values.length;
			}
			public Integer getElementAt(int index) {
				return values[index];
			}
		});		
	}
	
	public void setStatus(){
		// Daten f�r die Status anzeige holen
		Double konto = 2456.45;
		Integer verbrauch = 25;
		Integer silo = 76;
		Integer runde = 25;
		Integer maxRunden = 100;
		
		this.lblkonto.setText("Konto: " + konto);
		this.lblFutter.setText("Verbrauch: " + verbrauch);
		this.lblSilo.setText("Bestand: " + silo);
		this.lblRunde.setText("Runde: " + runde + "/" + maxRunden);
	}
	
	public void setTabChangeListener() {
		this.mtp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("Tab: " + mtp.getSelectedIndex());
				if(mtp.getSelectedIndex() ==  0) {
					setPflanzen();
					setTiere();
					setProdukte();
				}
			}
		});
	}
	
	public void setNewRoundAction() {
		this.btnNewRound.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				mtp.setSelectedIndex(0);	
			}
		});
	}
}
