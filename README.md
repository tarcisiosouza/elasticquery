#Elastic Search - Query API
Perform queries specifying the terms in Elastic Search filtered by a timestamp range on the URL.
Output file is a set of documents (white space separeted fields).

## How to run

Compile and package the code using

    mvn package

Now you should have a .jar called `elasticquery-0.0.1-SNAPSHOT-job.jar`
in the directory `target` that contains all the classes necessary to run. Copy
the .jar to the cluster and execute using 

    hadoop jar elasticquery-0.0.1-SNAPSHOT-job.jar propertiesFileName outputFile

where you have to provide a properties file with the following structure:

| argument     | description                                              |
|--------------|----------------------------------------------------------|
| -query terms | The terms you want to find in URLs with or without quotes|
| -DateFrom    | The initial timestamp to match the query                 |
| -DateTo      | The final timestamp                                      |
| -limit       | The limit of the query (total of documents in the output)|

| argument     | german elections from 2004 to 2013                       |
|--------------|----------------------------------------------------------|
| -query terms | bundestagswahl merkel                                    |
| -DateFrom    | 2004/01/01 21:12:15                                      |
| -DateTo      | 2013/12/31 22:12:15                                      |
| -limit       | 20                                                       |
execute:

    hadoop jar elasticquery-0.0.1-SNAPSHOT-job.jar elections2004_2013.properties output.txt

## Output format

The Output file contains text records per each line and each line with
the following fields:

| field   | description                                          |
|---------|------------------------------------------------------|
|orig     | The URL which contains query terms                   |
|ts       | timestamp matched in the query 					     |
|filename | Redirect URL                                         |
|file     | Filename                                             |
|offset   | -                                                    |
|orig     |                                                      |

