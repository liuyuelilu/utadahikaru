/*表结构插入*/
DROP TABLE IF EXISTS  u_permission ;

CREATE TABLE  u_permission  (
   id  serial NOT NULL,
   url  varchar(256) DEFAULT NULL ,
   name  varchar(64) DEFAULT NULL ,
  PRIMARY KEY ( id )
);

/*Table structure for table  u_role  */

DROP TABLE IF EXISTS  u_role ;

CREATE TABLE  u_role  (
   id  serial NOT NULL,
   name  varchar(32) DEFAULT NULL,
   type  varchar(10) DEFAULT NULL ,
  PRIMARY KEY ( id )
);

/*Table structure for table  u_role_permission  */

DROP TABLE IF EXISTS  u_role_permission ;

CREATE TABLE  u_role_permission  (
   rid  integer DEFAULT NULL ,
   pid  integer DEFAULT NULL
);

/*Table structure for table  u_user  */

DROP TABLE IF EXISTS  u_user ;

CREATE TABLE  u_user  (
   id  serial NOT NULL,
   nickname  varchar(20) DEFAULT NULL ,
   email  varchar(128) DEFAULT NULL ,
   pswd  varchar(32) DEFAULT NULL ,
   create_time  timestamp DEFAULT NULL,
   last_login_time  timestamp DEFAULT NULL ,
   status  smallint DEFAULT 1  ,
  PRIMARY KEY ( id )
) ;

/*Table structure for table  u_user_role  */

DROP TABLE IF EXISTS  u_user_role ;

CREATE TABLE  u_user_role  (
   uid  integer DEFAULT NULL,
   rid  integer DEFAULT NULL
) ;

/*Table structure for table  u_pay  */

DROP TABLE IF EXISTS  u_pay ;

CREATE TABLE  u_pay  (
   id  serial NOT NULL,
   pay  integer DEFAULT NULL,
   time  timestamp DEFAULT NULL,
  PRIMARY KEY ( id )
) ;
