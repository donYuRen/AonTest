drop table if exists insurer_policy;
drop table if exists insurer;
drop table if exists policy;
create table insurer (
       id bigint GENERATED by default as identity ,
       name varchar(255) not null,
       price decimal(10,2) not null
);

create table policy (
       id  bigint GENERATED by default as identity,
       criteria varchar(255) not null,
       stringvalue varchar(255),
       intvalue int
);       
       
create table insurer_policy (
      id bigint GENERATED by default as identity,
      policy_id bigint not null,
      insurer_id bigint not null,
      CONSTRAINT pb_fk_policy_id FOREIGN KEY (policy_id) REFERENCES policy (id),
      constraint pb_fk_insurer_id FOREIGN KEY (insurer_id) REFERENCES insurer (id)
);
