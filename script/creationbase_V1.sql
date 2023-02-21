/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de création :  07/05/2021 16:01:49                      */
/*==============================================================*/


drop index ADRESSE_PK;

drop table ADRESSE;

drop index AJOUT2_FK;

drop index AJOUT_FK;

drop index AJOUT_PK;

drop table AJOUT;

drop index APPARTIENT_A2_FK;

drop index APPARTIENT_A_FK;

drop index APPARTIENT_A_PK;

drop table APPARTIENT_A;

drop index AVIS_PK;

drop table AVIS;

drop index CARTEBANCAIRE_PK;

drop table CARTEBANCAIRE;

drop index CATEGORIE_PK;

drop table CATEGORIE;

drop index PASSE_FK;

drop index REDIGE_FK;

drop index POSSEDE_FK;

drop index HABITE_FK;

drop index CLIENT_PK;

drop table CLIENT;

drop index CONTIENT2_FK;

drop index CODEPROMO_PK;

drop table CODEPROMO;

drop index CONTIENT_FK;

drop index COMMANDE_PK;

drop table COMMANDE;

drop index LIGNECOMMANDE_PK;

drop table LIGNECOMMANDE;

drop index MARQUE_PK;

drop table MARQUE;

drop index EST_FK;

drop index DETIENT_FK;

drop index PRODUIT_PK;

drop table PRODUIT;

drop index DECOMPOSE_FK;

drop index SOUSCATEGORIE_PK;

drop table SOUSCATEGORIE;

/*==============================================================*/
/* Table : ADRESSE                                              */
/*==============================================================*/
create table ADRESSE (
   IDADRESSE            INT4                 not null,
   NUMEROADRESSE        INT4                 not null,
   RUEADRESSE           TEXT                 not null,
   VILLEADRESSE         TEXT                 not null,
   PAYSADRESSE          TEXT                 not null,
   CPADRESSE            INT4                 not null,
   constraint PK_ADRESSE primary key (IDADRESSE)
);

/*==============================================================*/
/* Index : ADRESSE_PK                                           */
/*==============================================================*/
create unique index ADRESSE_PK on ADRESSE (
IDADRESSE
);

/*==============================================================*/
/* Table : AJOUT                                                */
/*==============================================================*/
create table AJOUT (
   IDPRODUIT            INT4                 not null,
   IDLIGNECOMMANDE      INT4                 not null,
   constraint PK_AJOUT primary key (IDPRODUIT, IDLIGNECOMMANDE)
);

/*==============================================================*/
/* Index : AJOUT_PK                                             */
/*==============================================================*/
create unique index AJOUT_PK on AJOUT (
IDPRODUIT,
IDLIGNECOMMANDE
);

/*==============================================================*/
/* Index : AJOUT_FK                                             */
/*==============================================================*/
create  index AJOUT_FK on AJOUT (
IDPRODUIT
);

/*==============================================================*/
/* Index : AJOUT2_FK                                            */
/*==============================================================*/
create  index AJOUT2_FK on AJOUT (
IDLIGNECOMMANDE
);

/*==============================================================*/
/* Table : APPARTIENT_A                                         */
/*==============================================================*/
create table APPARTIENT_A (
   IDPRODUIT            INT4                 not null,
   IDSOUSCATEGORIE      INT4                 not null,
   constraint PK_APPARTIENT_A primary key (IDPRODUIT, IDSOUSCATEGORIE)
);

/*==============================================================*/
/* Index : APPARTIENT_A_PK                                      */
/*==============================================================*/
create unique index APPARTIENT_A_PK on APPARTIENT_A (
IDPRODUIT,
IDSOUSCATEGORIE
);

/*==============================================================*/
/* Index : APPARTIENT_A_FK                                      */
/*==============================================================*/
create  index APPARTIENT_A_FK on APPARTIENT_A (
IDPRODUIT
);

/*==============================================================*/
/* Index : APPARTIENT_A2_FK                                     */
/*==============================================================*/
create  index APPARTIENT_A2_FK on APPARTIENT_A (
IDSOUSCATEGORIE
);

/*==============================================================*/
/* Table : AVIS                                                 */
/*==============================================================*/
create table AVIS (
   IDAVIS               INT4                 not null,
   DATEAVIS             DATE                 not null,
   DESCRIPTIONAVIS      TEXT                 not null,
   NOTEAVIS             INT4                 not null,
   constraint PK_AVIS primary key (IDAVIS)
);

/*==============================================================*/
/* Index : AVIS_PK                                              */
/*==============================================================*/
create unique index AVIS_PK on AVIS (
IDAVIS
);

/*==============================================================*/
/* Table : CARTEBANCAIRE                                        */
/*==============================================================*/
create table CARTEBANCAIRE (
   IDCARTE              INT4                 not null,
   NUMEROCARTE          INT4                 not null,
   EXPIRATIONCARTE      DATE                 not null,
   CRYPTOCARTE          INT4                 not null,
   TITULAIRECARTE       TEXT                 not null,
   constraint PK_CARTEBANCAIRE primary key (IDCARTE)
);

/*==============================================================*/
/* Index : CARTEBANCAIRE_PK                                     */
/*==============================================================*/
create unique index CARTEBANCAIRE_PK on CARTEBANCAIRE (
IDCARTE
);

/*==============================================================*/
/* Table : CATEGORIE                                            */
/*==============================================================*/
create table CATEGORIE (
   IDCATEGORIE          INT4                 not null,
   NOMCATEGORIE         TEXT                 not null,
   constraint PK_CATEGORIE primary key (IDCATEGORIE)
);

/*==============================================================*/
/* Index : CATEGORIE_PK                                         */
/*==============================================================*/
create unique index CATEGORIE_PK on CATEGORIE (
IDCATEGORIE
);

/*==============================================================*/
/* Table : CLIENT                                               */
/*==============================================================*/
create table CLIENT (
   IDCLIENT             INT4                 not null,
   IDADRESSE            INT4                 not null,
   IDCOMMANDE           INT4                 not null,
   IDCARTE              INT4                 not null,
   IDAVIS               INT4                 not null,
   NOMCLIENT            TEXT                 not null,
   PRENOMCLIENT         TEXT                 not null,
   DATENAISSANCECLIENT  DATE                 null,
   MDPCLIENT            TEXT                 not null,
   MAILCLIENT           TEXT                 not null,
   TELEPHONECLIENT      TEXT                 null,
   CIVILITECLIENT       TEXT                 not null,
   constraint PK_CLIENT primary key (IDCLIENT)
);

/*==============================================================*/
/* Index : CLIENT_PK                                            */
/*==============================================================*/
create unique index CLIENT_PK on CLIENT (
IDCLIENT
);

/*==============================================================*/
/* Index : HABITE_FK                                            */
/*==============================================================*/
create  index HABITE_FK on CLIENT (
IDADRESSE
);

/*==============================================================*/
/* Index : POSSEDE_FK                                           */
/*==============================================================*/
create  index POSSEDE_FK on CLIENT (
IDCARTE
);

/*==============================================================*/
/* Index : REDIGE_FK                                            */
/*==============================================================*/
create  index REDIGE_FK on CLIENT (
IDAVIS
);

/*==============================================================*/
/* Index : PASSE_FK                                             */
/*==============================================================*/
create  index PASSE_FK on CLIENT (
IDCOMMANDE
);

/*==============================================================*/
/* Table : CODEPROMO                                            */
/*==============================================================*/
create table CODEPROMO (
   IDPRODUIT2           INT4                 not null,
   IDCOMMANDE           INT4                 null,
   VALCODEPROMO         DECIMAL              not null,
   NOMCODEPROMO         TEXT                 not null,
   TYPECODEPROMO        TEXT                 null,
   DATEEXP              DATE                 not null,
   constraint PK_CODEPROMO primary key (IDPRODUIT2)
);

/*==============================================================*/
/* Index : CODEPROMO_PK                                         */
/*==============================================================*/
create unique index CODEPROMO_PK on CODEPROMO (
IDPRODUIT2
);

/*==============================================================*/
/* Index : CONTIENT2_FK                                         */
/*==============================================================*/
create  index CONTIENT2_FK on CODEPROMO (
IDCOMMANDE
);

/*==============================================================*/
/* Table : COMMANDE                                             */
/*==============================================================*/
create table COMMANDE (
   IDCOMMANDE           INT4                 not null,
   IDLIGNECOMMANDE      INT4                 not null,
   PRIXTOTALCOMMANDE    DECIMAL              not null,
   STATUSCOMMANDE       TEXT                 not null,
   PRIXAVANTREMISE      DECIMAL              not null,
   REMISE               DECIMAL              not null,
   CODEREMISE           TEXT                 null,
   constraint PK_COMMANDE primary key (IDCOMMANDE)
);

/*==============================================================*/
/* Index : COMMANDE_PK                                          */
/*==============================================================*/
create unique index COMMANDE_PK on COMMANDE (
IDCOMMANDE
);

/*==============================================================*/
/* Index : CONTIENT_FK                                          */
/*==============================================================*/
create  index CONTIENT_FK on COMMANDE (
IDLIGNECOMMANDE
);

/*==============================================================*/
/* Table : LIGNECOMMANDE                                        */
/*==============================================================*/
create table LIGNECOMMANDE (
   IDLIGNECOMMANDE      INT4                 not null,
   QUANTITEAJOUTER      INT4                 not null,
   constraint PK_LIGNECOMMANDE primary key (IDLIGNECOMMANDE)
);

/*==============================================================*/
/* Index : LIGNECOMMANDE_PK                                     */
/*==============================================================*/
create unique index LIGNECOMMANDE_PK on LIGNECOMMANDE (
IDLIGNECOMMANDE
);

/*==============================================================*/
/* Table : MARQUE                                               */
/*==============================================================*/
create table MARQUE (
   IDMARQUE             INT4                 not null,
   NOMMARQUE            TEXT                 not null,
   constraint PK_MARQUE primary key (IDMARQUE)
);

/*==============================================================*/
/* Index : MARQUE_PK                                            */
/*==============================================================*/
create unique index MARQUE_PK on MARQUE (
IDMARQUE
);

/*==============================================================*/
/* Table : PRODUIT                                              */
/*==============================================================*/
create table PRODUIT (
   IDPRODUIT            INT4                 not null,
   IDAVIS               INT4                 not null,
   IDMARQUE             INT4                 not null,
   NOMPRODUIT           TEXT                 not null,
   DESCRIPTIFPRODUIT    TEXT                 not null,
   constraint PK_PRODUIT primary key (IDPRODUIT)
);

/*==============================================================*/
/* Index : PRODUIT_PK                                           */
/*==============================================================*/
create unique index PRODUIT_PK on PRODUIT (
IDPRODUIT
);

/*==============================================================*/
/* Index : DETIENT_FK                                           */
/*==============================================================*/
create  index DETIENT_FK on PRODUIT (
IDAVIS
);

/*==============================================================*/
/* Index : EST_FK                                               */
/*==============================================================*/
create  index EST_FK on PRODUIT (
IDMARQUE
);

/*==============================================================*/
/* Table : SOUSCATEGORIE                                        */
/*==============================================================*/
create table SOUSCATEGORIE (
   IDSOUSCATEGORIE      INT4                 not null,
   IDCATEGORIE          INT4                 not null,
   NOMSOUSCATEGORIE     TEXT                 not null,
   constraint PK_SOUSCATEGORIE primary key (IDSOUSCATEGORIE)
);

/*==============================================================*/
/* Index : SOUSCATEGORIE_PK                                     */
/*==============================================================*/
create unique index SOUSCATEGORIE_PK on SOUSCATEGORIE (
IDSOUSCATEGORIE
);

/*==============================================================*/
/* Index : DECOMPOSE_FK                                         */
/*==============================================================*/
create  index DECOMPOSE_FK on SOUSCATEGORIE (
IDCATEGORIE
);

alter table AJOUT
   add constraint FK_AJOUT_AJOUT_PRODUIT foreign key (IDPRODUIT)
      references PRODUIT (IDPRODUIT)
      on delete restrict on update restrict;

alter table AJOUT
   add constraint FK_AJOUT_AJOUT2_LIGNECOM foreign key (IDLIGNECOMMANDE)
      references LIGNECOMMANDE (IDLIGNECOMMANDE)
      on delete restrict on update restrict;

alter table APPARTIENT_A
   add constraint FK_APPARTIE_APPARTIEN_PRODUIT foreign key (IDPRODUIT)
      references PRODUIT (IDPRODUIT)
      on delete restrict on update restrict;

alter table APPARTIENT_A
   add constraint FK_APPARTIE_APPARTIEN_SOUSCATE foreign key (IDSOUSCATEGORIE)
      references SOUSCATEGORIE (IDSOUSCATEGORIE)
      on delete restrict on update restrict;

alter table CLIENT
   add constraint FK_CLIENT_HABITE_ADRESSE foreign key (IDADRESSE)
      references ADRESSE (IDADRESSE)
      on delete restrict on update restrict;

alter table CLIENT
   add constraint FK_CLIENT_PASSE_COMMANDE foreign key (IDCOMMANDE)
      references COMMANDE (IDCOMMANDE)
      on delete restrict on update restrict;

alter table CLIENT
   add constraint FK_CLIENT_POSSEDE_CARTEBAN foreign key (IDCARTE)
      references CARTEBANCAIRE (IDCARTE)
      on delete restrict on update restrict;

alter table CLIENT
   add constraint FK_CLIENT_REDIGE_AVIS foreign key (IDAVIS)
      references AVIS (IDAVIS)
      on delete restrict on update restrict;

alter table CODEPROMO
   add constraint FK_CODEPROM_CONTIENT2_COMMANDE foreign key (IDCOMMANDE)
      references COMMANDE (IDCOMMANDE)
      on delete restrict on update restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_CONTIENT_LIGNECOM foreign key (IDLIGNECOMMANDE)
      references LIGNECOMMANDE (IDLIGNECOMMANDE)
      on delete restrict on update restrict;

alter table PRODUIT
   add constraint FK_PRODUIT_DETIENT_AVIS foreign key (IDAVIS)
      references AVIS (IDAVIS)
      on delete restrict on update restrict;

alter table PRODUIT
   add constraint FK_PRODUIT_EST_MARQUE foreign key (IDMARQUE)
      references MARQUE (IDMARQUE)
      on delete restrict on update restrict;

alter table SOUSCATEGORIE
   add constraint FK_SOUSCATE_DECOMPOSE_CATEGORI foreign key (IDCATEGORIE)
      references CATEGORIE (IDCATEGORIE)
      on delete restrict on update restrict;

