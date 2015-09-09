/**
  * Class to represent a job in the SMTWTP problem.
  *
  * @author Quentin Baert
  */
class Job(val execTime: Int, val dueTime: Int, val weight: Int) {

  override def toString: String =
    "Exec   : " + this.execTime + "\n" +
    "Due    : " + this.dueTime + "\n" +
    "Weight : " + this.weight + "\n"

}
