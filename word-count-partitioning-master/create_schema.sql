DROP TABLE IF EXISTS word_count , file_folder;
DROP FUNCTION IF EXISTS update_insert_word(character varying, integer);

CREATE TABLE word_count (word varchar(128) NOT NULL, count int NOT NULL, PRIMARY KEY (word));
CREATE TABLE pdf_files (id serial NOT NULL, filepath varchar(512) NOT NULL, PRIMARY KEY (id));

CREATE FUNCTION update_insert_word (inputword character varying, inputcount integer)
  RETURNS void AS $BODY$
BEGIN

 update word_count set count = count + inputcount where word = inputword;
IF found THEN
 return;
END IF;
 insert into word_count (word, count) values (inputword, inputcount);
 return;

END;
$BODY$
LANGUAGE plpgsql;

insert into pdf_files(filepath)
	values
	('3G Evolution HSPA and LTE for Mobile Broad band by Erik Dahlman.pdf')
,('Activiti in Action.pdf')
,('Automotive Engineering Powertrain, Chassis System and Vehicle Body(BBS).pdf')
,('Beginning Hibernate 2nd Edition.pdf')
,('Beginning Scala, 2nd Edition.pdf')
,('Clean_Code.pdf')
,('Express Web Application Development.pdf')
,('Getting Started with Hibernate 3.pdf')
,('GitRealSlides.pdf')
,('Hadoop in Action.pdf')
,('Hadoop in Practice 2012.pdf')
,('Hadoop.The.Definitive.Guide.3rd.Edition.May.2012.pdf')
,('Jump Start Bootstrap.pdf')
,('Mastering Hadoop.pdf')
,('New English File Intermediate Students Book.pdf')
,('New English File Intermediate WB Key.pdf')
,('New English File Intermediate Workbook.pdf')
,('New English File Pre-intermediate Students Book.pdf')
,('New English File Pre-intermediate Teachers Book.pdf')
,('New English File Pre-intermediate WB Key.pdf')
,('Play for Scala.pdf')
,('Pro Apache Hadoop, 2nd Edition.pdf')
,('Pro Spring Batch.pdf')
,('Programming Scala.pdf')
,('Scala Cookbook.pdf')
,('Spring.Batch_.in_.Action.Oct_.2011.pdf')
,('THE LORD OF THE RINGS.pdf')
,('rc155-010d-Mockito_4.pdf')
,('spring-batch-reference.pdf')
,('streamlineenglish-connections-studentsbook.pdf');