----------------------
-- Créer les tables
----------------------
CREATE TABLE IMAGES (
	path				TEXT 	PRIMARY KEY
	);	

CREATE TABLE LABELS (
	id			INTEGER 	PRIMARY KEY AUTOINCREMENT,
	imageId 		TEXT	NOT NULL,
	text 			TEXT	NOT NULL,
	dateAdded  		TEXT	NOT NULL,
	FOREIGN KEY(imageId) REFERENCES IMAGES(path)
	);
