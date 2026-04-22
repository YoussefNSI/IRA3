# TP Spring Core – IoC & DI

Exercice 5 : Escape Rooms

L’objectif est de mettre en application les concepts d’injection de dépendance et d’inversion de contrôle avec Spring.

- La classes `EscapeRoomsApplication` et `Startup` ne doivent pas être modifiées.
- Toutes	les	classes	des	packages `org.sebsy.demo.escaperooms.controller` et `org.sebsy.demo.escaperooms.bll` doivent être déclarées comme des beans/composants Spring. 
  - Vous pouvez uniquement ajouter ou modifier les annotations de Spring (`@Component`, `@Service`, `@Profile` et `@Autowired`)
    - Il y a 3 profiles possibles pour l’application :
      - `passage`  un chemin valide pour accéder aux chambres
      - `trap`  vous êtes dans une impasse
      - `treasure`  vous accédez au trésor
    - Copier la ligne suivante dans application.properties :
      ```editorconfig
        spring.profiles.active=passage,treasure
      ```
- Vous devez compléter toutes les classes annotées `@Service` avec un des profils. Ils sont tous utilisés

L’exécution de la classe `EscapeRoomsApplication`, doit afficher les traces suivantes dans la console :

```editorconfig
Entrez dans la salle 1 :
Bravo, vous avez trouvé la première salle !
Entrez dans la salle 2 :
Bravo, vous avez trouvé la deuxième salle ! 
Entrez dans la salle du trésor :
Gagné, vous avez trouvé la salle du trésor !
```
