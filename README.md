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

|  Properties input file: German Elections from 2004 to 2013              |
|--------------|----------------------------------------------------------|
| field        | value                                                    |
|--------------|----------------------------------------------------------|
| -query terms | bundestagswahl merkel                                    |
| -DateFrom    | 2004/01/01 21:12:15                                      |
| -DateTo      | 2013/12/31 22:12:15                                      |
| -limit       | 20                                                       |


|  Properties input file: German Elections from 2004 to 2013              |


execute:

    hadoop jar elasticquery-0.0.1-SNAPSHOT-job.jar elections2004_2013.properties output.txt

## Output format

The Output file contains JSON records per each line and each JSON document with
the following fields:

| field | description                                          |
|-------|------------------------------------------------------|
|ts     | timestamp matched in the query 											 |
|id     | ts + original url                                    |
|comp   | compressedsize                                       |
|redir  | Redirect URL                                         |
|file   | Filename                                             |
|offset | -                                                    |
|orig   | The URL which contains query terms                   |

