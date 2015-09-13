import scala.util.Random

/** Class to represent a random scheduler for the SMTWTP problem.
  *
  * @constructor create a new randome scheduler.
  * @param instance instance to schedule
  *
  * @author Quentin Baert
  */
class RandomScheduler(override val instance: Instance) extends Scheduler(instance) {

  /** @see scheduler.Scheduler.schedule() */
  override def schedule: List[Job] = new Random() shuffle this.instance.jobs

}
