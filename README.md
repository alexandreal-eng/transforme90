# TRANSFORME 90 — version 0.2.0 (étape 2 : base du projet)

« 90 jours pour construire une meilleure version de soi. »

## Ce qui fonctionne dans cette version
- Thème bleu nuit et or, navigation inférieure (Accueil, Programme, Routine, Mental, Plus).
- Base de données locale Room (progression, objectifs, habitudes, transactions) et préférences DataStore.
- Accueil : jour courant sur 90, barre de progression, bouton « Terminer la journée » (enregistré sur le téléphone).
- Programme : grille des 90 jours (jours terminés en doré, jour courant entouré).

## Pas encore développé
Routine, Mental (90 séances), Finances, Statistiques, Rappels, Paramètres : étapes 3 à 7.
Ces écrans affichent pour l'instant un message « sera développé à l'étape X ».

## Ouvrir et compiler (Android Studio)
1. Installer Android Studio (version récente).
2. Menu Fichier > Ouvrir, choisir le dossier `transforme90`.
3. Attendre la synchronisation Gradle (connexion Internet nécessaire la première fois).
4. Brancher le téléphone (débogage USB activé) puis cliquer sur le bouton Exécuter,
   ou menu Build > Build APK(s). L'APK se trouve dans `app/build/outputs/apk/debug/`.

## Obtenir l'APK sans Android Studio
Mettre le projet sur GitHub : le fichier `.github/workflows/build.yml` compile l'APK
automatiquement (onglet Actions > dernière exécution > téléchargement « TRANSFORME90-apk »).

## Avertissement
Ce code n'a pas pu être compilé ni testé dans l'environnement de création.
Les premières erreurs éventuelles de compilation seront corrigées à l'étape suivante.
