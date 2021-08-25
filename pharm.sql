/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     25/8/2021 14:20:01                           */
/*==============================================================*/


drop index RPRO_PHARM_FK;

drop index RPHAM_PHARMPRO_FK;

drop index PHARM_PROD_PK;

drop table PHARM_PROD;

drop index RTBLUSER_TBLPHAR_FK;

drop index TBLPHARMACY_PK;

drop table TBLPHARMACY;

drop index TBLPRODUCT_PK;

drop table TBLPRODUCT;

drop index TBLTYPEUSER_PK;

drop table TBLTYPEUSER;

drop index RTBLTYPE_TLBUSER_FK;

drop index TBLUSER_PK;

drop table TBLUSER;

/*==============================================================*/
/* Table: PHARM_PROD                                            */
/*==============================================================*/
create table PHARM_PROD (
   PHARMPROD_ID         SERIAL               not null,
   PHARMACY_ID          INT4                 null,
   PRODUCT_ID           INT4                 null,
   PRICE                DECIMAL(5,2)         not null,
   constraint PK_PHARM_PROD primary key (PHARMPROD_ID)
);

/*==============================================================*/
/* Index: PHARM_PROD_PK                                         */
/*==============================================================*/
create unique index PHARM_PROD_PK on PHARM_PROD (
PHARMPROD_ID
);

/*==============================================================*/
/* Index: RPHAM_PHARMPRO_FK                                     */
/*==============================================================*/
create  index RPHAM_PHARMPRO_FK on PHARM_PROD (
PHARMACY_ID
);

/*==============================================================*/
/* Index: RPRO_PHARM_FK                                         */
/*==============================================================*/
create  index RPRO_PHARM_FK on PHARM_PROD (
PRODUCT_ID
);

/*==============================================================*/
/* Table: TBLPHARMACY                                           */
/*==============================================================*/
create table TBLPHARMACY (
   PHARMACY_ID          SERIAL               not null,
   USER_ID              INT4                 null,
   NAME                 CHAR(100)            not null,
   ADDRESS              CHAR(100)            not null,
   PHONE                CHAR(10)             not null,
   HOURSOPERATION       VARCHAR(100)         not null,
   DATEUPDATE           DATE                 not null,
   constraint PK_TBLPHARMACY primary key (PHARMACY_ID),
   constraint AK_IDENTIFIER_1_TBLPHARM unique (PHARMACY_ID)
);

/*==============================================================*/
/* Index: TBLPHARMACY_PK                                        */
/*==============================================================*/
create unique index TBLPHARMACY_PK on TBLPHARMACY (
PHARMACY_ID
);

/*==============================================================*/
/* Index: RTBLUSER_TBLPHAR_FK                                   */
/*==============================================================*/
create  index RTBLUSER_TBLPHAR_FK on TBLPHARMACY (
USER_ID
);

/*==============================================================*/
/* Table: TBLPRODUCT                                            */
/*==============================================================*/
create table TBLPRODUCT (
   PRODUCT_ID           SERIAL               not null,
   NAME                 CHAR(100)            not null,
   LABORATORY           CHAR(100)            not null,
   CERTIFICATION        BOOL                 not null,
   constraint PK_TBLPRODUCT primary key (PRODUCT_ID),
   constraint AK_IDENTIFIER_1_TBLPRODU unique (PRODUCT_ID)
);

/*==============================================================*/
/* Index: TBLPRODUCT_PK                                         */
/*==============================================================*/
create unique index TBLPRODUCT_PK on TBLPRODUCT (
PRODUCT_ID
);

/*==============================================================*/
/* Table: TBLTYPEUSER                                           */
/*==============================================================*/
create table TBLTYPEUSER (
   TYPEUSER_ID          SERIAL               not null,
   ROLE                 VARCHAR(100)         not null,
   constraint PK_TBLTYPEUSER primary key (TYPEUSER_ID)
);

/*==============================================================*/
/* Index: TBLTYPEUSER_PK                                        */
/*==============================================================*/
create unique index TBLTYPEUSER_PK on TBLTYPEUSER (
TYPEUSER_ID
);

/*==============================================================*/
/* Table: TBLUSER                                               */
/*==============================================================*/
create table TBLUSER (
   USER_ID              SERIAL               not null,
   NAME                 VARCHAR(100)         not null,
   LAST_NAME            VARCHAR(100)         not null,
   EMAIL                VARCHAR(100)         not null,
   PASSWORD             VARCHAR(100)         not null,
   ADDRESS              VARCHAR(100)         not null,
   TYPEUSER_ID          INT4                 not null,
   IMGUSER              TEXT                 not null,
   REGISTRATIONDATE     DATE                 not null,
   DATEUPDATE           DATE                 not null,
   BIRTHDAYDATE         DATE                 not null,
   constraint PK_TBLUSER primary key (USER_ID)
);

/*==============================================================*/
/* Index: TBLUSER_PK                                            */
/*==============================================================*/
create unique index TBLUSER_PK on TBLUSER (
USER_ID
);

/*==============================================================*/
/* Index: RTBLTYPE_TLBUSER_FK                                   */
/*==============================================================*/
create  index RTBLTYPE_TLBUSER_FK on TBLUSER (
TYPEUSER_ID
);

alter table PHARM_PROD
   add constraint FK_PHARM_PR_RPHAM_PHA_TBLPHARM foreign key (PHARMACY_ID)
      references TBLPHARMACY (PHARMACY_ID)
      on delete restrict on update restrict;

alter table PHARM_PROD
   add constraint FK_PHARM_PR_RPRO_PHAR_TBLPRODU foreign key (PRODUCT_ID)
      references TBLPRODUCT (PRODUCT_ID)
      on delete restrict on update restrict;

alter table TBLPHARMACY
   add constraint FK_TBLPHARM_RTBLUSER__TBLUSER foreign key (USER_ID)
      references TBLUSER (USER_ID)
      on delete restrict on update restrict;

alter table TBLUSER
   add constraint FK_TBLUSER_RTBLTYPE__TBLTYPEU foreign key (TYPEUSER_ID)
      references TBLTYPEUSER (TYPEUSER_ID)
      on delete restrict on update restrict;

