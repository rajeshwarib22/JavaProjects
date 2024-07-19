drop database if exists  BOL;
create database BOL;
use BOL;


create table account_registry(
AccountNumber varchar(50),
LastName varchar(50),
FirstName varchar(50),
TaxIDNumber varchar(50),
constraint pk_an primary key(AccountNumber)
);

desc account_registry;

create table account_checking(
AccountNumber varchar(50),
CheckingBalance varchar(50),
MinCheckingBalance decimal(10,2) generated always as (CheckingBalance * 0.25) stored,
MaxCheckingWithdrawal decimal(10,2) generated always as (CheckingBalance * 0.40) stored,
constraint pk_acan primary key(AccountNumber),
constraint fk_acan foreign key(AccountNumber) references account_registry(AccountNumber)
);

desc account_checking;

create table account_savings(
AccountNumber varchar(50),
SavingsBalance varchar(50),
MaxSavingWithdrawal decimal(10,2) generated always as (SavingsBalance * 0.55) stored,
MinSavingBalance decimal(10,2) generated always as (SavingsBalance * 0.20) stored,
SavingsInterestRate decimal(10,2),
constraint pk_asan primary key(AccountNumber),
constraint fk_asan foreign key(AccountNumber) references account_registry(AccountNumber)
);

desc account_savings;
