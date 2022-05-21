# Evaluation Unit 2

# Libraries
```scala
//The necessary libraries are imported
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier//algoritmo de Machine Learning multilayer perceptron
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}
```

# 1-.Load DataSet
This dataset brings us features about 3 iris flowers variants
```scala
//1.- Carga el dataset
val dat = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")
```
![Alt text](Images/flower.png)

# 2-.Show Columns
```scala
//2.- Nombre de las columnas
println(dat.columns.toSeq)
```

# 3-.Show Schema
```scala
//3.- Esquema
println(dat.schema)
dat.printSchema()
```


# 4-.Show the first 5 columns

```scala
//4.- mostrar numero de columnas en este caso 5
dat.select(dat.columns.slice(0,5).map(m=>col(m)):_*).show()
// numero de renglones en este caso 5
dat.show(5)
```

# 5-.use describe() Method

```scala
//5.-Utilizar el metodo describe
dat.describe().show()
```


# 6-.Transform the data into categories and labels using indexers
```scala
//6.-Transformacion para datos categoricos y etiquetas a clasificar
//etiqueta
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("label").fit(dat)
var dat1=labelIndexer.transform(dat)
//caracteristicas
val featureIndexer = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features")
var data=featureIndexer.transform(dat1)
```
