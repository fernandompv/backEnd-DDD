CREATE TABLE brand
(id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(250) NOT NULL);

CREATE TABLE price
(id INT AUTO_INCREMENT PRIMARY KEY,
brand_id INT,
start_date TIMESTAMP  NOT NULL,
end_date TIMESTAMP  NOT NULL,
price_list INT NOT NULL,
product_id INT NOT NULL,
priority Bigint NOT NULL,
price FLOAT(24) NOT NULL,
currency VARCHAR(250) NOT NULL,
foreign key (brand_id) references brand(id));