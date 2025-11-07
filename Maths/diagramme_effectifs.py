import matplotlib.pyplot as plt
import numpy as np

# Données du tableau
categories = ['1', '2', '3', '4', '5', '6', '7']
effectifs = [48, 72, 96, 64, 39, 25, 3]
effectifs_cumules = [48, 120, 216, 280, 319, 344, 347]

# Créer une figure avec deux sous-graphiques
fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(14, 6))

# Diagramme en bâton des effectifs
ax1.bar(categories, effectifs, color='steelblue', alpha=0.7, edgecolor='black')
ax1.set_xlabel('Nombre de pièces (xᵢ)', fontsize=12)
ax1.set_ylabel('Effectifs (nᵢ)', fontsize=12)
ax1.set_title('Diagramme en bâton des effectifs', fontsize=14, fontweight='bold')
ax1.grid(axis='y', alpha=0.3)

# Ajouter les valeurs sur les bâtons
for i, v in enumerate(effectifs):
    ax1.text(i, v + 2, str(v), ha='center', va='bottom', fontsize=10)

# Diagramme en bâton des effectifs cumulés
ax2.bar(categories, effectifs_cumules, color='coral', alpha=0.7, edgecolor='black')
ax2.set_xlabel('Nombre de pièces (xᵢ)', fontsize=12)
ax2.set_ylabel('Effectifs Cumulés (ECO)', fontsize=12)
ax2.set_title('Diagramme en bâton des effectifs cumulés', fontsize=14, fontweight='bold')
ax2.grid(axis='y', alpha=0.3)

# Ajouter les valeurs sur les bâtons
for i, v in enumerate(effectifs_cumules):
    ax2.text(i, v + 5, str(v), ha='center', va='bottom', fontsize=10)

plt.tight_layout()
plt.savefig('diagramme_effectifs.png', dpi=300, bbox_inches='tight')
plt.show()
