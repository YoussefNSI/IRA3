import matplotlib.pyplot as plt
import numpy as np

# Données du tableau
categories = ['1', '2', '3', '4', '5', '6', '7']
effectifs = [48, 72, 96, 64, 39, 25, 3]
frequences = [0.138, 0.207, 0.277, 0.184, 0.112, 0.072, 0.009]
frequences_cumulees = [0.138, 0.345, 0.622, 0.806, 0.918, 0.990, 0.999]

# Convertir les catégories en nombres pour la courbe
x = np.array([int(c) for c in categories])

# Créer une figure
fig, ax = plt.subplots(figsize=(12, 7))

# Tracer la courbe cumulative des fréquences cumulées
ax.plot(x, frequences_cumulees, marker='o', linewidth=2.5, markersize=10, 
        color='darkblue', label='Courbe cumulative', linestyle='-')

# Ajouter une grille
ax.grid(True, alpha=0.3, linestyle='--')

# Ajouter les étiquettes et titre
ax.set_xlabel('Nombre de pièces (xᵢ)', fontsize=12, fontweight='bold')
ax.set_ylabel('Fréquences Cumulées (Fᵢ)', fontsize=12, fontweight='bold')
ax.set_title('Courbe cumulative des fréquences cumulées', fontsize=14, fontweight='bold')

# Configurer les axes
ax.set_xticks(x)
ax.set_ylim(0, 1.05)
ax.set_xlim(0.5, 7.5)

# Ajouter les valeurs des fréquences cumulées sur les points
for i, (xi, fi) in enumerate(zip(x, frequences_cumulees)):
    ax.text(xi, fi + 0.03, f'{fi:.3f}', ha='center', va='bottom', fontsize=10, fontweight='bold')

# Ajouter une ligne horizontale à 1.0 (100%)
ax.axhline(y=1.0, color='red', linestyle='--', alpha=0.5, linewidth=1)
ax.text(0.6, 1.02, '100%', fontsize=10, color='red')

# Ajouter une légende
ax.legend(fontsize=11, loc='lower right')

plt.tight_layout()
plt.savefig('courbe_cumulative_frequences.png', dpi=300, bbox_inches='tight')
plt.show()

print("Courbe cumulative tracée et sauvegardée en 'courbe_cumulative_frequences.png'")
