README

1. LIST of PROJECT :
	cs414.a5.richard2.client	- Client Module
	cs414.a5.richard2.common	- Common Module
	cs414.a5.richard2.server	- Server Module
	cs414.a5.richard2.Exception	- Exception Module
	docs				- Project Documentation	
	.project			- Eclipse Project

2. TO RUN:

	a. Run rmic to generate the stub for remote object 
	
		rmic cs414.a5.richard2.server.ParkingGarageImpl

	b. Start the RMI Registry with port

		start rmiregistry 2002

	c. Run Parking Garage Server: (only one server)

		java cs414.a5.richard2.server.ParkingGarageServer localhost 2002
	
	d.Run Entry Client UI: ( many entry client UI)

		java cs414.a5.richard2.client.EntryUI localhost 2002

	e.Run Exit Client UI: ( many exit client UI)

		java cs414.a5.richard2.client.ExitMainUI localhost 2002

	f.Run Management Main UI : ( report usage , sale ticket and update new capacity

		java cs414.a5.richard2.client.ManagementMainUI localhost 2002

3. STRONG / WEAK or MISSINGs POINT

	a. STRONG POINTS
		
		+ System supports everything in iteration 2.

		+ Multiple Entry and Exit Parking clients can run independently with successful callback mechanism.

        	+ It is use of patterns    
        
        	+ User interface flow is easy.

		+ System can report usage and sale ticket in hourly, daily and monthly.

	b. WEAK POINTS:
		
		+ System can not report which hours are the most used on average day over the last month.

4. PATTERNS USED:
	a. Observer
		Observer pattern is used between the Server and Client so that the Client's will be notified when changes are made to the state of the garage.

	b. Proxy
		Proxy pattern is used to wrap the clients UI. For example:  PaymentTicketUI can be of any type: paymentTicket,lostTicket or noPayTicket 

	c.Singleton
		Singleton pattern is used to get single instance of UI classes for each client.
	
	d.Strategy
		Strategy pattern is used for ViewRatesUIInterface. Each of the classes that implement it, have their own implementations of overridden methods.

	e.Facade
		The ParkingGarageImpl class hides the RMI details of implementation from the rest of the application.Thus, we do not know the complexities of the Server operations

	f.Controller, Delegation, Indirection
		The ParkingGarageImpl acts as a controller delegating work to other TicketImpl, PaymentImpl, ...
	g.Creater
		The ExitMainUI's children such as PaymentTicketUI creates the other closely related UI objects CardPaymentUI and CashPaymentUI.

	h.Polymorphism
		It is used with interfaces being implemented and superclasses being extended.


5. Refactoring / Improvements:

	- Created separate packages for major components (client, common, exception, server).

	- Converted views from text-based interface to swing GUI.

	- Converted from one client to multiple GUI-based client with a single server.

	- Used RMI to connect views to server / controllers

	- Putting common methods in exitClient and entryClient superclass, so all children UI can use them and help connect to server.

	- Extracted out methods in ActionListeners of the UI classes.
