# Java server programming
A ball game with client and server side programming

Protocol:
To begin with, the server never never initiates an exchange of data, it is initiated by the client and the server’s job is respond to this signal, which in my case the client, first initiates a connection, while the server has opened a socket and is waiting for new connections. Writer and Reader is the way I used for communicating with the server, the Writer sends the data to server and reader reads the information that is sent from server-side (server response). My client class has 4 “functions”. The first one is passing the server an ID, the second one is for getting all the players that are connected, then fetching the ball holder id and the passing the ball function.
An example of data exchanging order:
1)Once the server is up and ready for new connections, we run the client, once the client is run, first of all it creates an unique ID ( for more pleasing interface I just used a number generator which goes up to 1000, but in need for really unique ID’s UUID can be used) the server expects a value (ClientHandler class), so the client sends it with a Writer and waits for server response, than the server gets the id, creates a new player object and sets the player id which was just sent from the client, when that is completed, the server sends the response (“success”) , and the client now knows that it was approved and then it can continue on.
 This happens for pretty much for every function in the program. The client sends that it needs some type of data, the server does all the calculations, the logic and sends back to the client the response and displays it.

Client threads:
The client thread is created when the new client is launched, what that means, that multiple clients could be launched and ready to use. It is created in “ServerProgram” and terminated once the client is closed.

Server threads:
The server has its main thread for listening new connections. It handles all the communication with the client (receiving the id, waiting for commands for the menu).
