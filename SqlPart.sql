DROP DATABASE dryclean;
CREATE DATABASE DryClean;
USE DryClean;


CREATE TABLE Customer(
	CID INT PRIMARY KEY,
    CName VARCHAR(30),
    CPhone_Number INT,
    CEmail VARCHAR(30),
    CAddress VARCHAR(30)
);

CREATE TABLE Item(
	IID INT PRIMARY KEY,
    IType VARCHAR(15),
    ICost INT,
    CID INT,
    FOREIGN KEY (CID) REFERENCES Customer(CID)
);

CREATE TABLE Store(
	SID INT PRIMARY KEY,
    SName VARCHAR(30),
    SPhone_Number INT,
    SAddress VARCHAR(30)
);


CREATE TABLE Employee(
	EID INT PRIMARY KEY,
    EName VARCHAR(30),
    EGender VARCHAR(10),
    EPhone_Number INT,
    EEmail VARCHAR(30),
    EAddress VARCHAR(30),
    EType VARCHAR(30),
    ESalary INT,
    EPassword INT,
    SID INT,
    FOREIGN KEY (SID) REFERENCES Store(SID)
);

CREATE TABLE Item_Employee(
  EID INT,
  IID INT,
  FOREIGN KEY (IID) REFERENCES Item(IID),
  FOREIGN KEY (EID) REFERENCES Employee(EID),
  PRIMARY KEY (EID, IID)
);

CREATE TABLE Supplier(
	SuID INT PRIMARY KEY,
    SuName VARCHAR(30),
    SuPhone_Number INT,
    SuAddress VARCHAR(30),
    SuEmail VARCHAR(30)
);

CREATE TABLE Equipment(
	EqID INT PRIMARY KEY,
    EqName VARCHAR(30),
    EqQuality VARCHAR(30),
    EqCost INT,
    SuID INT,
	FOREIGN KEY (SuID) REFERENCES Supplier(SuID)
);

CREATE TABLE Equipment_Store(
	EqID INT,
	SID INT,
	FOREIGN KEY (SID) REFERENCES Store(SID),
	FOREIGN KEY (EqID) REFERENCES Equipment(EqID),
	PRIMARY KEY (EqID, SID)
);

insert into Customer values
(1,"Musab Masalmah",0595811938,"musab@gmail.com","Sinjel-Ramallah"),
(2,"Mohammad Dallash",0595888888,"mohammad1@gmail.com","Ramallah"),
(3,"Mohammad Shrateh",0595888777,"mohammad2@gmail.com","Ramallah");


insert into Item values (1,"Mu",20,1);

insert into Supplier values (1,"Mu",20,"ss","sss");

insert into Equipment values (1,"Mu","good",100,1);

select * from Customer;
select * from Employee;

select * 
from Item i
where i.IType = 'm'

