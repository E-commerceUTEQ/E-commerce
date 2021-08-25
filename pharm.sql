/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     25/8/2021 1:00:23                            */
/*==============================================================*/


drop index RTBLPRO_TBLPHARM_FK;

drop index RTBLUSER_TBLPHAR_FK;

drop index TBLPHARMACY_PK;

drop table TBLPHARMACY;

drop index TBLPRODUCT_PK;

drop table TBLPRODUCT;

drop index TBLUSER_PK;

drop table TBLUSER;

/*==============================================================*/
/* Table: TBLPHARMACY                                           */
/*==============================================================*/
create table TBLPHARMACY (
   PHARMACY_ID          SERIAL               not null,
   USER_ID              INT4                 null,
   PRODUCT_ID           INT4                 null,
   NAME                 CHAR(100)            not null,
   ADDRESS              CHAR(100)            not null,
   PHONE                CHAR(10)             not null,
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
/* Index: RTBLPRO_TBLPHARM_FK                                   */
/*==============================================================*/
create  index RTBLPRO_TBLPHARM_FK on TBLPHARMACY (
PRODUCT_ID
);

/*==============================================================*/
/* Table: TBLPRODUCT                                            */
/*==============================================================*/
create table TBLPRODUCT (
   PRODUCT_ID           SERIAL               not null,
   NAME                 CHAR(100)            not null,
   PRICE_               DECIMAL              not null,
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
/* Table: TBLUSER                                               */
/*==============================================================*/
create table TBLUSER (
   USER_ID              SERIAL               not null,
   NAME                 VARCHAR(100)         not null,
   LAST_NAME            VARCHAR(100)         not null,
   EMAIL                VARCHAR(100)         not null,
   PASSWORD             VARCHAR(100)         not null,
   ADDRESS              VARCHAR(100)         not null,
   TYPE                 VARCHAR(100)         not null,
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

alter table TBLPHARMACY
   add constraint FK_TBLPHARM_RTBLPRO_T_TBLPRODU foreign key (PRODUCT_ID)
      references TBLPRODUCT (PRODUCT_ID)
      on delete restrict on update restrict;

alter table TBLPHARMACY
   add constraint FK_TBLPHARM_RTBLUSER__TBLUSER foreign key (USER_ID)
      references TBLUSER (USER_ID)
      on delete restrict on update restrict;

