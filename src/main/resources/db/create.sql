CREATE TABLE brand (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  code VARCHAR(45) NOT NULL COMMENT 'code',
  name VARCHAR(45) NOT NULL COMMENT 'name',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX udx_brand_code_type (code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE category (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  code VARCHAR(45) NOT NULL COMMENT 'code',
  name VARCHAR(45) NOT NULL COMMENT 'name',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX udx_category_code_type (code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE brand_category_mapping (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  brand_code VARCHAR(45) NOT NULL COMMENT '品牌code',
  category_code VARCHAR(45) NOT NULL COMMENT '品类code',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX udx_brand_category_mapping (brand_code,category_code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE goods (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  brand_code VARCHAR(45) NOT NULL COMMENT '品牌',
  goods_code VARCHAR(45) NOT NULL COMMENT 'code',
  goods_name VARCHAR(45) NOT NULL COMMENT 'name',
  illustration VARCHAR(4000) NOT NULL DEFAULT '' COMMENT '产品说明',
  url VARCHAR(4000) NOT NULL DEFAULT '' COMMENT 'url',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX udx_sku_brand_sku_type (brand_code,goods_code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE goods_category_mapping (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  category_code VARCHAR(45) NOT NULL COMMENT '品类',
  goods_code VARCHAR(45) NOT NULL COMMENT 'sku',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX udx_goods_category_mapping_goods_category_type (goods_code,category_code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE sku (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  brand_code VARCHAR(45) NOT NULL COMMENT 'brand',
  goods_code VARCHAR(45) NOT NULL COMMENT 'sku',
  type VARCHAR(45) NOT NULL COMMENT '类别',
  sku_code VARCHAR(45) NOT NULL COMMENT 'sku',
  sku_name VARCHAR(45) NOT NULL COMMENT 'name',
  sku_by_name VARCHAR(45) NOT NULL COMMENT '别名',
  url VARCHAR(4000) NOT NULL DEFAULT '' COMMENT 'url',
  extension JSON DEFAULT NULL COMMENT '扩展信息',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  INDEX idx_sku_goods_code (goods_code),
  INDEX idx_sku_sku_code (sku_code),
  UNIQUE INDEX idx_sku_brand_sku_type (brand_code,sku_code,type)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE attachment (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  biz_code VARCHAR(45) NOT NULL COMMENT '业务类型 + 唯一键',
  type VARCHAR(45) NOT NULL COMMENT  '附件类型，img',
  url VARCHAR(4000) NOT NULL COMMENT 'url',
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  INDEX idx_attachment_biz_code (biz_code)
)ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8mb4;