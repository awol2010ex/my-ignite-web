CREATE TABLE `t_sql_datamodel` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_sql_datamodel_item` (
  `ID` varchar(32) NOT NULL,
  `MODELID` varchar(32) NOT NULL,
  `TABLENAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_sql_datamodel_item_column` (
  `ID` varchar(32) NOT NULL,
  `COLUMNNAME` varchar(100) DEFAULT NULL,
  `COLUMNTYPE` varchar(32) DEFAULT NULL,
  `MODELITEMID` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;