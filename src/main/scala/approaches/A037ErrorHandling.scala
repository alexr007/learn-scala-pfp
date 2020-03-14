package approaches

import cats.data.{Kleisli, OptionT}
import cats.{ApplicativeError, Functor}
import org.http4s.{HttpRoutes, Response}
import org.http4s.dsl.Http4sDsl

import scala.util.control.NoStackTrace

object A037ErrorHandling {
  trait Category
  trait Categories[F]

  sealed trait BusinessError extends NoStackTrace
  case object RandomError extends BusinessError

  class Program[F[_]: Functor](categories: Categories[F]) {

//    def findAll: F[List[Category]] = category.maybeFindAll.map {
//      case Right(c) => c
//      case Left(RandomError) => List.empty[Category]
//    }

  }

  type ApThrow[F[_]] = ApplicativeError[F, Throwable]
  class SameProgram[F[_]: ApThrow](categories: Categories[F]) {

//    def findAll: F[List[Category]] = category.findAll.handleError {
//      case RandomError => List.empty[Category]
//    }

  }

  sealed trait UserError extends NoStackTrace
  final case class UserAlreadyExists(username: String) extends UserError
  final case class UserNotFound(username: String) extends UserError
  final case class InvalidUserAge(age: Int) extends UserError

  trait HttpErrorHandler[F[_], E <: Throwable] {
    def handle(routes: HttpRoutes[F]): HttpRoutes[F]
  }

  abstract class RoutesHttpErrorHandler[F[_], E <: Throwable] extends HttpErrorHandler[F, E] with Http4sDsl[F] {
    def A: ApplicativeError[F, E]

    def handler: E => F[Response[F]]

    def handle(routes: HttpRoutes[F]): HttpRoutes[F] =
      Kleisli { req =>
        OptionT {
          A.handleErrorWith(routes.run(req).value) { e =>
            A.map(handler(e))(x => Option(x))
          }
        }
      } // handle
  } // class

}
