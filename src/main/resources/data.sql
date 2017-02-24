
insert into insurer(id, name, price) values(1, 'insurer1', '300.00');
insert into insurer(id, name, price) values(2, 'insurer2', '298.75');
insert into insurer(id, name, price) values(3, 'insurer3', '297.26');
insert into insurer(id, name, price) values(4, 'insurer4', '299.34');

insert into policy(id, criteria, stringvalue, intvalue) values(1, 'postcode','2000,2001', -1);
insert into policy(id, criteria, stringvalue, intvalue) values(2, 'postcode','2048,2075,2123,3046', -1);
insert into policy(id, criteria, stringvalue, intvalue) values(3, 'postcode','2021,2034,2123,3046', -1);
insert into policy(id, criteria, stringvalue, intvalue) values(4, 'occupation','Butcher,Plumber', -1);
insert into policy(id, criteria, stringvalue, intvalue) values(5, 'occupation','Butcher', -1);
insert into policy(id, criteria, stringvalue, intvalue) values(6, 'turnover',null, 200000);
insert into policy(id, criteria, stringvalue, intvalue) values(7, 'turnover',null, 400000);
insert into policy(id, criteria, stringvalue, intvalue) values(8, 'postcode','2000', -1);

insert into insurer_policy(id, policy_id, insurer_id) values(1, 1, 2);
insert into insurer_policy(id, policy_id, insurer_id) values(2, 5, 2);
insert into insurer_policy(id, policy_id, insurer_id) values(4, 4, 3);
insert into insurer_policy(id, policy_id, insurer_id) values(5, 8, 3);
insert into insurer_policy(id, policy_id, insurer_id) values(6, 6, 3);
insert into insurer_policy(id, policy_id, insurer_id) values(7, 7, 4);
