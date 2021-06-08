insert into user (version, username, password, isadmin) values (0, 'admin', '$2a$10$doLe4LDxURaxwTq7wE95b..a67HH5qRA0cG7mfK9ZCUEbL7FCvmOi', 1);
insert into user (version, username, password, isadmin) values (0, 'user', '$2a$10$pXgo7lFMQ7M5FMRTeS8xGuNvPFOI9uMeYddZG1a.kyMh9KNnt3ndS', 0);


insert into gate (version, number, flight_id, availablefrom, availableto) values (0, 'A100', null, null, null);
insert into gate (version, number, flight_id, availablefrom, availableto) values (0, 'A200', null, null, null);
insert into gate (version, number, flight_id, availablefrom, availableto) values (0, 'B100', null, null, null);
insert into gate (version, number, flight_id, availablefrom, availableto) values (0, 'B200', null, null, null);


insert into flight (version, number) values (0, 'AB-1234');
insert into flight (version, number) values (0, 'BC-5678');
insert into flight (version, number) values (0, 'DE-9012');
insert into flight (version, number) values (0, 'FG-3456');