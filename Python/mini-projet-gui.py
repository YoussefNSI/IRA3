import sys
from PyQt5.QtWidgets import (QApplication, QMainWindow, QWidget, QVBoxLayout, QHBoxLayout,
                             QPushButton, QLabel, QLineEdit, QComboBox, QListWidget,
                             QListWidgetItem, QMessageBox, QTabWidget, QTextEdit, QFrame)
from PyQt5.QtCore import Qt
from PyQt5.QtGui import QFont, QColor

# Structure de données pour les trains
trains = {
    'TUN-PAR': {'places_total': 5, 'places_restantes': 5, 'passagers': set()},
    'TUN-ROM': {'places_total': 3, 'places_restantes': 3, 'passagers': set()},
    'TUN-MAD': {'places_total': 4, 'places_restantes': 4, 'passagers': set()},
}

tickets = []


class ReservationApp(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Gestion des Reservations de Trains")
        self.setGeometry(100, 100, 1000, 700)
        
        # Style moderne avec gradient
        self.setStyleSheet("""
            QMainWindow {
                background: qlineargradient(x1:0, y1:0, x2:1, y2:1,
                                          stop:0 #667eea, stop:1 #764ba2);
            }
            QTabWidget::pane {
                border: 1px solid #333;
                background-color: #f5f5f5;
            }
            QTabBar::tab {
                background-color: #e0e0e0;
                color: #333;
                padding: 8px 20px;
                border: 1px solid #ccc;
                border-bottom: none;
                margin-right: 2px;
                font-weight: bold;
            }
            QTabBar::tab:selected {
                background-color: #667eea;
                color: white;
            }
            QPushButton {
                background-color: #667eea;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                font-weight: bold;
                font-size: 11px;
            }
            QPushButton:hover {
                background-color: #764ba2;
            }
            QPushButton:pressed {
                background-color: #5568d3;
            }
            QLineEdit, QComboBox {
                padding: 8px;
                border: 2px solid #ddd;
                border-radius: 5px;
                background-color: white;
                font-size: 11px;
            }
            QLineEdit:focus, QComboBox:focus {
                border: 2px solid #667eea;
            }
            QLabel {
                color: #333;
                font-weight: bold;
            }
            QListWidget {
                border: 2px solid #ddd;
                border-radius: 5px;
                background-color: white;
            }
            QTextEdit {
                border: 2px solid #ddd;
                border-radius: 5px;
                background-color: white;
                font-family: Courier;
            }
        """)
        
        # Widget central
        central_widget = QWidget()
        self.setCentralWidget(central_widget)
        
        # Onglets
        tabs = QTabWidget()
        
        # Onglet 1 : Afficher les trains
        tab1 = self.create_afficher_trains_tab()
        tabs.addTab(tab1, "Voir les Trains")
        
        # Onglet 2 : Réserver une place
        tab2 = self.create_reserver_place_tab()
        tabs.addTab(tab2, "Reserver une Place")
        
        # Onglet 3 : Annuler une réservation
        tab3 = self.create_annuler_reservation_tab()
        tabs.addTab(tab3, "Annuler Reservation")
        
        # Onglet 4 : Afficher les passagers
        tab4 = self.create_afficher_passagers_tab()
        tabs.addTab(tab4, "Passagers")
        
        # Onglet 5 : Trains complets
        tab5 = self.create_trains_complets_tab()
        tabs.addTab(tab5, "Trains Complets")
        
        # Onglet 6 : Tickets
        tab6 = self.create_tickets_tab()
        tabs.addTab(tab6, "Tickets")
        
        layout = QVBoxLayout()
        layout.addWidget(tabs)
        central_widget.setLayout(layout)
    
    def create_frame_with_style(self):
        """Crée un frame stylisé"""
        frame = QFrame()
        frame.setStyleSheet("""
            QFrame {
                background-color: white;
                border-radius: 10px;
                padding: 15px;
            }
        """)
        return frame
    
    def create_afficher_trains_tab(self):
        """Onglet pour afficher les trains"""
        widget = QWidget()
        layout = QVBoxLayout()
        
        # Titre
        title = QLabel("Trains Disponibles")
        title_font = QFont()
        title_font.setPointSize(14)
        title_font.setBold(True)
        title.setFont(title_font)
        title.setStyleSheet("color: white; margin-bottom: 10px;")
        layout.addWidget(title)
        
        # Frame pour les trains
        frame = self.create_frame_with_style()
        frame_layout = QVBoxLayout()
        
        # Liste des trains
        self.train_list = QListWidget()
        frame_layout.addWidget(self.train_list)
        
        frame.setLayout(frame_layout)
        layout.addWidget(frame)
        
        # Bouton rafraîchir
        refresh_btn = QPushButton("Rafraichir")
        refresh_btn.clicked.connect(self.rafraichir_trains)
        layout.addWidget(refresh_btn)
        
        widget.setLayout(layout)
        self.rafraichir_trains()
        return widget
    
    def rafraichir_trains(self):
        """Rafraîchit la liste des trains"""
        self.train_list.clear()
        for code_trajet, info in trains.items():
            places_restantes = info['places_restantes']
            places_total = info['places_total']
            statut = "Disponible" if places_restantes > 0 else "COMPLET"
            
            item_text = f"{code_trajet} | {places_restantes}/{places_total} places | [{statut}]"
            item = QListWidgetItem(item_text)
            
            # Colorer selon la disponibilité
            if places_restantes == 0:
                item.setBackground(QColor("#ffcccc"))
            elif places_restantes <= 2:
                item.setBackground(QColor("#ffffcc"))
            else:
                item.setBackground(QColor("#ccffcc"))
            
            self.train_list.addItem(item)
    
    def create_reserver_place_tab(self):
        """Onglet pour réserver une place"""
        widget = QWidget()
        layout = QVBoxLayout()
        
        # Titre
        title = QLabel("Reserver une Place")
        title_font = QFont()
        title_font.setPointSize(14)
        title_font.setBold(True)
        title.setFont(title_font)
        title.setStyleSheet("color: white; margin-bottom: 10px;")
        layout.addWidget(title)
        
        # Frame pour le formulaire
        frame = self.create_frame_with_style()
        frame_layout = QVBoxLayout()
        
        # Choix du trajet
        frame_layout.addWidget(QLabel("Trajet :"))
        self.reserver_trajet_combo = QComboBox()
        self.reserver_trajet_combo.addItems(trains.keys())
        frame_layout.addWidget(self.reserver_trajet_combo)
        
        # Nom du passager
        frame_layout.addWidget(QLabel("Nom du Passager :"))
        self.reserver_nom_input = QLineEdit()
        self.reserver_nom_input.setPlaceholderText("Entrez le nom du passager")
        frame_layout.addWidget(self.reserver_nom_input)
        
        frame.setLayout(frame_layout)
        layout.addWidget(frame)
        
        # Bouton réserver
        reserver_btn = QPushButton("Reserver")
        reserver_btn.setStyleSheet("""
            QPushButton {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 12px 20px;
                border-radius: 5px;
                font-weight: bold;
                font-size: 12px;
            }
            QPushButton:hover {
                background-color: #45a049;
            }
        """)
        reserver_btn.clicked.connect(self.reserver_place)
        layout.addWidget(reserver_btn)
        
        widget.setLayout(layout)
        return widget
    
    def reserver_place(self):
        """Réserve une place"""
        code_trajet = self.reserver_trajet_combo.currentText()
        nom_passager = self.reserver_nom_input.text().strip()
        
        if not nom_passager:
            QMessageBox.warning(self, "Erreur", "Veuillez entrer le nom du passager.")
            return
        
        if nom_passager in trains[code_trajet]['passagers']:
            QMessageBox.warning(self, "Erreur", f"{nom_passager} est deja inscrit sur {code_trajet}.")
            return
        
        if trains[code_trajet]['places_restantes'] <= 0:
            QMessageBox.warning(self, "Erreur", f"Le trajet {code_trajet} est complet.")
            return
        
        trains[code_trajet]['passagers'].add(nom_passager)
        trains[code_trajet]['places_restantes'] -= 1
        
        numero_place = trains[code_trajet]['places_total'] - trains[code_trajet]['places_restantes']
        ticket = (nom_passager, code_trajet, numero_place)
        tickets.append(ticket)
        
        QMessageBox.information(self, "Succes", 
                              f"Reservation confirmee !\n"
                              f"Passager : {nom_passager}\n"
                              f"Trajet : {code_trajet}\n"
                              f"Place : {numero_place}")
        
        self.reserver_nom_input.clear()
        self.rafraichir_trains()
    
    def create_annuler_reservation_tab(self):
        """Onglet pour annuler une réservation"""
        widget = QWidget()
        layout = QVBoxLayout()
        
        # Titre
        title = QLabel("Annuler une Reservation")
        title_font = QFont()
        title_font.setPointSize(14)
        title_font.setBold(True)
        title.setFont(title_font)
        title.setStyleSheet("color: white; margin-bottom: 10px;")
        layout.addWidget(title)
        
        # Frame pour le formulaire
        frame = self.create_frame_with_style()
        frame_layout = QVBoxLayout()
        
        # Choix du trajet
        frame_layout.addWidget(QLabel("Trajet :"))
        self.annuler_trajet_combo = QComboBox()
        self.annuler_trajet_combo.addItems(trains.keys())
        frame_layout.addWidget(self.annuler_trajet_combo)
        
        # Nom du passager
        frame_layout.addWidget(QLabel("Nom du Passager :"))
        self.annuler_nom_input = QLineEdit()
        self.annuler_nom_input.setPlaceholderText("Entrez le nom du passager a annuler")
        frame_layout.addWidget(self.annuler_nom_input)
        
        frame.setLayout(frame_layout)
        layout.addWidget(frame)
        
        # Bouton annuler
        annuler_btn = QPushButton("Annuler Reservation")
        annuler_btn.setStyleSheet("""
            QPushButton {
                background-color: #f44336;
                color: white;
                border: none;
                padding: 12px 20px;
                border-radius: 5px;
                font-weight: bold;
                font-size: 12px;
            }
            QPushButton:hover {
                background-color: #da190b;
            }
        """)
        annuler_btn.clicked.connect(self.annuler_reservation)
        layout.addWidget(annuler_btn)
        
        widget.setLayout(layout)
        return widget
    
    def annuler_reservation(self):
        """Annule une réservation"""
        code_trajet = self.annuler_trajet_combo.currentText()
        nom_passager = self.annuler_nom_input.text().strip()
        
        if not nom_passager:
            QMessageBox.warning(self, "Erreur", "Veuillez entrer le nom du passager.")
            return
        
        if nom_passager not in trains[code_trajet]['passagers']:
            QMessageBox.warning(self, "Erreur", f"{nom_passager} n'est pas inscrit sur {code_trajet}.")
            return
        
        trains[code_trajet]['passagers'].remove(nom_passager)
        trains[code_trajet]['places_restantes'] += 1
        
        global tickets
        tickets = [t for t in tickets if not (t[0] == nom_passager and t[1] == code_trajet)]
        
        QMessageBox.information(self, "Succes", 
                              f"Annulation confirmee !\n"
                              f"Passager : {nom_passager}\n"
                              f"Trajet : {code_trajet}")
        
        self.annuler_nom_input.clear()
        self.rafraichir_trains()
    
    def create_afficher_passagers_tab(self):
        """Onglet pour afficher les passagers"""
        widget = QWidget()
        layout = QVBoxLayout()
        
        # Titre
        title = QLabel("Liste des Passagers")
        title_font = QFont()
        title_font.setPointSize(14)
        title_font.setBold(True)
        title.setFont(title_font)
        title.setStyleSheet("color: white; margin-bottom: 10px;")
        layout.addWidget(title)
        
        # Frame
        frame = self.create_frame_with_style()
        frame_layout = QVBoxLayout()
        
        # Choix du trajet
        frame_layout.addWidget(QLabel("Selectionnez un trajet :"))
        self.passagers_trajet_combo = QComboBox()
        self.passagers_trajet_combo.addItems(trains.keys())
        self.passagers_trajet_combo.currentTextChanged.connect(self.rafraichir_passagers)
        frame_layout.addWidget(self.passagers_trajet_combo)
        
        # Liste des passagers
        self.passagers_list = QListWidget()
        frame_layout.addWidget(self.passagers_list)
        
        frame.setLayout(frame_layout)
        layout.addWidget(frame)
        
        widget.setLayout(layout)
        self.rafraichir_passagers()
        return widget
    
    def rafraichir_passagers(self):
        """Rafraîchit la liste des passagers"""
        code_trajet = self.passagers_trajet_combo.currentText()
        self.passagers_list.clear()
        
        passagers = sorted(trains[code_trajet]['passagers'])
        
        if passagers:
            for i, nom in enumerate(passagers, 1):
                item = QListWidgetItem(f"{i}. {nom}")
                item.setBackground(QColor("#e3f2fd"))
                self.passagers_list.addItem(item)
        else:
            item = QListWidgetItem("Aucun passager inscrit")
            item.setBackground(QColor("#ffebee"))
            self.passagers_list.addItem(item)
    
    def create_trains_complets_tab(self):
        """Onglet pour afficher les trains complets"""
        widget = QWidget()
        layout = QVBoxLayout()
        
        # Titre
        title = QLabel("Trains Complets")
        title_font = QFont()
        title_font.setPointSize(14)
        title_font.setBold(True)
        title.setFont(title_font)
        title.setStyleSheet("color: white; margin-bottom: 10px;")
        layout.addWidget(title)
        
        # Frame
        frame = self.create_frame_with_style()
        frame_layout = QVBoxLayout()
        
        # Liste des trains complets
        self.complets_list = QListWidget()
        frame_layout.addWidget(self.complets_list)
        
        frame.setLayout(frame_layout)
        layout.addWidget(frame)
        
        # Bouton rafraîchir
        refresh_btn = QPushButton("Rafraichir")
        refresh_btn.clicked.connect(self.rafraichir_trains_complets)
        layout.addWidget(refresh_btn)
        
        widget.setLayout(layout)
        self.rafraichir_trains_complets()
        return widget
    
    def rafraichir_trains_complets(self):
        """Rafraîchit la liste des trains complets"""
        self.complets_list.clear()
        trains_complets = [code for code, info in trains.items() if info['places_restantes'] == 0]
        
        if trains_complets:
            for code_trajet in trains_complets:
                item_text = f"{code_trajet} ({trains[code_trajet]['places_total']} places)"
                item = QListWidgetItem(item_text)
                item.setBackground(QColor("#ffcccc"))
                self.complets_list.addItem(item)
        else:
            item = QListWidgetItem("Aucun train complet")
            item.setBackground(QColor("#ccffcc"))
            self.complets_list.addItem(item)
    
    def create_tickets_tab(self):
        """Onglet pour afficher les tickets"""
        widget = QWidget()
        layout = QVBoxLayout()
        
        # Titre
        title = QLabel("Tickets Generes")
        title_font = QFont()
        title_font.setPointSize(14)
        title_font.setBold(True)
        title.setFont(title_font)
        title.setStyleSheet("color: white; margin-bottom: 10px;")
        layout.addWidget(title)
        
        # Frame
        frame = self.create_frame_with_style()
        frame_layout = QVBoxLayout()
        
        # Affichage des tickets
        self.tickets_text = QTextEdit()
        self.tickets_text.setReadOnly(True)
        frame_layout.addWidget(self.tickets_text)
        
        frame.setLayout(frame_layout)
        layout.addWidget(frame)
        
        # Bouton rafraîchir
        refresh_btn = QPushButton("Rafraichir")
        refresh_btn.clicked.connect(self.rafraichir_tickets)
        layout.addWidget(refresh_btn)
        
        widget.setLayout(layout)
        self.rafraichir_tickets()
        return widget
    
    def rafraichir_tickets(self):
        """Rafraîchit l'affichage des tickets"""
        self.tickets_text.clear()
        
        if tickets:
            text = f"Nombre total de tickets : {len(tickets)}\n\n"
            for i, (nom, trajet, place) in enumerate(tickets, 1):
                text += f"{i}. Ticket({nom}, {trajet}, {place})\n"
            self.tickets_text.setText(text)
        else:
            self.tickets_text.setText("Aucun ticket genere.")


def main():
    app = QApplication(sys.argv)
    window = ReservationApp()
    window.show()
    sys.exit(app.exec_())


if __name__ == "__main__":
    main()
