resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers  += "spark-packages" at "https://dl.bintray.com/spark-packages/maven/"
resolvers += "maven-central" at "http://central.maven.org/maven2/"

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.7")