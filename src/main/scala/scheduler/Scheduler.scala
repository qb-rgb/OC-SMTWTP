package scheduler

import instance.Instance

/** Class to represent an abstract scheduler for the SMTWTP problem.
  *
  * @constructor create a new scheduler.
  * @param instance instance to schedule
  *
  * @author Quentin Baert
  */
abstract class Scheduler(val instance: Instance) {

  /** Schedule the instance.
    *
    * @return scheduled instance's list of jobs
    */
  def schedule: Instance

  // Cost function for the schedule.
  protected def costFunction(jobs: Instance): Int = {
    def innerCost(jobs: Instance, actualTime: Int, actualScore: Int): Int =
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

  /** Score of the schedule. */
  val score: Int = this costFunction this.schedule

}
