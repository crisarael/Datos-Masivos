//1. Desarrollar un algoritmo en scala que calcule el radio de un circulo

val circulo = 15
val pi= 2*3.1416
val rad = circulo/pi
println (rad)

//2. Desarrollar un algoritmo en scala que me diga si un numero es primo

val numero : Int = 7
var primo : Boolean = true

for(i <- Range(2, numero)) {
  if((numero % i) == 0) {
    primo = false
  }
}
if(primo){
  println("Es Primo")
} else {
  println("No es Primo")
}

//3. Dada la variable bird = "tweet", utiliza interpolacion de string para
//imprimir "Estoy ecribiendo un tweet"

var bird = "tweet"
printf("Estoy escribiendo un %s", bird)

//4. Dada la variable mensaje = "Hola Luke yo soy tu padre!" utiliza slice para
// extraer la secuencia "Luke"
var mensaje = "Hola Luke yo soy tu padre!"
mensaje.slice(5,11)

//6. Dada la tupla (2,4,5,1,2,3,3.1416,23) regresa el numero 3.1416
val tupla = (2,4,5,1,2,3,3.1416,23)
tupla._7