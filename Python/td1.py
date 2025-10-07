# Exercice 1
print("--- Exercice 1 ---\n")

list_semaine = ["lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"]

for jour in list_semaine:
    if jour == "vendredi":
        print(jour + " : Chouette c'est Vendredi")
    elif jour in ["samedi", "dimanche"]:
        print(jour + " : Repos ce week-end")
    else:
        print(jour + " : Au travail")

# Exercice 2
print("\n--- Exercice 2 ---\n")

list_min = [8, 4, 6, 1, 5]

def min_bis(liste):
    min = liste[0]
    for nb in liste:
        if nb < min:
            min = nb
    return min

print("minimum de list_min : ", min_bis(list_min))

# Exercice 3
print("\n--- Exercice 3 ---\n")

liste_notes = [14, 9, 13, 15, 12]

note_max = max(liste_notes)
note_min = min(liste_notes)
moyenne = round(sum(liste_notes) / len(liste_notes), 2)

print("Note max : ", note_max)
print("Note min : ", note_min)
print("Moyenne : ", moyenne)
if moyenne < 12 and moyenne > 10:
    print("Mention Passable")
elif moyenne > 12 and moyenne < 14:
    print("Mention Assez Bien")
elif moyenne > 14:
    print("Mention Bien")

# Exercice 4
print("\n--- Exercice 4 ---\n")

for i in range(1, 21):
    if i % 2 == 0 and i < 11 :
        print(i)
    elif i % 2 != 0 and i > 10:
        print(i)

# Exercice 5

matrice = [[1, 2], [3, 4]]

for i in range(len(matrice)):
    for j in range(len(matrice[i])):
        print(f"Element à la ligne {i} et colonne {j}: {matrice[i][j]}")

# Exercice 6

for i in range(1, 10):
    print("*" * i)

# Exercice 7

import random

position = 0
position_finale = 5
nombre_sauts = 0

print(f"Position initiale : {position}")

while position != position_finale:
    saut = random.choice([-1, 1])
    position += saut
    nombre_sauts += 1
    print(f"Saut {nombre_sauts} : position = {position}")

print(f"\nLa puce a atteint la position {position_finale} en {nombre_sauts} sauts!")


