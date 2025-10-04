# AMODE MALL Samir | BOCCARA Benjamin

# ⚠️⬇️ Lire le paragraphe 'Information sur le TP' ⬇️⚠️
# TP3 de TechnologiesJava

## Informations Sur le TP

## ️️️️️️️️⚠️Une fois le projet lancé se connecter avec l'email `samir@gmail.com` & le mot de passe `onadorelejava`⚠️
* Le mot de passe est bien haché, et on peut vérifier le hash de son mot de passe sur `PasswordGenerator.java`
* On a une base de donnée d'utilisateur et de tournois
* Un adherent peut s'inscrire et se désinscrire aux tournois
* Les todolists fonctionnent
* Les hauts de pages et le login.html et le index.jsp ont était mis a jour et fonctionnel
* On a un bouton 'acceuil' qui nous permet de retourner au menu sur chaque page jsp qui est unique et réutilisable
* La durée d'une session est bien de 5 Minutes


## Lancement
### Lancement IntelliJ
Ouvrir le projet dans IntelliJ Idea.

Si cela ne crée pas de configuration de lancement, ajouter une configuration :
* Faire le `+` puis `Tomcat > Local`
  * Dans `Application server`, il nous faut un `Tomcat 10` (il suffit du Tomcat Core dézippé quelque part)
* Vérifier dans `Deployement`
  * on doit avoir l'artefact `Gradle ... Exploded` (sinon l'ajouter)
  * en bas de cette fenêtre remplir `Application context` avec `/tennis`

Le contexte de déploiement conseillé est donc `/tennis`.
Et l'application est accessible à [http://localhost:8080/tennis/](http://localhost:8080/tennis/)

### Dans un serveur d'application Java
Faire le build : `./gradlew build` et déployer le fichier war créé dans `build/libs` sur le serveur avec l'interface Apache Tomcat GUI Manager.
