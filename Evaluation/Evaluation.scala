//1-.Start a new spark session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

//2-.Load the Netflix Csv file, and make spark infer the data
val NetflixDf = spark.read.option("header", "true").option("inferSchema",
  "true")csv("Netflix_2011_2016.csv")

//3-.What are the name of the columns
NetflixDf.columns
    
//4-.how is the schema?
NetflixDf.printSchema()

//5-.imprimir las 5 columnas 
NetflixDf.select($"Date",$"Open",$"High",$"Low", $"Close").show()

//6-.use describe() function to learn about the dataframe
NetflixDf.describe()


//7-.Create a new data frame with a new column "HV Ratio", this is the relation betwen column "High" and "Volume"
var NewDf = NetflixDf.withColumn("HV Ratio", NetflixDf("High")/NetflixDf("Volume"))

//8-.What day have the maximum "OPEN" 
NetflixDf.select($"Date", $"Open").show(1)

//9-.Meaning of close with your words

//10-.Maximum and minimum column volume
NetflixDf.select(max ($"Volume")).show()
NetflixDf.select(min ($"Volume")).show()


//#11-.With Scala/Spark Syntax $ answer the following:

//A HOW MANY DAYS WAS THE COLUMN CLOSE BELOW 600?
var a = NetflixDf.filter($"Close" < 600).count()

//B WHAT PERCENTAGE OF THE TIME WAS THE HIGH COLUMN GREATER THAN 500
var b = (NetflixDf.filter($"High" > 500).count()*100)/1260

//C What is the Pearson correlation between the "High" column and the "Volume" column?
NetflixDf.select(corr("High", "Volume").alias("correlacion de Pearson")).show()

//D What is the maximum of the “High” column per year?
NetflixDf.groupBy(year(NetflixDf("Date")).alias("Year")).max("High").sort(asc("Year")).show()


//E What is the average of the “Close” column for each calendar month?
NetflixDf.groupBy(month(NetflixDf("Date")).alias("Month")).avg("Close").sort(asc("Month")).show()