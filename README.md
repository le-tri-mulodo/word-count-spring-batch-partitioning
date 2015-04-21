# word-count-spring-batch-partitioning
Use Spring Batch to implement count word in PDF files follow Master/Slave model
## Master/Slave model

## Word count algorithm


Read line by line of text file.
Use StringTokenizer to cut word when matching the regular exepression such as: .,\t{}()<> ...
Add the word cut by StringTokenizer to a map as a key and the value will be the numbers of occurrence of that word in context. 

## Setup, build and run
## SetUp

Open `application.properties` and config.

+ Config values for the datasource:
  + batch.jdbc.driver: Postgresql driver for datasource
  + batch.jdbc.url: URL of word_count data schema
  + batch.jdbc.url_repo: URL of job repository schema
  + batch.jdbc.username & batch.jdbc.password: User name & password to access datasource
+ Config values for database
  + batch.schema.script: Create schema
  + batch.drop.script: Drop schema
  + batch.job_repo.databaseType: Specify database
+ Config ActiveMQ
  + broker.url: ActiveMQ URL

## Build

To build, execute from the top level directory:

`$ mvn clean install`

## Run

Start ActiveMQ

+ Setup data locations:

`$ mkdir -p $HOME/image_submissions/bulk`

+ start ActiveMQ

`$ java -Djava.awt.headless=true -jar message-job-launch/target/message-job-launch-1.0.0.BUILD-SNAPSHOT.jar`

Start PostgresqlDB server:

``

Start the slave:

``

Start the master:

``



