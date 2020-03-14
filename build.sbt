import Dependencies._

name                     := "learn-scala-pfp"
version                  := "0.0.1"
ThisBuild / scalaVersion := "2.13.1"

scalacOptions ++= Seq(
  "-Ymacro-annotations",
  //  "-feature",             // turn ON feature warning TO fatal
)
scalacOptions --= Seq(
  "-Xfatal-warnings",
//  "-Ywarn-unused",
//  "-Ywarn-dead-code",       // dead code following this construct
)

libraryDependencies ++= Seq(

  compilerPlugin("org.typelevel"  %% "kind-projector"  % "0.11.0" cross CrossVersion.full ),
  compilerPlugin("org.augustjune" %% "context-applied" % "0.1.2"),

  "org.typelevel"  %% "cats-core"                 % "2.1.0",
  "org.typelevel"  %% "cats-effect"               % "2.1.0",
  "dev.profunktor" %% "console4cats"              % "0.8.1",
  "org.manatki"    %% "derevo-cats"               % "0.10.5",
  "org.manatki"    %% "derevo-cats-tagless"       % "0.10.5",
  "co.fs2"         %% "fs2-core"                  % "2.2.2",
  "com.olegpy"     %% "meow-mtl-core"             % "0.4.0",
  "com.olegpy"     %% "meow-mtl-effects"          % "0.4.0",

  // @newtype annotation
  Libraries.newtype,
  // refined types
  Libraries.refinedCore,

  Libraries.http4sServer,
  Libraries.http4sClient,
  Libraries.http4sDsl,
  "com.github.julien-truffaut" %% "monocle-core"  % "2.0.1",
  "com.github.julien-truffaut" %% "monocle-macro" % "2.0.1",

)

