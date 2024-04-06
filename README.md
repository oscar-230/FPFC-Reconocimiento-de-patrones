# FPFC-Taller #3 Reconocimiento de patrones
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
Por ejemplo, la función `f(x)=5k+ln(3)/(8−x)^x` podría ser representada en Scala de la siguiente manera:
```scala
Suma(
  Prod(Numero(5.0), Atomo('k')),
  Div(
    Logaritmo(Numero(3.0)),
    Expo(
      Resta(Numero(8.0), Atomo('x')),
      Atomo('x')
    )
  )
)
```
Ahora, si tenemos una función `f(x)` y queremos hallar sus ceros (es decir `{x: f(x) = 0})`, podemos aplicar el metodo de Newton (iterativo). 
* Sea `x0` una aproximacion inicial.
1) si `f(xi)` está suficientemente de 0, `xi` es una solución de `f(x)=0`
2) Sino, sea `xi+1 = xi - f(xi)/f'(xi)`.

En este taller usted implementará el algoritmo de Newton para hallar la raíz de una función representada por una expresión de tipo Expr. El objetivo es adquirir destreza
para escribir código funcional usando la técnica de reconocimiento de patrones.

## 1.1 Mostrando expresiones
Implemente una función `mostrar` que, dada una expresión `e`, devuelve una cadena que representa la definición simbólica de la función, sin ambigüedades.

```scala
def mostrar(e: Expr): String = {...}
```
Por ejemplo, para las siguiente expresiones:

```scala
val expr1 = Suma(Atomo('x'), Numero(2))
val expr2 = Prod(Atomo('x'), Atomo('x'))
val expr3 = Suma(expr1, Expo(expr2, Numero(5)))
val expr4 = Logaritmo(Atomo('x'))
val expr5 = Prod(Div(expr1, expr2), Resta(expr3, expr4))
val expr6 = Expo(Atomo('x'), Numero(3))
```
Los valores resultantes de invocar `mostrar` son, respectivamente:
```scala
val res1: String = "(x + 2.0)"
val res2: String = "(x * x)"
val res3: String = "((x + 2.0) + ((x * x) ^ 5.0))"
val res4: String = "lg(x)"
val res5: String = "(((x + 2.0) / (x * x)) * (((x + 2.0) + ((x * x) ^ 5.0)) - (lg(x))))"
val res6: String = "(x ^ 3.0)"
```
## 1.2 Derivando expresiones
Implemente una función `derivar` que dada una función representada por la expresión `f` y un átomo `a` (que representa la variable de la función), retorne la expresión correspondiente a su derivada con respecto a esa variable; todos los demás átomos de f deben ser considerados como constantes.
```scala
def derivar(f: Expr, a: Atomo): Expr = {...}
```
Por ejemplo, a las siguientes invocaciones:
```scala
mostrar(derivar(expr6, Atomo('x')))
mostrar(derivar(expr2, Atomo('x')))
mostrar(derivar(expr2, Atomo('y')))
mostrar(derivar(Suma(Atomo('k'), Prod(Numero(3.0), Atomo('x'))), Atomo('x')))
```
La función responde así:
```scala
val res6: String = "((x ^ 3.0) * (((1.0 * 3.0) / x) + (0.0 * (lg(x)))))"
val res7: String = "((1.0 * x) + (x * 1.0))"
val res8: String = "((0.0 * x) + (x * 0.0))"
val res9: String = "(0.0 + ((0.0 * x) + (3.0 * 1.0)))"
```
## 1.3. Evaluando expresiones
Implemente una función `evaluar` que, dada una función representada por la expresión `f`, un átomo `a` (que representa la variable de la función), y un valor `v` flotante, calcule la evaluación de la función en `v`.

Nota: Suponga que la fórmula de entrada solamente tendrá un tipo átomo, es decir que no dependerá de varias variables, ademas que siempre podrá ser evaluada.

```scala
def evaluar(f: Expr, a: Atomo, v: Double): Double = {...}
```
Por ejemplo, a las siguientes invocaciones:
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
```
La función responde así:
```scala
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
## 1.4. Limpiando expresiones
Implemente una función limpiar que dada una función representada por la expresión `f`, retorne una fórmula equivalente pero que no contenga ceros ni unos innecesarios.
```scala
def limpiar(f: Expr): Expr = {...}
```
Por ejemplo, a las siguientes invocaciones:
```scala
limpiar(derivar(Suma(Atomo('k'), Prod(Numero(3.0), Atomo('x'))), Atomo('x')))
mostrar(limpiar(derivar(Suma(Atomo('k'), Prod(Numero(3.0), Atomo('x'))), Atomo('x'))))
```
la función responde así:
```scala
val res26: Newton.Expr = Numero(3.0)
val res27: String = 3.0
```
## 1.5. Hallando raíces
Implementa una función iterativa `raizNewton` que, dada una función representada por la expresión `f`, un átomo `a` (que representa la variable de la función), un valor flotante `x0` candidato a raíz de `f`, y una función booleana `ba`, retorne una raíz, `r`, de la función usando el método de Newton. (`ba` es una función que recibe la expresión `f`, el átomo `a` y el valor candidato a raíz, `x0`, y devuelve true si `f(x0)` está suficientemente cerca de 0).
```scala
def raizNewton(f: Expr, a: Atom, x0: Double, ba: (Expr, Atom, Double) => Boolean): Double = {...}
```
Por ejemplo, a las siguientes invocaciones:
```scala
def buenaAprox(f: Expr, a: Atom, d: Double): Boolean = {
  evaluar(f, Map(a -> Numero(d))) < 0.001
}

val e1 = Resta(Prod(Atomo('x'), Atomo('x')), Numero(2.0))
val e2 = Resta(Prod(Atomo('x'), Atomo('x')), Numero(4.0))
val e3 = Suma(Resta(Prod(Atomo('x'), Atomo('x')), Numero(4.0)), Prod(Numero(3.0), Atomo('x')))
raizNewton(e1, Atomo('x'), 2.0, buenaAprox)
raizNewton(e2, Atomo('x'), 2.0, buenaAprox)
raizNewton(e3, Atomo('x'), 2.0, buenaAprox)
```
La función responde así:
```scala
def buenaAprox(f: Newton.Expr, a: Newton.Atomo, d: Double): Boolean

val e1: Newton.Resta = Resta(Prod(Atomo(x), Atomo(x)), Numero(2.0))
val e2: Newton.Resta = Resta(Prod(Atomo(x), Atomo(x)), Numero(4.0))
val e3: Newton.Suma = Suma(Resta(Prod(Atomo(x), Atomo(x)), Numero(4.0)), Prod(Numero(3.0), Atomo(x)))
val res28: Double = 1.4142156862745099
val res29: Double = 2.0
val res30: Double = 1.0000029768726761
```
