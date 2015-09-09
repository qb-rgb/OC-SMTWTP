/**
  * Class to represent an abstract scheduler for an instance of the SMTWTP
  * problem.
  *
  * @author Quentin Baert
  */
abstract class Scheduler(val instance: Instance) {

  def schedule: List[Job]

  protected def costFunction(jobs: List[Job]): Int = {
    def innerCost(jobs: List[Job], actualTime: Int, actualScore: Int): Int =
      if (jobs.isEmpty)
        actualScore
      else {
        val actualJob = jobs.head
        val newTime = actualTime + actualJob.execTime
        val penality = math.max(newTime - actualJob.dueTime, 0)
        val newScore = actualScore + penality * actualJob.weight

        innerCost(jobs.tail, newTime, newScore)
      }

    innerCost(jobs, 0, 0)
  }

  val score: Int = this costFunction this.schedule

}
