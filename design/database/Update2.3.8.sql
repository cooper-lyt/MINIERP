

-- 20140714-6  | 温总 667
-- 应收账款:	￥0.00	应收账款-代办:	￥0.00	余额(不含代收):	￥0.00	余额:	￥0.00

update ACCOUNT_OPER set ACCOUNTS_RECEIVABLE = 647 where    CUSTOMER_ORDER = '20140714-6';


-- 20141117-7  | 温总 230

update ACCOUNT_OPER set ACCOUNTS_RECEIVABLE = 220 where CUSTOMER_ORDER = '20141117-7';

-- *20150314-10 | 张鹏 刘国富-廊坊 82526
-- 应收账款:	￥410,360.00	应收账款-代办:	￥0.00	余额(不含代收):	-￥410,360.00	余额:	-￥410,360.00

update ACCOUNT_OPER set ACCOUNTS_RECEIVABLE = 80572 where CUSTOMER_ORDER = '20150314-10';


-- *20150314-11 | 张鹏 35168
update ACCOUNT_OPER set ACCOUNTS_RECEIVABLE = 34336 where CUSTOMER_ORDER = '20150314-11';



-- *20150323-12 | 任小卫 7400      7,400
-- 应收账款:	￥7,400.00	应收账款-代办:	￥0.00	余额(不含代收):	-￥7,400.00	余额:	-￥7,400.00

update ACCOUNT_OPER set ACCOUNTS_RECEIVABLE = 7320 where CUSTOMER_ORDER = '20150323-12';


-- 20150323-6  | 贾素娟 800
-- 应收账款:	￥136,351.00	应收账款-代办:	￥6,674.00	余额(不含代收):	-￥136,351.00	余额:	-￥143,025.00

update ACCOUNT_OPER set PROXY_ACCOUNTS_RECEIVABLE=775 where CUSTOMER_ORDER = '20150323-6';


-- 20150324-6  | 姜绵友 2475
-- 应收账款:	￥0.00	应收账款-代办:	￥2,475.00	余额(不含代收):	￥0.00	余额:	-￥2,475.00
update ACCOUNT_OPER set PROXY_ACCOUNTS_RECEIVABLE=2400 where CUSTOMER_ORDER = '20150324-6';