import Newton._

//Pruebas para la funcion comparar
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

//pruebas para la funcion derivar
mostrar(derivar(expr6, Atomo('x')))
mostrar(derivar(expr2, Atomo('x')))
mostrar(derivar(expr2, Atomo('y')))
mostrar(derivar(Suma(Atomo('k'), Prod(Numero(3.0), Atomo('x'))), Atomo('x')))

//pruebas para la funcion evaluar
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

//Pruebas para la funcion limpiar
limpiar(derivar(Suma(Atomo('k'), Prod(Numero(3.0), Atomo('x'))), Atomo('x')))
mostrar(limpiar(derivar(Suma(Atomo('k'), Prod(Numero(3.0), Atomo('x'))), Atomo('x'))))
