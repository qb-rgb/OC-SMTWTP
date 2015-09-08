/**
  * Class representing a job in the SMTWTP problem.
  *
  * @author Quentin Baert
  */
class Job(val execTime: Int, val dueTime: Int, val weight: Int) {

  override def toString: String =
    "---------------------\n" +
    "Exec   : " + this.execTime + "\n" +
    "Due    : " + this.dueTime + "\n" +
    "Weight : " + this.weight
    "---------------------\n"

}
