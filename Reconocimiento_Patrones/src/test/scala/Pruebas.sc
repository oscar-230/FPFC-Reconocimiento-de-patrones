import Newton._

//Pruebas para la funcion comparar
val expr1 = Suma(Atomo('x'), Numero(2))
val expr2 = Prod(Atomo('x'), Atomo('x'))
val expr3 = Suma(expr1, Expo(expr2, Numero(5)))
val expr4 = Logaritmo(Atomo('x'))
val expr5 = Prod(Div(expr1, expr2), Resta(expr3, expr4))
val expr6 = Expo(Atomo('x'), Numero(3))
val expr7 = Resta(Atomo('y'), Numero(3))
val expr8 = Div(Numero(10), Numero(2))
val expr9 = Suma(Prod(Numero(2), Numero(3)), Atomo('z'))
val expr10 = Expo(Prod(Atomo('x'), Numero(4)), Numero(2))
val expr11 = Prod(Suma(Atomo('a'), Atomo('b')), Resta(Atomo('c'), Atomo('d')))
val expr12 = Div(Prod(Numero(5), Atomo('e')), Numero(5))
val expr13 = Logaritmo(Expo(Numero(2), Numero(3)))
val expr14 = Suma(Expo(Numero(2), Numero(3)), Prod(Numero(4), Numero(5)))
val expr15 = Prod(Expo(Numero(2), Numero(3)), Atomo('x'))
val expr16 = Resta(Logaritmo(Atomo('x')), Numero(1))
val expr17 = Prod(Atomo('y'), Resta(Atomo('x'), Numero(1)))
val expr18 = Div(Prod(Numero(3), Atomo('x')), Suma(Numero(1), Atomo('x')))

val res1: String = mostrar(expr1)// x + 2
val res2: String = mostrar(expr2)// x * x
val res3: String = mostrar(expr3)// (x + 2) + ((x * x) ^ 5)
val res4: String = mostrar(expr4)// lg(x)
val res5: String = mostrar(expr5)// (x + 2) / (x * x) * ((x + 2) + ((x * x) ^ 5) - lg(x))
val res6: String = mostrar(expr6)// x ^ 3
val res7: String = mostrar(expr7)// y - 3
val res8: String = mostrar(expr8)// 10 / 2
val res9: String = mostrar(expr9)// 2 * 3 + z
val res10: String = mostrar(expr10)// ((x * 4) ^ 2)
val res11: String = mostrar(expr11)// ((a + b) * (c - d))
val res12: String = mostrar(expr12)// ((5 * e) / 5)
val res13: String = mostrar(expr13)// lg((2 ^ 3))
val res14: String = mostrar(expr14)// ((2 ^ 3) + (4 * 5))
val res15: String = mostrar(expr15)// ((2 ^ 3) * x)
val res16: String = mostrar(expr16)// lg(x) - 1
val res17: String = mostrar(expr17)// y * (x - 1)
val res18: String = mostrar(expr18)// ((3 * x) / (1 + x))


//pruebas para la funcion derivar
mostrar(derivar(Atomo('x'), Atomo('x')))//1
mostrar(derivar(Atomo('k'), Atomo('x')))//0
mostrar(derivar(expr6, Atomo('x')))// (x ^ 3) * ( 0 * lg(x)) + ((3/x) * 1)
mostrar(derivar(expr2, Atomo('x')))// (1 * x) + (x * 1)
mostrar(derivar(expr2, Atomo('y')))// (0 * x) + (x * 0)
mostrar(derivar(expr8, Atomo('x')))//((0 * 2) -(10 * 0)) / (2 ^ 2)
mostrar(derivar(expr15, Atomo('x')))// ((2 ^ 3) * ((0 * lg(2)) + ((3 / 2) * 0))) * x) + ((2 ^ 3) * 1))
mostrar(derivar(expr17, Atomo('x')))// (0 * (x - 1) + (y * (1 - 0))
mostrar(derivar(Suma(Atomo('k'), Prod(Numero(3.0), Atomo('x'))), Atomo('x')))// (0 + ((0 * x) + (3 * 1)))

//pruebas para la funcion evaluar
mostrar(Atomo('k'))
evaluar(Atomo('k'), Atomo('x'),1.0)//NaN
mostrar(Atomo('k'))
evaluar(Atomo('k'), Atomo('k'),1.0)//1.0
mostrar(Numero(5.0))
evaluar(Numero(5.0), Atomo('x'), 1.0)// 5
mostrar(Atomo('x'))
evaluar(Atomo('x'), Atomo('x'), 5.0)// 5
mostrar(Suma(expr1, expr2))
evaluar(Suma(expr1, expr2), Atomo('x'), 5.0)// 32
mostrar(Prod(expr1, expr2))
evaluar(Prod(expr1, expr2), Atomo('x'), 5.0)// 175
mostrar(Resta(expr1, expr2))
evaluar(Resta(expr1, expr2), Atomo('x'), 5.0)// -18
mostrar(Div(expr1, expr2))
evaluar(Div(expr1, expr2), Atomo('x'), 5.0)// 0.28
mostrar(Expo(expr1, expr2))
evaluar(Expo(expr1, expr2), Atomo('x'), 5.0)// 1.3410
mostrar(Logaritmo(expr1))
evaluar(Logaritmo(expr1), Atomo('x'), 5.0)// 1.9459


//Pruebas para la funcion limpiar
limpiar(derivar(Suma(Atomo('k'), Prod(Numero(3.0), Atomo('x'))), Atomo('x')))// 3
mostrar(limpiar(derivar(Suma(Atomo('k'), Prod(Numero(3.0), Atomo('x'))), Atomo('x'))))

limpiar(Suma(Numero(0.0), Prod(Numero(1.0), Atomo('x'))))// x
mostrar(limpiar(Suma(Numero(0.0), Prod(Numero(1.0), Atomo('x')))))

limpiar(Suma(Numero(0.0), Numero(0.0)))// 0
mostrar(limpiar(Suma(Numero(0.0), Numero(0.0))))

limpiar(Prod(Numero(1.0), Atomo('y')))// y
mostrar(limpiar(Prod(Numero(1.0), Atomo('y'))))

limpiar(Div(Atomo('z'), Numero(1.0)))// z
mostrar(limpiar(Div(Atomo('z'), Numero(1.0))))

limpiar(Logaritmo(Numero(1.0)))// lg(1)
mostrar(limpiar(Logaritmo(Numero(1.0))))

limpiar(Suma(Prod(Atomo('z'), Expo(Numero(1.0), Numero(3.0))), Numero(0.0)))// z*(1 ^ 3)
mostrar(limpiar(Suma(Prod(Atomo('z'), Expo(Numero(1.0), Numero(3.0))), Numero(0.0))))

limpiar(Suma(Prod(Atomo('a'), Resta(Numero(1.0), Div(Numero(0.0), Atomo('b')))), Expo(Numero(2.0), Numero(0.0))))// a + 1
mostrar(limpiar(Suma(Prod(Atomo('a'), Resta(Numero(1.0), Div(Numero(0.0), Atomo('b')))), Expo(Numero(2.0), Numero(0.0)))))

//pruebas para la función raizNewton
val e1 = Resta(Prod(Atomo('x'), Atomo('x')), Numero(2.0))
val e2 = Resta(Prod(Atomo('x'), Atomo('x')), Numero(4.0))
val e3 = Suma(Resta(Prod(Atomo('x'), Atomo('x')), Numero(4.0)), Prod(Numero(3.0), Atomo('x')))
val e4 = Resta(Prod(Atomo('x'), Atomo('x')), Numero(9.0))
val e5 = Resta(Prod(Atomo('x'), Prod(Atomo('x'), Atomo('x'))), Numero(27.0))
val e6 = Suma(Resta(Prod(Atomo('x'), Atomo('x')), Prod(Numero(4.0), Atomo('x'))), Numero(4.0))
val e7 = Prod(Resta(Atomo('x'), Numero(1.0)), Prod(Resta(Atomo('x'), Numero(1.0)), Resta(Atomo('x'), Numero(1.0))))
val e8 = Prod(Resta(Atomo('x'), Numero(2.0)), Suma(Atomo('x'), Numero(2.0)))

def buenaAprox(f: Expr, a: Atomo, d: Double): Boolean = {
  evaluar(f, a, d) < 0.001 // Cambia el valor según la precisión deseada
}

raizNewton(e1, Atomo('x'), 2.0, buenaAprox)// 1.41421
raizNewton(e2, Atomo('x'), 2.0, buenaAprox)// 2
raizNewton(e3, Atomo('x'), 2.0, buenaAprox)// 1
raizNewton(e4, Atomo('x'), 3.0, buenaAprox)// 3
raizNewton(e5, Atomo('x'), 4.0, buenaAprox)// 3
raizNewton(e6, Atomo('x'), 2.0, buenaAprox)// 2
raizNewton(e7, Atomo('x'), 2.0, buenaAprox)// 1
raizNewton(e8, Atomo('x'), 1.0, buenaAprox)// 1
