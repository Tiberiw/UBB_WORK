---VALIDARE STRING NOT NULL ---
CREATE OR ALTER Function IS_NOT_NULL(@string nvarchar(100))
   RETURNS INT
AS
BEGIN
	IF @string IS NOT NULL
		RETURN 1

	RETURN 0
	 
END

GO

--- VALIDARE STRING GENDER ---
CREATE OR ALTER FUNCTION CHECK_GENDER(@gender varchar(20))
	RETURNS INT
AS
BEGIN
	
	IF(@gender IN ('male', 'female', 'none'))
		RETURN 1;

	RETURN 0;
END;

GO

--- CRUD PENTRU USERI ---
CREATE OR ALTER PROCEDURE CRUD_USERS
			@username varchar(50),
			@email varchar(50),
			@dob DATE,
			@gender varchar(10),
			@lastLogin DATETIME,
			@noOfRows INT = 1
AS
BEGIN
	SET NOCOUNT ON;

		IF(dbo.IS_NOT_NULL(@username) = 1 AND
			dbo.IS_NOT_NULL(@email) = 1 AND
			dbo.IS_NOT_NULL(@gender) = 1 AND
			dbo.CHECK_GENDER(@gender) = 1)
			BEGIN
				
				-- INSERT --
				DECLARE @n INT = 0;

				DECLARE @lastId INT =
					(SELECT ISNULL( max(uid), 0 ) + 1 FROM Users);

				DECLARE @SearchString varchar(50) = '' +  @username + '%';


	

				WHILE (@n < @noOfRows)
					BEGIN
						
						DECLARE @usernameUnique varchar(50) = @username + CONVERT(varchar(10), @lastId);
						DECLARE @emailUnique varchar(50) = @email + CONVERT(varchar(10), @lastId);


						INSERT INTO Users
						VALUES	(@usernameUnique, @emailUnique, @dob, @gender, @lastLogin);
		
						SET @n = @n + 1;
						SET @lastId = @lastId + 1;

					END;

				-- SELECT --
				
				SELECT * FROM Users ORDER BY username;

				-- UPDATE --

				UPDATE Users
				SET username = @username + '_CRUD'
				WHERE username LIKE @SearchString;
				SELECT * FROM Users ORDER BY username;


				-- DELETE --

				DELETE FROM Users
				WHERE username LIKE @username + '_CRUD';

				SELECT * FROM Users ORDER BY username;

				PRINT 'Operatii CRUD pentru Users executate cu succes.';

			END
		ELSE
			BEGIN
				RAISERROR('Invalid input!', 18, 1);
			END

END;
	
SELECT * FROM Users;
EXEC CRUD_USERS 'usernameTest', 'test@email.com', '2019-03-08', 'none', '2022-06-26 15:51:18.620', 3; 

GO

-- CRUD PENTRU GROUPS --
CREATE OR ALTER PROCEDURE CRUD_GROUPS
				@groupName varchar(50),
				@description varchar(100),
				@noOfRows INT = 1
AS
BEGIN
		SET NOCOUNT ON;

		IF( dbo.IS_NOT_NULL(@groupName) = 1 AND
			dbo.IS_NOT_NULL(@description) = 1)
			BEGIN
				
					-- INSERT --

					DECLARE @n INT = 0;

					WHILE (@n < @noOfRows)
					BEGIN
						
						INSERT INTO Groups VALUES	(@groupName, @description);
						SET @n = @n + 1;

					END;

					-- SELECT --
					
					SELECT * FROM Groups ORDER BY name;

					-- UPDATE --

					UPDATE Groups
					SET name = @groupName + '_CRUD'
					WHERE name LIKE @groupName;

					SELECT * FROM Groups ORDER BY name;

					-- DELETE --


					DELETE FROM Groups
					WHERE name LIKE @groupName + '_CRUD';

					SELECT * FROM Groups ORDER BY name;

					PRINT 'Operatii CRUD pentru Groups executate cu succes.';


			END
		ELSE
			BEGIN
				RAISERROR('Invalid input!', 18, 1);
			END

END

SELECT * FROM Groups;
EXEC CRUD_GROUPS 'grupTest', 'descriereGrupTest', 3;


GO

---VALIDARE FOREIGN KEYS ---
CREATE OR ALTER Function IS_VALID_GROUP_MEMBERSHIP(@groupId INT, @userId INT)
   RETURNS INT
AS
BEGIN
	IF ( EXISTS(SELECT * FROM Groups WHERE gid = @groupId) AND EXISTS(SELECT * FROM Users WHERE uid = @userId) )
		RETURN 1
	RETURN 0
	 
END

GO

-- CRUD PENTRU GROUP MEMBERSHIPS --
CREATE OR ALTER PROCEDURE CRUD_GROUP_MEMBERSHIPS
					@userID INT,
					@groupID INT
AS
BEGIN
	SET NOCOUNT ON;
	IF(dbo.IS_VALID_GROUP_MEMBERSHIP(@groupID,@userID) = 1)
		BEGIN
			

			-- INSERT --
			INSERT INTO GroupMemberships VALUES(@userID, @groupID);

			-- SELECT --
			SELECT * FROM GroupMemberships ORDER BY uid;

			-- UPDATE --
				
			

			-- DELETE --
			DELETE FROM GroupMemberships
			WHERE uid=@userID AND gid=@groupID;

			SELECT * FROM GroupMemberships ORDER BY uid;


		END
	ELSE
		BEGIN
			RAISERROR('Invalid input!', 18, 1);
		END

END
SELECT * FROM GroupMemberships;
EXEC CRUD_GROUP_MEMBERSHIPS 10060, 13321;

GO

--- VIEWS ---

CREATE OR ALTER VIEW ViewNumberGroups
AS
	select G.name AS GroupName, COUNT(*) AS NumberOfUsers
	FROM Groups as G
	INNER JOIN GroupMemberships AS GM ON G.gid = GM.gid
	INNER JOIN Users as U ON GM.uid = U.uid
	GROUP BY G.name;

GO

CREATE OR ALTER VIEW ViewListGroups
AS
	select G.name AS GroupName
	FROM Groups as G
	INNER JOIN GroupMemberships AS GM ON G.gid = GM.gid
	INNER JOIN Users as U ON GM.uid = U.uid
	WHERE U.username = 'Tony Stark';

GO

SELECT * FROM ViewNumberGroups;

SELECT * FROM ViewListGroups;

--- INDECSI ---

-- Users
CREATE NONCLUSTERED INDEX N_idx_Username ON Users (username);

-- Groups
CREATE NONCLUSTERED INDEX N_idx_Groupname ON Groups (name);

-- Group Memberships
CREATE NONCLUSTERED INDEX N_idx_GMGid ON GroupMemberships (gid);
CREATE NONCLUSTERED INDEX N_idx_GMUid ON GroupMemberships (uid);

