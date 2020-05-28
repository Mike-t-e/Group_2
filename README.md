# Group_2

Before running the java program you must first set up the repository structure.

create the following repository structure

sqlite - db
       - java - connect - net - sqlitetutorial
       
1.create a database named chinook.db and use ".read" on final_DB or use the "chinook.db" in the repository and place it in sqlite/db

2. place the driver file in sqlite/java/connect
3. place the java files in sqlite/java/connect/net/sqlitetutorial
4. change directory to sqlite/db/java/connect
5. execute this command "javac net/sqlitetutorial/Connect.java && java -cp .:sqlite-jdbc-3.27.2.1.jar net.sqlitetutorial.Connect". this will compile and run the program, if you're using a windows computer you will need to alter the command slightly for it to work properly.

OBS!
You can also use the sqlite.zip file to unpack a finished repository and skip directly to step 5 above
0BS!
