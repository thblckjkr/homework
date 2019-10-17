

public class HelloWorldServiceImpl extends HelloWorldServicePOA {
	public HelloWorldServiceImpl() {
		super();
	}

	public String sayHello(String who) {
		return "Hello "+who+" from your friend CORBA server :-)";
	}
}
