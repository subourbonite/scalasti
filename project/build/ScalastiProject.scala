import sbt._

import org.clapper.sbtplugins.MarkdownPlugin

import grizzled.file.implicits._

class ScalastiProject(info: ProjectInfo)
extends DefaultProject(info) with MarkdownPlugin with posterous.Publish
{
    /* ---------------------------------------------------------------------- *\
                         Compiler and SBT Options
    \* ---------------------------------------------------------------------- */

    override def compileOptions = Unchecked :: super.compileOptions.toList
    override def parallelExecution = true // why not?

    /* ---------------------------------------------------------------------- *\
                             Various settings
    \* ---------------------------------------------------------------------- */

    val sourceDocsDir = "src" / "docs"
    val targetDocsDir = "target" / "doc"
    val usersGuide = sourceDocsDir / "users-guide.md"
    val markdownFiles = (path(".") * "*.md") +++ usersGuide
    val markdownHtmlFiles = transformPaths(targetDocsDir,
                                           markdownFiles,
                                           {_.replaceAll("\\.md$", ".html")})
    val markdownSources = markdownFiles +++
                          (sourceDocsDir / "markdown.css") +++
                          (sourceDocsDir ** "*.js")

    /* ---------------------------------------------------------------------- *\
                       Managed External Dependencies
    \* ---------------------------------------------------------------------- */

    val newReleaseToolsRepository = "Scala Tools Repository" at
        "http://nexus.scala-tools.org/content/repositories/snapshots/"

    val scalatest = "org.scalatest" % "scalatest" %
        "1.0.1-for-scala-2.8.0.Beta1-with-test-interfaces-0.3-SNAPSHOT"

    val scalaj_collection = "org.scalaj" %% "scalaj-collection" % "1.0.Beta2"

    val stringTemplate = "org.antlr" % "stringtemplate" % "3.2.1"

    val orgClapperRepo = "clapper.org Maven Repository" at
        "http://maven.clapper.org"
    val grizzled = "org.clapper" % "grizzled-scala" % "0.4.2"

    /* ---------------------------------------------------------------------- *\
                                   Tasks
    \* ---------------------------------------------------------------------- */

    /* ---------------------------------------------------------------------- *\
                              Private Methods
    \* ---------------------------------------------------------------------- */

    private def transformPaths(targetDir: Path, 
                               paths: PathFinder,
                               transform: (String) => String): Iterable[Path] =
    {
        val justFileNames = paths.get.map(p => p.asFile.basename.getPath)
        val transformedNames = justFileNames.map(s => transform(s))
        transformedNames.map(s => targetDir / s)
    }
}
