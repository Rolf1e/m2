CREATE TABLE `tbl_deal`
(
    `id`          bigint(20) NOT NULL,
    `creator`     varchar(255) DEFAULT NULL,
    `date`        datetime     DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `img_url`     varchar(255) DEFAULT NULL,
    `price_new`   double       DEFAULT NULL,
    `price_old`   double       DEFAULT NULL,
    `promo_code`  varchar(255) DEFAULT NULL,
    `shop_link`   varchar(255) DEFAULT NULL,
    `shop_name`   varchar(255) DEFAULT NULL,
    `temperature` int(11)      DEFAULT NULL,
    `title`       varchar(255) DEFAULT NULL
);


INSERT INTO `tbl_deal` (`id`, `creator`, `date`, `description`, `img_url`, `price_new`, `price_old`, `promo_code`,
                        `shop_link`, `shop_name`, `temperature`, `title`)
VALUES (1, 'EssaiDeal', '2021-10-12 13:57:54',
        'G.Skill Trident Z Neo F4-4000C18D-32GTZN Module de mémoire 32 Go 2 x 16 Go DDR4 4000 MHzTypeDDR4 SDRAMCouleurNoir/Argent/RGBEAN4713294224156Référence du fabrica',
        'https://static-pepper.dealabs.com/threads/raw/default/2220841_1/re/234x330/qt/60/2220841_1.jpg', 190.88, 265,
        'PROMO_CODE', 'https://www.dealabs.com/visit/thread/2220841?API=true&user_id={{user_id}}&device_id=7377839',
        'Amazon', 68, 'Mémoire RAM G.Skill Trident Z Neo (F4-4000C18D-32GTZN) - 32 Go (2x16 Go), DDR4, 4000 MHz'),
       (2, 'J.Oldman', '2021-10-12 08:52:47',
        'Excellent prix pour ce 4ème opus de Fallout qui atteint son meilleur prix jamais vu  :3 &#x2192; Version GOTY à 7.99€ https://www.gamebillet.com/fallout-4-game-',
        'https://static-pepper.dealabs.com/threads/raw/default/2220705_1/re/234x330/qt/60/2220705_1.jpg', 3.98, -1,
        'PROMO_CODE', 'https://www.dealabs.com/visit/thread/2220705?API=true&user_id={{user_id}}&device_id=7377839',
        'Gamebillet', 174, 'Fallout 4 sur PC (Dématérialisé - Steam)');
