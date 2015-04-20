# word-count-spring-batch-partitioning
Use Spring Batch to implement count word in PDF files follow Master/Slave model
## Master/Slave model

## Word count algorithm


Read line by line of text file.
Use StringTokenizer to cut word when matching the regular exepression such as: .,\t{}()<> ...
Add the word cut by StringTokenizer to a map as a key and the value will be the numbers of occurrence of that word in context. 

## Setup, build and run
