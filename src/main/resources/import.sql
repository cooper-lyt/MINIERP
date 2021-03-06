-- If you are using Hibernate as the JPA provider, you can use this file to load seed data into the database using SQL statements
-- The portable approach is to use a startup component (such as the @PostConstruct method of a @Startup @Singleton) or observe a lifecycle event fired by Seam Servlet

-- 系统参数
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('system.business.forwordToTask', 'BOOLEAN', 'true', '业务建立后是否自动跳转到业务处理页面,是:true');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.autoGenerateStoreInCode', 'BOOLEAN', 'true', '是否自动生成入库单编号,是:true');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.autoGenerateStoreOutCode', 'BOOLEAN', 'true', '是否自动生成出库单编号,是:true');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.storeResRegRule', 'STRING', '^\\w+\\-\\w+$', '货品编号规则(正则表表达),一但建立不可更改,因为已有数据可能不符合规则');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.resRegRule', 'STRING', '^\\w+$', '物资编号规则(正则表表达),一但建立不可更改,因为已有数据可能不符合规则');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.finance.bankAccount', 'STRING', '1002', '银行总帐科目代码');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.finance.cashAccount', 'STRING', '1001', '现金总帐科目代码');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.finance.customerAccount', 'STRING', '112201', '客户应收账款科目代码');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.finance.proxyAccount', 'STRING', '112202', '客户代收应收账款科目代码');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO) VALUES ('erp.finance.advance', 'STRING', '2203', '预收账款科目代码');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO) VALUES ('erp.finance.mgrFee', 'STRING', '6602', '管理费用');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO) VALUES ('erp.finance.receive', 'STRING', '6001', '主营业务收入');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.finance.beginningDay', 'INTEGER', '12', '结算起始日 例： 结算日为 27 则2月的 2015-1-27 至 2015-2-26 日');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.finance.beginUpMonth', 'BOOLEAN', 'true', '结算起始日是否为上月日期 例：true: 结算日为 27 则2月的 2015-1-27 至 2015-2-26 日');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO) VALUES ('erp.finance.c.wrod', 'STRING', '销', '销售凭证字号');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.finance.useAdvance', 'BOOLEAN', 'false', '使用预收货款');


INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ad.DEPOSIT_BACK', 'STRING', '%s 退预存款', '退预存款 预收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.s.DEPOSIT_BACK', 'STRING', '%s 退款存入', '退预存款');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.pac.PROXY_SAVINGS', 'STRING', '%s 代收款', '代收存入 代收应收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.s.PROXY_SAVINGS', 'STRING', '%s 代收存入', '代收存入');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ad.CUSTOMER_SAVINGS', 'STRING', '%s 客户预存', '客户存款 预收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ac.CUSTOMER_SAVINGS', 'STRING', '%s 客户支付', '客户存款 应收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ac.PROXY_SAVINGS', 'STRING', '%s 代收欠款', '客户存款 应收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.o.CUSTOMER_SAVINGS', 'STRING', '%s 客户存入', '客户存款');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ad.DEPOSIT_PAY', 'STRING', '%s 支付货款', '支付货款 预收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ac.DEPOSIT_PAY', 'STRING', '%s 支付货款', '支付货款 应收账款');


INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.mf.MONEY_FREE', 'STRING', '%s 减免', '减免 管理费用');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ac.MONEY_FREE', 'STRING', '%s 减免货款', '减免 应收账款');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ad.ORDER_PAY', 'STRING', '%s 发货', '订单支付 预收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ac.ORDER_PAY', 'STRING', '%s 赊款发货', '订单支付 应收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.pac.ORDER_PAY', 'STRING', '%s 代收发货', '订单支付 应收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.rm.ORDER_PAY', 'STRING', '%s 发货', '支付货款 主营业务收入');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ac.ORDER_BACK', 'STRING', '%s 退货冲账', '退货退款 应收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.pac.ORDER_BACK', 'STRING', '%s 退货冲账', '退货退款 代办应收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.rm.ORDER_BACK', 'STRING', '%s 退货冲账', '退货退款 主营业务收入');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ad.ORDER_BACK', 'STRING', '%s 退货存入', '退货退款 预收账款');
INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.ADF.ac.c.ORDER_BACK', 'STRING', '%s 退货退款', '退货退款 应收账款');


INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO) VALUES ('erp.dispatch.QQKey', 'STRING', 'noSet', 'QQKey');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.sale.receiveAddress', 'STRING', 'CITY', '收货地址默认提取字段 CITY 城市 ADD 地址');

INSERT INTO DG_SYSTEM.SYSTEM_PARAM (ID, TYPE, VALUE, MEMO)
VALUES ('erp.customer.balanceInput', 'BOOLEAN', 'true', '是否可修改客户帐户余额');


-- 业务类别

INSERT INTO DG_SYSTEM.BUSINESS_CATEGORY (ID, NAME, PRIORITY) VALUES ('erp.sale', '销售业务', 1);
INSERT INTO DG_SYSTEM.BUSINESS_CATEGORY (ID, NAME, PRIORITY) VALUES ('erp.storage', '仓储业务', 2);


-- 业务

INSERT INTO DG_SYSTEM.BUSINESS_DEFINE (ID, NAME, WF_NAME, START_PAGE, START_DATA_VALIDATOR, TASK_SERVICE, CATEGORY, MEMO, VERSION)
VALUES
  ('erp.business.order', '销售订单', 'order', '/business/startPrepare/erp/sale/CreateSaleOrder.xhtml', '', '', 'erp.sale',
   '销售订单', 0);
INSERT INTO DG_SYSTEM.BUSINESS_DEFINE (ID, NAME, WF_NAME, START_PAGE, START_DATA_VALIDATOR, TASK_SERVICE, CATEGORY, MEMO, VERSION)
VALUES
  ('erp.business.orderCancel', ' ', 'orderCancel', '/business/startPrepare/erp/sale/StoreResBackCreate.xhtml', '', '',
   'erp.sale', '客户退货', 0);
INSERT INTO DG_SYSTEM.BUSINESS_DEFINE (ID, NAME, WF_NAME, START_PAGE, START_DATA_VALIDATOR, TASK_SERVICE, CATEGORY, MEMO, VERSION)
VALUES
  ('erp.business.allocation', '仓库调拨', 'stockAllocation', '/business/startPrepare/erp/store/AllocationCreate.xhtml', '',
   '', 'erp.storage', '仓库调拨', 0);

INSERT INTO DG_SYSTEM.BUSINESS_DEFINE (ID, NAME, WF_NAME, START_PAGE, START_DATA_VALIDATOR, TASK_SERVICE, CATEGORY, MEMO, VERSION)
VALUES ('erp.business.orderMoneyBack', '撤单退款', 'cancelOrderMoney', NULL, '', '', 'erp.sale', '撤单退款', 0);


INSERT INTO DG_SYSTEM.BUSINESS_DEFINE (ID, NAME, WF_NAME, START_PAGE, START_DATA_VALIDATOR, TASK_SERVICE, CATEGORY, MEMO, VERSION)
VALUES ('erp.business.inventory', '盘点', 'inventory', '/business/startPrepare/erp/store/Inventory.xhtml', '', '',
        'erp.storage', '盘点', 0);

-- 测试业务
INSERT INTO DG_SYSTEM.BUSINESS_DEFINE (ID, NAME, WF_NAME, START_PAGE, START_DATA_VALIDATOR, TASK_SERVICE, CATEGORY, MEMO, VERSION)
VALUES ('system.business.test', '测试流程', 'processTest', '', '', '', 'erp.sale', '测试流程', 0);


-- 功能种类
INSERT INTO DG_SYSTEM.FUNC_CATEGORY (ID, NAME, ICON, PRIORITY, MEMO)
VALUES ('system.config', '系统设置', '', '1', '超级管理员,一般由实施方有此权限');
INSERT INTO DG_SYSTEM.FUNC_CATEGORY (ID, NAME, ICON, PRIORITY, MEMO) VALUES ('system.manager', '系统管理', '', '2', '管理员');

INSERT INTO DG_SYSTEM.FUNC_CATEGORY (ID, NAME, ICON, PRIORITY, MEMO) VALUES ('erp.res.mgr', '物资货品', '', 20, '');


INSERT INTO DG_SYSTEM.FUNC_CATEGORY (ID, NAME, ICON, PRIORITY, MEMO) VALUES ('erp.produce.mgr', '生产管理', '', 30, '');


INSERT INTO DG_SYSTEM.FUNC_CATEGORY (ID, NAME, ICON, PRIORITY, MEMO) VALUES ('erp.storage.mgr', '仓储管理', '', 40, '');
INSERT INTO DG_SYSTEM.FUNC_CATEGORY (ID, NAME, ICON, PRIORITY, MEMO) VALUES ('erp.storage.store', '出入库', '', 50, '');


INSERT INTO DG_SYSTEM.FUNC_CATEGORY (ID, NAME, ICON, PRIORITY, MEMO) VALUES ('erp.sale.mgr', '销售管理', '', 70, '');

INSERT INTO DG_SYSTEM.FUNC_CATEGORY (ID, NAME, ICON, PRIORITY, MEMO) VALUES ('erp.search', '查询报表', '', 90, '');
INSERT INTO DG_SYSTEM.FUNC_CATEGORY (ID, NAME, ICON, PRIORITY, MEMO) VALUES ('erp.total', '统计分析', '', 93, '');

INSERT INTO DG_SYSTEM.FUNC_CATEGORY (ID, NAME, ICON, PRIORITY, MEMO) VALUES ('finance.config', '财务功能', '', 100, '');

INSERT INTO DG_SYSTEM.FUNC_CATEGORY (ID, NAME, ICON, PRIORITY, MEMO) VALUES ('erp.transport', '运输管理', '', 200, '');

-- 功能
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('system.param', '系统参数管理', 'system.config', '', '/func/system/config/SystemParams.seam', '', '2', '系统运行方式设置');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('system.person', '人员维护', 'system.manager', '', '/func/system/manager/PersonMgr.seam', '', '3', '管理自然人');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('system.businessConfig', '业务管理', 'system.config', '', '/func/system/config/BusinessMgr.seam', '', '3', '业务处理配置');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('system.employee', '员工管理', 'system.manager', '', '/func/system/manager/EmployeeMgr.seam', '', '4', '员工和组织机构管理');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('system.role', '角色管理', 'system.config', '', '/func/system/config/RoleMgr.seam', '', '4', '角色管理和角色分配启动业务');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('system.word', '字典管理', 'system.manager', '', '/func/system/manager/WordMgr.seam', '', '5', '字典管理');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('system.jbpmProcessDeployment', '流程部署', 'system.config', '', '/func/system/jbpm/ProcessDefinition.seam', '', 7,
   '部署JBPM PAR 流程');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('system.processInstanceMgr', '流程管理', 'system.manager', '', '/func/system/ProcessInstanceMgr.seam', '', '6', '');


INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.data.res', '物资管理', 'erp.res.mgr', '', '/func/erp/res/ResMgr.seam', '', '10', '物资和物资种类设置');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.data.storeRes', '货品管理', 'erp.res.mgr', '', '/func/erp/res/StoreResMgr.seam', '', '10', '货品管理');

INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.data.store', '仓库管理', 'erp.storage.mgr', '', '/func/erp/store/StoreMgr.seam', '', '11', '仓库管理及仓库权限分配');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.store.area', '仓库存储区设置', 'erp.storage.mgr', '', '/func/erp/store/StoreAreaMgr.seam', '', '1', '仓库存储区域设置');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.store.inventory', '盘点', 'erp.storage.mgr', '', '/func/erp/store/Inventory.seam', '', '1', '盘点');


INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.store.stockSearch', '库存查询', 'erp.res.mgr', '', '/func/erp/search/StockSearch.seam', '', '1', '库存查询');


INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.data.resUnit', '计量单位管理', 'erp.res.mgr', '', '/func/erp/res/UnitMgr.seam', '', '9', '物资计量单位组和计量单位管理');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.store.materialIn', '收料入库', 'erp.storage.store', '', '/func/erp/store/storeChange/MaterialIn.seam', '', '3',
   '物资入库');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.store.produceIn', '生产入库', 'erp.storage.store', '', '/func/erp/store/storeChange/ProduceIn.seam', '', '1',
   '产成品，半成品入库');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.store.allocation', '仓库调拨', 'erp.storage.store', '', '/func/erp/store/StoreAllocation.seam', '', '4', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.store.otherStockChange', '库存变动', 'erp.storage.store', '', '/func/erp/store/storeChange/OtherStockChange.seam',
   '', '2', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.store.stockChangeSearch', '出入库查询', 'erp.storage.store', '', '/func/erp/store/StockChangeSearch.seam', '', '9',
   '');


INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.sale.myOrder', '订单', 'erp.sale.mgr', '', '/func/erp/sale/CustomerOrder.seam', '', '10', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.sale.cancelAndbackRes', '客户退货', 'erp.sale.mgr', '', '/func/erp/sale/CancelOrderAndBackRes.seam', '', '10', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.sale.customerArea', '销售区域', 'erp.sale.mgr', '', '/func/erp/sale/mgr/CustomerArea.seam', '', '1', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.sale.customerLevel', '客户级别', 'erp.sale.mgr', '', '/func/erp/sale/mgr/CustomerLevel.seam', '', '2', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.sale.quotedPrice', '客户报价', 'erp.sale.mgr', '', '/func/erp/sale/QuotedPrice.seam', '', '3', '');


INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.sale.orderRebateProgram', '销售员提成方案', 'erp.sale.mgr', '', '/func/erp/sale/mgr/OrderRebateProgramMgr.seam', '',
   '20', '');

INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.saler.middleManMgr', '客户关系人', 'erp.sale.mgr', '', '/func/erp/sale/mgr/MiddleManMgr.seam', '', '100', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.cashier.moneySave', '收款', 'erp.sale.mgr', '', '/func/erp/finance/cashier/CustomerMoneySavings.seam', '', '501',
   '');


INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.sale.customerMgr', '客户管理', 'erp.sale.mgr', '', '/func/erp/sale/Customer.seam', '', '3', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.sale.customerContact', '客户通讯录', 'erp.sale.mgr', '', '/func/erp/sale/CustomerContact.seam', '', '4', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.sale.customerOper', '客户操作', 'erp.sale.mgr', '', '/func/erp/sale/CustomerView.seam', '', '5', '');




INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('finance.config.accounting', '科目设置', 'finance.config', '', '/func/erp/finance/accountancy/AccountingMgr.seam', '',
   '1', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('finance.config.bank', '银行帐户', 'finance.config', '', '/func/erp/finance/BankAccountMgr.seam', '', '2', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('finance.prepareAccount', '记账', 'finance.config', '', '/func/erp/finance/cashier/MakeAccount.seam', '', '3', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('finance.account', '结账', 'finance.config', '', '/func/erp/finance/Accounting.seam', '', '4', '');


INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.transport.carMgr', '车辆管理', 'erp.transport', '', '/func/erp/transport/CarsMgr.seam', '', '1', '');

INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.product.factoryMgr', '工厂管理', 'erp.produce.mgr', '', '/func/erp/product/mgr/FactoryMgr.seam', '', '1', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.product.factoryGroup', '生厂小组管理', 'erp.produce.mgr', '', '/func/erp/product/ProductGroupMgr.seam', '', '2', '');


INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.search.giftsTotal', '赠品统计', 'erp.search', '', '/func/erp/sale/GiftsTotal.seam', '', '300',
   '');

INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.search.customerShip', '客户货品往来明细', 'erp.search', '', '/func/erp/sale/SaleCustomerShipReport.seam', '', '400',
   '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.search.groupStoreInTotal', '生产小组入库汇总', 'erp.search', '', '/func/erp/store/ProcductGroupStoreInTotal.seam', '',
   '500', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.search.sallInOutDayReoper', '货品往来分组汇总', 'erp.search', '', '/func/erp/sale/CustomerResTotal.seam', '', '500',
   '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.search.customerMoneyReport', '客户货款往来', 'erp.search', '', '/func/erp/sale/CustomerMoneyReport.seam', '', '510',
   '');

INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.search.storeResSaleTotal', '货品销售统计', 'erp.search', '', '/func/erp/sale/StoreResSaleTotal.seam', '', '520',
   '');

INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.search.deliveryFareSearch', '运费查询', 'erp.search', '', '/func/erp/sale/DeliveryFareSearch.seam', '', '530',
   '');


INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.total.customerBalance', '客户帐户分析', 'erp.total', '', '/func/erp/total/customerMoney.seam', '', '100', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.total.orderMoneyChart', '订单销售情况', 'erp.total', '', '/func/erp/total/SaleMoneyChart.seam', '', '120', '');
INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.total.procedureSaleChart', '产品销售情况', 'erp.total', '', '/func/erp/total/ProcedureSaleChart.seam', '', '130', '');


INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.total.resYearChart', '产品年销售趋势', 'erp.total', '', '/func/erp/total/YearResSaleChart.seam', '', '140', '');

INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.total.monthMoneyChart', '月销售分析图', 'erp.total', '', '/func/erp/total/MonthMoneyChart.seam', '', '140', '');

INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO)
VALUES ('erp.total.yearMoneyChart', '年销售分析图', 'erp.total', '', '/func/erp/total/YearMoneyChart.seam', '', '140', '');






INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.search.stockChangeItemGroup', '出/入库分组汇总', 'erp.search', '', '/func/erp/store/StoreResInOutReport.seam', '',
   '600', '');

INSERT INTO DG_SYSTEM.FUNCTION (ID, NAME, CATEGORY, ICON, LOCATION, BANNER, PRIORITY, MEMO) VALUES
  ('erp.search.storeChangeTotal', '仓库货品出入库汇总', 'erp.search', '', '/func/erp/store/StoreChangeTotal.seam', '', '620',
   '');

-- 角色


INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('system.config', '系统设置', '调整系统运行方式', 1);
INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('system.manager', '系统管理', '系统管理', 2);


INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('erp.sale.manager', '销售主管', '管理销售', 20);
INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('erp.storage.manager', '仓储主管', '可多仓库', 10);


INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('erp.storage.store', '仓库管理员', '仓库', 11);
INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('erp.sale.saler', '销售员', '销售员', 21);

INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('erp.produce.manager', '生产管理', '', 30);
INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('erp.produce.factory.mgr', '工厂主管', '', 32);


INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('erp.transport.manager', '运输管理', '', 31);

INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('erp.storage.dispatch', '调度', '业务流中的调度阶段', 40);

INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('erp.finance.cashier', '出纳', '业务流中的收款阶段', 50);

INSERT INTO DG_SYSTEM.ROLE (ID, NAME, DESCRIPTION, PRIORITY) VALUES ('erp.finance.accountancy', '会计', '', 60);


-- ROLE_FUNCTION 角色种类
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('system.config', 'system.param');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('system.config', 'system.businessConfig');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('system.config', 'system.employee');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('system.config', 'system.person');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('system.config', 'system.role');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('system.config', 'system.jbpmProcessDeployment');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('system.config', 'system.word');


INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('system.manager', 'system.employee');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('system.manager', 'system.person');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('system.manager', 'system.word');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('system.manager', 'system.processInstanceMgr');


INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.manager', 'erp.data.storeRes');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.manager', 'erp.data.res');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.manager', 'erp.data.store');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.manager', 'erp.data.resUnit');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.manager', 'erp.store.stockChangeSearch');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.manager', 'erp.search.groupStoreInTotal');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.manager', 'erp.search.stockChangeItemGroup');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.manager', 'erp.store.stockSearch');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.manager', 'erp.search.storeChangeTotal');


INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.store.area');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.store.materialIn');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.store.stockSearch');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.store.produceIn');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.store.inventory');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.store.otherStockChange');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.store.stockChangeSearch');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.store.allocation');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.search.stockChangeItemGroup');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.search.groupStoreInTotal');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.data.storeRes');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.store', 'erp.search.storeChangeTotal');

INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.sale.customerMgr');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.sale.myOrder');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.sale.customerContact');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.search.customerShip');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.sale.cancelAndbackRes');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.search.sallInOutDayReoper');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.search.customerMoneyReport');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.sale.customerOper');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'system.processInstanceMgr');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.search.giftsTotal');



INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.total.orderMoneyChart');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.search.storeResSaleTotal');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.search.deliveryFareSearch');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.total.procedureSaleChart');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.total.resYearChart');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.total.monthMoneyChart');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.saler', 'erp.total.yearMoneyChart');


INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.sale.customerArea');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.sale.customerLevel');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.sale.customerMgr');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.sale.customerContact');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.saler.middleManMgr');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.search.customerShip');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.search.sallInOutDayReoper');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.search.customerMoneyReport');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.total.customerBalance');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.sale.orderRebateProgram');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.sale.customerOper');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.total.orderMoneyChart');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.search.storeResSaleTotal');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.search.deliveryFareSearch');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.total.procedureSaleChart');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.total.resYearChart');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.search.giftsTotal');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.total.monthMoneyChart');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.sale.manager', 'erp.total.yearMoneyChart');

INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.storage.dispatch','erp.store.stockSearch');

INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.accountancy', 'finance.config.accounting');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.accountancy', 'finance.config.bank');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.accountancy', 'finance.account');

INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.cashier', 'finance.config.bank');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.cashier', 'erp.search.sallInOutDayReoper');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.cashier', 'erp.search.customerMoneyReport');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.cashier', 'erp.total.customerBalance');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.cashier', 'erp.sale.customerOper');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.cashier', 'erp.cashier.moneySave');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.cashier', 'finance.prepareAccount');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.finance.cashier', 'finance.account');

INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.transport.manager', 'erp.transport.carMgr');

INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.produce.manager', 'erp.data.res');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.produce.manager', 'erp.data.resUnit');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.produce.manager', 'erp.product.factoryMgr');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.produce.manager', 'erp.product.factoryGroup');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.produce.manager', 'erp.data.storeRes');
INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.produce.manager', 'erp.search.groupStoreInTotal');

INSERT INTO DG_SYSTEM.ROLE_FUNCTION (ROL_ID, FUN_ID) VALUES ('erp.produce.factory.mgr', 'erp.product.factoryGroup');

-- ADMIN INSERY
-- INSERT INTO DG_SYSTEM.PERSON (ID,NAME,CREDENTIALS_TYPE,_FOREIGN,CREDENTIALS_NUMBER,DATE_OF_BIRTH) VALUES ('admin','admin','OTHER',1,'1','2013-07-15 10:27:08');
-- INSERT INTO DG_SYSTEM.EMPLOYEE(ID,ENABLE,PERSON_ID,PASSWORD,ORGANIZATION) VALUES ('admin',b'1','admin','admin','0');
-- INSERT INTO DG_SYSTEM.ROLE_CATE_EMP (EMP_ID, ROLE_ID) VALUES ('admin','admin');
-- INSERT INTO DG_SYSTEM.ROLE_ROLE_CATEGROY(ROLE_ID, CAT_ID) VALUES ('system.manager','admin');



-- WORD

INSERT INTO DG_SYSTEM.WORD_CATEGORY (ID, NAME, MEMO, SYSTEM) VALUES ('finance.accountType', '科目种类', '', b'1');

INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('finance.accountType.Asset', 'Asset', '资产类科目', 'finance.accountType', '', '1', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('finance.accountType.Liability', 'Liability', '负债类科目', 'finance.accountType', '', '2', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('finance.accountType.OwnerEquity', 'OwnerEquity', '所有者权益类科目', 'finance.accountType', '', '3', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('finance.accountType.profitLoss', 'profitLoss', '损益类科目', 'finance.accountType', '', '4', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('finance.accountType.cost', 'cost', '成本类科目', 'finance.accountType', '', '5', b'1');

INSERT INTO DG_SYSTEM.WORD_CATEGORY (ID, NAME, MEMO, SYSTEM) VALUES ('system.empJob', '职位', '', b'1');

INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('system.empJob.manager', 'manager', '经理', 'system.empJob', '', '3', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('system.empJob.boss', 'boss', '总经理', 'system.empJob', '', '5', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('system.empJob.factoryManager', 'factoryManager', '厂长', 'system.empJob', '', '4', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('system.empJob.emp', 'emp', '职员', 'system.empJob', '', '2', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('system.empJob.other', 'ohter', '其它', 'system.empJob', '', '10', b'1');

INSERT INTO DG_SYSTEM.WORD_CATEGORY (ID, NAME, MEMO, SYSTEM) VALUES ('erp.customerType', '客户类型', '', b'1');

INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.customerType.dealer', 'dealer', '分销商', 'erp.customerType', '', '1', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.customerType.construct', 'construct', '开发商', 'erp.customerType', '', '2', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.customerType.project', 'project', '工程用户', 'erp.customerType', '', '2', b'1');

INSERT INTO DG_SYSTEM.WORD_CATEGORY (ID, NAME, MEMO, SYSTEM) VALUES ('erp.bank', '银行', '', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.ccb', 'CCB', '建设银行', 'erp.bank', '', 2, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.boc', 'BOC', '中国银行', 'erp.bank', '', 1, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.abc', 'ABC', '农业银行', 'erp.bank', '', 3, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.icbc', 'ICBC', '工商银行', 'erp.bank', '', 4, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.cmbc', 'CMBC', '民生银行', 'erp.bank', '', 12, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.cmb', 'CMB', '招商银行', 'erp.bank', '', 5, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.cib', 'CIB', '兴业银行', 'erp.bank', '', 13, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.bob', 'BOB', '北京银行', 'erp.bank', '', 14, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.bcm', 'BCM', '交通银行', 'erp.bank', '', 6, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.ceb', 'CEB', '光大银行', 'erp.bank', '', 7, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.citic', 'CITIC', '中信银行', 'erp.bank', '', 8, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.gdb', 'GDB', '广东发展银行', 'erp.bank', '', 9, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.spdb', 'SPDB', '上海浦东发展银行', 'erp.bank', '', 10, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.sdb', 'SDB', '深圳发展银行', 'erp.bank', '', 11, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.bank.other', 'OTHER', '其它', 'erp.bank', '', 99, b'0');


INSERT INTO DG_SYSTEM.WORD_CATEGORY (ID, NAME, MEMO, SYSTEM) VALUES ('erp.middleManType', '中间人类型', '', b'1');

INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.middleManType.saler', 'saler', '销售人员', 'erp.middleManType', '', 1, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.middleManType.dealer', 'dealer', '经销商', 'erp.middleManType', '', 2, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.middleManType.contact', 'contact', '客户联系人', 'erp.middleManType', '', 3, b'1');

INSERT INTO DG_SYSTEM.WORD_CATEGORY (ID, NAME, MEMO, SYSTEM) VALUES ('erp.needResReason', '产品需求原因', '', b'1');

INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.needResReason.order', 'order', '订单', 'erp.needResReason', '', 1, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.needResReason.supplement', 'supplement', '订单补发', 'erp.needResReason', '', 2, b'1');

INSERT INTO DG_SYSTEM.WORD_CATEGORY (ID, NAME, MEMO, SYSTEM) VALUES ('erp.orderBackReason', '订单撤消原因', '', b'1');

INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.orderBackReason.resChange', 'resChange', '订单需求更改', 'erp.orderBackReason', '', 1, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.orderBackReason.inputError', 'inputError', '输入错误', 'erp.orderBackReason', '', 2, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.orderBackReason.byCustomer', 'byCustomer', '客户退单', 'erp.orderBackReason', '', 3, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.orderBackReason.other', 'other', '其它', 'erp.orderBackReason', '', 4, b'1');

INSERT INTO DG_SYSTEM.WORD_CATEGORY (ID, NAME, MEMO, SYSTEM) VALUES ('erp.orderResBackReason', '退货原因', '', b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.BackReason.customer', 'resChange', '客户原因', 'erp.orderResBackReason', '', 1, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.BackReason.shipError', 'inputError', '发货错误', 'erp.orderResBackReason', '', 2, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.BackReason.shipLazy', 'byCustomer', '发货延迟', 'erp.orderResBackReason', '', 3, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.BackReason.res', 'byCustomer', '质量问题', 'erp.orderResBackReason', '', 4, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.BackReason.other', 'other', '其它', 'erp.orderResBackReason', '', 5, b'1');

INSERT INTO DG_SYSTEM.WORD_CATEGORY (ID, NAME, MEMO, SYSTEM) VALUES ('erp.customerContactType', '客户联系方式类型', '', b'1');

INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.customerContactType.main', 'main', '采购联系人', 'erp.customerContactType', '', 1, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.customerContactType.finance', 'finance', '财务', 'erp.customerContactType', '', 2, b'1');

INSERT INTO DG_SYSTEM.WORD_CATEGORY (ID, NAME, MEMO, SYSTEM) VALUES ('erp.allocationReason', '调库原因', '', b'1');

INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.allocationReason.order', 'order', '订单发货调库', 'erp.allocationReason', '', 1, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.allocationReason.full', 'full', '仓库储存空间不足', 'erp.allocationReason', '', 2, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.allocationReason.save', 'save', '售销备货', 'erp.allocationReason', '', 3, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.allocationReason.task', 'task', '日常调库', 'erp.allocationReason', '', 4, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.allocationReason.other', 'other', '其它原因', 'erp.allocationReason', '', 5, b'1');

INSERT INTO DG_SYSTEM.WORD_CATEGORY (ID, NAME, MEMO, SYSTEM) VALUES ('erp.quotType', '报价类型', '', b'1');

INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.quotType.first', 'first', '客户询价', 'erp.quotType', '', 1, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.quotType.prepare', 'prepare', '预订单', 'erp.quotType', '', 2, b'1');
INSERT INTO DG_SYSTEM.WORD (ID, _KEY, _VALUE, CATEGORY, DESCRIPTION, PRIORITY, ENABLE)
VALUES ('erp.quotType.other', 'othre', '其它', 'erp.quotType', '', 20, b'1');


INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (11, '北京市', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (12, '天津市', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (13, '河北省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (14, '山西省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (15, '内蒙古自治区', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (21, '辽宁省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (22, '吉林省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (23, '黑龙江省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (31, '上海市', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (32, '江苏省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (33, '浙江省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (34, '安徽省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (35, '福建省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (36, '江西省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (37, '山东省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (41, '河南省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (42, '湖北省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (43, '湖南省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (44, '广东省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (45, '广西壮族自治区', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (46, '海南省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (50, '重庆市', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (51, '四川省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (52, '贵州省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (53, '云南省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (54, '西藏自治区', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (61, '陕西省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (62, '甘肃省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (63, '青海省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (64, '宁夏回族自治区', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (65, '新疆维吾尔自治区', 0);


INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (71, '台湾省', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (81, '香港特别行政区', 0);
INSERT INTO DG_SYSTEM.PROVINCE (PID, NAME, SORT) VALUES (82, '澳门特别行政区', 0);

INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1101, 11, '市辖区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1201, 12, '市辖区', 0);
;
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1301, 13, '石家庄市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1302, 13, '唐山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1303, 13, '秦皇岛市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1304, 13, '邯郸市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1305, 13, '邢台市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1306, 13, '保定市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1307, 13, '张家口市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1308, 13, '承德市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1309, 13, '沧州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1310, 13, '廊坊市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1311, 13, '衡水市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1401, 14, '太原市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1402, 14, '大同市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1403, 14, '阳泉市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1404, 14, '长治市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1405, 14, '晋城市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1406, 14, '朔州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1407, 14, '晋中市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1408, 14, '运城市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1409, 14, '忻州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1410, 14, '临汾市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1411, 14, '吕梁市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1501, 15, '呼和浩特市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1502, 15, '包头市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1503, 15, '乌海市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1504, 15, '赤峰市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1505, 15, '通辽市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1506, 15, '鄂尔多斯市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1507, 15, '呼伦贝尔市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1508, 15, '巴彦淖尔市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1509, 15, '乌兰察布市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1522, 15, '兴安盟', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1525, 15, '锡林郭勒盟', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (1529, 15, '阿拉善盟', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2101, 21, '沈阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2102, 21, '大连市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2103, 21, '鞍山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2104, 21, '抚顺市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2105, 21, '本溪市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2106, 21, '丹东市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2107, 21, '锦州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2108, 21, '营口市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2109, 21, '阜新市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2110, 21, '辽阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2111, 21, '盘锦市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2112, 21, '铁岭市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2113, 21, '朝阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2114, 21, '葫芦岛市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2201, 22, '长春市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2202, 22, '吉林市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2203, 22, '四平市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2204, 22, '辽源市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2205, 22, '通化市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2206, 22, '白山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2207, 22, '松原市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2208, 22, '白城市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2224, 22, '延边朝鲜族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2301, 23, '哈尔滨市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2302, 23, '齐齐哈尔市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2303, 23, '鸡西市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2304, 23, '鹤岗市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2305, 23, '双鸭山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2306, 23, '大庆市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2307, 23, '伊春市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2308, 23, '佳木斯市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2309, 23, '七台河市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2310, 23, '牡丹江市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2311, 23, '黑河市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2312, 23, '绥化市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (2327, 23, '大兴安岭地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3101, 31, '市辖区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3201, 32, '南京市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3202, 32, '无锡市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3203, 32, '徐州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3204, 32, '常州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3205, 32, '苏州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3206, 32, '南通市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3207, 32, '连云港市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3208, 32, '淮安市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3209, 32, '盐城市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3210, 32, '扬州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3211, 32, '镇江市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3212, 32, '泰州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3213, 32, '宿迁市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3301, 33, '杭州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3302, 33, '宁波市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3303, 33, '温州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3304, 33, '嘉兴市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3305, 33, '湖州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3306, 33, '绍兴市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3307, 33, '金华市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3308, 33, '衢州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3309, 33, '舟山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3310, 33, '台州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3311, 33, '丽水市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3401, 34, '合肥市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3402, 34, '芜湖市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3403, 34, '蚌埠市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3404, 34, '淮南市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3405, 34, '马鞍山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3406, 34, '淮北市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3407, 34, '铜陵市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3408, 34, '安庆市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3410, 34, '黄山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3411, 34, '滁州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3412, 34, '阜阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3413, 34, '宿州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3415, 34, '六安市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3416, 34, '亳州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3417, 34, '池州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3418, 34, '宣城市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3501, 35, '福州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3502, 35, '厦门市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3503, 35, '莆田市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3504, 35, '三明市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3505, 35, '泉州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3506, 35, '漳州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3507, 35, '南平市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3508, 35, '龙岩市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3509, 35, '宁德市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3601, 36, '南昌市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3602, 36, '景德镇市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3603, 36, '萍乡市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3604, 36, '九江市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3605, 36, '新余市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3606, 36, '鹰潭市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3607, 36, '赣州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3608, 36, '吉安市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3609, 36, '宜春市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3610, 36, '抚州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3611, 36, '上饶市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3701, 37, '济南市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3702, 37, '青岛市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3703, 37, '淄博市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3704, 37, '枣庄市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3705, 37, '东营市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3706, 37, '烟台市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3707, 37, '潍坊市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3708, 37, '济宁市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3709, 37, '泰安市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3710, 37, '威海市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3711, 37, '日照市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3712, 37, '莱芜市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3713, 37, '临沂市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3714, 37, '德州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3715, 37, '聊城市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3716, 37, '滨州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (3717, 37, '菏泽市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4101, 41, '郑州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4102, 41, '开封市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4103, 41, '洛阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4104, 41, '平顶山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4105, 41, '安阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4106, 41, '鹤壁市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4107, 41, '新乡市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4108, 41, '焦作市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4109, 41, '濮阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4110, 41, '许昌市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4111, 41, '漯河市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4112, 41, '三门峡市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4113, 41, '南阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4114, 41, '商丘市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4115, 41, '信阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4116, 41, '周口市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4117, 41, '驻马店市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4190, 41, '省直辖县级行政区划', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4201, 42, '武汉市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4202, 42, '黄石市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4203, 42, '十堰市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4205, 42, '宜昌市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4206, 42, '襄阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4207, 42, '鄂州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4208, 42, '荆门市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4209, 42, '孝感市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4210, 42, '荆州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4211, 42, '黄冈市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4212, 42, '咸宁市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4213, 42, '随州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4228, 42, '恩施土家族苗族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4290, 42, '省直辖县级行政区划', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4301, 43, '长沙市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4302, 43, '株洲市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4303, 43, '湘潭市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4304, 43, '衡阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4305, 43, '邵阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4306, 43, '岳阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4307, 43, '常德市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4308, 43, '张家界市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4309, 43, '益阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4310, 43, '郴州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4311, 43, '永州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4312, 43, '怀化市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4313, 43, '娄底市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4331, 43, '湘西土家族苗族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4401, 44, '广州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4402, 44, '韶关市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4403, 44, '深圳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4404, 44, '珠海市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4405, 44, '汕头市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4406, 44, '佛山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4407, 44, '江门市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4408, 44, '湛江市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4409, 44, '茂名市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4412, 44, '肇庆市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4413, 44, '惠州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4414, 44, '梅州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4415, 44, '汕尾市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4416, 44, '河源市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4417, 44, '阳江市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4418, 44, '清远市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4419, 44, '东莞市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4420, 44, '中山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4451, 44, '潮州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4452, 44, '揭阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4453, 44, '云浮市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4501, 45, '南宁市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4502, 45, '柳州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4503, 45, '桂林市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4504, 45, '梧州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4505, 45, '北海市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4506, 45, '防城港市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4507, 45, '钦州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4508, 45, '贵港市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4509, 45, '玉林市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4510, 45, '百色市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4511, 45, '贺州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4512, 45, '河池市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4513, 45, '来宾市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4514, 45, '崇左市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4601, 46, '海口市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4602, 46, '三亚市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4603, 46, '三沙市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (4690, 46, '省直辖县级行政区划', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5001, 50, '市辖区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5101, 51, '成都市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5103, 51, '自贡市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5104, 51, '攀枝花市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5105, 51, '泸州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5106, 51, '德阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5107, 51, '绵阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5108, 51, '广元市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5109, 51, '遂宁市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5110, 51, '内江市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5111, 51, '乐山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5113, 51, '南充市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5114, 51, '眉山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5115, 51, '宜宾市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5116, 51, '广安市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5117, 51, '达州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5118, 51, '雅安市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5119, 51, '巴中市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5120, 51, '资阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5132, 51, '阿坝藏族羌族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5133, 51, '甘孜藏族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5134, 51, '凉山彝族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5201, 52, '贵阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5202, 52, '六盘水市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5203, 52, '遵义市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5204, 52, '安顺市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5205, 52, '毕节市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5206, 52, '铜仁市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5223, 52, '黔西南布依族苗族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5226, 52, '黔东南苗族侗族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5227, 52, '黔南布依族苗族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5301, 53, '昆明市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5303, 53, '曲靖市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5304, 53, '玉溪市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5305, 53, '保山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5306, 53, '昭通市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5307, 53, '丽江市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5308, 53, '普洱市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5309, 53, '临沧市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5323, 53, '楚雄彝族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5325, 53, '红河哈尼族彝族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5326, 53, '文山壮族苗族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5328, 53, '西双版纳傣族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5329, 53, '大理白族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5331, 53, '德宏傣族景颇族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5333, 53, '怒江傈僳族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5334, 53, '迪庆藏族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5401, 54, '拉萨市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5421, 54, '昌都地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5422, 54, '山南地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5423, 54, '日喀则地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5424, 54, '那曲地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5425, 54, '阿里地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (5426, 54, '林芝地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6101, 61, '西安市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6102, 61, '铜川市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6103, 61, '宝鸡市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6104, 61, '咸阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6105, 61, '渭南市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6106, 61, '延安市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6107, 61, '汉中市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6108, 61, '榆林市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6109, 61, '安康市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6110, 61, '商洛市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6201, 62, '兰州市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6202, 62, '嘉峪关市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6203, 62, '金昌市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6204, 62, '白银市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6205, 62, '天水市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6206, 62, '武威市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6207, 62, '张掖市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6208, 62, '平凉市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6209, 62, '酒泉市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6210, 62, '庆阳市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6211, 62, '定西市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6212, 62, '陇南市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6229, 62, '临夏回族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6230, 62, '甘南藏族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6301, 63, '西宁市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6321, 63, '海东地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6322, 63, '海北藏族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6323, 63, '黄南藏族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6325, 63, '海南藏族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6326, 63, '果洛藏族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6327, 63, '玉树藏族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6328, 63, '海西蒙古族藏族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6401, 64, '银川市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6402, 64, '石嘴山市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6403, 64, '吴忠市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6404, 64, '固原市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6405, 64, '中卫市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6501, 65, '乌鲁木齐市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6502, 65, '克拉玛依市', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6521, 65, '吐鲁番地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6522, 65, '哈密地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6523, 65, '昌吉回族自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6527, 65, '博尔塔拉蒙古自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6528, 65, '巴音郭楞蒙古自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6529, 65, '阿克苏地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6530, 65, '克孜勒苏柯尔克孜自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6531, 65, '喀什地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6532, 65, '和田地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6540, 65, '伊犁哈萨克自治州', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6542, 65, '塔城地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6543, 65, '阿勒泰地区', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (6590, 65, '自治区直辖县级行政区划', 0);


INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (7101, 71, '台湾', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (8101, 81, '香港', 0);
INSERT INTO DG_SYSTEM.CITY (PID, FID, NAME, SORT) VALUES (8201, 82, '澳门', 0);

