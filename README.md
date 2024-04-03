# FPFC-Reconocimiento-de-patrones
## 1. Ejercicios de programación: Método de Newton

El objetivo de este ejercicio es implementar el método de Newton para hallar raíces de una función cualquiera. Una función estará representada por una expresión construida a partir de un trait denominado `Expr` definido así:

```scala
trait Expr
case class Numero(d: Double) extends Expr
case class Atomo(x: Char) extends Expr
case class Suma(e1: Expr, e2: Expr) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr
case class Resta(e1: Expr, e2: Expr) extends Expr
case class Div(e1: Expr, e2: Expr) extends Expr
case class Expo(e1: Expr, e2: Expr) extends Expr
case class Logaritmo(e1: Expr) extends Expr
```
Se pueden crear expresiones que representen un flotante, una variable, una operación binaria (suma, resta, multiplicación, división y exponenciación) o una operación unaria (para el logaritmo natural).
## 1.1 Mostrando expresiones
Implemente una función `mostrar` que, dada una expresión `e`, devuelve una cadena que representa la definición simbólica de la función, sin ambigüedades.

```scala
def mostrar(e: Expr): String = {...}
```
Por ejemplo:

```scala
val expr1 = Suma(Atomo('x'), Numero(2))
val expr2 = Prod(Atomo('x'), Atomo('x'))
val expr3 = Suma(expr1, Expo(expr2, Numero(5)))
val expr4 = Logaritmo(Atomo('x'))
val expr5 = Prod(Div(expr1, expr2), Resta(expr3, expr4))
val expr6 = Expo(Atomo('x'), Numero(3))

// Resultados
val res1: String = "(x + 2.0)"
val res2: String = "(x * x)"
val res3: String = "((x + 2.0) + ((x * x) ^ 5.0))"
val res4: String = "lg(x)"
val res5: String = "(((x + 2.0) / (x * x)) * (((x + 2.0) + ((x * x) ^ 5.0)) - (lg(x))))"
val res6: String = "(x ^ 3.0)"
```
## 1.2 Derivando expresiones
Implemente una función `derivar` que, dada una función representada por la expresión `f` y un átomo `a`, retorne la expresión correspondiente a su derivada con respecto a esa variable.
```scala
def derivar(f: Expr, a: Atomo): Expr = {...}
```
Por ejemplo:
```scala
mostrar(derivar(expr6, Atomo('x')))
mostrar(derivar(expr2, Atomo('x')))
mostrar(derivar(expr2, Atomo('y')))
mostrar(derivar(Suma(Atomo('k'), Prod(Numero(3.0), Atomo('x'))), Atomo('x')))

// Resultados
val res6: String = "((x ^ 3.0) * (((1.0 * 3.0) / x) + (0.0 * (lg(x)))))"
val res7: String = "((1.0 * x) + (x * 1.0))"
val res8: String = "((0.0 * x) + (x * 0.0))"
val res9: String = "(0.0 + ((0.0 * x) + (3.0 * 1.0)))"
```
## 1.3. Evaluando expresiones
Implemente una función `evaluar` que, dada una función representada por la expresión `f`, un átomo `a`, y un valor `v` flotante, calcule la evaluación de la función en `v`
Nota: Suponga que la fórmula de entrada solamente tendrá un tipo átomo, es decir que no dependerá de varias variables, además que siempre podrá ser evaluada.

```scala
def evaluar(f: Expr, a: Atomo, v: Double): Double = {...}
```
Por ejemplo: 
```scala
mostrar(Numero(5.0))
evaluar(Numero(5.0), Atomo('x'), 1.0)
mostrar(Atomo('x'))
evaluar(Atomo('x'), Atomo('x'), 5.0)
mostrar(Suma(expr1, expr2))
evaluar(Suma(expr1, expr2), Atomo('x'), 5.0)
mostrar(Prod(expr1, expr2))
evaluar(Prod(expr1, expr2), Atomo('x'), 5.0)
mostrar(Resta(expr1, expr2))
evaluar(Resta(expr1, expr2), Atomo('x'), 5.0)
mostrar(Div(expr1, expr2))
evaluar(Div(expr1, expr2), Atomo('x'), 5.0)
mostrar(Expo(expr1, expr2))
evaluar(Expo(expr1, expr2), Atomo('x'), 5.0)
mostrar(Logaritmo(expr1))
evaluar(Logaritmo(expr1), Atomo('x'), 5.0)

// Resultados
val res10: String = "5.0"
val res11: Double = 5.0
val res12: String = "x"
val res13: Double = 5.0
val res14: String = "((x + 2.0) + (x * x))"
val res15: Double = 32.0
val res16: String = "((x + 2.0) * (x * x))"
val res17: Double = 175.0
val res18: String = "((x + 2.0) - (x * x))"
val res19: Double = -18.0
val res20: String = "((x + 2.0) / (x * x))"
val res21: Double = 0.28
val res22: String = "((x + 2.0) ^ (x * x))"
val res23: Double = 1.341068619663965E21
val res24: String = "lg((x + 2.0))"
val res25: Double = 1.9459101490553132
```
