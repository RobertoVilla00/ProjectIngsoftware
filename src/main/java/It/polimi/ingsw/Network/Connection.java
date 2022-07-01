package It.polimi.ingsw.Network;


import It.polimi.ingsw.Message.Message;
import It.polimi.ingsw.Message.MessageContent;
import It.polimi.ingsw.Observer.Observable;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.exit;


/**
 * The class that representing the connection.
 */
public class Connection extends Observable implements Runnable {

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Server server;
	private boolean active = true;
	private Object lock;
	private Timer timer;
	private boolean isAlive;
	private boolean enteredLobby;

	/**
	 * The constructor of the connection.
	 * @param socket: the Socket.
	 * @param server: the Server.
	 * @throws IOException: signals that an I/O exception has occurred.
	 */
	public Connection(Socket socket, Server server) throws IOException {
		this.socket = socket;
		this.server = server;
		this.lock = new Object();
		this.isAlive = true;
		this.timer = new Timer();
		this.enteredLobby=false;

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(isAlive){
					isAlive = false;
				}
				else{
					if(enteredLobby) {
						System.out.println("Connection timed out");
						exit(0);
					}
				}
			}
		}, 10000,10000);

		in = new ObjectInputStream(socket.getInputStream());
	}

	/**
	 * It used to send message on the Object Output Stream.
	 * @param message: the message to be sent.
	 */
	public synchronized void SendMessage(Object message) {
		try {
			out.reset();
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Return true if the connection is active.
	 * @return true if the connection is active.
	 */
	public synchronized boolean isActive() {
		return active;
	}

	/**
	 * It creates a thread that invoke the SendMessage method.
	 * @param message: the message to be sent.
	 */
	public void AsyncSend(final Object message) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				SendMessage(message);
			}
		}).start();
	}

	/**
	 * It used to read a message from the Object Input Stream.
	 * @return the message which is read.
	 * @throws NoSuchElementException: in case the element being requested does not exist.
	 * @throws IOException: signals that an I/O exception has occurred.
	 */
	public Message ReadMessage() throws NoSuchElementException, IOException {
		Object inputObject = null;
		try {
			inputObject = in.readObject();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (Message) inputObject;
	}

	/**
	 * It used to read a message from the Object Input Stream.
	 * @param content: the content of a message.
	 * @return the message received.
	 * @throws NoSuchElementException: if the element being requested does not exists.
	 * @throws IOException: signals that an i/o exception has occurred.
	 */
	public Message ReadSpecificMessage(MessageContent content) throws NoSuchElementException, IOException {
		Object inputObject = null;
		try {
			inputObject = in.readObject();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Message message = (Message) inputObject;
		if(message.getMessageContent()==content) return message;
		else{
			isAlive = true;
			return null;
		}
	}


	/**
	 * It used to close the connection.
	 */
	public synchronized void closeConnection() {
		System.out.println("Connection closed from the server side");
		try {
			socket.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		active = false;
	}

	/**
	 * It used to invoke the method which closed the connection.
	 */
	public void Close() {
		closeConnection();
		server.DeregisterConnection(this);
	}

	/**
	 * The main method of the thread. It calls the Lobby method in the Server and the HandleConnection method.
	 */
	public void run() {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			server.Lobby(this);
			while (isActive()) {
				handleConnection();
			}
		}
		catch (SocketException e){
			exit(0);
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			Close();
		}
	}

	/**
	 * It is used to invoke the ReadMessage and the handleReceivedMessage on the server.
	 */
	private void handleConnection() {
		try {
			synchronized (lock) {
				Message message = ReadMessage();
				if(message.getMessageContent() == MessageContent.PING){
					isAlive = true;
				}
				server.handleReceivedMessage(message);
			}
		}
		catch (IOException e) {
		}
	}


	/**
	 * It used to set the connection to active or inactive.
	 * @param value: boolean indicating if the connection is active or inactive.
	 */
	public void setActive(boolean value){
		this.active=value;
	}

	/**
	 * It set true if a player is entered in the lobby.
	 * @param value: boolean indicating if a player is entered in the lobby.
	 */
	public void setEnteredLobby(boolean value){
		this.enteredLobby=value;
	}
}

