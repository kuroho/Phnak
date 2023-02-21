/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de cration :  12/05/2021 08:55:41                      */
/*==============================================================*/

/*==============================================================*/
/* Table : ADRESSE                                              */
/*==============================================================*/
create table ADRESSE (
   IDADRESSE            SERIAL               not null,
   IDCLIENT             INT4                 not null,
   NUMEROADRESSE        INT4                 not null,
   RUEADRESSE           TEXT                 not null,
   VILLEADRESSE         TEXT                 not null,
   PAYSADRESSE          TEXT                 not null,
   CPADRESSE            TEXT                 not null,
   constraint PK_ADRESSE primary key (IDADRESSE)
);

/*==============================================================*/
/* Index : ADRESSE_PK                                           */
/*==============================================================*/
create unique index ADRESSE_PK on ADRESSE (
IDADRESSE
);

/*==============================================================*/
/* Table : AVIS                                                 */
/*==============================================================*/
create table AVIS (
   IDAVIS               SERIAL               not null,
   IDPRODUIT            INT4                 not null,
   IDCLIENT             INT4                 not null,
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
/* Index : DETIENT_FK                                           */
/*==============================================================*/
create  index DETIENT_FK on AVIS (
IDPRODUIT
);

/*==============================================================*/
/* Table : CARTEBANCAIRE                                        */
/*==============================================================*/
create table CARTEBANCAIRE (
   IDCARTE              SERIAL               not null,
   IDCLIENT             INT4                 not null,
   NUMEROCARTE          NUMERIC              not null,
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
   IDCATEGORIE          SERIAL               not null,
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
   IDCLIENT             SERIAL               not null,
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
IDCLIENT
);

/*==============================================================*/
/* Index : POSSEDE_FK                                           */
/*==============================================================*/
create  index POSSEDE_FK on CLIENT (
IDCLIENT
);

/*==============================================================*/
/* Index : REDIGE_FK                                            */
/*==============================================================*/
create  index REDIGE_FK on CLIENT (
IDCLIENT
);

/*==============================================================*/
/* Table : CODEPROMO                                            */
/*==============================================================*/
create table CODEPROMO (
   IDCODEPROMO          INT4                 not null,
   VALCODEPROMO         DECIMAL              not null,
   NOMCODEPROMO         TEXT                 not null,
   TYPECODEPROMO        TEXT                 null,
   DATEEXP              DATE                 not null,
   constraint PK_CODEPROMO primary key (IDCODEPROMO)
);

/*==============================================================*/
/* Index : CODEPROMO_PK                                         */
/*==============================================================*/
create unique index CODEPROMO_PK on CODEPROMO (
IDCODEPROMO
);

/*==============================================================*/
/* Table : COMMANDE                                             */
/*==============================================================*/
create table COMMANDE (
   IDCOMMANDE           SERIAL               not null,
   IDCLIENT             INT4                 not null,
   IDCODEPROMO          INT4                 null,
   PRIXTOTALCOMMANDE    DECIMAL              not null,
   STATUSCOMMANDE       TEXT                 not null,
   PRIXAVANTREMISE      DECIMAL              not null,
   REMISE               DECIMAL              not null,
   constraint PK_COMMANDE primary key (IDCOMMANDE)
);

/*==============================================================*/
/* Index : COMMANDE_PK                                          */
/*==============================================================*/
create unique index COMMANDE_PK on COMMANDE (
IDCOMMANDE
);

/*==============================================================*/
/* Index : PASSE_FK                                             */
/*==============================================================*/
create  index PASSE_FK on COMMANDE (
IDCLIENT
);

/*==============================================================*/
/* Index : CONTIENT2_FK                                         */
/*==============================================================*/
create  index CONTIENT2_FK on COMMANDE (
IDCODEPROMO
);

/*==============================================================*/
/* Table : LIGNECOMMANDE                                        */
/*==============================================================*/
create table LIGNECOMMANDE (
   IDLIGNECOMMANDE      SERIAL               not null,
   IDPRODUIT            INT4                 not null,
   IDCOMMANDE           INT4                 not null,
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
/* Index : PRODUIT_LIGNECO_FK                                   */
/*==============================================================*/
create  index PRODUIT_LIGNECO_FK on LIGNECOMMANDE (
IDPRODUIT
);

/*==============================================================*/
/* Table : MARQUE                                               */
/*==============================================================*/
create table MARQUE (
   IDMARQUE             SERIAL               not null,
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
   IDPRODUIT            SERIAL               not null,
   IDMARQUE             INT4                 not null,
   NOMPRODUIT           TEXT                 not null,
   DESCRIPTIFPRODUIT    TEXT                 not null,
   PRIXPRODUIT          DECIMAL              null,
   constraint PK_PRODUIT primary key (IDPRODUIT)
);

/*==============================================================*/
/* Index : PRODUIT_PK                                           */
/*==============================================================*/
create unique index PRODUIT_PK on PRODUIT (
IDPRODUIT
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
   IDSOUSCATEGORIE      SERIAL               not null,
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
/* Index : SOUSCAT_CATEGORIE_FK                                 */
/*==============================================================*/
create  index SOUSCAT_CATEGORIE_FK on SOUSCATEGORIE (
IDCATEGORIE
);

/*==============================================================*/
/* Table : SOUSCAT_PRODUIT                                      */
/*==============================================================*/
create table SOUSCAT_PRODUIT (
   IDPRODUIT            INT4                 not null,
   IDSOUSCATEGORIE      INT4                 not null,
   constraint PK_SOUSCAT_PRODUIT primary key (IDPRODUIT, IDSOUSCATEGORIE)
);

/*==============================================================*/
/* Index : SOUSCAT_PRODUIT_PK                                   */
/*==============================================================*/
create unique index SOUSCAT_PRODUIT_PK on SOUSCAT_PRODUIT (
IDPRODUIT,
IDSOUSCATEGORIE
);

/*==============================================================*/
/* Index : SOUSCAT_PRODUIT2_FK                                  */
/*==============================================================*/
create  index SOUSCAT_PRODUIT2_FK on SOUSCAT_PRODUIT (
IDSOUSCATEGORIE
);

alter table ADRESSE
   add constraint FK_ADRESSE_HABITE_CLIENT foreign key (IDCLIENT)
      references CLIENT (IDCLIENT)
      on delete restrict on update restrict;

alter table AVIS
   add constraint FK_AVIS_DETIENT_PRODUIT foreign key (IDPRODUIT)
      references PRODUIT (IDPRODUIT)
      on delete restrict on update restrict;

alter table AVIS
   add constraint FK_AVIS_REDIGE_CLIENT foreign key (IDCLIENT)
      references CLIENT (IDCLIENT)
      on delete restrict on update restrict;

alter table CARTEBANCAIRE
   add constraint FK_CARTEBAN_POSSEDE_CLIENT foreign key (IDCLIENT)
      references CLIENT (IDCLIENT)
      on delete restrict on update restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_CONTIENT2_CODEPROM foreign key (IDCODEPROMO)
      references CODEPROMO (IDCODEPROMO)
      on delete restrict on update restrict;

alter table COMMANDE
   add constraint FK_COMMANDE_PASSE_CLIENT foreign key (IDCLIENT)
      references CLIENT (IDCLIENT)
      on delete restrict on update restrict;

alter table LIGNECOMMANDE
   add constraint FK_LIGNECOM_PRODUIT_L_PRODUIT foreign key (IDPRODUIT)
      references PRODUIT (IDPRODUIT)
      on delete restrict on update restrict;

alter table PRODUIT
   add constraint FK_PRODUIT_EST_MARQUE foreign key (IDMARQUE)
      references MARQUE (IDMARQUE)
      on delete restrict on update restrict;

alter table SOUSCATEGORIE
   add constraint FK_SOUSCATE_SOUSCAT_C_CATEGORI foreign key (IDCATEGORIE)
      references CATEGORIE (IDCATEGORIE)
      on delete restrict on update restrict;

alter table SOUSCAT_PRODUIT
   add constraint FK_SOUSCAT__SOUSCAT_P_PRODUIT foreign key (IDPRODUIT)
      references PRODUIT (IDPRODUIT)
      on delete restrict on update restrict;

alter table SOUSCAT_PRODUIT
   add constraint FK_SOUSCAT__SOUSCAT_P_SOUSCATE foreign key (IDSOUSCATEGORIE)
      references SOUSCATEGORIE (IDSOUSCATEGORIE)
      on delete restrict on update restrict;

