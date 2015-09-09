/**
  * Class to build objects that create instance of the SMTWTP problem from a
  * text file.
  *
  * @author Quentin Baert
  */
class InstanceBuilder(val instanceLength: Int, val valuesPerLine: Int) {

  val linesPerPart = this.instanceLength / this.valuesPerLine

  // Read a file
  private def readFile(filePath: String): String = {
    val source = scala.io.Source.fromFile(filePath)
    val lines = try {
      source.mkString
    } catch {
      case e: Exception => ""
    }

    source.close()

    lines
  }

  // Convert some lines in a list of Int
  private def processLines(lines: Array[String]): Array[Int] = {
    def processLine(line: String): Array[Int] =
      if (line.trim.isEmpty)
        Array()
      else
        ((line split "\\s+") filterNot (_.isEmpty)) map (_.toInt)

    lines flatMap processLine
  }

  // Convert a triplet of Int in an Instance object
  private def convertTriplet(triplet: (Int, Int, Int)): Job = {
    val (exec, due, weight) = triplet

    new Job(exec, due, weight)
  }

  // Read one instance from a data file
  private def readInstanceFrom(instanceLines: Array[String]): Instance = {
    val (execTimeLines, rest0) = (instanceLines take this.linesPerPart, instanceLines drop this.linesPerPart)
    val (dueTimeLines, rest1) = (rest0 take this.linesPerPart, rest0 drop this.linesPerPart)
    val weightLines = rest1 take this.linesPerPart

    val execTimes = this processLines execTimeLines
    val dueTimes = this processLines dueTimeLines
    val weights = this processLines weightLines

    val triplets = (execTimes, dueTimes, weights).zipped.toList

    val jobs = triplets map convertTriplet

    new Instance(jobs)
  }

  /**
    * Build a list of instances from a file
    *
    * @param file name of the file containing data
    *
    * @return list of instances
    */
  def getInstancesFrom(file: String): List[Instance] = {
    val lines = (this readFile file) split '\n'
    val linesPerInstance = (this.instanceLength / this.valuesPerLine) * 3

    val instancesLines = (lines grouped linesPerInstance).toList takeWhile (_.length == linesPerInstance)

    instancesLines map readInstanceFrom
  }

}
