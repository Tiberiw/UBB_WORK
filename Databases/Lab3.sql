/* Create the Version table */
CREATE TABLE Version(
	VersionNr INT DEFAULT 0,
	CONSTRAINT pk_version PRIMARY KEY (VersionNr)
);

/* Insert initial version */
INSERT INTO Version(VersionNr) VALUES
(0);

GO; 

/* Setter for version */
CREATE PROCEDURE setVersion @version INT
AS
BEGIN
UPDATE Version
SET VersionNr=@version;
END;

GO; 

/* Getter for version */
CREATE OR ALTER PROCEDURE getVersion @CrtVersion INT OUTPUT
AS
BEGIN
SELECT @CrtVersion = VersionNr FROM Version;
END;

GO;

/* Modify the gender column from Users table.
	varchar(20) -> char(6);
*/
CREATE OR ALTER PROCEDURE do_ModifyColumn
AS
BEGIN
ALTER TABLE Users
ALTER COLUMN gender char(6);
PRINT 'Column gender from table Users modified to char(6)';

EXEC setVersion 1;

DECLARE @CurrentVersion INT;
EXEC getVersion @CrtVersion = @currentVersion OUTPUT;
PRINT 'Current version: ' + @CurrentVersion; 
END;

GO;

/* Modify the gender column from Users table.
	char(6) -> varchar(20);
*/
CREATE OR ALTER PROCEDURE undo_ModifyColumn
AS
BEGIN
ALTER TABLE Users
ALTER COLUMN gender varchar(20)
PRINT 'Column gender from table Users modified to varchar(20)';

EXEC setVersion 0;

DECLARE @CurrentVersion INT;
EXEC getVersion @CrtVersion = @currentVersion OUTPUT;
PRINT 'Current version: ' + @CurrentVersion; 
END;

GO;

/* Add default value of NONE to gender column in table Users */
CREATE OR ALTER PROCEDURE do_AddDefaultConstraint
AS
BEGIN
ALTER TABLE Users
ADD CONSTRAINT df_gender
DEFAULT 'None' FOR gender;
PRINT 'Added default NONE value constraint for column gender in table Users'

EXEC setVersion 2;

DECLARE @CurrentVersion INT;
EXEC getVersion @CrtVersion = @currentVersion OUTPUT;
PRINT 'Current version: ' + @CurrentVersion; 
END;

GO;

/* Remove default value of NONE to gender column in table Users */
CREATE OR ALTER PROCEDURE undo_AddDefaultConstraint
AS
BEGIN
ALTER TABLE Users
DROP CONSTRAINT df_gender;
PRINT 'Removed default NONE value constraint for column gender in table Users'

EXEC setVersion 1;

DECLARE @CurrentVersion INT;
EXEC getVersion @CrtVersion = @currentVersion OUTPUT;
PRINT 'Current version: ' + @CurrentVersion; 
END;

GO; 

/* Create the table Address */
CREATE OR ALTER PROCEDURE do_CreateTable
AS
BEGIN
CREATE TABLE Address(
	UserId INT PRIMARY KEY,
	City varchar(255),
	Country varchar(255),
);
PRINT 'Table Address created';

EXEC setVersion 3;

DECLARE @CurrentVersion INT;
EXEC getVersion @CrtVersion = @currentVersion OUTPUT;
PRINT 'Current version: ' + @CurrentVersion; 
END;

GO;

/* Remove the table Address */
CREATE OR ALTER PROCEDURE undo_CreateTable
AS
BEGIN
DROP TABLE Address;
PRINT 'Table Address removed';

EXEC setVersion 2;

DECLARE @CurrentVersion INT;
EXEC getVersion @CrtVersion = @currentVersion OUTPUT;
PRINT 'Current version: ' + @CurrentVersion; 
END;

GO;

/* Add column Street varchar(255) to the table Address */
CREATE OR ALTER PROCEDURE do_AddColumn
AS
BEGIN
ALTER TABLE Address
ADD Street varchar(255);
PRINT 'Column Street varchar(255) added on table Address'

EXEC setVersion 4;

DECLARE @CurrentVersion INT;
EXEC getVersion @CrtVersion = @currentVersion OUTPUT;
PRINT 'Current version: ' + @CurrentVersion; 
END;

GO;

/* Remove column Street varchar(255) from the table Address */
CREATE OR ALTER PROCEDURE undo_AddColumn
AS
BEGIN
ALTER TABLE ADDRESS
DROP COLUMN Street;
PRINT 'Column Street varchar(255) removed from table Address'

EXEC setVersion 4;

DECLARE @CurrentVersion INT;
EXEC getVersion @CrtVersion = @currentVersion OUTPUT;
PRINT 'Current version: ' + @CurrentVersion; 
END;

GO;

/* Add Foreign Key constraint to the table Address */
CREATE OR ALTER PROCEDURE do_AddForeignKey
AS
BEGIN
ALTER TABLE Address
ADD CONSTRAINT fk_useraddress FOREIGN KEY (UserId) REFERENCES Users(uid);
PRINT 'Added foreign key constraint on table Address';

EXEC setVersion 5;

DECLARE @CurrentVersion INT;
EXEC getVersion @CrtVersion = @currentVersion OUTPUT;
PRINT 'Current version: ' + @CurrentVersion; 
END;

GO;

/* Remove Foreign Key constraint from the table Address */
CREATE OR ALTER PROCEDURE undo_AddForeignKey
AS
BEGIN
ALTER TABLE Address
DROP CONSTRAINT fk_useraddress;
PRINT 'Removed foreign key constraint from table Address';

EXEC setVersion 4;

DECLARE @CurrentVersion INT;
EXEC getVersion @CrtVersion = @currentVersion OUTPUT;
PRINT 'Current version: ' + @CurrentVersion; 
END;

GO;

/* Main function 
	PARAMETERS: @version: INT
*/
CREATE OR ALTER PROCEDURE Main @version FLOAT
AS
BEGIN
	IF(@version < 0 OR @version > 10)
	  BEGIN
		PRINT 'VERSIUNE INEXISTENTA!';
		RETURN 1;
	  END;

	IF(@version <> CAST(@version AS INT))
		BEGIN
			PRINT 'INTRODUCETI UN INT!';
			RETURN 1;
		END;

	DECLARE @currentVersion INT;
	EXEC getVersion @CrtVersion = @currentVersion OUTPUT;

	IF(@currentVersion = @version)
		PRINT 'NO OPERATIONS NEEDED';

	DECLARE @commands TABLE (number INT NOT NULL, command varchar(100))
	INSERT INTO @commands VALUES
	(1, 'ModifyColumn'),
	(2, 'AddDefaultConstraint'),
	(3, 'CreateTable'),
	(4, 'AddColumn'),
	(5, 'AddForeignKey')

	DECLARE @prefix varchar(4);
	DECLARE @lower INT;
	SET @lower = 1;
	SET @prefix = 'do';

	IF(@currentVersion > @version)
		BEGIN
			SET @prefix = 'undo';
			SET @lower = 0;
		END

	DECLARE @CURRENT_COMMAND varchar(100);
	DECLARE @sql NVARCHAR(100);

	WHILE(@currentVersion != @version)
		BEGIN
			
			SELECT @CURRENT_COMMAND = command FROM @commands WHERE number = (@currentVersion + @lower);
			SET @sql = CONCAT_WS('_', @prefix, @CURRENT_COMMAND);
			PRINT @sql
			EXEC @sql;
			EXEC getVersion @CrtVersion = @currentVersion OUTPUT;
		END;

	PRINT 'DONE';
	RETURN 0;
END;

EXEC Main 5;
EXEC Main 3;
EXEC Main 2;
EXEC Main 0.5;
EXEC Main 0;

