/** Class to represent EDD (Earliest Due Date) schedulers for the SMTWTP
  * problem.
  *
  * @constructor create a new EDD scheduler.
  * @param instance instance to schedule
  *
  * @author Quentin Baert
  */
class EDDScheduler(override val instance: Instance) extends Scheduler(instance) {

  /** @see scheduler.Scheduler.schedule() */
  override def schedule: List[Job] =
    this.instance.jobs sortWith (_.dueTime < _.dueTime)

}
