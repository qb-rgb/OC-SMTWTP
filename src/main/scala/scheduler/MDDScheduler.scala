/**
  * Class to represent MDD (Modified Due Date) schedulers for the SMTWTP
  * problem.
  *
  * @author Quentin Baert
  */
class MDDScheduler (override val instance: Instance) extends Scheduler(instance) {

  override def schedule: List[Job] = {
    def mddScore(actualTime: Int, job: Job): Int =
      math.max(actualTime + job.execTime, job.dueTime)

    def innerSchedule(jobs: List[Job], scheduledJobs: List[Job], actualTime: Int): List[Job] = {
      if (jobs.isEmpty)
        scheduledJobs
      else {
        val orderedJobs = jobs sortWith ((j1, j2) => mddScore(actualTime, j1) < mddScore(actualTime, j2))
        val job = orderedJobs.head

        innerSchedule(orderedJobs.tail, job :: scheduledJobs, actualTime + job.execTime)
      }
    }

    innerSchedule(this.instance.jobs, List(), 0).reverse
  }

}
