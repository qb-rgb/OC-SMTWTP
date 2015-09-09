/**
  * Class to represent EDD (Earliest Due Date) schedulers for the SMTWTP
  * problem.
  *
  * @author Quentin Baert
  */
class EDDScheduler(override val instance: Instance) extends Scheduler(instance) {

  override def schedule: List[Job] =
    this.instance.jobs sortWith (_.dueTime < _.dueTime)

}
