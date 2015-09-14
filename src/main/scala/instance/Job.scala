package instance

/** Class to represent a job in the SMTWTP problem.
  *
  * @constructor create a new job.
  * @param execTime execution time of the job
  * @param dueTime due date of the job
  * @param weight weight of the job
  *
  * @author Quentin Baert
  */
class Job(val execTime: Int, val dueTime: Int, val weight: Int) {

  override def toString: String =
    "Exec   : " + this.execTime + "\n" +
    "Due    : " + this.dueTime + "\n" +
    "Weight : " + this.weight + "\n"

}
