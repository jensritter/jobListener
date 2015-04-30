import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.jens.test.MainInterface
import org.jens.test.model.Job
import org.jens.test.services.JobManagerInterface
import org.springframework.beans.factory.BeanFactory

@Usage("JobManager")
public class jobmanager {

    public JobManagerInterface findJobManager(InvocationContext context) {
        def what = context.attributes['spring.beanfactory'];
        BeanFactory factory = (BeanFactory) what;
        JobManagerInterface jobManager = factory.getBean(JobManagerInterface.class);
        return jobManager
    }

    public MainInterface findMain(InvocationContext context) {
        def what = context.attributes['spring.beanfactory'];
        BeanFactory factory = (BeanFactory) what;
        MainInterface main = factory.getBean(MainInterface.class);
        return main;
    }

    @Usage("Create a new job")
    @Command
    public String create(@Required @Argument String username, @Required @Argument String kredinr, InvocationContext context) {
        JobManagerInterface jobManager = findJobManager(context);
        Job job = new Job();
        job.setKredi(Integer.valueOf(kredinr));
        job.setName(username);
        job = jobManager.addJob(job);

        return "Job created as : " + job.getId();
    }


    @Usage("count jobs currently in queue")
    @Command
    public String count(InvocationContext context) {
        JobManagerInterface jobManager = findJobManager(context);
        int count = jobManager.list().size();
        return "" + count;
    }

    @Usage("list jobs currently in queue")
    @Command
    public String list(InvocationContext context) {
        JobManagerInterface jobManager = findJobManager(context);
        StringBuilder out = new StringBuilder();
        for (Job job : jobManager.list()) {
            out.append(job.getId());
            out.append("\n");
        }
        if (out.length() > 0) {
            return out.toString();
        }
        return "-no entries-";
    }

    @Usage("Shutdown")
    @Command
    public String shutdown(InvocationContext context) {
        MainInterface main = findMain(context);
        main.setKeepRunning(false);
        return "Shutting down";
    }

    String dropJob(String id) {
        return null
    }




}
