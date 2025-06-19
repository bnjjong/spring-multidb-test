CREATE TABLE campaign_budget_balance (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         campaign_id BIGINT              NOT NULL,
                                         initial_budget DECIMAL(19,4)    NOT NULL,
                                         balance        DECIMAL(19,4)    NOT NULL
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT = '캠페인 예산 잔액 정보';
