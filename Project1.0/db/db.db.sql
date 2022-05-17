BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Category" (
	"categoryId"	INTEGER NOT NULL UNIQUE,
	"name"	TEXT,
	"description"	TEXT,
	"pictureId"	TEXT,
	PRIMARY KEY("categoryId" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Item" (
	"itemId"	INTEGER NOT NULL UNIQUE,
	"name"	TEXT NOT NULL,
	"description"	TEXT,
	"price"	INTEGER NOT NULL,
	"pictureId"	TEXT,
	"categoryId"	INTEGER,
	PRIMARY KEY("itemId" AUTOINCREMENT),
	FOREIGN KEY("categoryId") REFERENCES "Category"("categoryId")
);
CREATE TABLE IF NOT EXISTS "OrderItems" (
	"orderItemsId"	INTEGER NOT NULL UNIQUE,
	"orderId"	INTEGER,
	"item1"	INTEGER,
	"item2"	INTEGER,
	"item3"	INTEGER,
	"item4"	INTEGER,
	"item5"	INTEGER,
	"item6"	INTEGER,
	"item7"	INTEGER,
	"item8"	INTEGER,
	"item9"	INTEGER,
	"item10"	INTEGER,
	PRIMARY KEY("orderItemsId" AUTOINCREMENT),
	FOREIGN KEY("item1") REFERENCES "Item"("itemId"),
	FOREIGN KEY("item10") REFERENCES "Item"("itemId"),
	FOREIGN KEY("item8") REFERENCES "Item"("itemId"),
	FOREIGN KEY("item3") REFERENCES "Item"("itemId"),
	FOREIGN KEY("orderId") REFERENCES "Order"("orderId"),
	FOREIGN KEY("item2") REFERENCES "Item"("itemId"),
	FOREIGN KEY("item9") REFERENCES "Item"("itemId"),
	FOREIGN KEY("item5") REFERENCES "Item"("itemId"),
	FOREIGN KEY("item4") REFERENCES "Item"("itemId"),
	FOREIGN KEY("item6") REFERENCES "Item"("itemId"),
	FOREIGN KEY("item7") REFERENCES "Item"("itemId")
);
CREATE TABLE IF NOT EXISTS "Employee" (
	"employeeId"	INTEGER NOT NULL UNIQUE,
	"name"	TEXT,
	"address"	TEXT,
	"postcode"	TEXT,
	"phoneNumber"	TEXT,
	"email"	TEXT,
	PRIMARY KEY("employeeId" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Customer" (
	"customerId"	INTEGER NOT NULL UNIQUE,
	"name"	TEXT,
	"address"	TEXT,
	"postcode"	TEXT,
	"phoneNumber"	TEXT,
	"email"	TEXT,
	PRIMARY KEY("customerId" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Order" (
	"orderId"	INTEGER NOT NULL UNIQUE,
	"orderItemsId"	INTEGER,
	"customerId"	INTEGER,
	"employeeId"	INTEGER,
	"deliveryCharge"	INTEGER,
	"serviceCharge"	INTEGER,
	"totalg"	INTEGER,
	PRIMARY KEY("orderId" AUTOINCREMENT),
	FOREIGN KEY("customerId") REFERENCES "Customer"("customerId"),
	FOREIGN KEY("orderItemsId") REFERENCES "OrderItems"("orderItemsId"),
	FOREIGN KEY("employeeId") REFERENCES "Employee"("employeeId")
);
INSERT INTO "Category" ("categoryId","name","description","pictureId") VALUES (1,'pizza','Pizzas','1'),
 (2,'Burger','Burgers','2'),
 (3,'Drinks','Drinks','3');
INSERT INTO "Item" ("itemId","name","description","price","pictureId","categoryId") VALUES (1,'Cheese & Tomato','pizza',12.99,'4',1),
 (2,'Meat Feast','pizza',8.99,'5',1),
 (3,'Vegan','pizza',5.99,'6',1),
 (4,'Cheese Burger','burger',4.99,'7',2),
 (5,'Double Cheese urger','burger',3.99,'8',2),
 (6,'Chicken Burger','Burger',4.99,'9',2),
 (7,'Pepsi','Drink',1.2,'10',3),
 (8,'7up','Drinl',1,'11',3),
 (9,'Water','Drink',0.5,'12',3);
INSERT INTO "OrderItems" ("orderItemsId","orderId","item1","item2","item3","item4","item5","item6","item7","item8","item9","item10") VALUES (1,1,1,4,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "Employee" ("employeeId","name","address","postcode","phoneNumber","email") VALUES (1,'big boss','2 gg','M1','07444444','big@boss.com');
INSERT INTO "Customer" ("customerId","name","address","postcode","phoneNumber","email") VALUES (1,'Mahmoud','15 hu','M1','07555555','m@m.com');
INSERT INTO "Order" ("orderId","orderItemsId","customerId","employeeId","deliveryCharge","serviceCharge","totalg") VALUES (1,1,1,1,4,5,55);
COMMIT;
