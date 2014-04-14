UPDATE DG_SYSTEM.BUSINESS_DEFINE SET NAME='客户退货' WHERE ID='erp.business.orderCancel';
UPDATE DG_SYSTEM.FUNCTION SET NAME='客户退货' WHERE ID='erp.sale.cancelAndbackRes';

INSERT INTO DG_SYSTEM.BUSINESS_DEFINE(ID, NAME, WF_NAME, START_PAGE, START_DATA_VALIDATOR, TASK_SERVICE, CATEGORY, MEMO, VERSION) VALUES('erp.business.orderMoneyBack','撤单退款','cancelOrderMoney',null ,'','','erp.sale','撤单退款',0);

use MINI_ERP;

ALTER TABLE MINI_ERP.ORDER_BACK DROP FOREIGN KEY ORDER_BACK_ibfk_2;
ALTER TABLE MINI_ERP.ORDER_BACK DROP CUSTOMER_ORDER;
ALTER TABLE MINI_ERP.ORDER_BACK DROP BACK_TYPE;

ALTER TABLE MINI_ERP.PRODUCT_BACK_STORE_IN ADD STORE_OUT BIT(1) NOT NULL;

ALTER TABLE MINI_ERP.PRODUCT_BACK_STORE_IN RENAME TO MINI_ERP.BACK_DISPATCH;

UPDATE MINI_ERP.BACK_DISPATCH SET STORE_OUT = b'1';




ALTER TABLE MINI_ERP.BACK_ITEM ADD DISPATCH VARCHAR(32) NULL;

ALTER TABLE MINI_ERP.BACK_ITEM
ADD FOREIGN KEY (DISPATCH)
REFERENCES MINI_ERP.BACK_DISPATCH (ID)
  ON UPDATE RESTRICT
  ON DELETE RESTRICT
;

ALTER TABLE MINI_ERP.BACK_ITEM ADD STATUS VARCHAR(10) NOT NULL;
ALTER TABLE MINI_ERP.BACK_ITEM ADD TOTAL_MONEY DECIMAL(19, 3) NULL;
ALTER TABLE MINI_ERP.BACK_ITEM ADD REBATE DECIMAL(19, 4) NOT NULL;


UPDATE MINI_ERP.BACK_ITEM SET REBATE = 100;

UPDATE MINI_ERP.BACK_ITEM SET STATUS = 'STORE_IN';

DELIMITER //

CREATE FUNCTION CALC_ITEM_MONEY(I_COUNT DECIMAL(19,4) , U_MONEY DECIMAL(19,3), M_REBATE DECIMAL (19,4), MONEY_UNIT VARCHAR(32),
                                STORE_RES  VARCHAR(32)) RETURNS DECIMAL(19,3)

  BEGIN

    DECLARE FLOAT_CONVERT_RATE DECIMAL(19,10);

    DECLARE UNIT_GROUP_ID VARCHAR(32);

    DECLARE UNIT_TYPE VARCHAR(32);

    DECLARE RES_ID VARCHAR(32);

    DECLARE M_RESULT DECIMAL(19,3);

    SELECT RES INTO RES_ID FROM STORE_RES WHERE ID= STORE_RES;
    SELECT UNIT_GROUP INTO UNIT_GROUP_ID FROM RES WHERE ID=RES_ID;
    SELECT TYPE INTO UNIT_TYPE FROM UNIT_GROUP WHERE ID= UNIT_GROUP_ID;

    IF UNIT_TYPE = 'FLOAT_CONVERT' THEN
      SELECT FLOAT_CONVERSION_RATE INTO FLOAT_CONVERT_RATE FROM STORE_RES WHERE ID = STORE_RES;

      SET M_RESULT =  (I_COUNT * FLOAT_CONVERT_RATE) * ((M_REBATE / 100 ) * U_MONEY);

    ELSE
      SET M_RESULT =  I_COUNT * ((M_REBATE / 100 ) * U_MONEY);
    END IF;

    RETURN(M_RESULT);

  END;//

DELIMITER ;

UPDATE MINI_ERP.BACK_ITEM  SET
  TOTAL_MONEY= CALC_ITEM_MONEY(COUNT,MONEY,REBATE,MONEY_UNIT,STORE_RES);

DROP FUNCTION CALC_ITEM_MONEY;

UPDATE MINI_ERP.BACK_ITEM SET DISPATCH = 'ff808081451df9940145261eff15014a' WHERE ID='ff808081451df9940145263272760166';
UPDATE MINI_ERP.BACK_ITEM SET DISPATCH = 'ff808081451df99401452585e5ab00b3' WHERE ID='ff808081451df99401452642a9360167';
-- ----------------------------------------------------

ALTER TABLE MINI_ERP.BACK_DISPATCH_ITEM DROP FOREIGN KEY BACK_DISPATCH_ITEM_ibfk_1;
ALTER TABLE MINI_ERP.BACK_DISPATCH_ITEM DROP FOREIGN KEY BACK_DISPATCH_ITEM_ibfk_2;
DROP TABLE MINI_ERP.BACK_DISPATCH_ITEM;