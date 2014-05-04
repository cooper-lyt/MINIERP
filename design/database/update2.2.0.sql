INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES ('erp.search.preparePayList','客户预存款查询','erp.sale.mgr','','/func/erp/sale/PreparePayList.seam','','901','');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES ('erp.search.backPrepareList','客户退预存款查询','erp.sale.mgr','','/func/erp/sale/BackPrepareMoneyList.seam','','902','');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.search.preparePayList');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.search.backPrepareList');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.search.preparePayList');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.search.backPrepareList');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.accountancy', 'erp.search.preparePayList');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.accountancy', 'erp.search.backPrepareList');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.cashier', 'erp.search.preparePayList');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.cashier', 'erp.search.backPrepareList');




ALTER TABLE MINI_ERP.ACCOUNT_OPER ADD ADVANCE_RECEIVABLE DECIMAL(19,3) NOT NULL;
ALTER TABLE MINI_ERP.ACCOUNT_OPER ADD ACCOUNTS_RECEIVABLE DECIMAL(19,3) NOT NULL;
ALTER TABLE MINI_ERP.ACCOUNT_OPER ADD PROXY_ACCOUNTS_RECEIVABLE DECIMAL(19,3) NOT NULL;
ALTER TABLE MINI_ERP.ACCOUNT_OPER ADD USE_CHECK BIT(1) NOT NULL;

ALTER TABLE MINI_ERP.CUSTOMER ADD ADVANCE_MONEY DECIMAL(19,3) NOT NULL;
ALTER TABLE MINI_ERP.CUSTOMER ADD ACCOUNT_MONEY DECIMAL(19,3) NOT NULL;
ALTER TABLE MINI_ERP.CUSTOMER ADD PROXY_ACCOUNT_MONEY DECIMAL(19,3) NOT NULL;


ALTER TABLE MINI_ERP.ORDER_BACK ADD RES_MONEY DECIMAL(19,3) NOT NULL;






ALTER TABLE MINI_ERP.ACCOUNT_OPER DROP FOREIGN KEY ACCOUNT_OPER_ibfk_1;
ALTER TABLE MINI_ERP.ACCOUNT_OPER DROP DEBIT_ACCOUNT;

ALTER TABLE MINI_ERP.ACCOUNT_OPER DROP FOREIGN KEY ACCOUNT_OPER_ibfk_2;
ALTER TABLE MINI_ERP.ACCOUNT_OPER DROP CREDIT_ACCOUNT;

ALTER TABLE MINI_ERP.ACCOUNT_OPER DROP OPER_MONEY;


ALTER TABLE MINI_ERP.ORDER_BACK DROP SAVE_MONEY;

ALTER TABLE MINI_ERP.CUSTOMER_ORDER DROP RECEIVE_MONEY;