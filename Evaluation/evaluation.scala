
//The necessary libraries are imported
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors



var spark = SparkSession.builder().getOrCreate()
var data = spark.read.option("header", "true").option("inferSchema","true")csv("G:/DATOS_MASIVOS/Iris.csv")


data.columns



data.printSchema()


data.select($5.1,$3.5,$1.4,$0.2,$”iris-setosa”)




data.describe()




// Build the classification model and explain its architecture.
// Print the model results
var labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedSpecies").fit(data)
var indexed = labelIndexer.transform(data).withColumnRenamed("indexedSpecies", "label") 

//divided into 70% training and 30% testing
var labelIndexer2 = new StringIndexer().setInputCol("label").setOutputCol("indexedSpecies").fit(indexed)
var assembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features")
var  features = assembler.transform(indexed)

//specific layers of the neural network as well as their characteristics
var splits = features.randomSplit(Array(0.7, 0.3), seed = 1234L)
var train = splits(0)
var test = splits(1)
//arrangement of the layers of the neural network, in this case we choose certain values ​​from the same arrangement of the already mentioned layer
var layers = Array[Int](4, 5, 4, 3)

//training model creation
var trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

//variable for the training model
var model = trainer.fit(train)

//will give us the precision values
var result = model.transform(test)
var predictionAndLabels = result.select("prediction", "label")
var evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

//print the values ​​and stop the spark session
println(s"\n\nTest set accuracy = ${evaluator.evaluate(predictionAndLabels)}")




