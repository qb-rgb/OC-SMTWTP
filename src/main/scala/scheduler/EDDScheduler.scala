/**
  * Class that represents EDD (Earliest Due Date) scheduler for the
  * SMTWTP problem.
  *
  * @author Quentin Baert
  */
class EDDScheduler(override val instance: Instance) extends Scheduler(instance) {

  override def schedule: List[Job] =
    this.instance.jobs sortWith (_.dueTime < _.dueTime)

}
