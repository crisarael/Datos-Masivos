
//The necessary libraries are imported
import org.apache.spark.ml.classification.LinearSVC


val training  = spark.read.format("libsvm").load("C:/Spark/spark-2.4.8-bin-hadoop2.7/data/mllib/sample_libsvm_data.txt")


val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)


val lsvcModel = lsvc.fit(training)

println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")

