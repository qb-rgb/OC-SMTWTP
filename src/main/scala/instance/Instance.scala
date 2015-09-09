/**
  * Class representing an instance of the SMTWTP problem.
  *
  * @author Quentin Baert
  */
class Instance(val jobs: List[Job]) {

  override def toString: String = {
    def showIndexedJob(jobs: List[Job], index: Int): String =
      if (jobs.isEmpty)
        ""
      else
        "---------- " + index + " ----------\n" +
        jobs.head.toString +
        showIndexedJob(jobs.tail, index + 1)

    showIndexedJob(this.jobs, 0)
  }

}
