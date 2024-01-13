/*
	--- COMPLETARE TABEL Tests
*/
insert into Tests(Name)
VALUES
	('Groups_DIV_1000'),
	('Events_DIV_1000'),
	('GroupMemberships_DIV_1000');

/*
	--- COMPLETARE TABEL Tables ---
*/
insert into Tables(Name)
VALUES
	('GroupMemberships'),
	('Events'),
	('Groups'),
	('Users');


/*
	--- CREARE VIEW-URI ---
*/

CREATE OR ALTER VIEW vw_1_table
AS
	select * 
	from Groups
GO

CREATE VIEW vw_2_tables
AS
	select G.name as GroupName, E.name as EventName
	FROM Groups as G
	INNER JOIN Events as E ON G.gid = E.gid;
GO

CREATE OR ALTER VIEW vw_2_tables_group_by
AS
	select G.name AS GroupName, U.username as UserName, U.gender as Gender
	FROM Groups as G
	INNER JOIN GroupMemberships AS GM ON G.gid = GM.gid
	INNER JOIN Users as U ON GM.uid = U.uid
	GROUP BY G.name, G.gid, U.username, U.gender, U.uid;
GO

/*
	--- COMPLETARE TABEL Views ---
*/
insert into Views(Name)
VALUES
	('vw_1_table'),
	('vw_2_tables'),
	('vw_2_tables_group_by');

GO

/*
	--- COMPLETARE TABEL TestViews
*/
insert into TestViews
VALUES
	(1,1),
	(2,2),
	(3,3);

/*
	--- COMPLETARE TABEL TestTables
*/
insert into TestTables
VALUES
	--- Groups_DIV_1000
	(1,3,1000,3),
	(1,2,0,2),
	(1,1,0,1),
	--- Events_DIV_1000 
	(2,2,1000,2),
	(2,3,1,3),
	--- GroupMemberships_DIV_1000
	(3,1,1000,1),
	(3,3,1000,2),
	(3,4,1,3);

GO;
/*
	--- CREARE PROCEDURA GENERALA VIEW-URI ---
*/

CREATE OR ALTER PROCEDURE test_vw_general
@idTest INT
AS
BEGIN
	
	--- GET VIEW NAME ---
	DECLARE @viewName varchar(100) = 
		(SELECT V.Name FROM Views V
			INNER JOIN TestViews TV ON TV.ViewID = V.ViewID
			WHERE TV.TestID = @idTest);

	DECLARE @comanda varchar(200) = 
		N'SELECT * FROM ' + @viewName;

	EXECUTE(@comanda);
END



GO

/*
	--- CREARE PROCEDURA INSERT Groups ---
*/
CREATE OR ALTER PROCEDURE insert_groups_test
@noOfRows INT
AS
BEGIN

	SET NOCOUNT ON;
	DECLARE @nume varchar(100);
	DECLARE @descriere varchar(100);
	DECLARE @n INT = 0;
	DECLARE @lastId INT = 
		(SELECT ISNULL( max(gid), 0 ) + 1 FROM Groups)

	WHILE @n < @noOfRows
	BEGIN
		set @nume = 'GROUP_NAME_TEST' + CONVERT(VARCHAR(10), @lastId);
		set @descriere = 'GROUP_DESCRIPTION_TEST' + CONVERT(varchar(10), @lastId);
		INSERT INTO Groups(name, description)
		VALUES
			(@nume, @descriere);
		SET @lastId = @lastId + 1;
		SET @n = @n + 1;
	END

	PRINT '' + CONVERT(varchar(10), @noOfRows) + ' groups inserted!';

END

/*
	--- CREARE PROCEDURA INSERT Users ---
*/

CREATE OR ALTER PROCEDURE insert_users_test
@noOfRows INT
AS
BEGIN

	SET NOCOUNT ON;
	DECLARE @n INT = 0;
	DECLARE @lastId INT =
		(SELECT ISNULL( max(uid), 0 ) + 1 FROM Users);
	DECLARE @username varchar(50);
	DECLARE @email varchar(50);
	

	WHILE @n < @noOfRows
	BEGIN
		SET @username = 'USERNAME_TEST' + CONVERT(varchar(10), @lastId);
		SET @email = 'EMAIL_TEST' + CONVERT(varchar(10), @lastId);
		INSERT INTO Users(username, email, dateOfBirth, gender, lastLogin)
		VALUES
			(@username, @email, GETDATE(), 'none', CURRENT_TIMESTAMP);
		
		SET @n = @n + 1;
		SET @lastId = @lastId + 1;

	END;

		PRINT '' + CONVERT(varchar(10), @noOfRows) + ' users inserted!';

END;

/*
	--- CREARE PROCEDURA INSERT Events ---
*/
CREATE OR ALTER PROCEDURE insert_events_test
@noOfRows INT
AS
BEGIN

	SET NOCOUNT ON;
	DECLARE @n INT = 0;
	DECLARE @lastId INT =
		(SELECT ISNULL( max(eid), 0 ) + 1 FROM Events);
	DECLARE @name varchar(30);
	--- GET A FOREIGN KEY ---
	DECLARE @fk INT;
	SELECT @fk = MAX(gid) FROM Groups
	WHERE name LIKE 'GROUP_NAME_TEST%';

	WHILE @n < @noOfRows
	BEGIN
		SET @name = 'EVENT_TEST' + CONVERT(varchar(10), @lastId);
		INSERT INTO Events(gid, name, participants)
		VALUES
			(@fk, @name, 10);
		SET @n = @n + 1;
		SET @lastId = @lastId + 1;
	END;

	PRINT '' + CONVERT(varchar(10), @noOfRows) + ' events inserted!';


END;

/*
	--- CREARE PROCEDURA INSERT GroupMemberships ---
*/

CREATE OR ALTER PROCEDURE insert_groupmemberships_test
@noOfRows INT
AS
BEGIN

	SET NOCOUNT ON;
	DECLARE @n INT = 0;
	DECLARE @fkUser INT;
	DECLARE @fkGroup INT;
	--- GET USER Foreign Key ---
	SELECT @fkUser = MAX(uid) FROM Users
	WHERE username LIKE 'USER%';

	DECLARE cursorGroups CURSOR FAST_FORWARD FOR
	SELECT TOP (@noOfRows) gid from Groups 
	WHERE name LIKE 'GROUP%';

	OPEN cursorGroups;

	FETCH NEXT FROM cursorGroups INTO @fkGroup;

	WHILE @n < @noOfRows
	BEGIN
		
		INSERT INTO GroupMemberships
		VALUES
			(@fkUser, @fkGroup);
		SET @n = @n+1;
		FETCH NEXT FROM cursorGroups INTO @fkGroup;
	END;

	CLOSE cursorGroups;
	DEALLOCATE cursorGroups;

	PRINT '' + CONVERT(varchar(10), @noOfRows) + ' GroupMemberships inserted!';


END;


/*
	--- CREARE PROCEDURA DELETE Groups
*/
CREATE OR ALTER PROCEDURE delete_Groups_test
AS
BEGIN
	SET NOCOUNT ON;
	DELETE FROM Groups;

	PRINT '' + CONVERT(VARCHAR(10), @@ROWCOUNT) + 'groups deleted';
END;

/*
	--- CREARE PROCEDURA DELETE Users
*/
CREATE OR ALTER PROCEDURE delete_Users_test
AS
BEGIN
	SET NOCOUNT ON;
	DELETE FROM Users;

	PRINT '' + CONVERT(VARCHAR(10), @@ROWCOUNT) + 'users deleted';
END;

/*
	--- CREARE PROCEDURA DELETE Events
*/
CREATE OR ALTER PROCEDURE delete_Events_test
AS
BEGIN
	SET NOCOUNT ON;
	DELETE FROM Events;

	PRINT '' + CONVERT(VARCHAR(10), @@ROWCOUNT) + 'events deleted';
END;

/*
	--- CREARE PROCEDURA DELETE GroupMemberships
*/
CREATE OR ALTER PROCEDURE delete_GroupMemberships_test
AS
BEGIN
	SET NOCOUNT ON;
	DELETE FROM GroupMemberships;

	PRINT '' + CONVERT(VARCHAR(10), @@ROWCOUNT) + 'group memberships deleted';
END;

/*
	--- CREARE PROCEDURA GENERALA REMOVE ---
*/
CREATE OR ALTER PROCEDURE test_remove_general
@idTest INT
AS
BEGIN
	/*
	AM TEST ID;
	SUNT IN REMOVE;
	TREBUIE SA LUAM TestTables cu T.testID = Tables.TestID
	ORDERED BY Position CRESCATOR;
	CAT TIMP 
	*/

	DECLARE @testName varchar(100) = (SELECT T.Name FROM Tests T WHERE T.TestID = @idTest);
	DECLARE @tableName varchar(100);
	DECLARE @NoOfRows INT;
	DECLARE @procedura varchar(50);

	DECLARE cursorTable CURSOR FAST_FORWARD FOR
		SELECT T.Name, TT.NoOfRows FROM TestTables TT
		INNER JOIN Tables T ON T.TableID = TT.TableID
		WHERE TT.TestID = @idTest
		ORDER BY TT.Position;

	OPEN cursorTable;

	FETCH NEXT FROM cursorTable INTO @tableName, @NoOfRows;

	WHILE(@testName NOT LIKE '' + @tableName + '_DIV_' + CONVERT(NVARCHAR(10), @NoOfRows)) AND (@@FETCH_STATUS = 0)
	BEGIN

			SET @procedura = 'delete_' + @tableName + '_test';
			EXEC @procedura;
			FETCH NEXT FROM cursorTable INTO @tableName, @NoOfRows;
	END;

	SET @procedura = 'delete_' + @tableName + '_test';
	EXEC @procedura;

	CLOSE cursorTable;
	DEALLOCATE cursorTable;

END;




/*
	--- CREARE PROCEDURA GENERALA INSERT ---
*/
CREATE OR ALTER PROCEDURE test_insert_general
@idTest INT
AS
BEGIN

	DECLARE @testName varchar(100) = (SELECT T.Name FROM Tests T WHERE T.TestID = @idTest);
	DECLARE @tableName varchar(100);
	DECLARE @NoOfRows INT;
	DECLARE @procedura varchar(50);

	DECLARE cursorTable CURSOR FAST_FORWARD FOR
		SELECT T.Name, TT.NoOfRows FROM TestTables TT
		INNER JOIN Tables T ON T.TableID = TT.TableID
		WHERE TT.TestID = @idTest
		ORDER BY TT.Position DESC;

	OPEN cursorTable;

	FETCH NEXT FROM cursorTable INTO @tableName, @NoOfRows;

	WHILE(@testName NOT LIKE '' + @tableName + '_DIV_' + CONVERT(NVARCHAR(10), @NoOfRows)) AND (@@FETCH_STATUS = 0)
	BEGIN

			SET @procedura = 'insert_' + @tableName + '_test';
			EXEC @procedura @NoOfRows;
			FETCH NEXT FROM cursorTable INTO @tableName, @NoOfRows;
	END;

	SET @procedura = 'insert_' + @tableName + '_test';
	EXEC @procedura @NoOfRows;

	CLOSE cursorTable;
	DEALLOCATE cursorTable;

END;




/* 
	--- CREARE PROCEDURA GENERALA TEST ---
*/
CREATE OR ALTER PROCEDURE run_test
@idTest FLOAT
AS
BEGIN

--- VALIDARE idTest ---
	IF(@idTest <> CAST(@idTest AS INT))
		BEGIN
			PRINT 'INTRODUCETI UN INT!';
			RETURN 1;
		END;

	IF NOT EXISTS(SELECT * FROM Tests WHERE TestID=@idTest)
		BEGIN
			PRINT 'Id-ul de test nu exista!';
			RETURN 1;
		END

	DECLARE @ds DATETIME;
	DECLARE @di DATETIME;
	DECLARE @de DATETIME;

	SET @ds = GETDATE();

	EXEC test_remove_general @idTest;
	EXEC test_insert_general @idTest;

	SET @di = GETDATE();

	EXEC test_vw_general @idTest;

	SET @de = GETDATE();

	DECLARE @testName varchar(100) = 
		(SELECT T.Name FROM Tests T WHERE T.TestID = @idTest);

	declare @description varchar(100) =
		'TestRun' + convert(varchar(7), (select ISNULL( max(TestRunID), 0 ) + 1 from TestRuns)) + ' ' + @testName;


	INSERT INTO TestRuns VALUES(@description, @ds, @de);
	
	DECLARE @viewID INT =
		(SELECT V.ViewID FROM Views V
		INNER JOIN TestViews TV ON TV.ViewID = V.ViewID
		WHERE TV.TestID = @idTest);

	DECLARE @tableID INT =
		(SELECT TB.TableID FROM Tests T
		INNER JOIN TestTables TT ON TT.TestID = T.TestID
		INNER JOIN Tables TB ON TB.TableID = TT.TableID
		WHERE T.TestID = @idTest
		AND T.Name LIKE '' + TB.Name + '_DIV_' + CONVERT(varchar(10), TT.NoOfRows));



	DECLARE @testRunID INT = 
		(SELECT TOP 1 T.TestRunID FROM TestRuns T
		WHERE T.Description = @description
		ORDER BY T.TestRunID DESC);

	INSERT INTO TestRunTables VALUES (@testRunID, @tableID, @ds, @di);
	INSERT INTO TestRunViews VALUES (@testRunID, @viewID, @di, @de);

	PRINT 'Test Realizat in ' +  CONVERT(VARCHAR(10), DATEDIFF(millisecond, @ds, @de)) + ' milisecunde';



END


SELECT * FROM TestRuns;
SELECT * FROM TestRunTables;
SELECT * FROM TestRunViews;


Exec run_test 3;
