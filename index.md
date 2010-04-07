---
title: Scalasti, a Scala interface to the Java StringTemplate library
layout: withTOC
---

## Introduction

The Scalasti library is a [Scala][] interface to the [StringTemplate][]
Java template library. It provides a subset of the features of
[StringTemplate][], using a more Scala-friendly syntax.

## Rationale

[StringTemplate][] is one of many Java-based template engines; other
examples include [Google's Closure Templates][], [FreeMarker][] and
[Velocity][]. There are also Scala-based template engines, such as
[Scalate][], a powerful template language that bills itself as being
"like JSP without the crap, but with added Scala coolness."

The problem with most template languages, though, is that they're a little
(or a lot) *too* powerful. As Terence Parr, creator of StringTemplate,
wrote, in his paper
[*Enforcing Strict Model-View Separation in Template Engines*][]:

> The mantra of every experienced web application developer is the same:
> thou shalt separate business logic from display. Ironically, almost all
> template engines allow violation of this separation principle, which is
> the very impetus for HTML template engine development. This situation is
> due mostly to a lack of formal definition of separation and fear that
> enforcing separation emasculates a template’s power.
>
> ...
>
> Without exception, programmers espouse separation of logic and display as
> an ideal principle. In practice, however, programmers and engine
> producers are loath to enforce separation, fearing loss of power
> resulting in a crucial page that they cannot generate while satisfying
> the principle. Instead, they encour- age rather than enforce the
> principle, leaving themselves a gaping “backdoor” to avoid insufficient
> page generation power. Unfortunately, under deadline pressure,
> programmers will use this backdoor routinely as an expedient if it is
> available to them, thus, entangling logic and display.
>
> ...
>
> The opposite situation is more common where programmers em- bed business
> logic in their templates as an expedient to avoid having to update their
> data model. Given a Turing-complete template programming language,
> programmers are tempted to add logic directly where they need it in the
> template instead of having the data model do the logic and passing in the
> boolean result, thereby, decoupling the view from the model.

I wanted to use StringTemplate from Scala, which is eminently feasible, since
there's a Java version of StringTemplate. However, StringTemplate relies
on `java.util.Collection` classes, such as `Map` and `List`; these classes
are clumsy to use in Scala, compared to their Scala counterparts. So,
I created the Scalasti wrapper interface, which exposes StringTemplate
capabilities in a more Scala-friendly way.

## Installation

The easiest way to install the Scalasti library is to download a
pre-compiled jar from the [*clapper.org* Maven repository][]. However, you
can also get certain build tools to download it for you.

### Installing for Maven

If you're using [Maven][], you can get the Scalasti library from the
[*clapper.org* Maven Repository][]. The relevant pieces of information are:

* Group ID: `clapper.org`
* Artifact ID: `scalasti`
* Version: `0.1`
* Type: `jar`
* Repository: `http://maven.clapper.org/`

Creating the appropriate Maven configuration items is left as an exercise
for the reader. (One of the things I like about using [SBT][] is that I
never have to look at Maven XML.)

### Using with SBT

If you're using [SBT][] (the Simple Build Tool) to compile your code, you
can place the following lines in your project file (i.e., the Scala file in
your `project/build/` directory):

    val orgClapperRepo = "clapper.org Maven Repository" at
        "http://maven.clapper.org"
    val scalasti = "org.clapper" % "scalasti" % "0.1"

## Source Code Repository

The source code for the Scalasti library is maintained on [GitHub][]. To
clone the repository, run this command:

    git clone git://github.com/bmc/scalasti.git

## Building from Source

Building the Scalasti library requires [SBT][]. Install SBT, as described
at the SBT web site. Then, assuming you have an `sbt` shell script (or .BAT
file, for Windows), run:

    sbt update

That command will pull down the external jars on which the Scalasti library
depends. After that step, build the library with:

    sbt compile test package

The resulting jar file will be in the top-level `target` directory.

## API Documentation

The Scaladoc-generated the [API documentation][] is available locally.
In addition, you can generate your own version with:

    sbt doc

## Author

[Brian M. Clapper][]

## Contributing to Scalasti

Scalasti is still under development. If you have suggestions or
contributions, feel free to fork the [Scalasti repository][], make your
changes, and send me a pull request.

## Copyright and License

Scalasti is copyright &copy; 2010 Brian M. Clapper and is released under a
[BSD License][].

[API documentation]: apidocs/
[BSD License]: license.html
[Scala]: http://www.scala-lang.org/
[StringTemplate]: http://www.stringtemplate.org/
[Scalasti]: http://bmc.github.com/scalasti
[Scalasti home page]: http://bmc.github.com/scalasti
[Scalasti repository]: http://github.com/bmc/scalasti
[Brian M. Clapper]: mailto:bmc@clapper.org
[Google's Closure Templates]: http://code.google.com/closure/templates/index.html
[Velocity]: http://velocity.apache.org/
[FreeMarker]: http://www.freemarker.org/
[Scalate]: http://scalate.fusesource.org/
[*Enforcing Strict Model-View Separation in Template Engines*]: http://www.cs.usfca.edu/~parrt/papers/mvc.templates.pdf
[SBT]: http://code.google.com/p/simple-build-tool
[*clapper.org* Maven repository]: http://maven.clapper.org/org/clapper/
[Maven]: http://maven.apache.org/
[GitHub repository]: http://github.com/bmc/scalasti
[GitHub]: http://github.com/bmc/