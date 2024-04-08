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

limpiar(Suma(Numero(0.0), Prod(Numero(1.0), Atomo('x'))))
mostrar(limpiar(Suma(Numero(0.0), Prod(Numero(1.0), Atomo('x')))))

limpiar(Suma(Numero(0.0), Numero(0.0)))
mostrar(limpiar(Suma(Numero(0.0), Numero(0.0))))

limpiar(Prod(Numero(1.0), Atomo('y')))
mostrar(limpiar(Prod(Numero(1.0), Atomo('y'))))

limpiar(Div(Atomo('z'), Numero(1.0)))
mostrar(limpiar(Div(Atomo('z'), Numero(1.0))))

limpiar(Logaritmo(Numero(1.0)))
mostrar(limpiar(Logaritmo(Numero(1.0))))

limpiar(Suma(Prod(Atomo('z'), Expo(Numero(1.0), Numero(3.0))), Numero(0.0)))
mostrar(limpiar(Suma(Prod(Atomo('z'), Expo(Numero(1.0), Numero(3.0))), Numero(0.0))))

limpiar(Suma(Prod(Atomo('a'), Resta(Numero(1.0), Div(Numero(0.0), Atomo('b')))), Expo(Numero(2.0), Numero(0.0))))
mostrar(limpiar(Suma(Prod(Atomo('a'), Resta(Numero(1.0), Div(Numero(0.0), Atomo('b')))), Expo(Numero(2.0), Numero(0.0)))))

//pruebas para la función raizNewton
val e1 = Resta(Prod(Atomo('x'), Atomo('x')), Numero(2.0))
val e2 = Resta(Prod(Atomo('x'), Atomo('x')), Numero(4.0))
val e3 = Suma(Resta(Prod(Atomo('x'), Atomo('x')), Numero(4.0)), Prod(Numero(3.0), Atomo('x')))

def buenaAprox(f: Expr, a: Atomo, d: Double): Boolean = {
  evaluar(f, a, d) < 0.001 // Cambia el valor según la precisión deseada
}

raizNewton(e1, Atomo('x'), 2.0, buenaAprox)
raizNewton(e2, Atomo('x'), 2.0, buenaAprox)
raizNewton(e3, Atomo('x'), 2.0, buenaAprox)





