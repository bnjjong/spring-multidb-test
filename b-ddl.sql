CREATE TABLE campaign_budget_balance (
                                         id              BIGSERIAL      PRIMARY KEY,
                                         campaign_id     BIGINT         NOT NULL,
                                         initial_budget  NUMERIC(19,4)  NOT NULL,
                                         balance         NUMERIC(19,4)  NOT NULL
);
COMMENT ON TABLE campaign_budget_balance IS '캠페인 예산 잔액 정보';

INSERT INTO campaign_budget_balance (campaign_id, initial_budget, balance) VALUES
                                                                               (1, 1000.0000, 1000.0000),
                                                                               (2, 1500.5000, 1500.5000),
                                                                               (3, 2500.7500, 2500.7500),
                                                                               (4,  500.1234,  500.1234),
                                                                               (5, 9999.9999, 9999.9999);
