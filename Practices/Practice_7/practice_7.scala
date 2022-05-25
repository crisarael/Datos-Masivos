
//The necessary libraries are imported
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession

val data = spark.read.format("libsvm").load("C:/spark/spark-2.4.8-bin-hadoop2.7/data/mllib/sample_libsvm_data.txt")

println ("Numero de lineas en el archivo de datos:" + data.count ())



val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3), 100L)
// The result is the type of the array, and the array stores the data of type DataSet

//Append to the training set (fit operation) to train a Bayesian model
val naiveBayesModel = new NaiveBayes().fit(trainingData)

//The model calls transform() to make predictions and generate a new DataFrame.

val predictions = naiveBayesModel.transform(testData)

//Output prediction result data
predictions.show()


val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")
// Precision
val precision = evaluator.evaluate(predictions)

//Print the error rate
println ("error rate =" + (1-precision))
