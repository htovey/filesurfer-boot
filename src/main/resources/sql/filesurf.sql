CREATE TABLE `DIRECTORY` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `NOTE` (
  `NOTE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEXT` varchar(1000) DEFAULT 'null',
  `USER_ID` varchar(45) DEFAULT NULL,
  `CREATE_DT` datetime DEFAULT NULL,
  `SAVE_DT` varchar(45) DEFAULT NULL,
  `DIRECTORY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`NOTE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `PERSON` (
  `PERSONID` int(11) NOT NULL,
  `PERSON_TYPE` varchar(45) NOT NULL DEFAULT 'USER',
  `FNAME` varchar(45) DEFAULT NULL,
  `LNAME` varchar(45) DEFAULT NULL,
  `PHONE` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `USER_ID` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PERSONID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `USER` (
  `USER_ID` varchar(12) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_ID_UNIQUE` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
