import org.crsh.cli.Command
import org.crsh.cli.Option
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext

@Usage("SpringContext methods")
public class spring {

    @Usage("View beans")
    @Command
    public String list(
            @Usage("Show generic SpringBeans also") @Option(names = ["showSpring", "s"]) String showSpring,
            InvocationContext context) {

        StringBuilder out = new StringBuilder();
        String[] names = context.attributes['spring.beanfactory'].getBeanDefinitionNames();
        List<String> lst = new LinkedList<>();
        boolean hideSpring = true;
        if (showSpring != null) {
            hideSpring = false;
        }
        for (String name : names) {
            def test = context.attributes['spring.beanfactory'].getBean(name);
            String klasse = test.getClass().getName();
            if (hideSpring && klasse.startsWith("org.springframework")) {
                continue;
            }
            lst.add(klasse + " (" + name + ")");
        }
        Collections.sort(lst);
        for (String it : lst) {
            out.append(it).append("\n");
        }
        return out.toString();
    }

}
