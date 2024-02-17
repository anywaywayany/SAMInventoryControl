-- insert into user_roles (user_id, roles) values ('1', 'MANAGEMENT');
-- insert into user_roles (user_id, roles) values ('2', 'STORAGEADMIN');
-- insert into user_roles (user_id, roles) values ('3', 'FIELDSERVICETECHNICIAN');
-- insert into user_roles (user_id, roles) values ('4', 'ORDERFULLFILLMENT');
-- insert into user_roles (user_id, roles) values ('5', 'PROJECTMANAGER');
-- insert into user_roles (user_id, roles) values ('6', 'SUPPORT')

-- insert into user_storage(id, role, created_At, last_login, activated, email, hashed_password, user_name, first_name, last_name, phone)
-- values(1, 'Storageadmin', '2019-05-26', '2023-05-26', true, 'agnes.toccafondi@viipo.ae','$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe',
--         'management', 'Eula', 'Lane', '(762) 526-5961')

-- insert into user_storage(id, hashed_password, user_name, role) values (1, '$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe','management', 'Management' );


-- USERS --

insert into users(id, created_At, last_login, first_name, last_name, user_name, email,
                  hashed_password, phone, role)
values (1, date '2019-05-26', date '2023-05-26', 'Management', 'Lane', 'management',
        'management.toccafondi@viipo.ae',
        '$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.',
        '(762) 444526-5961', 'MANAGMENT');

insert into users(id, created_At, last_login, first_name, last_name, user_name, email,
                  hashed_password, phone, role)
values (2, date '2019-05-26', date '2023-05-26', 'Peter', 'Hardworker', 'PetHar',
        'peter.hardworker@gmail.to',
        '$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.',
        '(762) 444526-5961', 'STORAGEADMIN');

insert into users(id, created_At, last_login, first_name, last_name, user_name, email,
                  hashed_password, phone, role)
values (3, date '2019-05-26', date '2023-05-26', 'Susan', 'Installer', 'SusIns',
        'susan.installer@gmail.to',
        '$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.',
        '(762) 444526-5961', 'FIELDSERVICETECHNICIAN');

insert into users(id, created_At, last_login, first_name, last_name, user_name, email,
                  hashed_password, phone, role)
values (4, date '2019-05-26', date '2023-05-26', 'Max', 'Technician', 'MaxTec',
        'max.technician@gmail.to',
        '$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.',
        '(762) 444526-5961', 'FIELDSERVICETECHNICIAN');


-- insert into user_storage(id, created_At,last_login,first_name,last_name,user_name,email,hashed_password,phone,role) values(2,date '2019-05-26',date '2023-05-26','Eula_storageadmin','Lane','storageadmin','storageadmin.toccafondi@viipo.ae','$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.','(762) 526-5961','STORAGEADMIN');
-- insert into user_storage(id, created_At,last_login,first_name,last_name,user_name,email,hashed_password,phone,role) values(4,date '2019-05-26',date '2023-05-26','Eula_fieldservicetechnician','Lane','fieldservicetechnician','fieldservicetechnician.toccafondi@viipo.ae','$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.','(762) 526-5961','FIELDSERVICETECHNICIAN');
-- insert into user_storage(id, created_At,last_login,first_name,last_name,user_name,email,hashed_password,phone,role) values(5,date '2019-05-26',date '2023-05-26','Eula_orderfullfillment','Lane','orderfullfillment','orderfullfillment.toccafondi@viipo.ae','$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.','(762) 526-5961','ORDERFULLFILLMENT');
-- insert into user_storage(id, created_At,last_login,first_name,last_name,user_name,email,hashed_password,phone,role) values(6,date '2019-05-26',date '2023-05-26','Eula_projectmanager','Lane','projectmanager','projectmanager.toccafondi@viipo.ae','$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.','(762) 526-5961','ROJECTMANAGER');
-- insert into user_storage(id, created_At,last_login,first_name,last_name,user_name,email,hashed_password,phone,role) values(7,date '2019-05-26',date '2023-05-26','Eula_support','Lane','support','support.toccafondi@viipo.ae','$2a$10$jpLNVNeA7Ar/ZQ2DKbKCm.MuT2ESe.Qop96jipKMq7RaUgCoQedV.','(762) 526-5961','SUPPORT');


-- insert into user_storage(id, role, activated, email, hashed_password, user_name, first_name, last_name,phone) values (3, 'Fieldservicetechnician', true, 'agnes.toccafondi@viipo.ae', '$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe','management','Eula','Lane','(762) 526-5961' );
-- insert into user_storage(id, role, created_At, last_login, activated, email, hashed_password, user_name, first_name, last_name,phone) values (4, 'Orderfullfillment', '2019-05-26', '2023-05-26', true, 'agnes.toccafondi@viipo.ae', '$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe','management','Eula','Lane','(762) 526-5961' )
-- insert into user_storage(id, role, created_At, last_login, activated, email, hashed_password, user_name, first_name, last_name,phone) values (5, 'Projectmanager', '2019-05-26', '2023-05-26', true, 'agnes.toccafondi@viipo.ae', '$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe','management','Eula','Lane','(762) 526-5961' )
-- insert into user_storage(id, role, created_At, last_login, activated, email, hashed_password, user_name, first_name, last_name,phone) values (5, 'Support', '2019-05-26', '2023-05-26', true, 'agnes.toccafondi@viipo.ae', '$2a$10$xdbKoM48VySZqVSU/cSlVeJn0Z04XCZ7KZBjUBC00eKo5uLswyOpe','management','Eula','Lane','(762) 526-5961' )
--

-- Storages --

insert into storages(id, storage_name, street, house_no, door_no, zip_code, city)
values (1, 'Hauptlager', 'Spengergasse', 10, 20, 1050, 'Wien');
insert into storages(id, storage_name, street, house_no, door_no, zip_code, city)
values (2, 'Kunde', 'Lagert beim Kunden', 1, 1, 0, 'Österreich');
insert into storages(id, storage_name, street, house_no, door_no, zip_code, city)
values (3, 'Lager STMK', 'Herrengasse', 100, 1, 8020, 'Graz');
insert into storages(id, storage_name, street, house_no, door_no, zip_code, city)
values (4, 'Lager KTN', 'Lindwurmstra?e', 4, 1, 9020, 'Klagenfurt');
insert into storages(id, storage_name, street, house_no, door_no, zip_code, city)
values (5, 'Lager VBG', 'St. Anna Straße', 11, 1, 6900, 'Bregenz');
insert into storages(id, storage_name, street, house_no, door_no, zip_code, city)
values (6, 'Lager SBG', 'Ignaz Rieder Kai', 7, 1, 5020, 'Salzburg');
insert into storages(id, storage_name, street, house_no, door_no, zip_code, city)
values (7, 'Lager NOE', 'Heidenheimer Straße', 24, 1, 3100, 'St. Poelten');


-- Producers --

insert into producers (id, producer_name, short_name)
values (1, 'Cisco', 'Cisco');
insert into producers (id, producer_name, short_name)
values (2, 'HP', 'HP');
insert into producers (id, producer_name, short_name)
values (3, 'Snom', 'Snom');
insert into producers (id, producer_name, short_name)
values (4, 'Aruba', 'Aruba');

-- CPE´s SFP´s Supplies --

--Cisco
insert into cpes (id, fk_producer, macaddress, serialnumber, type)
values (1, 1, '04-F4-2C-E8-6B-CB', 'WAE9805FA1TO', 'ROUTER');
insert into cpes (id, fk_producer, macaddress, serialnumber, type)
values (2, 1, 'C3-64-5F-32-D4-26', 'NDZ2081U82ZB', 'ROUTER');
insert into cpes (id, fk_producer, macaddress, serialnumber, type)
values (3, 1, '1B-14-14-A7-4C-DA', 'NDZ2083U82ZB', 'ROUTER');
insert into cpes (id, fk_producer, macaddress, serialnumber, type)
values (4, 1, '3B-7B-98-3D-B0-38', 'NDZ2087U82AB', 'ROUTER');
insert into cpes (id, fk_producer, macaddress, serialnumber, type)
values (5, 1, 'D7-79-85-5D-27-50', 'NDZ2087US2ZB', 'ROUTER');


-- Aruba
insert into cpes (id, fk_producer, macaddress, serialnumber, type)
values (6, 4, '3C-E4-69-D6-92-2F', 'AJZ2087U82ZB', 'SWITCH');
-- Snom
insert into CPES (id, fk_producer, macaddress, serialnumber, type)
values (7, 3, '38-E2-64-85-84-3E', 'ASZ2137U82ZB', 'IP_PHONE');
insert into cpes (id, fk_producer, macaddress, serialnumber, type)
values (8, 3, '4B-0F-97-EE-A9-98', 'AJZ2023U82ZB', 'IP_PHONE');
insert into CPES (id, fk_producer, macaddress, serialnumber, type)
values (9, 3, 'FC-44-F7-6A-86-90', 'ASZ2087U000B', 'IP_PHONE');
-- HP
insert into CPES (id, fk_producer, macaddress, serialnumber, type)
values (10, 2, '00-09-48-0A-58-2A', 'ASZ2087U82ZB', 'SWITCH');

insert into sfps (id, fk_producer, serialnumber, nic_speed, type_sfp, wavelength, type)
values (1, 1, 'ACW260719G3', 10, 2, '1310 nm', 'SFP');
insert into sfps (id, fk_producer, serialnumber, nic_speed, type_sfp, wavelength, type)
values (2, 1, 'ACW260719G3', 1, 2, '550 nm', 'SFP');

insert into supplies (id, description, amount)
values (1, 'Patch Kabel elektr. Cat5e 5 Meter', 10);
insert into supplies (id, description, amount)
values (2, 'Patch Kabel opt. SM 3 Meter LC/LC', 5);
insert into supplies (id, description, amount)
values (3, 'TST Bonding 1,5 Meter ', 10);
insert into supplies (id, description, amount)
values (4, 'Patch Kabel opt. SM 2 Meter LC/E2000', 5);
insert into supplies (id, description, amount)
values (5, 'Patch Kabel elektr. Cat5e 2 Meter', 10);
insert into supplies (id, description, amount)
values (6, 'Patch Kabel opt. MM 1,5 Meter LC/LC', 5);

-- Cisco
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (1, 'C927');
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (2, 'C1111-4p');
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (3, 'ASR920-12SZ-IM');
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (4, 'C897VAB');
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (5, 'C1117-4p');

--HP
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (6, 'HP J9050A');
-- Aruba
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (7, 'Aruba 2530 8-PoE+');
--Snom
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (8, 'M300');
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (9, 'D865');
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (10, 'D862');
-- cisco SFP
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (11, 'GLC-LH-SM');
insert into OBJECT_TYPES (ID, OBJECT_TYPE_NAME)
values (12, 'LC-SX-MM');


-- Storageobjects at User Management
insert into STORAGE_OBJECTS (ID, FK_CPE, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (1, 1, 1, 1);
insert into STORAGE_OBJECTS (ID, FK_CPE, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (2, 2, 2, 1);
insert into STORAGE_OBJECTS (ID, FK_CPE, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (3, 3, 3, 1);
insert into STORAGE_OBJECTS (ID, FK_CPE, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (4, 4, 4, 1);
insert into STORAGE_OBJECTS (ID, FK_CPE, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (5, 5, 5, 1);
insert into STORAGE_OBJECTS (ID, FK_CPE, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (6, 10, 6, 1);
insert into STORAGE_OBJECTS (ID, FK_CPE, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (7, 6, 7, 1);
insert into STORAGE_OBJECTS (ID, FK_CPE, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (8, 7, 8, 1);
insert into STORAGE_OBJECTS (ID, FK_CPE, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (9, 8, 9, 1);
insert into STORAGE_OBJECTS (ID, FK_CPE, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (10, 9, 10, 1);
insert into STORAGE_OBJECTS (ID, FK_SFP, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (11, 1, 11, 1);
insert into STORAGE_OBJECTS (ID, FK_SFP, FK_OBJECT_TYPE, STORAGE_OBJECTS.FK_STORAED_AT_USER)
values (12, 1, 12, 1);

--MM insert von freien Lagerobjekten

-- insert Cisco Router
insert into cpes (created_at, fk_producer, id, last_modified_at, macaddress, serialnumber, type) values (TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'), 1, 20, TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'),'04-F4-2C-E8-6B-CB','aabw34by65', 'ROUTER');
insert into cpes (created_at, fk_producer, id, last_modified_at, macaddress, serialnumber, type) values (TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'), 1, 21, TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'),'C3-64-5F-32-D4-26','xyz634aa45', 'ROUTER');
insert into cpes (created_at, fk_producer, id, last_modified_at, macaddress, serialnumber, type) values (TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'), 1, 22, TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'),'D3-B0-58-6C-A6-B5','grba6432sg', 'ROUTER');
insert into cpes (created_at, fk_producer, id, last_modified_at, macaddress, serialnumber, type) values (TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'), 1, 23, TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'),'47-55-C6-9C-B6-4F','abrac56313', 'ROUTER');
insert into cpes (created_at, fk_producer, id, last_modified_at, macaddress, serialnumber, type) values (TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'), 1, 24, TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'),'3B-7B-98-3D-B0-38','cd35yv532b', 'ROUTER');
-- insert HP Switch
insert into cpes (created_at, fk_producer, id, last_modified_at, macaddress, serialnumber, type) values (TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'), 2, 25, TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'),'3B-7B-98-3D-B0-38','zybw34by65', 'SWITCH');
insert into cpes (created_at, fk_producer, id, last_modified_at, macaddress, serialnumber, type) values (TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'), 2, 26, TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'),'D7-79-85-5D-27-50','gh5634aa45', 'SWITCH');
-- insert SNOM IP Phones
insert into cpes (created_at, fk_producer, id, last_modified_at, macaddress, serialnumber, type) values (TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'), 3, 27, TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'),'A0-3C-24-7F-DA-9D','thea6432sg', 'IP_PHONE');
insert into cpes (created_at, fk_producer, id, last_modified_at, macaddress, serialnumber, type) values (TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'), 3, 28, TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'),'75-BA-5F-FB-F8-4C','gls5c56313', 'IP_PHONE');
-- insert Aruba Switch
insert into cpes (created_at, fk_producer, id, last_modified_at, macaddress, serialnumber, type) values (TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'), 4, 29, TO_DATE('2024-02-10 16:48:02', 'YYYY-MM-DD HH24:MI:SS'),'00-09-48-0A-58-2A','9skwyv532b', 'SWITCH');

-- insert Storageobjects C927/C111-4P/ASR
insert into storage_objects (connection_no, processed, project_device, created_at, fk_cpe, fk_object_type, fk_reservation, fk_sfp, fk_storaed_at_user, fk_storage, fk_supply, id,last_modified_at, remark, status) values (null, null, 0, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 20, 1, null, null, null, 1, null, 100, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 'IOS up to date', null);
insert into storage_objects (connection_no, processed, project_device, created_at, fk_cpe, fk_object_type, fk_reservation, fk_sfp, fk_storaed_at_user, fk_storage, fk_supply, id,last_modified_at, remark, status) values (null, null, 0, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 21, 2, null, null, null, 1, null, 101, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 'PoE capable', null);
insert into storage_objects (connection_no, processed, project_device, created_at, fk_cpe, fk_object_type, fk_reservation, fk_sfp, fk_storaed_at_user, fk_storage, fk_supply, id,last_modified_at, remark, status) values (null, null, 0, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 22, 1, null, null, null, 1, null, 102, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 'old IOS', null);
insert into storage_objects (connection_no, processed, project_device, created_at, fk_cpe, fk_object_type, fk_reservation, fk_sfp, fk_storaed_at_user, fk_storage, fk_supply, id,last_modified_at, remark, status) values (null, null, 0, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 23, 3, null, null, null, 1, null, 103, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 'check ports', null);
insert into storage_objects (connection_no, processed, project_device, created_at, fk_cpe, fk_object_type, fk_reservation, fk_sfp, fk_storaed_at_user, fk_storage, fk_supply, id,last_modified_at, remark, status) values (null, null, 0, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 24, 1, null, null, null, 1, null, 104, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 'dirty', null);
-- insert Storagobject HP SWitch
insert into storage_objects (connection_no, processed, project_device, created_at, fk_cpe, fk_object_type, fk_reservation, fk_sfp, fk_storaed_at_user, fk_storage, fk_supply, id,last_modified_at, remark, status) values (null, null, 0, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 25, 6, null, null, null, 1, null, 105, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 'IOS up to date', null);
insert into storage_objects (connection_no, processed, project_device, created_at, fk_cpe, fk_object_type, fk_reservation, fk_sfp, fk_storaed_at_user, fk_storage, fk_supply, id,last_modified_at, remark, status) values (null, null, 0, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 26, 6, null, null, null, 1, null, 106, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 'PSU missing', null);
-- insert Storageobjects IP Phones
insert into storage_objects (connection_no, processed, project_device, created_at, fk_cpe, fk_object_type, fk_reservation, fk_sfp, fk_storaed_at_user, fk_storage, fk_supply, id,last_modified_at, remark, status) values (null, null, 0, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 27, 8, null, null, null, 1, null, 107, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 'Package broken', null);
insert into storage_objects (connection_no, processed, project_device, created_at, fk_cpe, fk_object_type, fk_reservation, fk_sfp, fk_storaed_at_user, fk_storage, fk_supply, id,last_modified_at, remark, status) values (null, null, 0, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 28, 9, null, null, null, 1, null, 108, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 'check ports', null);
-- insert Storageobject ARUBA Switch
insert into storage_objects (connection_no, processed, project_device, created_at, fk_cpe, fk_object_type, fk_reservation, fk_sfp, fk_storaed_at_user, fk_storage, fk_supply, id,last_modified_at, remark, status) values (null, null, 0, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 29, 7, null, null, null, 1, null, 109, TO_DATE('2024-02-11 12:34:56', 'YYYY-MM-DD HH24:MI:SS'), 'refurbished device', null);



--delete from RESERVATIONS where ID = 1;
insert into reservations (id, reserved_at, fk_user, last_modified, completed, connection_no,
                          reserved_description)
values (1, TIMESTAMP ' 2023-12-25 11:16:47.672127', 1, TIMESTAMP ' 2023-12-25 11:16:47.677192', 0,
        12335134,
        'Kunde Bike Service Speedy');
insert into reservations (id, reserved_at, fk_user, last_modified, completed, connection_no, reserved_description)
values (2, TIMESTAMP ' 2023-12-25 11:16:47.678165', 1, TIMESTAMP ' 2023-12-25 11:16:47.678504', 0, 123413521,
        'Kunde benötigt PoE fähiges Device');

-- insert into object_types(id, objectType_name)
-- values (1, 'IMFC433');
-- insert into object_types(id, objectType_name)
-- values (2, 'EQVN997');

-- insert into storage_objects (id, fk_reservation, fk_CPE, fk_supply,
--                              fk_storaed_at_user, fk_storage, remark, status, project_device)
-- values (1, 1, 1 ,1 , 3, 1, 'pellentesque quis', 'AVAILABLE', true);
-- --insert into storage_objects (id, object_type, project_device, remark, fk_storage_object, fk_storaed_at_user, status, fk_reservation, supply, fk_cpe, SFP) values (2, 2, 0, 'pede malesuada in imperdiet et', 6, null, 'AVAILABLE', 2, null, '1', null);
