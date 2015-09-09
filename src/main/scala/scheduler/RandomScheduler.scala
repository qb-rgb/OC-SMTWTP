import scala.util.Random

/**
  * Class to represent a random scheduler for the SMTWTP problem.
  *
  * @author Quentin Baert
  */
class RandomScheduler(override val instance: Instance) extends Scheduler(instance) {

  override def schedule: List[Job] = new Random() shuffle this.instance.jobs

}
