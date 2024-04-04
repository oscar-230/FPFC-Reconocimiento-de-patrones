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


  def mostrar(e:Expr): String = {
    e match {
      case Numero(d) => d.toString
      case Atomo(x)=> x.toString
      case Suma(e1, e2) => s"(${mostrar(e1)} + ${mostrar(e2)})"
      case Prod(e1, e2) => s"(${mostrar(e1)} * ${mostrar(e2)})"
      case Resta(e1, e2) => s"(${mostrar(e1)} - ${mostrar(e2)})"
      case Div(e1, e2) => s"(${mostrar(e1)} / ${mostrar(e2)})"
      case Expo(e1, e2) => s"(${mostrar(e1)} ^ ${mostrar(e2)})"
      case Logaritmo(e1) => s"lg(${mostrar(e1)})"
    }
  }

  /*
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
