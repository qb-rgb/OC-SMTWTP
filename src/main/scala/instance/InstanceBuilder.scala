/**
  * Class to build objects that create instance of the SMTWTP problem from a
  * text file.
  *
  * @author Quentin Baert
  */
class InstanceBuilder(val instanceLength: Int) {

  // Number of value per line in the file
  private val valuesPerLine: Int = 20

  // Read a file
  def readFile(filePath: String): String = {
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
  private def processLines(lines: Array[String]): Array[Int] =
    (lines.mkString split "\\s+") map (value => value.toInt)

  // Convert a triplet of Int in an Instance object
  private def convertTriplet(triplet: (Int, Int, Int)): Job = {
    val (exec, due, weight) = triplet

    new Job(exec, due, weight)
  }

  // Read one instance from a data file
  private def readInstanceFrom(instanceString: String): Instance = {
    val linesPerPart = this.instanceLength / this.valuesPerLine
    val lines = instanceString split '\n'
    val (execTimeLines, rest0) = (lines take linesPerPart, lines drop linesPerPart)
    val (dueTimeLines, rest1) = (rest0 take linesPerPart, rest0 drop linesPerPart)
    val (weightLines, rest) = (rest1 take linesPerPart, rest1 drop linesPerPart)

    assert(rest.isEmpty)

    val execTimes = this processLines execTimeLines
    val dueTimes = this processLines dueTimeLines
    val weights = this processLines weightLines

    val triplets = (execTimes, dueTimes, weights).zipped.toList

    val jobs = triplets map convertTriplet

    new Instance(jobs)
  }

}
