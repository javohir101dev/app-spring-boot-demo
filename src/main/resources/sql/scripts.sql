-- create database transfer;

-- Adding USER
insert into users (address, birthday, first_name, last_name, username, gender, is_active)
VALUES ('Tashkent', '2000-01-25', 'Javohir', 'Uralov', 'java', true, true);

-- Adding Cards
insert into cards(balance, currency, expiry, is_active, number, user_id) VALUES
(10000000, 'UZS', '11/26', true, '8600000000000001', 1 );
insert into cards(balance, currency, expiry, is_active, number, user_id) VALUES
(20000000, 'UZS', '11/26', true, '8600000000000002', 1 );

-- Insert OPERATION_CODES
insert into operation_codes(name, exchange_currency, is_upper) values ('UZS2UZS', 0, null);
insert into operation_codes(name, exchange_currency, is_upper) values ('UZS2USD', 10700, false);
insert into operation_codes(name, exchange_currency, is_upper) values ('UZS2EUR', 10850, false);

insert into operation_codes(name, exchange_currency, is_upper) values ('USD2UZS', 10700, true);
insert into operation_codes(name, exchange_currency, is_upper) values ('USD2USD', 0, null);
insert into operation_codes(name, exchange_currency, is_upper) values ('USD2EUR', 150, false);

insert into operation_codes(name, exchange_currency, is_upper) values ('EUR2UZS', 10850, true);
insert into operation_codes(name, exchange_currency, is_upper) values ('EUR2USD', 150, true);
insert into operation_codes(name, exchange_currency, is_upper) values ('EUR2EUR', 0, null);

-- Inserting LIMITS
insert into limits(operation_code, currency, min_limit, max_limit) VALUES ('UZS2UZS', 'UZS', 100000, 1000000000);
insert into limits(operation_code, currency, min_limit, max_limit) VALUES ('UZS2USD', 'UZS', 1070000, 1000000000);
insert into limits(operation_code, currency, min_limit, max_limit) VALUES ('UZS2EUR', 'UZS', 1085000, 1000000000);

insert into limits(operation_code, currency, min_limit, max_limit) VALUES ('USD2UZS', 'USD', 100, 100000);
insert into limits(operation_code, currency, min_limit, max_limit) VALUES ('USD2USD', 'USD', 100, 100000);
insert into limits(operation_code, currency, min_limit, max_limit) VALUES ('USD2EUR', 'USD', 100, 100000);

insert into limits(operation_code, currency, min_limit, max_limit) VALUES ('EUR2UZS', 'EUR', 100, 100000);
insert into limits(operation_code, currency, min_limit, max_limit) VALUES ('EUR2USD', 'EUR', 100, 100000);
insert into limits(operation_code, currency, min_limit, max_limit) VALUES ('EUR2EUR', 'EUR', 100, 100000);

-- Inserting Commissions
insert into commissions(operation_code, is_upper, rate) values ('UZS2UZS', true, 5);
insert into commissions(operation_code, is_upper, rate) values ('UZS2USD', true, 5);
insert into commissions(operation_code, is_upper, rate) values ('UZS2EUR', true, 5);

insert into commissions(operation_code, is_upper, rate) values ('USD2UZS', false, 5);
insert into commissions(operation_code, is_upper, rate) values ('USD2USD', true, 10);
insert into commissions(operation_code, is_upper, rate) values ('USD2EUR', true, 10);

insert into commissions(operation_code, is_upper, rate) values ('EUR2UZS', false, 5);
insert into commissions(operation_code, is_upper, rate) values ('EUR2USD', true, 10);
insert into commissions(operation_code, is_upper, rate) values ('EUR2EUR', true, 10);





