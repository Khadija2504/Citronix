# 🌱 Citronix - Gestion de Fermes de Citrons 🍋

**Citronix** est une application innovante de gestion pour les fermes de citrons, conçue pour aider les agriculteurs à optimiser la production, les récoltes et les ventes tout en respectant des contraintes agricoles précises.

---

## 🌟 **Contexte du Projet**
Les fermiers rencontrent souvent des difficultés pour suivre efficacement leurs champs, arbres, récoltes et ventes. **Citronix** propose une solution pour centraliser et automatiser ces tâches afin d'améliorer leur productivité.

---

## 🛠 **Fonctionnalités Principales**
### 🎯 Gestion des Fermes
- **CRUD complet** : créer, modifier, consulter et supprimer une ferme.
- **Informations suivies** : nom, localisation, superficie, date de création.
- **Recherche multicritère** avec `CriteriaBuilder`.

### 🏞 Gestion des Champs
- Associer des champs à une ferme avec une superficie définie.
- **Validation des superficies** : somme des superficies des champs < superficie de la ferme.

### 🌳 Gestion des Arbres
- Suivi de l'âge et du champ d'appartenance des arbres.
- Calcul automatique de l'âge des arbres.
- Détermination de la productivité annuelle :
  - Jeunes arbres (< 3 ans) : **2,5 kg/saison**.
  - Arbres matures (3-10 ans) : **12 kg/saison**.
  - Arbres vieux (> 10 ans) : **20 kg/saison**.

### 🌾 Gestion des Récoltes
- Suivi des récoltes par saison (hiver, printemps, été, automne).
- Enregistrement de la quantité totale récoltée et de la date de récolte.

### 📦 Gestion des Ventes
- Enregistrement des ventes avec date, prix unitaire et client.
- Calcul automatique du **revenu** : `quantité * prixUnitaire`.

---

## ⚙️ **Contraintes**
- **Superficie minimale/massimale des champs** : 0.1 ha - 50% de la superficie totale de la ferme.
- **Densité maximale d'arbres** : 100 arbres/ha.
- **Durée de vie des arbres** : 20 ans (non productifs au-delà).
- **Période de plantation** : uniquement entre **mars et mai**.
- **Récolte unique par saison** et par champ.

---

## 🔧 **Technologies Utilisées**
- **Backend** : Spring Boot avec API REST.
- **Architecture** : MVC (Controller, Service, Repository, Entity).
- **Validation des Données** : Annotations Spring.
- **Gestion des Exceptions** : Centralisée.
- **Tests** : JUnit et Mockito.
- **Automatisation** : Lombok, Builder Pattern, MapStruct.

---

## 📝 **Compétences Techniques**
- Installation et configuration de l'environnement.
- Développement de composants métier et d'accès aux données.
- Conception et gestion d'une base de données relationnelle.
- Préparation et exécution de tests unitaires.

---

## 🚀 **Démonstration**
### Endpoints principaux :
1. **Création d'une ferme**  
2. **Ajout de champs et d'arbres**  
3. **Enregistrement des récoltes**  
4. **Calcul automatique de la productivité**  
5. **Suivi des ventes avec calcul de revenus**


