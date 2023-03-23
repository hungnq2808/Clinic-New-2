create database Clinic_systems
use Clinic_systems
use PMS
go

drop database Clinic_systems
--1



--2
create table dbo.locations(
    id int IDENTITY(1,1) primary key,
    name varchar(50) not null unique,
    location nvarchar(100)
)

create table dbo.medicines(
    id int IDENTITY(1,1) primary key,
    name varchar(50) not null unique,
    price money not null
)



--3

create table dbo.specializations(
    id int IDENTITY(1,1) primary key,
    name varchar(50) not null unique
)

create table dbo.user_account(
	id int IDENTITY(1,1) primary key,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(254) not null,
    password varchar(60) not null,
    age tinyint,
    phone varchar(11) not null unique,
    gender bit,
    address nvarchar(100),
    dob date,
	location_id int foreign key references dbo.locations (id),
	specialization_id int foreign key references specializations (id),
)


create table dbo.permision (
	id int IDENTITY(1,1) primary key,
	permision nvarchar,
)

create table dbo.account_permision(
	id int IDENTITY (1,1) primary key,
	account_id int foreign key references dbo.user_account(id),
	permision_id int foreign key references dbo.permision(id),
)

create table dbo.schedule(
	id int IDENTITY(1,1) primary key,
	time_schedule date,
)

create table dbo.slot (
	id int IDENTITY(1,1) primary key,
	date_schedule time
)

create table dbo.schedule_detail (
	id int IDENTITY(1,1) primary key,
	schedule_id int foreign key references dbo.schedule (id),
	slot_id int foreign key references dbo.slot (id)
)

--4

create table dbo.patients_account(
	id int IDENTITY(1,1) primary key,
	patient_username nvarchar(100) not null,
	patient_phone_number nvarchar(13) not null,
	patient_email nvarchar(100)
)

create table dbo.patients_details(
    id int IDENTITY(1,1) primary key,
	patient_firstname nvarchar(100),
	patient_lastname nvarchar(100),
    patient_age int,
	patient_gender bit,
	patient_address varchar(100),
	symptom nvarchar(1000),
	patiens_account_id int foreign key references patients_account (id)
)

create table dbo.services(
    id    int IDENTITY(1,1) primary key,
    name  varchar(50) not null unique,
    price money not null, 
	status bit
)


--5

create table dbo.time_table (
	id int IDENTITY(1,1) primary key,
	schedule_detail_id int foreign key references dbo.schedule_detail (id),
	doctor_details_id int foreign key references dbo.user_account(id),
	time_status bit
)

create table dbo.appointments(
    id int IDENTITY(1,1) primary key,
    patient_id int,
    foreign key (patient_id) references dbo.patients_details (id),
    user_detail_id int,
    foreign key (user_detail_id) references dbo.user_account (id),
    service_id int,
    foreign key (service_id) references dbo.services (id),
    date date,
    time time,
    status bit
)


--6

create table prescriptions(
    id int IDENTITY(1,1) primary key,
    appointment_id int,
    foreign key (appointment_id) references dbo.appointments(id),
    medicine_id int,
    foreign key (medicine_id) references dbo.medicines (id),
    quantity int,
    dosage varchar(50),
    duration varchar(50)
)




--thêm lịch cho bác sĩ (schedule)


go