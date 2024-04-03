import Newton._
val expr1=Suma(Atomo('x'), Numero(2))
val expr2=Prod(Atomo('x'), Atomo('x'))
val expr3=Suma(expr1, Expo(expr2,Numero(5)))
val expr4=Logaritmo(Atomo('x'))
val expr5=Prod(Div(expr1, expr2), Resta(expr3,expr4))
val expr6=Expo(Atomo('x'),Numero(3))