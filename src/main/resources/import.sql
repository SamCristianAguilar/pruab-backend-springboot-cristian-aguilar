/* Populate tabla users */

INSERT INTO users ( address, name, number_id, surname  ) VALUES('carrera 11# 14-08','Andrés','12345','Guzmán');
INSERT INTO users ( address, name, number_id, surname  ) VALUES('carrera 11# 14-08','Mr. John','54321','Doe');
INSERT INTO users ( address, name, number_id, surname  ) VALUES('carrera 11# 14-08','Linus','84946','Torvalds');
INSERT INTO users ( address, name, number_id, surname  ) VALUES('carrera 11# 14-08','Rasmus','32919','Lerdorf');


/* Populate tabla products */
INSERT INTO products (name, price) VALUES('Panasonic Pantalla LCD', 259990);
INSERT INTO products (name, price) VALUES('Sony Camara digital DSC-W320B', 123490);
INSERT INTO products (name, price) VALUES('Apple iPod shuffle', 1499990);
INSERT INTO products (name, price) VALUES('Sony Notebook Z110', 37990);
INSERT INTO products (name, price) VALUES('Hewlett Packard Multifuncional F2280', 69990);
INSERT INTO products (name, price) VALUES('Bianchi Bicicleta Aro 26', 69990);
INSERT INTO products (name, price) VALUES('Mica Comoda 5 Cajones', 299990);

/* Creamos algunas facturas */
INSERT INTO orders (create_at, observation, user_id, status) VALUES('2021-02-21 09:17:35', 'Factura equipos de oficina',  1, 'Recibido');

INSERT INTO order_items (quantity, product_id, order_id) VALUES(1, 1, 1);
INSERT INTO order_items (quantity, product_id, order_id) VALUES(2, 4, 1);
INSERT INTO order_items (quantity, product_id, order_id) VALUES(1, 5, 1);
INSERT INTO order_items (quantity, product_id, order_id) VALUES(1, 7, 1);

INSERT INTO orders (create_at, observation, user_id, status) VALUES('2021-02-21 11:17:35','Alguna nota importante!', 1, 'Recibido');
INSERT INTO order_items (quantity, product_id, order_id) VALUES(3, 6, 2);
INSERT INTO orders (create_at, observation, user_id, status) VALUES(NOW(),'Alguna nota importante!', 1, 'Recibido');
INSERT INTO order_items (quantity, product_id, order_id) VALUES(2, 4, 3);