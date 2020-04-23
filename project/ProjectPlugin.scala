import sbt.Keys._
import sbt._
import com.alejandrohdezma.sbt.github.SbtGithubPlugin

object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = plugins.JvmPlugin && SbtGithubPlugin

  object autoImport {

    val scalaExercisesV = "0.6.0-SNAPSHOT"

    def dep(artifactId: String) = "org.scala-exercises" %% artifactId % scalaExercisesV

    lazy val exercisesSettings = Seq(
      libraryDependencies ++= Seq(
        dep("exercise-compiler"),
        dep("definitions"),
        "com.chuusai"                %% "shapeless"                 % "2.3.3",
        "com.github.pureconfig"      %% "pureconfig"                % "0.12.2",
        "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % "1.2.5",
        "org.scalatest"              %% "scalatest"                 % "3.1.1",
        "org.scalatestplus"          %% "scalacheck-1-14"           % "3.1.1.1"
      )
    )
  }

  override def projectSettings: Seq[Def.Setting[_]] =
    Seq(
      organization := "org.scala-exercises",
      organizationName := "47 Degrees",
      organizationHomepage := Some(url("https://47deg.com")),
      scalaVersion := "2.13.1",
      resolvers ++= Seq(
        Resolver.sonatypeRepo("snapshots"),
        Resolver.sonatypeRepo("releases")
      )
    )
}
