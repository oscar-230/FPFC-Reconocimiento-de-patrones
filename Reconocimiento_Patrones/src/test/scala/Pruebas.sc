import Newton._

//Pruebas para la funcion comparar
Atomo('x')
val expr1 = Suma(Atomo('x'), Numero(2))
val expr2 = Prod(Atomo('x'), Atomo('x'))
val expr3 = Suma(expr1, Expo(expr2, Numero(5)))
val expr4 = Logaritmo(Atomo('x'))
val expr5 = Prod(Div(expr1, expr2), Resta(expr3, expr4))
val expr6 = Expo(Atomo('x'), Numero(3))

val res1: String = mostrar(expr1)
val res2: String = mostrar(expr2)
val res3: String = mostrar(expr3)
val res4: String = mostrar(expr4)
val res5: String = mostrar(expr5)
val res6: String = mostrar(expr6)

mostrar(derivar(expr6, Atomo('x')))
mostrar(derivar(expr2, Atomo('x')))
mostrar(derivar(expr2, Atomo('y')))
mostrar(derivar(Suma(Atomo('k'), Prod(Numero(3.0), Atomo('x'))), Atomo('x')))