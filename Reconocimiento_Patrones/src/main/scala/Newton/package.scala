import scala.annotation.tailrec

package object Newton {
  trait Expr
  case class Numero(d: Double) extends Expr
  case class Atomo(x: Char) extends Expr
  case class Suma(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr
  case class Resta(e1: Expr, e2: Expr) extends Expr
  case class Div(e1: Expr, e2: Expr) extends Expr
  case class Expo(e1: Expr, e2: Expr) extends Expr
  case class Logaritmo(e1: Expr) extends Expr


  def mostrar(e: Expr): String = {
    e match {
      case Numero(d) => d.toString
      case Atomo(x) => x.toString
      case Suma(e1, e2) => s"(${mostrar(e1)} + ${mostrar(e2)})"
      case Prod(e1, e2) => s"(${mostrar(e1)} * ${mostrar(e2)})"
      case Resta(e1, e2) => s"(${mostrar(e1)} - ${mostrar(e2)})"
      case Div(e1, e2) => s"(${mostrar(e1)} / ${mostrar(e2)})"
      case Expo(e1, e2) => s"(${mostrar(e1)} ^ ${mostrar(e2)})"
      case Logaritmo(e1) => s"lg(${mostrar(e1)})"
    }
  }


  def derivar(f: Expr, a: Atomo): Expr = {
    f match {
      case Numero(_) => Numero(0.0)
      case Atomo(x) => if (x ==a.x) Numero(1) else Numero(0)
      //case Atomo(x) if x == a.x => Numero(1)
      //case Atomo(_) => Numero(0)
      case Suma(e1, e2) => Suma(derivar(e1, a), derivar(e2, a))
      case Resta(e1, e2) => Resta(derivar(e1, a), derivar(e2, a))
      case Prod(e1, e2) => Suma(Prod(derivar(e1, a), e2), Prod(e1, derivar(e2, a)))
      case Div(e1, e2) => Div(Resta(Prod(derivar(e1, a), e2), Prod(e1, derivar(e2, a))), Expo(e2, Numero(2.0)))
      case Expo(e1, e2) => Prod(Expo(e1, e2), Suma(Prod(derivar(e2, a), Logaritmo(e1)), Prod(Div(e2, e1), derivar(e1, a))))
      case Logaritmo(e1) => Div(derivar(e1, a), e1)
    }
  }


  def evaluar(f: Expr, a: Atomo, v: Double): Double = {
    f match {
      case Numero(d) => d
      case Atomo(x) if x == a.x => v
      case Suma(e1, e2) => evaluar(e1, a, v) + evaluar(e2, a, v)
      case Resta(e1, e2) => evaluar(e1, a, v) - evaluar(e2, a, v)
      case Prod(e1, e2) => evaluar(e1, a, v) * evaluar(e2, a, v)
      case Div(e1, e2) => evaluar(e1, a, v) / evaluar(e2, a, v)
      case Expo(e1, e2) => Math.pow(evaluar(e1, a, v), evaluar(e2, a, v))
      case Logaritmo(e1) => Math.log(evaluar(e1, a, v))
    }
  }


  def limpiar(f: Expr): Expr = {
    f match {
      case Numero(d) if d == 0.0 => Numero(0.0)
      case Numero(d) if d == 1.0 => Numero(1.0)
      case Suma(e1, e2) => (limpiar(e1), limpiar(e2)) match {
        case (Numero(0.0), e) => e
        case (e, Numero(0.0)) => e
        case (e1, e2) => Suma(e1, e2)
      }
      case Resta(e1, e2) => (limpiar(e1), limpiar(e2)) match {
        case (Numero(0.0), e) => e
        case (e, Numero(0.0)) => e
        case (e1, e2) => Resta(e1, e2)
      }
      case Prod(e1, e2) => (limpiar(e1), limpiar(e2)) match {
        case (Numero(0.0), _) => Numero(0.0)
        case (_, Numero(0.0)) => Numero(0.0)
        case (Numero(1.0), e) => e
        case (e, Numero(1.0)) => e
        case (e1, e2) => Prod(e1, e2)
      }
      case Div(e1, e2) => (limpiar(e1), limpiar(e2)) match {
        case (Numero(0.0), _) => Numero(0.0)
        case (e, Numero(1.0)) => e
        case (e1, e2) => Div(e1, e2)
      }
      case Expo(e1, e2) => (limpiar(e1), limpiar(e2)) match {
        case (_, Numero(0.0)) => Numero(1.0)
        case (e, Numero(1.0)) => e
        case (e1, e2) => Expo(e1, e2)
      }
      case Logaritmo(e1) => Logaritmo(limpiar(e1))
      case _ => f
    }
  }

  
  def raizNewton(f: Expr, a: Atomo, x0: Double, ba: (Expr, Atomo, Double) => Boolean): Double = {
    @tailrec
    def iterar(xn: Double): Double = {
      val fxn = evaluar(f, a, xn)
      val derf = derivar(f, a)
      val derfxn = evaluar(derf, a, xn)
      val xn1 = xn - fxn / derfxn

      (f, a, xn) match {
        case (f, a, xn) if ba(f, a, xn) => xn
        case _ => iterar(xn1)
      }
    }

    iterar(x0)
  }

}
