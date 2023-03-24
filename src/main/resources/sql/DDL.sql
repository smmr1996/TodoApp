CREATE TABLE IF NOT EXISTS todo (
	id integer AUTO_INCREMENT PRIMARY KEY,
    name varchar(255),
    description varchar(255)
);

CREATE TABLE IF NOT EXISTS task (
  parent_id INT,
  name VARCHAR(255),
  description VARCHAR(255),
  FOREIGN KEY (parent_id) REFERENCES todo(id)
);
