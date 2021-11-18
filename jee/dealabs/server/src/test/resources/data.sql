INSERT INTO tbl_user (first_name, last_name, password, nickname)
VALUES ('tigran', 'tigran', 'a', 'rolfie');

INSERT INTO tbl_user (first_name, last_name, password, nickname)
VALUES ('thomas', 'thomas', 'a', 'thomas');

INSERT INTO tbl_user (first_name, last_name, password, nickname)
VALUES ('benat', 'benat', 'a', 'benat');
INSERT INTO tbl_user (first_name, last_name, password, nickname)
VALUES ('emma', 'emma', 'a', 'emma');

INSERT INTO tbl_deal (id, date, description, img_url, price_new, price_old, promo_code,
                      shop_link, shop_name, title, fk_creator)
VALUES (1, '2021-10-12 13:57:54',
        'G.Skill Trident Z Neo F4-4000C18D-32GTZN Module de mémoire 32 Go 2 x 16 Go DDR4 4000 MHzTypeDDR4 SDRAMCouleurNoir/Argent/RGBEAN4713294224156Référence du fabrica',
        'https://static-pepper.dealabs.com/threads/raw/default/2220841_1/re/234x330/qt/60/2220841_1.jpg', 190.88, 265,
        'PROMO_CODE', 'https://www.dealabs.com/visit/thread/2220841?API=true&user_id={{user_id}}&device_id=7377839',
        'Amazon', 'Mémoire RAM G.Skill Trident Z Neo (F4-4000C18D-32GTZN) - 32 Go (2x16 Go), DDR4, 4000 MHz', 1);

INSERT INTO tbl_deal (id, date, description, img_url, price_new, price_old, promo_code,
                      shop_link, shop_name, title, fk_creator)
VALUES (2, '2021-10-12 08:52:47',
        'Excellent prix pour ce 4ème opus de Fallout qui atteint son meilleur prix jamais vu  :3 &#x2192; Version GOTY à 7.99€ https://www.gamebillet.com/fallout-4-game-',
        'https://static-pepper.dealabs.com/threads/raw/default/2220705_1/re/234x330/qt/60/2220705_1.jpg', 3.98, -1,
        'PROMO_CODE', 'https://www.dealabs.com/visit/thread/2220705?API=true&user_id={{user_id}}&device_id=7377839',
        'Gamebillet', 'Fallout 4 sur PC (Dématérialisé - Steam)', 1);

INSERT INTO tbl_deal (id, date, description, img_url, price_new, price_old, promo_code,
                      shop_link, shop_name, title, fk_creator)
VALUES (3, '2021-10-12 13:45:35',
        'Bonjour a tous, cette fois jai un pc gamer fixe pas mal. :) Regardez par vous même:  AMD Ryzen  5 3600 et carte graphique GeForce® RTX 3060.16 Go ramSsd 500 Go',
        'https://static-pepper.dealabs.com/threads/raw/default/2220832_1/re/234x330/qt/60/2220832_1.jpg', 1007.39, -1,
        'PROMO_CODE', 'https://www.dealabs.com/visit/thread/2220832?API=true&user_id={{user_id}}&device_id=7377839',
        'CSL Computer', 'PC Fixe CSL Sprint 5891 - Ryzen 5 3600, RTX 3060, 16 Go RAM, 500 Go SSD', 2);
INSERT INTO tbl_deal (id, date, description, img_url, price_new, price_old, promo_code,
                      shop_link, shop_name, title, fk_creator)
VALUES (4, '2021-10-12 14:51:56',
        'Livraison gratuite en point relais et à domicile. Lien vers l offre de remboursement de 75€&gt; Produit vendu et expédié par CdiscountCaractéristiques du produi',
        'https://static-pepper.dealabs.com/threads/raw/default/2220866_1/re/234x330/qt/60/2220866_1.jpg', 585.39, -1,
        'PROMO_CODE', 'https://www.dealabs.com/visit/thread/2220866?API=true&user_id={{user_id}}&device_id=7377839',
        'Cdiscount',
        'PC portable 15.6" HP Pavilion Gaming 15-ec1190nf - Full HD, Ryzen 5-4600H, 8 Go RAM, 256 Go SSD, GTX 1650 Ti (via ODR 75€)',
        3);

INSERT INTO tbl_deal (id, date, description, img_url, price_new, price_old, promo_code,
                      shop_link, shop_name, title, fk_creator)
VALUES (5, '2021-10-12 15:14:29',
        'Pas mal avec une RTX 3050 ! (y) &#x21E8; ODR de 75€&#x25BA; Caractéristiques &amp; DescriptionProcesseur : Intel Core i5 (11ème génération) 11300HMémoire vive (',
        'https://static-pepper.dealabs.com/threads/raw/default/2220873_1/re/234x330/qt/60/2220873_1.jpg', 787.99, -1,
        'PROMO_CODE', 'https://www.dealabs.com/visit/thread/2220873?API=true&user_id={{user_id}}&device_id=7377839',
        'Cdiscount',
        'PC Portable 17.3" HP Pavilion Gaming 17-cd2091nf - Full HD IPS 144Hz, i5-11300H, RAM 8 Go, SSD 512 Go, RTX 3050, Windows 10 (Via ODR de 75€)',
        4);
