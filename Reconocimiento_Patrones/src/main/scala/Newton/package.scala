package object Newton {
  trait Expr
  case class Numero (d:Double) extends Expr
  case class Atomo (x:Char) extends Expr
  case class Suma(e1:Expr, e2:Expr) extends Expr
  case class Prod(e1:Expr, e2:Expr) extends Expr
  case class Resta(e1:Expr, e2:Expr) extends Expr
  case class Div(e1:Expr, e2:Expr) extends Expr
  case class Expo(e1:Expr, e2:Expr) extends Expr
  case class Logaritmo(e1:Expr) extends Expr

/*
  def mostrar(e:Expr): String = {
  }
  def derivar(f :Expr, a:Atomo):Expr = {

  }
  def evaluar(f :Expr, a:Atomo, v:Double):Double ={

  }
  def limpiar(f :Expr):Expr = {

  }
  def raizNewton(f :Expr, a:Atomo, x0:Double, ba:(Expr, Atomo, Double)=>Boolean):Double ={

  }
  */
}
